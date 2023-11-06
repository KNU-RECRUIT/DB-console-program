package lab7;

import java.sql.*;

public class Main {
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String USER_UNIVERSITY ="university";
    public static final String USER_PASSWD ="comp322";
    public static final String TABLE_NAME = "POST_INFO";

    public static void main(String[] args) {
        Connection conn = null; // Connection object
        Statement stmt = null;	// Statement object
        String sql = ""; // an SQL statement
        try {
            // Load a JDBC driver for Oracle DBMS
            Class.forName("oracle.jdbc.driver.OracleDriver");
            // Get a Connection object
            System.out.println("Success!");
        }catch(ClassNotFoundException e) {
            System.err.println("error = " + e.getMessage());
            System.exit(1);
        }

        // Make a connection
        try{
            conn = DriverManager.getConnection(URL, USER_UNIVERSITY, USER_PASSWD);
            System.out.println("Connected.");
        }catch(SQLException ex) {
            ex.printStackTrace();
            System.err.println("Cannot get a connection: " + ex.getLocalizedMessage());
            System.err.println("Cannot get a connection: " + ex.getMessage());
            System.exit(1);
        }
    }
}
