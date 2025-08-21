package com.studentinfo;

import java.sql.*;
import java.util.*;

public class StudentDAO {
    private static final String URL = "jdbc:mysql://db:3306/studentdb";
    private static final String USER = "root";
    private static final String PASS = "password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // Create table if not exists
    public StudentDAO() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100),
                    age INT,
                    course VARCHAR(100)
                )
                """;
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        System.out.print("Enter course: ");
        String course = sc.next();

        String sql = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, course);
            pstmt.executeUpdate();
            System.out.println("✅ Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(Scanner sc) {
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("✅ Student deleted.");
            else System.out.println("❌ Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Scanner sc) {
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new name: ");
        String name = sc.next();
        System.out.print("Enter new age: ");
        int age = sc.nextInt();
        System.out.print("Enter new course: ");
        String course = sc.next();

        String sql = "UPDATE students SET name=?, age=?, course=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, course);
            pstmt.setInt(4, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("✅ Student updated.");
            else System.out.println("❌ Student not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getStudent(Scanner sc) {
        System.out.print("Enter student ID: ");
        int id = sc.nextInt();

        String sql = "SELECT * FROM students WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("course")
                );
                System.out.println(s);
            } else {
                System.out.println("❌ Student not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listStudents() {
        String sql = "SELECT * FROM students";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("course")
                );
                System.out.println(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
