package persistence.connector;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Written by Théo Gauchoux
 * A connector to a remote PostgreSQL database hosted in an Heroku App
 */
public class ConnectorPG extends Connector {

    /**
     * Config to connect to the database
     */
    private String jdbc;
    private String host;
    private String port;
    private String database;
    private String user;
    private String password;
    private String sslFooter;

    @Override
    public Connection getConnection() {
        if (jdbc == null) {
            loadConfiguration();
        }
        String url = "jdbc:" + jdbc + "://" + host + ":" + port + "/" + database + sslFooter;
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Please check if you have the jdbc postgresql driver .jar");
            return null;
        }
    }

    /**
     * Load configuration from property file according to the DB mode (prod or test)
     */
    public void loadConfiguration() {
        Properties prop = new Properties();
        String propFileName = ".env_" + this.dbmode.name().toLowerCase();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try {
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                System.err.println("property file '" + propFileName + "' not found in the classpath");
            }
            this.jdbc = prop.getProperty("jdbc");
            this.host = prop.getProperty("host");
            this.port = prop.getProperty("port");
            this.database = prop.getProperty("database");
            this.user = prop.getProperty("user");
            this.password = prop.getProperty("password");
            this.sslFooter = prop.getProperty("sslFooter");
        } catch (Exception e) {
            System.err.println("can't read property file");
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
