import java.sql.*;

public class DB_Connection {
    static Connection conn;
    static Statement st;

    DB_Connection() {
        try {
            // Load the JDBC driver
            // Class.forName("com.mysql.jdbc.Driver");

            // Establish a connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/job_portal", "root", "");
            st = conn.createStatement();
            if (conn != null) {
                System.out.println("Connected to the database successfully.");
            } else {
                System.out.println("Failed To Connect to database");
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}
