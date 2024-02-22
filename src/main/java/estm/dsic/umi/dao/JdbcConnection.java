package estm.dsic.umi.dao;



import javax.json.JsonReader;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static String DB_DRIVER;
    private static String DB_Name;
    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;
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
            // TODO: Read JSON from file using JSON-P

            try {
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (SQLException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        return connection;
    }
}
