package estm.dsic.umi.dao;



import javax.json.JsonReader;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_Name = "tp2_jee";
    private static String DB_URL = "jdbc:mysql://localhost:3306/" + DB_Name + "?useSSL=false";
    private static String DB_USERNAME = "user1";
    private static String DB_PASSWORD = "user1";
    private static Connection connection;

    private JdbcConnection(
            String DB_DRIVER,
            String DB_Name,
            String DB_URL,
            String DB_USERNAME,
            String DB_PASSWORD
    ) {
        this.DB_DRIVER = DB_DRIVER;
        this.DB_Name = DB_Name;
        this.DB_URL = DB_URL;
        this.DB_USERNAME = DB_USERNAME;
        this.DB_PASSWORD = DB_PASSWORD;
    }

    public static Connection getInstance() {
        if (connection == null) {
            try {
                Context context = new InitialContext();
                DataSource dataSource = (DataSource) context.lookup("jdbc/myDB");
                connection = dataSource.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
