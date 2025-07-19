package model;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publishedYear;
    private boolean available;

    // Constructor
    public Book(String title, String author, String isbn, int publishedYear, boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.available = available;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // toString() method to display book info
    @Override
    public String toString() {
        return "\nTitle: " + title +
                "\nAuthor: " + author +
                "\nISBN: " + isbn +
                "\nPublished Year: " + publishedYear +
                "\nAvailable: " + (available ? "Yes" : "No");
    }
}
