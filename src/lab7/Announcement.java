package lab7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Announcement {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	public static final String TABLE_NAME = "POST_INFO";
	public static String MANAGER_ID = "";
	
	    
	public void instruction() {
		System.out.println("1.");
		
		
	}
	
	public Announcement(String id)
	{
		this.MANAGER_ID = id;
	}
	
	public void QueryMyAnnouncement() {
		Connection conn = null; // Connection object
	    Statement stmt = null;	// Statement object
	    ResultSet rs = null;    // Resultset object
		
		try {
            // Load a JDBC driver for Oracle DBMS
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch(ClassNotFoundException e) {
            System.err.println("error = " + e.getMessage());
            System.exit(1);
        }

        // Make a connection
        try{
            conn = DriverManager.getConnection(URL, USER_UNIVERSITY, USER_PASSWD);
        }catch(SQLException ex) {
            ex.printStackTrace();
            System.err.println("Cannot get a connection: " + ex.getLocalizedMessage());
            System.err.println("Cannot get a connection: " + ex.getMessage());
            System.exit(1);
        }

     
        String id = MANAGER_ID;
       
		String sql = "SELECT * FROM POST_INFO WHERE POST_ID IN (SELECT POST_ID FROM ANNOUNCEMENT_INFO WHERE MANAGER_ID = '"+MANAGER_ID+"') AND TYPE = 'A'";

        // check if the user exists
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("인증에 실패하였습니다. 프로그램을 종료합니다.");
                System.exit(1);
            }
            else {
            	System.out.printf("%-12s|%-10s|%-10s|%-5s|\n", "POST ID", "Posted", "Updated", "Views");
            	System.out.println("------------+----------+----------+-----|");
            	while(rs.next())
            	{
                String postID = rs.getString(1);
                Date postedDate = rs.getDate(2);
                Date updateDate = rs.getDate(3);
                int views = rs.getInt(4);
                
                
                System.out.print(postID);
                System.out.print("|");
                System.out.print(postedDate);
                System.out.print("|");
                System.out.print(updateDate);
                System.out.print("|");
                System.out.print(views);
                System.out.println("|");
                
            	}
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

        
	}
}