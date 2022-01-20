import connection.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Connection connection=ConnectionProvider.setConnection();
        String create="CREATE TABLE IF NOT EXISTS newTable(" +
                "names varchar (20))";
        PreparedStatement preparedStatement= connection.prepareStatement(create);
        preparedStatement.execute();
        preparedStatement.close();

    }
}
