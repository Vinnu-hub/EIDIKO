import java.sql.*;

public class InsertPrepared {
    public static void main(String[] args) {
        // JDBC URL, username, and password
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Update with your database URL
        String username = "staff"; // Update with your database username
        String password = "vinnu321"; // Update with your database password

        // Declare JDBC objects
        try (Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = con.prepareStatement("INSERT INTO students (student_id, name, percentage) VALUES (?, ?, ?)")) {

            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loaded successfully.");

            // Set the parameters
            stmt.setInt(1, 101); // Set the student_id parameter
            stmt.setString(2, "Ratan"); // Set the name parameter
            stmt.setFloat(3, 85.5f); // Set the percentage parameter (assuming percentage is a float)

            // Execute the update
            int rowsInserted = stmt.executeUpdate();
            System.out.println(rowsInserted + " record(s) inserted");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error.");
            e.printStackTrace();
        }
    }
}
