package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Connector {

    private final static String JDBC     = "postgresql";
    private final static String HOST     = "ec2-54-247-92-185.eu-west-1.compute.amazonaws.com";
    private final static String PORT     = "5432";
    private final static String DATABASE = "df0nkq6bthnr4h";
    private final static String USER     = "gqwrbquxnetqam";
    private final static String PASSWORD = "e38e84444b98048d6fd2e214b1214d99d166823849206d570c9d1e837b0d4584";
    private final static String SSL_FOOTER = "?sslmode=require";

    private static Connection connection;

    protected Connection getConnection() {
        if (connection == null) {
            String url = "jdbc:" + JDBC + "://" + HOST + ":" + PORT + "/" + DATABASE + SSL_FOOTER;
            try {
                connection = DriverManager.getConnection(url, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
