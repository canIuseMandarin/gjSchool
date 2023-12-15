package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
//這裡有兩個class只是名稱相同(靜態跟動態class)
	String name;

	public static void main(String[] args) {
		//DbConnection dc = new DbConnection();
		//System.out.println(dc.name);
		System.out.println(DbConnection.getDB());
	}

	public static Connection getDB()
	{
		Connection c = null;
		String url = "jdbc:mysql://localhost:3306/school";
		String user = "root";
		String password = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("no connection");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("no Driver");
			e.printStackTrace();
		}
		
		return c;
	}
}
