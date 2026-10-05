package OmniDocs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection getConnection() throws Exception {
        // Reads from Railway variables automatically. If not found, uses hardcoded fallback.
        String url = System.getenv("MYSQL_URL") != null ? System.getenv("MYSQL_URL") : "jdbc:mysql://mysql.railway.internal:3306/omnidocs";
        String user = System.getenv("MYSQLUSER") != null ? System.getenv("MYSQLUSER") : "root";
        String password = System.getenv("MYSQLPASSWORD") != null ? System.getenv("MYSQLPASSWORD") : "Pragati2008";

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
