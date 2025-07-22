import model.Book;
import service.LibraryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is main class ... this statement is only to learn concept of branch in git...");
        LibraryService libraryService = new LibraryService();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by Title/Author");
            System.out.println("4. Update Book by ISBN");
            System.out.println("5. Delete Book by ISBN");
            System.out.println("6. Issue/Return Book by ISBN");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Book
                    Book newBook = getBookInput(scanner);
                    libraryService.addBook(newBook);
                    break;

                case 2:
                    libraryService.viewAllBooks();
                    break;

                case 3:
                    System.out.print("Enter title or author keyword to search: ");
                    String keyword = scanner.nextLine();
                    libraryService.searchBooksByTitleOrAuthor(keyword);
                    break;

                case 4:
                    System.out.print("Enter ISBN of the book to update: ");
                    String updateIsbn = scanner.nextLine();
                    Book updatedBook = getBookInput(scanner);
                    libraryService.updateBook(updateIsbn, updatedBook);
                    break;

                case 5:
                    System.out.print("Enter ISBN of the book to delete: ");
                    String deleteIsbn = scanner.nextLine();
                    libraryService.deleteBook(deleteIsbn);
                    break;

                case 6:
                    System.out.print("Enter ISBN to issue/return book: ");
                    String toggleIsbn = scanner.nextLine();
                    libraryService.toggleAvailability(toggleIsbn);
                    break;

                case 7:
                    System.out.println("Exiting... 👋");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }

        } while (choice != 7);

        scanner.close();
    }

    // Reusable input method for book info
    private static Book getBookInput(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter published year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Is the book available? (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        return new Book(title, author, isbn, year, available);
    }
}
