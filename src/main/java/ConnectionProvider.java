import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private static Connection connection;

    private ConnectionProvider(){}


    public static Connection setConnection() throws SQLException {
        if (connection==null || connection.isClosed()){
            connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","6642");
        }
        return connection;
    }

}
