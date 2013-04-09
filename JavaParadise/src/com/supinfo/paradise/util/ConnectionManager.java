package com.supinfo.paradise.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/paradise";
    private static final String USER = "paradise";
    private static final String PASSWORD = "supinfo";
    private static Connection CONNECTION;

    public static Connection getConnection() {
        if (CONNECTION == null) {
            try {
                CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD);
                CONNECTION.setAutoCommit(false);
            } catch (SQLException e) {
                throw new RuntimeException("Unable to create connection with database (url: " + URL + ", user: " + USER +
                        ", password: " + PASSWORD + ")", e);
            }
        }
        return CONNECTION;
    }

    public static void close() {
        try {
            CONNECTION.close();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to close connection", e);
        }
    }
}
