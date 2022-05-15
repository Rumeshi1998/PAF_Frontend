package conn;

import java.sql.*;

public class DBConnect {
	
	private static Connection connz;

	public static Connection getDatabase() throws ClassNotFoundException, SQLException {

		// This creates new conn object when conn is closed or it is null
		if (connz == null || connz.isClosed()) {
			//db driver
			Class.forName("com.mysql.jdbc.Driver");
			//database link username and password
			connz = DriverManager.getConnection("jdbc:mysql://localhost:3306/personal", "root", "");
		}
		
		return connz;
	}

}
