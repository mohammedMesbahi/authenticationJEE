package estm.dsic.umi.dao;


import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class JdbcConnection {
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
        return connection;
    }

}
