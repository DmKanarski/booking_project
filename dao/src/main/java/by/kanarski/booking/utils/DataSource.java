package by.kanarski.booking.utils;

import by.kanarski.booking.constants.DaoMessage;
import by.kanarski.booking.constants.DatabaseKeys;
import by.kanarski.booking.managers.ResourceManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Represents connections pool based on c3p0 library
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see ComboPooledDataSource
 */

public class DataSource {
    private static DataSource datasource = null;
    private ComboPooledDataSource cpds = null;

    /**
     * Connections pool cashe with configuration
     */

    private DataSource() {
        cpds = new ComboPooledDataSource();
        try {
            ResourceBundle bundle = ResourceManager.DATABASE.get();
            cpds.setDriverClass(bundle.getString(DatabaseKeys.JDBC_DRIVER_PATH));
            cpds.setJdbcUrl(bundle.getString(DatabaseKeys.DATABASE_PATH));
            cpds.setUser(bundle.getString(DatabaseKeys.USER_NAME));
            cpds.setPassword(bundle.getString(DatabaseKeys.PASSWORD));
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(50);
            cpds.setMaxStatements(180);
            cpds.setInitialPoolSize(10);
            //Avoding memmory leaks on hot redeploy
            cpds.setContextClassLoaderSource("library");
            cpds.setPrivilegeSpawnedThreads(true);
        } catch (PropertyVetoException e) {
            BookingSystemLogger.getInstance().logError(getClass(), DaoMessage.WRONG_DATASOURCE_SETTINGS + e);
        }
    }

    public static synchronized DataSource getInstance() {
        if (datasource == null) {
            datasource = new DataSource();
        }
        return datasource;
    }

    /**
     * Gives one of connectons from pool
     * @return connection
     * @throws SQLException
     */

    // TODO: 27.10.2016 Разобраться с пулом, не отпускает соединения вообще никогда

    public Connection getConnection() throws SQLException {
//        javax.sql.PooledConnection pooledConnection = cpds.getConnectionPoolDataSource().getPooledConnection();
//        Connection connection = pooledConnection.getConnection();
        Connection connection = cpds.getConnection();
        return connection;
    }

    public void destroy() {
        cpds.close();
    }

}
