/*
 * SQLSearchPattern.java
 *
 * Created on 20 marzec 2007, 00:48
 */

package library.server;

import java.sql.*;
import java.util.*;
import library.common.*;
import util.database.Enumerator;

/**
 * Searches for a book in database using search pattern defined in
 * SearchPattern object.
 *
 * @author Piotrek
 */
public class SQLSearchPattern {
    
    private SearchPattern pattern;
    
    /** Creates a new instance of SQLSearchPattern */
    public SQLSearchPattern(SearchPattern pattern) {
        this.pattern = pattern;
    }
    
    /**
     * returns LIKE statement for single word.
     * Example: ("title","Algorithms") will produce "title LIKE '%Algorithms%'"
     * when ignoreFirstCase is FALSE and "(title LIKE '%Algorithms%' OR title
     * LIKE '%algorithms%')" when set to TRUE.
     */
    protected String getLikeStatement(String fieldName, String word, boolean ignoreFirstCase) {
        String retval = fieldName + " LIKE '%" + word + "%'";
        if (!ignoreFirstCase)
            return retval;
        String changedCaseWord;
        if (Character.isUpperCase(word.charAt(0)))
            changedCaseWord = Character.toLowerCase(word.charAt(0)) + word.substring(1);
        else
            changedCaseWord = Character.toUpperCase(word.charAt(0)) + word.substring(1);
        return "(" + retval + " OR " + fieldName + " LIKE '%" + changedCaseWord + "%')";
    }
    
    /** returns WHERE substatement for book certain field.
     * Splits fields to words and searches for these words.
     * Statement for arguments ("author","Stefan Grabinski") is:
     * (author LIKE '%Stefan%' AND author LIKE '%Grabinski%')
     * @param ignoreFirstCase whether to ignore size of first letter
     * (will find "Algorithm" and "algorithm").
     * @return statement or empty string if fieldValue does not contain anything
     */
    protected String getWildcardStatement(String fieldName, String fieldValue, boolean ignoreFirstCase) {
        String parts[] = fieldValue.split("\\s+");
        if (parts.length == 0)
            return "";
        
        Enumerator retval = new Enumerator(" AND ");
        for(int i=0; i<parts.length; ++i)
            retval.addNotQuote(getLikeStatement(fieldName, parts[i], ignoreFirstCase));
        return "(" + retval.toString() + ")";
    }
    
    /** returns full SQL search statement */
    public String getSQLStatement() {
        Enumerator fields = new Enumerator(",",false);
        fields.add("bookId").add("publishYear").add("author").add("title");
        fields.add("publisher").add("(SELECT COUNT(*) FROM BookCopies BC WHERE " +
                "BC.bookId=B.bookId AND state="+BookCopy.State.AVAIL.ordinal()+") available");
        
        StringBuffer retval = new StringBuffer("SELECT ");
        retval.append(fields.toString()).append(" FROM Books B");
        
        Enumerator where = new Enumerator(" AND ");
        if(pattern.hasAuthor()) where.addNotQuote(getWildcardStatement("author", pattern.getAuthor(),false));
        if(pattern.hasTitle()) where.addNotQuote(getWildcardStatement("title", pattern.getTitle(),true));
        if(pattern.hasPublishYear()) where.add("publishYear",pattern.getPublishYear());
        if(pattern.hasPublisher()) where.add("publisher",pattern.getPublisher());
        if(pattern.onlyAvailableCopies) where.addNotQuote("available > 0");
        
        if (!where.isEmpty())
            retval.append(" WHERE ").append(where.toString());
        
        return retval.toString();
    }
    
    /** performs search in database and returns collection of results */
    public Collection<Book> performSearch(Database db) throws LibraryException {
        try {
            Collection<Book> retval = new ArrayList<Book>();
            Connection conn = db.getConnection();
            Statement st = conn.createStatement();
            
            ResultSet rs = st.executeQuery(getSQLStatement());
            while(rs.next()) {
                Book book = SQLBook.createBook(rs,true);
                if (book != null) retval.add(book);
            }
            
            st.close();
            
            return retval;
        }catch(SQLException e) {
            e.printStackTrace();
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        }catch(Exception e) {
            e.printStackTrace();
            throw new InternalErrorException(e.getLocalizedMessage());
        }
    }
    
}
