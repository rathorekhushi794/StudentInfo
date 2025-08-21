package com.studentinfo;

import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n=== Student Info System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Get Student by ID");
            System.out.println("5. List All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> dao.addStudent(sc);
                case 2 -> dao.deleteStudent(sc);
                case 3 -> dao.updateStudent(sc);
                case 4 -> dao.getStudent(sc);
                case 5 -> dao.listStudents();
                case 6 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
