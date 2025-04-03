import java.sql.*;
public class CREATE_DB
{
    static final String DB_URL= "jdbc:mysql://localhost:3306/";
    static final String USER= "root";
    static final String PASS= "";
    
    public static void main(String[]args){
        try(Connection conn= DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt= conn.createStatement();)
        {
            String sql= "CREATE DATABASE ELIE";
            stmt.executeUpdate(sql);
            System.out.println("Database Created successfuly...");
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
