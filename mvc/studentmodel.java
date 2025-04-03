package model;

public class student {
   private int studentID;
   private String name;
   private String department;
   private float marks;

   public student() {
   }

   public student(int studentID, String name, String department, float marks) {
      this.studentID = studentID;
      this.name = name;
      this.department = department;
      this.marks = marks;
   }

   public int getStudentID() {
      return this.studentID;
   }

   public void setStudentID(int studentID) {
      this.studentID = studentID;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDepartment() {
      return this.department;
   }

   public void setDepartment(String department) {
      this.department = department;
   }

   public float getMarks() {
      return this.marks;
   }

   public void setMarks(float marks) {
      this.marks = marks;
   }

   public String toString() {
      return "Student ID: " + this.studentID + ", Name: " + this.name + ", Department: " + this.department + ", Marks: " + this.marks;
   }
}
