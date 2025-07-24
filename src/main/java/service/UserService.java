package service;

import com.mongodb.client.MongoCollection;
import db.MongoDBConnection;
import model.User;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;

public class UserService {

    private final MongoCollection<Document> userCollection;

    public UserService() {
        this.userCollection = MongoDBConnection.getUserCollection();
    }

    // Register a new user
    public void registerUser(User user) {
        if (user.getRole().equalsIgnoreCase("admin") && adminExists()) {
            System.out.println("❌ Admin already exists. Only one admin is allowed.");
            return;
        }

        if (userExists(user.getUsername())) {
            System.out.println("❌ Username already exists. Try a different one.");
            return;
        }

        Document doc = new Document("username", user.getUsername())
                .append("password", user.getPassword())  // Note: Plain-text for now
                .append("role", user.getRole().toLowerCase());

        userCollection.insertOne(doc);
        System.out.println("✅ User registered successfully!");
    }

    // Check if a user exists by username
    public boolean userExists(String username) {
        Document userDoc = userCollection.find(eq("username", username)).first();
        return userDoc != null;
    }

    // Check if an admin already exists
    public boolean adminExists() {
        Document adminDoc = userCollection.find(eq("role", "admin")).first();
        return adminDoc != null;
    }

    // User login by username and password
    public User loginUser(String username, String password) {
        Document userDoc = userCollection.find(eq("username", username)).first();

        if (userDoc != null && userDoc.getString("password").equals(password)) {
            String role = userDoc.getString("role");
            return new User(username, password, role);
        }

        return null;
    }
}
