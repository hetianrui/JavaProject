package common;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javax.sql.DataSource;
import java.sql.*;

public class DBUtil {
    private final static String URL="jdbc:mysql://127.0.0.1:3306/java_OJ?characterEncoding=utf8&userSSL=true";
    private final static String USER="root";
    private final static String PASSWORD="123456";
    private static volatile DataSource dataSource=null;

    private DBUtil(){
    }
    public static DataSource getDataSource(){
        if(dataSource==null){
            synchronized (DBUtil.class){
                if(dataSource==null){
                    dataSource=new MysqlDataSource();
                    ((MysqlDataSource) dataSource).setURL(URL);
                    ((MysqlDataSource) dataSource).setUser(USER);
                    ((MysqlDataSource) dataSource).setPassword(PASSWORD);
                    return dataSource;
                }
            }
        }
        return dataSource;
    }
    public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void close(Connection connection, PreparedStatement ps, ResultSet rs){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
