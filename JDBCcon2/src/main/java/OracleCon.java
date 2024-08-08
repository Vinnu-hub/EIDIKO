import java.sql.*;

public class OracleCon {
    public static void main(String[] args) {
        // JDBC URL, username, and password
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Update with your database details
        String username = "staff"; // Update with your database username
        String password = "vinnu321"; // Update with your database password

        // Declare JDBC objects
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Step 1: Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loaded successfully.");

            // Step 2: Create a connection to the database
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully.");

            // Step 3: Create a statement object to execute queries
            stmt = con.createStatement();

            // Step 4: Execute the query to fetch names from the students table
            String query = "SELECT * FROM students"; // Select only the name column
            rs = stmt.executeQuery(query);
            System.out.println("Query executed successfully.");

            // Step 5: Process the result set to get names
            boolean hasData = false; // Flag to check if data is retrieved
            while (rs.next()) {
                String name = rs.getString("name"); // Retrieve the name column
                System.out.println("Student Name: " + name);

                String id = rs.getString("student_id"); // Retrieve the name column
                System.out.println("Student id: " + id);

                String percentage = rs.getString("percentage"); // Retrieve the name column
                System.out.println("Student per: " + percentage);
                hasData = true;
            }

            if (!hasData) {
                System.out.println("No data found in the 'students' table.");
            }

        } catch (Exception e) {
            System.out.println("Database error.");
            e.printStackTrace();
        } finally {
            // Step 6: Close all resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources.");
                e.printStackTrace();
            }
        }
    }
}
