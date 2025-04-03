import java.sql.*;
import java.util.Scanner;

public class PRODUCTCRUD {
    // Database URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/elie";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            con.setAutoCommit(false); // Start transaction

            while (true) {
                System.out.println("\n=== PRODUCT MANAGEMENT SYSTEM ===");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addProduct(con, scanner);
                        break;
                    case 2:
                        viewProducts(con);
                        break;
                    case 3:
                        updateProduct(con, scanner);
                        break;
                    case 4:
                        deleteProduct(con, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        con.close();
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert Product
    private static void addProduct(Connection con, Scanner scanner) {
        try {
            System.out.print("Enter Product Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();

            String sql = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);

            int rows = stmt.executeUpdate();
            con.commit();
            System.out.println(rows + " Product(s) added successfully!");

        } catch (SQLException e) {
            try {
                con.rollback();
                System.out.println("Transaction rolled back.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    // View Products
    private static void viewProducts(Connection con) {
        try {
            String sql = "SELECT * FROM Product";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n=== Product List ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ProductID") + 
                                   ", Name: " + rs.getString("ProductName") + 
                                   ", Price: " + rs.getDouble("Price") + 
                                   ", Quantity: " + rs.getInt("Quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update Product
    private static void updateProduct(Connection con, Scanner scanner) {
        try {
            System.out.print("Enter Product ID to Update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter New Product Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter New Quantity: ");
            int quantity = scanner.nextInt();

            String sql = "UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, quantity);
            stmt.setInt(4, id);

            int rows = stmt.executeUpdate();
            con.commit();
            System.out.println(rows + " Product(s) updated successfully!");

        } catch (SQLException e) {
            try {
                con.rollback();
                System.out.println("Transaction rolled back.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    // Delete Product
    private static void deleteProduct(Connection con, Scanner scanner) {
        try {
            System.out.print("Enter Product ID to Delete: ");
            int id = scanner.nextInt();

            String sql = "DELETE FROM Product WHERE ProductID=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            con.commit();
            System.out.println(rows + " Product(s) deleted successfully!");

        } catch (SQLException e) {
            try {
                con.rollback();
                System.out.println("Transaction rolled back.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
