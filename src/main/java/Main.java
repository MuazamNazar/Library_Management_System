import model.Book;
import model.User;
import service.LibraryService;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);
        User loggedInUser = null;

        System.out.println("=== Welcome to Library Management System ===");

        // Registration/Login Loop
        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.print("Enter your choice: ");
            int authChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (authChoice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                System.out.print("Enter role (admin/student): ");
                String role = scanner.nextLine().toLowerCase();

                User user = new User(username, password, role);
                userService.registerUser(user);

            } else if (authChoice == 2) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                loggedInUser = userService.loginUser(username, password);
                if (loggedInUser != null) {
                    System.out.println("✅ Logged in as " + loggedInUser.getUsername() + " (" + loggedInUser.getRole() + ")");
                    break;
                } else {
                    System.out.println("❌ Invalid credentials! Try again.");
                }
            } else {
                System.out.println("❌ Invalid choice. Try again.");
            }
        }

        // Menu Loop
        int choice;
        do {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. View All Books");
            System.out.println("2. Search Book by Title/Author");
            System.out.println("3. Issue/Return Book by ISBN");

            if (loggedInUser.getRole().equalsIgnoreCase("admin")) {
                System.out.println("4. Add Book");
                System.out.println("5. Update Book by ISBN");
                System.out.println("6. Delete Book by ISBN");
                System.out.println("7. Exit");
            } else {
                System.out.println("4. Exit");
            }

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            // Common for both admin and student
            switch (choice) {
                case 1:
                    libraryService.viewAllBooks();
                    break;

                case 2:
                    System.out.print("Enter title or author keyword to search: ");
                    String keyword = scanner.nextLine();
                    libraryService.searchBooksByTitleOrAuthor(keyword);
                    break;

                case 3:
                    System.out.print("Enter ISBN to issue/return book: ");
                    String toggleIsbn = scanner.nextLine();
                    libraryService.toggleAvailability(toggleIsbn);
                    break;

                default:
                    if (loggedInUser.getRole().equalsIgnoreCase("admin")) {
                        switch (choice) {
                            case 4:
                                Book newBook = getBookInput(scanner);
                                libraryService.addBook(newBook);
                                break;

                            case 5:
                                System.out.print("Enter ISBN of the book to update: ");
                                String updateIsbn = scanner.nextLine();
                                Book updatedBook = getBookInput(scanner);
                                libraryService.updateBook(updateIsbn, updatedBook);
                                break;

                            case 6:
                                System.out.print("Enter ISBN of the book to delete: ");
                                String deleteIsbn = scanner.nextLine();
                                libraryService.deleteBook(deleteIsbn);
                                break;

                            case 7:
                                System.out.println("👋 Exiting...");
                                break;

                            default:
                                System.out.println("❌ Invalid choice! Try again.");
                        }
                    } else {
                        if (choice == 4) {
                            System.out.println("👋 Exiting...");
                        } else {
                            System.out.println("❌ Invalid choice! Try again.");
                        }
                    }
            }

        } while ((loggedInUser.getRole().equalsIgnoreCase("admin") && choice != 7) ||
                (loggedInUser.getRole().equalsIgnoreCase("student") && choice != 4));

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
        scanner.nextLine(); // consume newline

        System.out.print("Is the book available? (true/false): ");
        boolean available = scanner.nextBoolean();
        scanner.nextLine(); // consume newline

        return new Book(title, author, isbn, year, available);
    }
}
