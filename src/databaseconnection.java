// package config;  

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseconnection {
  public static Connection getConnection() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/libraryapp";
    String username = "root";
    String password = "";
    Connection connection = DriverManager.getConnection(url, username, password);
    return connection;
  }
}
