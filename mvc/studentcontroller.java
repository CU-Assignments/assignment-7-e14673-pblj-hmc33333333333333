package controller;

import model.student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private static final String URL = "jdbc:mysql://localhost:3306/StudentDB";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection con;

    public StudentController() {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add Student
    public void addStudent(student student) {
        String sql = "INSERT INTO Students (Name, Department, Marks) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getDepartment());
            stmt.setFloat(3, student.getMarks());
            stmt.executeUpdate();
            con.commit();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    // Get All Students
    public List<student> getAllStudents() {
        List<student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new student(rs.getInt("StudentID"), rs.getString("Name"), rs.getString("Department"), rs.getFloat("Marks")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Update Student
    public void updateStudent(int id, String name, String department, float marks) {
        String sql = "UPDATE Students SET Name=?, Department=?, Marks=? WHERE StudentID=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, department);
            stmt.setFloat(3, marks);
            stmt.setInt(4, id);
            int rows = stmt.executeUpdate();
            con.commit();
            if (rows > 0) System.out.println("Student updated successfully!");
            else System.out.println("Student not found!");
        } catch (SQLException e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    // Delete Student
    public void deleteStudent(int id) {
        String sql = "DELETE FROM Students WHERE StudentID=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            con.commit();
            if (rows > 0) System.out.println("Student deleted successfully!");
            else System.out.println("Student not found!");
        } catch (SQLException e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    // Rollback transaction in case of an error
    private void rollbackTransaction() {
        try {
            if (con != null) {
                con.rollback();
                System.out.println("Transaction rolled back.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
