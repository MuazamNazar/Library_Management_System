
# 📚 Library Management System (Java + MongoDB)

A **console-based Library Management System** built using **Core Java** and **MongoDB**. This project allows users to manage books and includes a basic user authentication system with roles like `admin` and `student`.

---

## 🚀 Features

### ✅ Book Management
- Add new books
- View all books
- Search books by title or author
- Update book information by ISBN
- Delete books by ISBN
- Issue/return books

### 👥 User Management
- Register as a student or admin (only **1 admin** allowed)
- Login system for both admin and student
- Role-based access can be extended

---

## 🛠️ Tech Stack

| Technology   | Purpose                          |
|--------------|----------------------------------|
| Java         | Core application logic           |
| MongoDB      | Database for storing data        |
| Maven        | Project management/build tool    |
| IntelliJ IDEA| Development IDE                  |

---

## 💾 MongoDB Configuration

Make sure MongoDB is running locally on port `27017`.

- **Database**: `library_db`
- **Collections**:
  - `Book`
  - `User`

You can update the MongoDB URI in:

```java
MongoClients.create("mongodb://localhost:27017");
```

---

## 🔧 How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/library-management-system.git
   cd library-management-system
   ```

2. **Ensure MongoDB is running**

3. **Build and run the project**
   - Open in IntelliJ or any Java IDE
   - Run the `Main.java` class

---

## 👤 Roles

| Role     | Description                        |
|----------|------------------------------------|
| `admin`  | Full access to book operations     |
| `student`| View/search books (extendable)     |

---

## 📂 Project Structure

```
src/
│
├── db/
│   └── MongoDBConnection.java
│
├── model/
│   ├── Book.java
│   └── User.java
│
├── service/
│   ├── LibraryService.java
│   └── UserService.java
│
└── Main.java
```

---

## ✨ Future Enhancements
- Password hashing (e.g., SHA-256 or bcrypt)
- Role-based access control (RBAC)
- Borrowing history and due dates
- GUI using JavaFX or Swing
- JWT-based API with Spring Boot

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
