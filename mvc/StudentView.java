package view;

import controller.StudentController;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import model.student;

public class StudentView {
   public StudentView() {
   }

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      StudentController controller = new StudentController();

      while(true) {
         while(true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            String name;
            String department;
            float marks;
            int id;
            switch (choice) {
               case 1:
                  System.out.print("Enter Name: ");
                  name = scanner.nextLine();
                  System.out.print("Enter Department: ");
                  department = scanner.nextLine();
                  System.out.print("Enter Marks: ");
                  marks = scanner.nextFloat();
                  controller.addStudent(new student(0, name, department, marks));
                  break;
               case 2:
                  List<student> students = controller.getAllStudents();
                  PrintStream var10001 = System.out;
                  students.forEach(var10001::println);
                  break;
               case 3:
                  System.out.print("Enter Student ID to Update: ");
                  id = scanner.nextInt();
                  scanner.nextLine();
                  System.out.print("Enter New Name: ");
                  name = scanner.nextLine();
                  System.out.print("Enter New Department: ");
                  department = scanner.nextLine();
                  System.out.print("Enter New Marks: ");
                  marks = scanner.nextFloat();
                  controller.updateStudent(id, name, department, marks);
                  break;
               case 4:
                  System.out.print("Enter Student ID to Delete: ");
                  id = scanner.nextInt();
                  controller.deleteStudent(id);
                  break;
               case 5:
                  System.out.println("Exiting...");
                  scanner.close();
                  System.exit(0);
               default:
                  System.out.println("Invalid choice! Try again.");
            }
         }
      }
   }
}
