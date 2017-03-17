package persistence.connector;

import java.sql.Connection;

/**
 * An abstract connector to a database
 */
public abstract class Connector {

    /**
     * Singleton instance
     */
    private static Connector instance;

    /**
     * Empty constructor for singleton
     */
    protected Connector() {
    }

    /**
     * Get the unique instance of the Connector
     *
     * @return An instance of Connector
     */
    public static Connector getInstance() {
        if (instance == null) {
            instance = new ConnectorPG();
        }
        return instance;
    }

    /**
     * Get a connection to a database
     *
     * @return A connection to a database
     */
    public abstract Connection getConnection();

}
