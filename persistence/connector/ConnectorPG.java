package persistence.connector;

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
    private final static String HOST = "ec2-54-247-82-87.eu-west-1.compute.amazonaws.com";
    private final static String PORT = "5432";
    private final static String DATABASE = "dfupganb0rggik";
    private final static String USER = "icgvjargopicqc";
    private final static String PASSWORD = "be1b94bc52f730f74a2bf306d68d8fb3dc94f755fca93c98635705020966a1fb";
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
