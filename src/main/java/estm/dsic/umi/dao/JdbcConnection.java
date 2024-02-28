package estm.dsic.umi.dao;


import jakarta.annotation.Resource;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class JdbcConnection {
    @Resource(lookup = "jdbc/tp2_jee")
    DataSource myDB;
    static DataSource ds;
    static Connection connection;


    public static Connection getConnection() {
        if (ds == null) {
            try {
                InitialContext ctx = new InitialContext();
                ds = (DataSource) ctx.lookup("jdbc/tp2_jee");
                connection = ds.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // TODO: debug . break point 2 here
        return connection;
    }

}
