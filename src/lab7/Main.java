package lab7;

import java.sql.*;
import java.util.Scanner;

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

        // https://patorjk.com/software/taag/#p=display&f=Graffiti&t=RECRUIT
        System.out.println("________________________________________ ____ ___.______________\n" +
                            "\\______   \\_   _____/\\_   ___ \\______   \\    |   \\   \\__    ___/\n" +
                            " |       _/|    __)_ /    \\  \\/|       _/    |   /   | |    |   \n" +
                            " |    |   \\|        \\\\     \\___|    |   \\    |  /|   | |    |   \n" +
                            " |____|_  /_______  / \\______  /____|_  /______/ |___| |____|   \n" +
                            "        \\/        \\/         \\/       \\/                        \n" +
                            "-----------------------------------------------------------------");


        // login
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        // check if the user exists
        try {
            sql = "SELECT * FROM EMPLOYEE_INFO" + " WHERE ID = '" + id + "' AND NAME = '" + name + "'";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("인증에 실패하였습니다. 프로그램을 종료합니다.");
                System.exit(1);
            }
            else {
                System.out.println("Welcome " + name + "!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
