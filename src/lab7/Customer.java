package lab7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

public class Customer {
    public static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    public static final String USER_UNIVERSITY ="university";
    public static final String USER_PASSWD ="comp322";
    public static final String TABLE_NAME = "CUSTOMER_INFO";
    public static String MANAGER_ID = "";

    public int instruction() {
        System.out.println("1. 고객 수동 추가");
        System.out.println("2. 고객 조회");
        System.out.println("3. 고객 삭제");
        System.out.println("4. 뒤로 가기");

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        return input;
    }

    public void addCustomer() {
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

        Scanner sc = new Scanner(System.in);
        System.out.println("고객의 이름을 입력하세요.");
        String LName = sc.nextLine();
        System.out.println("고객의 성을 입력하세요.");
        String FName = sc.nextLine();
        System.out.println("고객의 성명을 입력하세요.");
        String name = sc.nextLine();
        System.out.println("고객의 성별을 입력하세요.");
        String gender = sc.nextLine();
        System.out.println("고객의 이메일 주소를 입력하세요.");
        String email = sc.nextLine();
        System.out.println("고객의 id를 입력하세요.");
        String id = sc.nextLine();
        System.out.println("고객의 비밀번호를 입력하세요.");
        String passwd = sc.nextLine();
        System.out.println("고객의 전화번호를 입력하세요.");
        String phone = sc.nextLine();
        System.out.println("고객의 생년월일을 입력하세요.");
        String birth = sc.nextLine();

        // 고객 정보 삽입
        String sql = "INSERT INTO CUSTOMER_INFO VALUES (" +
                FName + ", " +
                LName + ", " +
                name + ", " +
                gender + ", " +
                email + ", " +
                id + ", " +
                passwd + ", " +
                phone + ", " +
                birth + ")";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("고객 정보 삽입에 실패했습니다.");
            System.exit(1);
        }

    }
    public void queryCustomer(String id) {
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

        // 고객 정보 조회
        String sql = "SELECT * FROM CUSTOMER_INFO WHERE ID = " + id;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                System.out.println("고객 정보가 없습니다.");
                System.exit(1);
            }

            String FName = rs.getString("FNAME");
            String LName = rs.getString("LNAME");
            String name = rs.getString("NAME");
            String gender = rs.getString("GENDER");
            String email = rs.getString("EMAIL");
            String Id = rs.getString("ID");
            String passwd = rs.getString("PW");
            String phone = rs.getString("PHONE_NUMBER");
            String birth = rs.getString("BIRTH_DATE");

            System.out.println("고객 정보");
            System.out.println(String.format("%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s", "FNAME", "LNAME", "NAME", "GENDER", "EMAIL", "ID", "PW", "PHONE_NUMBER", "BIRTH_DATE"));
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s|%-7s", FName, LName, name, gender, email, Id, passwd, phone, birth));

        } catch (SQLException e) {
            System.out.println("고객 정보 조회에 실패했습니다.");
            System.exit(1);
        }
    }

    public void deleteCustomer(String id) {
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

        // 고객 정보 삭제
        String sql = "DELETE FROM CUSTOMER_INFO WHERE ID = " + id;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("고객 정보 삭제에 실패했습니다.");
            System.exit(1);
        }
    }
}
