package model;

public class User {
    private String username;
    private String password;
    private String role; // "admin", "student", or "librarian"

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters & Setters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    @Override
    public String toString() {
        return "Username: " + username + " | Role: " + role;
    }
}
