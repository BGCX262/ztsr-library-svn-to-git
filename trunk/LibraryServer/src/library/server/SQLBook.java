package library.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import library.common.*;
import util.database.Enumerator;


public class SQLBook {
    
    /** creates book object from result set from database query, without
     * writing whether the book is available
     * Result set <strong>has to</strong> contain the following fields:
     * title, author, publisher, publishYear, bookId (with these names).
     * Order of fields is irrelevant.
     *@return book or null on error
     */
    public static Book createBook(ResultSet rs) {
        return createBook(rs, false);
    }
    
    /** creates book object from result set from database query.
     * Result set <strong>has to</strong> contain the following fields:
     * title, author, publisher, publishYear, bookId (with these names).
     * Order of fields is irrelevant.
     * @param fillAvail if result set contains "available" INT field
     *@return book or null on error
     */
    public static Book createBook(ResultSet rs, boolean fillAvail) {
        try {
            Book retval = new Book(rs.getString("title"), rs.getString("author"),
                    rs.getString("publisher"), rs.getInt("publishYear"), rs.getInt("bookId"));
            
            if (fillAvail)
                retval.hasAvailableCopies = (rs.getInt("available") > 0);
            
            return retval;
            
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /** creates book copy object from result set from database query.
     * Does not set "book" field.
     * Result set <strong>has to</strong> contain the following fields:
     * copyId, returnDate, state (with these names).
     * Order of fields is irrelevant.
     *@return book copy or null on error
     */
    public static BookCopy createBookCopy(ResultSet rs) {
        try {
            Calendar retDate = new GregorianCalendar();
            retDate.setTime(rs.getDate("returnDate"));
            BookCopy retval = new BookCopy(rs.getInt("state"), rs.getInt("copyId"), retDate);
            
            return retval;
            
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /** creates reservation details object from result set from database query.
     * Does not set "bookCopy" field.
     * Result set <strong>has to</strong> contain the following fields:
     * reservQueue.
     * Order of fields is irrelevant.
     *@return book copy or null on error
     */
    public static ReservationDetails createReservationDetails(ResultSet rs) {
        try {
            ReservationDetails retval = new ConcreteReservationDetails(rs.getInt("reservQueue"));
            
            return retval;
            
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /** returns copies of the book */
    public static Collection<ReservationDetails> getBookCopies(int bookId, Database db)
    throws LibraryException {
        try {
            Collection<ReservationDetails> retval = new ArrayList<ReservationDetails>();
            Connection conn = db.getConnection();
            Statement st = conn.createStatement();
            
            String queryTxt = "SELECT bookId,publishYear,author,title,publisher " +
                    "FROM Books WHERE bookId="+Integer.toString(bookId);
            ResultSet rs = st.executeQuery(queryTxt);
            if (!rs.next())
                throw new LogicException(ResourceBundle.getBundle("library/server/server").getString("book_does_not_exist"));
            Book book = SQLBook.createBook(rs);
            rs.close();
            
            queryTxt = "SELECT copyId,returnDate,state, (SELECT COUNT(*) FROM " +
                    "Reservations R WHERE R.copyId=BC.copyId) reservQueue " +
                    "FROM BookCopies BC WHERE BC.bookId="+Integer.toString(bookId);
            rs = st.executeQuery(queryTxt);
            while(rs.next()) {
                ReservationDetails rd = createReservationDetails(rs);
                if (rd == null) continue;
                BookCopy copy = createBookCopy(rs);
                if (copy == null) continue;
                copy.book = book;
                rd.bookCopy = copy;
                retval.add(rd);
            }
            
            st.close();
            
            return retval;
        }catch(SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        }catch(LibraryException e) {
            throw e;
        } catch(Exception e) {
            throw new InternalErrorException(e.getLocalizedMessage());
        }
    }
    
    /** returns place in reservation queue of reservation with given number
     *@param reservationsFor id of book copy for which reservations are considered
     *@param reservationId id of reservation in the queue which place is counted
     *@returns place in queue or 0 on error
     */
    private static int countPlaceInQueue(int reservationsFor, int reservationId, Database db) {
        try {
            QueuePlaceCounter counter = new QueuePlaceCounter(reservationId);
            Statement statement = db.getConnection().createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT reservId,nextReserv " +
                    "FROM Reservations WHERE copyId="+Integer.toString(reservationsFor));
            while(rs.next())
                counter.handlePair(rs.getInt(1),rs.getInt(2));
            statement.close();
            
            return counter.getPlace();
        } catch(SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public static Collection<ReservationDetails> getReservedBooks(int readerId, Database db)
    throws LibraryException {
        try {
            Collection<ReservationDetails> retval = new ArrayList<ReservationDetails>();
            Connection conn = db.getConnection();
            Statement st = conn.createStatement();
            
            String queryTxt = "SELECT B.bookId,publishYear,author,title,publisher," +
                    "BC.copyId,returnDate,state,reservId, (SELECT COUNT(*) FROM " +
                    "Reservations R WHERE R.copyId=BC.copyId) reservQueue " +
                    "FROM Reservations R,BookCopies BC, Books B WHERE " +
                    "B.bookId=BC.bookId AND BC.copyId=R.copyId AND R.readerId=" +
                    Integer.toString(readerId);
            ResultSet rs = st.executeQuery(queryTxt);
            
            while(rs.next()) {
                ReservationDetails rd = createReservationDetails(rs);
                if (rd == null) continue;
                BookCopy copy = createBookCopy(rs);
                if (copy == null) continue;
                Book book = createBook(rs);
                if (book == null) continue;
                copy.book = book;
                rd.bookCopy = copy;
                rd.youInQueue = countPlaceInQueue(rs.getInt("copyId"),rs.getInt("reservId"), db);
                retval.add(rd);
            }
            
            st.close();
            
            return retval;
        }catch(SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        }catch(Exception e) {
            throw new InternalErrorException(e.getLocalizedMessage());
        }
    }
    
    public static Collection<BookCopy> getRequestedBooks(int readerId, Database db)
    throws LibraryException {
        try {
            Collection<BookCopy> retval = new ArrayList<BookCopy>();
            Connection conn = db.getConnection();
            Statement st = conn.createStatement();
            
            String queryTxt = "SELECT B.bookId,publishYear,author,title,publisher," +
                    "BC.copyId,returnDate,state FROM Requests R,BookCopies BC, Books B " +
                    "WHERE B.bookId=BC.bookId AND BC.copyId=R.copyId AND R.readerId=" +
                    Integer.toString(readerId);
            ResultSet rs = st.executeQuery(queryTxt);
            
            while(rs.next()) {
                BookCopy copy = createBookCopy(rs);
                if (copy == null) continue;
                Book book = createBook(rs);
                if (book == null) continue;
                copy.book = book;
                retval.add(copy);
            }
            st.close();
            
            return retval;
        }catch(SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        }catch(Exception e) {
            throw new InternalErrorException(e.getLocalizedMessage());
        }
    }
    
    public static Collection<BookCopy> getBorrowedBooks(int readerId, Database db)
    throws LibraryException {
        try {
            Collection<BookCopy> retval = new ArrayList<BookCopy>();
            Connection conn = db.getConnection();
            Statement st = conn.createStatement();
            
            String queryTxt = "SELECT B.bookId,publishYear,author,title,publisher," +
                    "BC.copyId,returnDate,state FROM BookCopies BC JOIN Books B " +
                    "ON B.bookId=BC.bookId WHERE BC.borrowedBy=" +
                    Integer.toString(readerId);
            ResultSet rs = st.executeQuery(queryTxt);
            
            while(rs.next()) {
                BookCopy copy = createBookCopy(rs);
                if (copy == null) continue;
                Book book = createBook(rs);
                if (book == null) continue;
                copy.book = book;
                retval.add(copy);
            }
            st.close();
            
            return retval;
        }catch(SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        }catch(Exception e) {
            throw new InternalErrorException(e.getLocalizedMessage());
        }
    }
    
    private static void request(int copyId, int readerId, Database db) throws LibraryException {
        Connection conn = db.getConnection();
        try {
            Statement st = conn.createStatement();
            
            String queryTxt = "SELECT state FROM BookCopies WHERE copyId="+Integer.toString(copyId);
            ResultSet rs = st.executeQuery(queryTxt);
            
            if (!rs.next())
                throw new LogicException(String.format(ResourceBundle.getBundle("library/server/server").getString("book_copy_no._%d_does_not_exist"), copyId));
            BookCopy.State state = BookCopy.State.values()[rs.getInt(1)];
            if (state != BookCopy.State.AVAIL)
                throw new LogicException(String.format(ResourceBundle.getBundle("library/server/server").getString("book_copy_no._%d_cannot_be_requested,_choose_available_copy"),copyId));
            
            st.executeUpdate("UPDATE BookCopies SET state="+BookCopy.State.REQUESTED.ordinal()+
                    " WHERE copyId="+Integer.toString(copyId));
            st.executeUpdate("INSERT INTO Requests(readerId,copyId) VALUES(" +
                    Integer.toString(readerId)+","+Integer.toString(copyId)+")");
            
            st.close();
        }catch(SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        }catch(LibraryException e) {
            throw e;
        }catch(Exception e) {
            throw new InternalErrorException(e.getLocalizedMessage());
        }
    }
    
    private static void reserve(int copyId, int readerId, Database db) throws LibraryException {
        Connection conn = db.getConnection();
        try {
            Statement st = conn.createStatement();
            String copyIdStr = Integer.toString(copyId);
            String readerIdStr = Integer.toString(readerId);
            
            String queryTxt = "SELECT state FROM BookCopies WHERE copyId="+Integer.toString(copyId);
            ResultSet rs = st.executeQuery(queryTxt);
            
            if (!rs.next())
                throw new LogicException(String.format(ResourceBundle.getBundle("library/server/server").getString("book_copy_no._%d_does_not_exist"), copyId));
            BookCopy.State state = BookCopy.State.values()[rs.getInt(1)];
            if (state != BookCopy.State.BORROWED && state != BookCopy.State.REQUESTED)
                throw new LogicException(String.format(ResourceBundle.getBundle("library/server/server").getString("book_copy_no._%d_cannot_be_reserved_because_it_is_not_borrowed"),copyId));
            
            rs.close();
            
            rs = st.executeQuery("SELECT COUNT(*) FROM Reservations WHERE copyId="+copyIdStr+
                    " AND readerId="+readerIdStr);
            rs.next();
            if (rs.getInt(1) != 0)
                throw new LogicException(ResourceBundle.getBundle("library/server/server").getString("you_have_already_reserved_this_book_copy"));
            rs.close();
            
            rs = st.executeQuery("SELECT COUNT(*) FROM Requests WHERE copyId="+copyIdStr+
                    " AND readerId="+readerIdStr);
            rs.next();
            if (rs.getInt(1) != 0)
                throw new LogicException(ResourceBundle.getBundle("library/server/server").getString("you_cannot_reserve_book_copy_you_have_already_requested"));
            rs.close();
            
            rs = st.executeQuery("SELECT borrowedBy FROM BookCopies WHERE copyId="+copyIdStr);
            rs.next();
            if (rs.getInt(1) == readerId)
                throw new LogicException(ResourceBundle.getBundle("library/server/server").getString("you_cannot_reserve_book_copy_you_have_already_borrowed"));
            rs.close();

            boolean noPreviousReservations;
            final int FIRST_RESERV_NEXT_LINK=-1; // value of "next" field in first reservation
            rs = st.executeQuery("SELECT COUNT(*) FROM Reservations WHERE copyId="+copyIdStr);
            rs.next();
            noPreviousReservations = (rs.getInt(1) == 0);
            
            Enumerator fields = new Enumerator();
            fields.add(readerId).add(copyId);
            if (noPreviousReservations) { // avoid 0 when nested SELECT returns MAX(NULL)
                fields.add(FIRST_RESERV_NEXT_LINK);
            } else {
                fields.add("(SELECT MAX(reservId) FROM Reservations WHERE copyId="
                        +Integer.toString(copyId)+")");
            }
            st.executeUpdate("INSERT INTO Reservations(readerId,copyId,nextReserv) VALUES(" +
                    fields.toString()+")");
            
            st.close();
        }catch(SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        }catch(LibraryException e) {
            throw e;
        }catch(Exception e) {
            throw new InternalErrorException(e.getLocalizedMessage());
        }
    }
    
    private static void cancelReservation(int copyId, int readerId, Database db) throws LibraryException {
        Connection conn = db.getConnection();
        try {
            Statement st = conn.createStatement();
            String readerIdStr=Integer.toString(readerId);
            String copyIdStr=Integer.toString(copyId);
            
            String fromWhere=" FROM Reservations WHERE copyId="+copyIdStr
                    + " AND readerId="+readerIdStr;
            String queryTxt = "SELECT COUNT(*)"+fromWhere;
            ResultSet rs = st.executeQuery(queryTxt);
            
            rs.next();
            if (rs.getInt(1) != 1)
                throw new LogicException(String.format(ResourceBundle.getBundle("library/server/server").getString("no_reservation_for_book_copy"), copyId));
            
            // link previous reservation with next reservation
            queryTxt = "UPDATE Reservations SET nextReserv=(SELECT nextReserv FROM " +
                    "Reservations WHERE copyId="+copyIdStr+" AND readerId="+readerIdStr+") " +
                    "WHERE nextReserv=(SELECT reservId "+fromWhere+")";
            st.executeUpdate(queryTxt);
            st.executeUpdate("DELETE"+fromWhere);
            
            st.close();
        }catch(SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        }catch(LibraryException e) {
            throw e;
        }catch(Exception e) {
            e.printStackTrace();
            throw new InternalErrorException(e.getLocalizedMessage());
        }
    }
    
    private static void borrow(int copyId, int readerId, Database db, Library library)
    throws LibraryException {
        Connection conn = db.getConnection();
        try {
            Statement st = conn.createStatement();
            String readerIdStr=Integer.toString(readerId);
            String copyIdStr=Integer.toString(copyId);
            
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Requests WHERE " +
                    "readerId="+readerIdStr+" AND copyId="+copyIdStr);
            
            rs.next();
            if (rs.getInt(1) != 1)
                throw new LogicException(String.format(ResourceBundle.getBundle("library/server/server").getString("no_request_for_book_copy"), copyId));
            
            st.executeUpdate("DELETE FROM Requests WHERE readerId="+readerIdStr+
                    " AND copyId="+copyIdStr);
            
            Enumerator fields = new Enumerator(",",true);
            fields.add("state",BookCopy.State.BORROWED.ordinal());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            fields.add("returnDate", df.format(library.countReturnDate().getTime()));
            fields.add("borrowedBy", readerId);
            st.executeUpdate("UPDATE BookCopies SET "+fields.toString()+" WHERE copyId="+
                    copyIdStr);
            
            st.close();
        }catch(SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        }catch(LibraryException e) {
            throw e;
        }catch(Exception e) {
            throw new InternalErrorException(e.getLocalizedMessage());
        }
    }
    
    /** reserves books in transaction */
    public static void reserve(int[] copyIds, int readerId, Database db) throws LibraryException {
        Transaction t = new Transaction(new ReserveOperation(copyIds, readerId, db));
        t.perform();
    }
    
    /** requests books. Guarantees transaction. */
    public static void request(int[] copyIds, int readerId, Database db) throws LibraryException {
        Transaction t = new Transaction(new RequestOperation(copyIds, readerId, db));
        t.perform();
    }
    
    public static void borrow(int[] copyIds, int readerId, Database db, Library library) throws LibraryException {
        Transaction t = new Transaction(new BorrowOperation(copyIds, readerId, db, library));
        t.perform();
    }
    
    /** requests books. Guarantees transaction. */
    public static void cancelReservations(int[] copyIds, int readerId, Database db)
    throws LibraryException {
        Transaction t = new Transaction(new CancelReservationOperation(copyIds, readerId, db));
        t.perform();
    }
    
    /** saves first copy of a book to database */
    public static void save(Database db, Book book, BookCopy copy) throws LibraryException {
        Transaction t = new Transaction(new AddBookOperation(db,book,copy));
        t.perform();
    }
    
    /** saves additional copy of a book to database */
    public static void save(Database db, int bookId, BookCopy copy) throws LibraryException {
        try {
            Connection conn = db.getConnection();
            Statement st = conn.createStatement();
            
            Enumerator values = new Enumerator(",",true);
            values.add(bookId).add(copy.state.ordinal());
            st.executeUpdate("INSERT INTO BookCopies(bookId,state) VALUES("
                    +values.toString()+")");
            
        }catch(SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        }catch(Exception e) {
            throw new InternalErrorException(e.getLocalizedMessage());
        }
    }
    
    public static void delete(Database db, int bookId) throws LibraryException {
        Transaction t = new Transaction(new DeleteBookOperation(db,bookId));
        t.perform();
    }
    
    
    
    
    /** represents command for reserve, request and cancel reservation */
    abstract static class CommonBookOperation extends Operation {
        protected int[] copyIds;
        protected int readerId;
        
        public CommonBookOperation(int[] copyIds, int readerId, Database db) {
            super(db);
            this.copyIds = copyIds;
            this.readerId = readerId;
        }
        
        public void perform() throws LibraryException {
            for(int i=0; i<copyIds.length; ++i)
                performSingleAction(i);
        }
        
        protected abstract void performSingleAction(int i) throws LibraryException;
    }
    
    static class RequestOperation extends CommonBookOperation {
        public RequestOperation(int[] copyIds, int readerId, Database db) {
            super(copyIds, readerId, db);
        }
        
        protected void performSingleAction(int i) throws LibraryException {
            request(copyIds[i],readerId,getDatabase());
        }
    }
    
    static class ReserveOperation extends CommonBookOperation {
        public ReserveOperation(int[] copyIds, int readerId, Database db) {
            super(copyIds, readerId, db);
        }
        
        protected void performSingleAction(int i) throws LibraryException {
            reserve(copyIds[i],readerId,getDatabase());
        }
    }
    
    static class CancelReservationOperation extends CommonBookOperation {
        public CancelReservationOperation(int[] copyIds, int readerId, Database db) {
            super(copyIds, readerId, db);
        }
        
        protected void performSingleAction(int i) throws LibraryException {
            cancelReservation(copyIds[i],readerId,getDatabase());
        }
    }
    
    static class BorrowOperation extends CommonBookOperation {
        
        private Library library;
        
        public BorrowOperation(int[] copyIds, int readerId, Database db, Library library) {
            super(copyIds, readerId, db);
            this.library = library;
        }
        
        protected void performSingleAction(int i) throws LibraryException {
            borrow(copyIds[i],readerId,getDatabase(),library);
        }
    }
    
    /** adds book with its first copy to database */
    static class AddBookOperation extends Operation {
        private Book book;
        private BookCopy copy;
        
        public AddBookOperation(Database db, Book book, BookCopy copy) {
            super(db);
            this.book = book;
            this.copy = copy;
        }
        
        public void perform() throws LibraryException {
            try {
                Statement st = getDatabase().getConnection().createStatement();
                
                Enumerator values1 = new Enumerator(",",true);
                values1.add(book.getAuthor()).add(book.getTitle()).add(book.getPublisher());
                values1.add(book.getPublishYear());
                st.executeUpdate("INSERT INTO Books(author,title,publisher,publishYear) VALUES("
                        +values1.toString()+")");
                
                Enumerator where = new Enumerator(" AND ");
                where.add("author",book.getAuthor()).add("title",book.getTitle());
                where.add("publisher",book.getPublisher()).add("publishYear",book.getPublishYear());
                ResultSet rs = st.executeQuery("SELECT bookId FROM Books WHERE "
                        +where.toString());
                rs.next();
                int bookId = rs.getInt(1);
                rs.close();
                
                Enumerator values2 = new Enumerator(",",true);
                values2.add(bookId).add(copy.state.ordinal());
                st.executeUpdate("INSERT INTO BookCopies(bookId,state) VALUES("
                        +values2.toString()+")");
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error"));
            }
        }
    }
    
    /** deletes book and its copies */
    static class DeleteBookOperation extends Operation {
        private int bookId;
        
        public DeleteBookOperation(Database db, int bookId) {
            super(db);
            this.bookId = bookId;
        }
        
        public void perform() throws LibraryException {
            try {
                Statement st = getDatabase().getConnection().createStatement();
                String bookIdStr = Integer.toString(bookId);
                
                st.executeUpdate("DELETE FROM BookCopies WHERE bookId="+bookIdStr);
                st.executeUpdate("DELETE FROM Books WHERE bookId="+bookIdStr);
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error"));
            }
        }
    }
}
