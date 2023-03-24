package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
//  private static final String driver = "org.h2.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/first";
//  private static final String url = "jdbc:h2:~/first";
    private static final String login = "Kostya";
    private static final String password = "Kostya_0306_911";

    public static Connection getConnection() {
        Connection connect = null;
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.out.println("Connection failed");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connect;
    }
}
