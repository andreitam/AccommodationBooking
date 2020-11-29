package andreitam.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionData {
    private static ConnectionData instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/booking";
    //user and password set as enviorment variable
    private String username = System.getenv("user");
    private String password = System.getenv("pass");

    private ConnectionData() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionData getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionData();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnectionData();
        }

        return instance;
    }
}
