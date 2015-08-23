package library.common;

import java.io.Serializable;

public abstract class AbstractBook implements Serializable {
    
    private String title;

    /** First and family names of the author */
    private String author;

    private String publisher;
    
    private int publishYear;
    
    public AbstractBook(String title, String author, String publisher, int publishYear) {
        setTitle(title);
        setAuthor(author);
        setPublisher(publisher);
        setPublishYear(publishYear);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher.trim();
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
    
    private static final long serialVersionUID = 8122795939177520609L;
}
