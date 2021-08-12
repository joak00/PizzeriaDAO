package classes;

import java.sql.Connection; 
import java.sql.DriverManager;

public class ConnectionDB {
  
	private static Connection connection;
    private static String userName = System.getenv().get("userMySQL");
    private static String pass = System.getenv().get("passwordMySQL");
    private static String dbName = "Pizzeria";
    private static String url = System.getenv().get("urlMySQL") + dbName;

    public static Connection getConnection() {
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection(url, userName, pass);
        } catch (Exception e) {
            System.out.println("Error al obtener la conexion");
        }
        return connection;
    }

}
