package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcontext {
	private final String serverName = "localhost";
	private final String dbName = "shoppingdb";
	private final String portNumber = "3306";
	private final String instance = "";
	private final String userID = "root";
	private final String password = "123456";
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://"+ serverName +":"+ portNumber +"/"+ dbName;
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(url, userID, password);
	}
}
