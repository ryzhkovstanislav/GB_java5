package company.ryzhkov.common.server;

import java.sql.*;

public class DB {
    private static DB ourInstance = new DB();
    private static Connection connection;
    private static Statement statement;
    private boolean auth;

    public static DB getInstance() {
        return ourInstance;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    private DB() {
    }

    public void verifyUsernameAndPassword(String username, String password) {
        try {
            connect();
            String q = String.format(
                    "SELECT * from users WHERE username = '%s' and password = '%s'",
                    username,
                    password
            );
            ResultSet rs = statement.executeQuery(q);
            while (rs.next()) {
                this.auth = true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Exception");
        } finally {
            disconnect();
        }
    }

    private void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/stanislavryzhkov/Documents/Джава/JAVA углубленное/project/1/filebag/server/src/main/resources/base.db");
        statement = connection.createStatement();
    }

    private void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
