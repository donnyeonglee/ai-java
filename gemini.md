# Java Swing Diary Application with MySQL

## Description
This project outlines the development of a simple diary application using Java Swing for the Graphical User Interface (GUI) and MySQL as the database for persistent storage. The application allows users to create, view, search, and delete diary entries.

## Technology Stack
*   **Programming Language:** Java
*   **GUI Framework:** Java Swing
*   **Database:** MySQL
*   **Database Connectivity:** JDBC (Java Database Connectivity)

## Project Structure
The project is organized within the `test` package in the `src/test` directory:

```
src/
└── test/
    ├── DiaryEntry.java      # Data Transfer Object (DTO)
    ├── DiaryDAO.java        # Database Access Object (DAO)
    ├── DiaryUI.java         # Main UI window and components
    └── DiaryApp.java        # Main application entry point
```

## Dependencies
*   **MySQL Connector/J:** Required to connect Java applications to a MySQL database.
    *   **How to include:** Add the `mysql-connector-java` JAR to your project's classpath.
        *   **Maven:**
            ```xml
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>8.0.28</version>
            </dependency>
            ```

## Database Setup
1.  **Create Database:**
    Use the following SQL script to create the database and the necessary table.

    ```sql
    -- Create the database if it doesn't exist
    CREATE DATABASE IF NOT EXISTS diary_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

    -- Use the newly created database
    USE diary_db;

    -- Create the diary_entries table
    CREATE TABLE IF NOT EXISTS diary_entries (
        id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        content TEXT NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    ```
2.  **Database Connection Details:**
    Update `DiaryDAO.java` with your MySQL credentials.

    **Connection String:**
    `jdbc:mysql://localhost:3306/diary_db?useSSL=false&serverTimezone=UTC`
    **Username:** `root`
    **Password:** `your_password`

## Aesthetics
*   **Colors:** A soft pastel theme.
    *   **Background:** `new Color(245, 240, 235)` (Light Beige)
    *   **Accent:** `new Color(100, 150, 180)` (Muted Blue)
    *   **Display Area:** `new Color(255, 252, 242)` (Off-white/Cream)
*   **Image:** A visual element `diary_visual.png` is integrated at the bottom of the UI to enhance visual appeal.

## Key Components Explained
*   **`DiaryEntry.java`:**
    *   Represents a single diary entry with fields: `id`, `title`, `content`, and `createdAt`.
*   **`DiaryDAO.java`:**
    *   Handles CRUD operations: `saveEntry`, `loadEntries`, `deleteEntry`, and `searchEntries`.
    *   Manages JDBC connections and SQL execution.
*   **`DiaryUI.java`:**
    *   The main `JFrame` that integrates the input fields (title, content), button controls (Save, Load, Delete, Search), and the list display area.
    *   Implements a custom styled button and handles all UI events.
*   **`DiaryApp.java`:**
    *   The entry point of the application.
    *   Sets the System Look and Feel and initializes `DiaryUI`.

## How to Run
1.  **Set up MySQL:** Create `diary_db` and `diary_entries` table.
2.  **Configure Connection:** Update `DiaryDAO.java` with your MySQL password.
3.  **Add JDBC Driver:** Ensure the MySQL Connector/J JAR is in the classpath.
4.  **Run:** Execute `DiaryApp.java`.
