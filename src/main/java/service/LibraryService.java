package service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import db.MongoDBConnection;
import model.Book;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Sorts.ascending;

public class LibraryService {

    private final MongoCollection<Document> bookCollection;

    public LibraryService() {
        this.bookCollection = MongoDBConnection.getBookCollection();
    }

    // 1. Add Book
    public void addBook(Book book) {
        Document doc = new Document("title", book.getTitle())
                .append("author", book.getAuthor())
                .append("isbn", book.getIsbn())
                .append("publishedYear", book.getPublishedYear())
                .append("available", book.isAvailable());

        bookCollection.insertOne(doc);
        System.out.println("✅ Book added successfully.");
    }

    // 2. View All Books
    public void viewAllBooks() {
        FindIterable<Document> books = bookCollection.find().sort(ascending("title"));

        System.out.println("\n📚 All Books in Library:");
        for (Document doc : books) {
            displayBook(doc);
        }
    }

    // 3. Search Book by Title or Author
    public void searchBooksByTitleOrAuthor(String keyword) {
        Bson filter = Filters.or(
                Filters.regex("title", keyword, "i"),
                Filters.regex("author", keyword, "i")
        );

        FindIterable<Document> results = bookCollection.find(filter);

        System.out.println("\nSearch Results for: \"" + keyword + "\"");
        for (Document doc : results) {
            displayBook(doc);
        }
    }

    // 4. Update Book by ISBN
    public void updateBook(String isbn, Book updatedBook) {
        Bson filter = Filters.eq("isbn", isbn);
        Document existingBook = bookCollection.find(filter).first();

        if (existingBook != null) {
            Bson updates = Updates.combine(
                    Updates.set("title", updatedBook.getTitle()),
                    Updates.set("author", updatedBook.getAuthor()),
                    Updates.set("publishedYear", updatedBook.getPublishedYear()),
                    Updates.set("available", updatedBook.isAvailable())
            );

            bookCollection.updateOne(filter, updates);
            System.out.println("✅ Book updated successfully.");
        } else {
            System.out.println("❌ Book with ISBN " + isbn + " not found.");
        }
    }

    // 5. Delete Book by ISBN
    public void deleteBook(String isbn) {
        Bson filter = Filters.eq("isbn", isbn);
        Document book = bookCollection.find(filter).first();

        if (book != null) {
            bookCollection.deleteOne(filter);
            System.out.println("✅ Book deleted successfully.");
        } else {
            System.out.println("❌ Book with ISBN " + isbn + " not found.");
        }
    }

    // 6. Toggle Availability (Issue/Return)
    public void toggleAvailability(String isbn) {
        Bson filter = Filters.eq("isbn", isbn);
        Document book = bookCollection.find(filter).first();

        if (book != null) {
            boolean currentAvailability = book.getBoolean("available");
            bookCollection.updateOne(filter, Updates.set("available", !currentAvailability));

            if (currentAvailability) {
                System.out.println("✅ Book has been issued.");
            } else {
                System.out.println("✅ Book has been returned.");
            }
        } else {
            System.out.println("❌ Book with ISBN " + isbn + " not found.");
        }
    }

    // Utility Method to Display Book Info
    private void displayBook(Document doc) {
        System.out.println("---------------------------");
        System.out.println("Title: " + doc.getString("title"));
        System.out.println("Author: " + doc.getString("author"));
        System.out.println("ISBN: " + doc.getString("isbn"));
        System.out.println("Published Year: " + doc.getInteger("publishedYear"));
        System.out.println("Available: " + (doc.getBoolean("available") ? "Yes" : "No"));
    }
}
