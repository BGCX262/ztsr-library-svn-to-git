/*
 * TestServer.java
 *
 * Created on 20 marzec 2007, 01:08
 */

package library.server;

import java.util.Collection;
import library.common.*;

/**
 *
 * @author Piotrek
 */
public class TestServer {
    
    /**
     * Creates a new instance of TestServer
     */
    public TestServer() {
    }
    
    public static void dump(Book book, boolean partOfCopy) {
        System.out.print(book.getAuthor()+" \""+book.getTitle()+"\" ");
        if (!partOfCopy) {
            if (book.hasAvailableCopies)
                System.out.print("s¹ egz.");
            else
                System.out.print("nie ma egz.");
        }
    }
    
    public static void dumpBooks(Collection<Book> books) {
        for(Book book : books) {
            dump(book, false);
            System.out.println();
        }
    }
    
    public static void dump(BookCopy copy) {
        dump(copy.book, true);
        System.out.print(" copy id: ");
        System.out.print(copy.copyId);
        System.out.print(" ");
        System.out.print(copy.state.toString());
        if(copy.state == BookCopy.State.BORROWED) {
            System.out.print(" returned ");
            System.out.print(copy.returnDate.get(java.util.Calendar.YEAR));
        }
    }
    
    public static void dump(ReservationDetails rd) {
        dump(rd.bookCopy);
        if (rd.bookCopy.state != BookCopy.State.BORROWED)
            return;
        System.out.print(" in queue: ");
        System.out.print(rd.queueSize);
        if (rd.youHaveReserved()) {
            System.out.print(" your place: ");
            System.out.print(rd.youInQueue);
        }
    }
    
    public static void dumpReservations(Collection<ReservationDetails> bookCopies) {
        for(ReservationDetails copy : bookCopies) {
            dump(copy);
            System.out.println();
        }
    }
    
    public static void dumpCopies(Collection<BookCopy> copies) {
        for(BookCopy copy : copies) {
            dump(copy);
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Library library=null;
        try{
            ServerSettings.getInstance().load();
            library = new Library();
            System.out.println("1");
            
            /*library.addBook(new Book("Kompilatory","Aho, Ullman","Prentice Hall",1993),
                    new BookCopy(BookCopy.State.AVAIL));
            library.addBookCopy(1,new BookCopy(BookCopy.State.AVAIL));*/
            
            
            AbstractSession session;
            LoginData ld;
            SearchPattern pattern = new SearchPattern();
            
            ld = new LoginData();
            session = library.logIn(ld);
            dumpBooks(library.findBooks(pattern)); // ok
            //library.getReservedBooks(session); // would throw permission exception
            
            System.out.println("login");
            pattern = new SearchPattern();
            pattern.setAuthor("Thomas");
            pattern.setTitle("Wprowadzenie do algorytmów");
            pattern.setPublishYear(1992);
            pattern.setPublisher("MIT press");
            pattern.onlyAvailableCopies = true;
            ld = new LoginData(11,"puste");
            //library.addReader(ld);
            //ld = new LoginData(11,"fa³szywe");
            session = library.logIn(ld);
            //dumpBooks(library.findBooks(pattern));
            int[] ids = {7}; // {1,0} robi b³¹d
            library.requestBooks(session, ids);
            //int[] ids2 = {2};
            //try{library.cancelReservations(session, ids2);}catch(Exception e) { e.printStackTrace(); }
            int[] ids3 = {7}; // {1,0} robi b³¹d
            library.reserveBooks(session, ids3);
            dumpReservations(library.getBookCopies(1));
            library.logOut(session); //library.logOut(session);
            
            
            session = library.logIn(ld);
            System.out.println("rezerwacje:");
            dumpReservations(library.getReservedBooks(session)); // now ok
            
            System.out.println("2");
        } catch(Exception e) {
            e.printStackTrace();
        }
        if(library!=null) library.shutdown();
        ServerSettings.getInstance().save();
        //library.common.SearchPattern pattern = new library.common.SearchPattern();
        //pattern.hasAuthor();
        System.out.println("shutdown");
        System.exit(0);
    }
    
}
