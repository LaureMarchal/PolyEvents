package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A connector to a remote PostgreSQL database hosted in an Heroku App
 */
public class ConnectorPG extends Connector {

    /**
     * Config to connect to the database
     * Don't show in a public repository
     */
    private final static String JDBC = "postgresql";
    private final static String HOST = "ec2-54-247-92-185.eu-west-1.compute.amazonaws.com";
    private final static String PORT = "5432";
    private final static String DATABASE = "df0nkq6bthnr4h";
    private final static String USER = "gqwrbquxnetqam";
    private final static String PASSWORD = "e38e84444b98048d6fd2e214b1214d99d166823849206d570c9d1e837b0d4584";
    private final static String SSL_FOOTER = "?sslmode=require";

    @Override
    public Connection getConnection() {
        String url = "jdbc:" + JDBC + "://" + HOST + ":" + PORT + "/" + DATABASE + SSL_FOOTER;
        try {
            return DriverManager.getConnection(url, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Please check if you have the jdbc postgresql driver .jar");
            return null;
        }
    }

}
