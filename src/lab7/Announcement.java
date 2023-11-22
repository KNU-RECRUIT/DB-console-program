package lab7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Announcement {
	public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	public static final String USER_UNIVERSITY ="university";
	public static final String USER_PASSWD ="comp322";
	public static final String TABLE_NAME = "POST_INFO";
	public static String MANAGER_ID = "";

    Connection conn = null; // Connection object
    Statement stmt = null;	// Statement object
    ResultSet rs = null;    // Resultset object
	    
	public void instruction() {
	        System.out.println("===============================");
	        System.out.println("|원하시는 메뉴를 선택하여 주시기 바랍니다|");
		System.out.println("|1. 내가 쓴 글 조회               |");
	        System.out.println("|2. 게시글 관리자 조회(전체)        |");
	        System.out.println("|3. 게시글 관리자 조회(부서 관리자급) |");
		System.out.println("===============================");
	}
	
	public Announcement(String id)
	{
		this.MANAGER_ID = id;
	}

    public void JDBCReady() {


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
    }
	public void QueryMyAnnouncement() {
        if (conn==null) {
            System.err.println("Error: Connection Error. Please call JDBCReady() or try Again.");
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
            	System.out.println("------------+----------+----------+-----|");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

        
	}
	public void QueryManagerOfDepartment() {

        if (conn==null) {
            System.err.println("Error: Connection Error. Please call JDBCReady() or try Again.");
        }

		String sql = "SELECT DISTINCT E.NAME, D.CONTACT, D.DEPARTMENT_ID FROM EMPLOYEE_INFO E, DEPARTMENT_INFO D, ANNOUNCEMENT_INFO A WHERE E.ID = D.HEAD_ID AND A.MANAGER_ID = E.ID";

        // check if the user exists
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("인증에 실패하였습니다. 프로그램을 종료합니다.");
                System.exit(1);
            }
            else {
            	System.out.printf("%-4s|%-10s|%-4s|\n","이름", "전화번호(Phone)", "부서");
            	System.out.println("=========================");
            	while(rs.next())
            	{
                String Name = rs.getString(1);
                String Phone = rs.getString(2);
                String Department = rs.getString(3);
                
                
                
                System.out.print(Name);
                System.out.print("|");
                System.out.print(Phone);
                System.out.print("|");
                System.out.print(Department);
                System.out.println("|");
   
            	}
            	System.out.println("=========================");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

        
	}
}
