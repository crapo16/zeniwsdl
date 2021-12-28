package ar.com.zeni.db.wP;

import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import ar.com.zeni.common.ZeniContextServer;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ConnectionImplementationWordPress implements ConnectionPropertiesWordPress {

    static DataSource _dsWP = null;
    private static ConnectionPool pool;
    private String port;
    private String host;
    private String pass;
    private String user;
    private String driver;
    private String db;
    private String jndi_connection_name;
    private String jndi_context;
    private String jdb_prefix;
    private String separator;

    /**
     * Constructor
     */
    public ConnectionImplementationWordPress() {
        ZeniContextServer.getInstance().printInfoLog("init ConnectionImplementationWordPress");
        readProperties();
    }

    private void readProperties() {
        ZeniContextServer.getInstance().printInfoLog("readProperties ConnectionImplementationWordPress");
        jndi_context = ZeniContextServer.getInstance().getPropertyWP(JNDI_CONTEXT);
        jndi_connection_name = ZeniContextServer.getInstance().getPropertyWP(JNDI_CONNECTION_NAME);
        jdb_prefix = ZeniContextServer.getInstance().getPropertyWP(DB_JDBC_PREFIX);
        separator = ZeniContextServer.getInstance().getPropertyWP(DB_SEPARATOR);
        db = ZeniContextServer.getInstance().getPropertyWP(DB_NAME);
        driver = ZeniContextServer.getInstance().getPropertyWP(DB_DRIVER);
        user = ZeniContextServer.getInstance().getPropertyWP(DB_USER);
        pass = ZeniContextServer.getInstance().getPropertyWP(DB_PASS);
        host = ZeniContextServer.getInstance().getPropertyWP(DB_HOST);
        port = ZeniContextServer.getInstance().getPropertyWP(DB_PORT);

    }

    /**
     * db conn
     *
     * @return Connection
     */
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            if (_dsWP == null) {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup(jndi_context);
                _dsWP = (DataSource) envContext.lookup(jndi_connection_name);
            }
            conn = _dsWP.getConnection();
        } catch (Exception ex) {
            if (pool == null) {
                String msg = "<!-- MYSQL DS " + "\n" + "<Resource name=\"" + jndi_connection_name + "\" auth=\"Container\" type=\"javax.sql.DataSource\"" + "\n" + "maxActive=\"50\" maxIdle=\"5\" maxWait=\"10000\"" + "\n" + "username=\"root\" password=\"pass\" driverClassName=\"com.mysql.jdbc.Driver\"" + "\n" + "url=\"jdbc:mysql://sdb03.aperturasoftware.local:3306/quonweb?relaxAutoCommit=true\"/>" + "\n" + "-->" + "\n" + "<!-- ORACLE DS" + "\n" + "<Resource name=\"" + jndi_connection_name + "\" auth=\"Container\" type=\"javax.sql.DataSource\"" + "\n" + "maxActive=\"50\" maxIdle=\"5\" maxWait=\"10000\"" + "\n" + "username=\"root\" password=\"pass\" driverClassName=\"oracle.jdbc.pool.OracleDataSource\"" + "\n" + "url=\"jdbc:oracle:thin:@sdb03.aperturasoftware.local:1521:quonweb\"/>" + "\n" + "-->";
                ZeniContextServer.getInstance().printErrorLog("No se encuentra DS en tomcat server, ingrese en el context.xml del tomcat \n" + msg);
                ZeniContextServer.getInstance().printErrorLog(ex);
                ZeniContextServer.getInstance().printErrorLog("Comenzando la creacion del pool local.");
                String url = jdb_prefix + host + ":" + port + separator + db;
                ZeniContextServer.getInstance().printErrorLog("Conexion URL: " + url);
                try {
                    pool = new ConnectionPool(url, user, pass, driver, host, port, db);
                } catch (SQLException e1) {
                    ZeniContextServer.getInstance().printErrorLog("No se puede establecer el pool a la base de datos.");
                    throw e1;
                }
            }
            try {
                conn = pool.checkout();
            } catch (SQLException e2) {
                ZeniContextServer.getInstance().printErrorLog("No se puede obtener una conexion a la base de datos desde el pool.");
                throw e2;
            }
        }
        if (conn == null) {
            ZeniContextServer.getInstance().printErrorLog("No se puede establecer una conexion a la base de datos.");
            throw new SQLException("No se puede establecer una conexion a la base de datos.");
        }
        return conn;
    }
}

class ZeniPooledConnectionWP implements Connection {

    Connection c;
    private boolean isFree = true;
    private ConnectionPool pool;

    public ZeniPooledConnectionWP(Connection c, ConnectionPool connectionPool) {
        this.c = c;
        this.pool = connectionPool;
    }

    private void returnToPool() {
        this.pool.checkin(this);
        setFree(true);
    }

    public void setFree(boolean b) {
        isFree = b;
    }

    public boolean isFree() {
        return isFree;
    }

    public void close() throws SQLException {
        returnToPool();
    }

    public void clearWarnings() throws SQLException {
        c.clearWarnings();
    }

    public void commit() throws SQLException {
        c.commit();
    }

    public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
        return c.createArrayOf(arg0, arg1);
    }

    public Blob createBlob() throws SQLException {
        return c.createBlob();
    }

    public Clob createClob() throws SQLException {
        return c.createClob();
    }

    public NClob createNClob() throws SQLException {
        return c.createNClob();
    }

    public SQLXML createSQLXML() throws SQLException {
        return c.createSQLXML();
    }

    public Statement createStatement() throws SQLException {
        return c.createStatement();
    }

    public Statement createStatement(int arg0, int arg1, int arg2) throws SQLException {
        return c.createStatement(arg0, arg1, arg2);
    }

    public Statement createStatement(int arg0, int arg1) throws SQLException {
        return c.createStatement(arg0, arg1);
    }

    public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
        return c.createStruct(arg0, arg1);
    }

    public boolean getAutoCommit() throws SQLException {
        return c.getAutoCommit();
    }

    public String getCatalog() throws SQLException {
        return c.getCatalog();
    }

    public Properties getClientInfo() throws SQLException {
        return c.getClientInfo();
    }

    public String getClientInfo(String arg0) throws SQLException {
        return c.getClientInfo(arg0);
    }

    public int getHoldability() throws SQLException {
        return c.getHoldability();
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return c.getMetaData();
    }

    public int getTransactionIsolation() throws SQLException {
        return c.getTransactionIsolation();
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return c.getTypeMap();
    }

    public SQLWarning getWarnings() throws SQLException {
        return c.getWarnings();
    }

    public boolean isClosed() throws SQLException {
        return c.isClosed();
    }

    public boolean isReadOnly() throws SQLException {
        return c.isReadOnly();
    }

    public boolean isValid(int arg0) throws SQLException {
        return c.isValid(arg0);
    }

    public boolean isWrapperFor(Class<?> arg0) throws SQLException {
        return c.isWrapperFor(arg0);
    }

    public String nativeSQL(String arg0) throws SQLException {
        return c.nativeSQL(arg0);
    }

    public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException {
        return c.prepareCall(arg0, arg1, arg2, arg3);
    }

    public CallableStatement prepareCall(String arg0, int arg1, int arg2) throws SQLException {
        return c.prepareCall(arg0, arg1, arg2);
    }

    public CallableStatement prepareCall(String arg0) throws SQLException {
        return c.prepareCall(arg0);
    }

    public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException {
        return c.prepareStatement(arg0, arg1, arg2, arg3);
    }

    public PreparedStatement prepareStatement(String arg0, int arg1, int arg2) throws SQLException {
        return c.prepareStatement(arg0, arg1, arg2);
    }

    public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException {
        return c.prepareStatement(arg0, arg1);
    }

    public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException {
        return c.prepareStatement(arg0, arg1);
    }

    public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException {
        return c.prepareStatement(arg0, arg1);
    }

    public PreparedStatement prepareStatement(String arg0) throws SQLException {
        return c.prepareStatement(arg0);
    }

    public void releaseSavepoint(Savepoint arg0) throws SQLException {
        c.releaseSavepoint(arg0);
    }

    public void rollback() throws SQLException {
        c.rollback();
    }

    public void rollback(Savepoint arg0) throws SQLException {
        c.rollback(arg0);
    }

    public void setAutoCommit(boolean arg0) throws SQLException {
        c.setAutoCommit(arg0);
    }

    public void setCatalog(String arg0) throws SQLException {
        c.setCatalog(arg0);
    }

    public void setClientInfo(Properties arg0) throws SQLClientInfoException {
        c.setClientInfo(arg0);
    }

    public void setClientInfo(String arg0, String arg1) throws SQLClientInfoException {
        c.setClientInfo(arg0, arg1);
    }

    public void setHoldability(int arg0) throws SQLException {
        c.setHoldability(arg0);
    }

    public void setReadOnly(boolean arg0) throws SQLException {
        c.setReadOnly(arg0);
    }

    public Savepoint setSavepoint() throws SQLException {
        return c.setSavepoint();
    }

    public Savepoint setSavepoint(String arg0) throws SQLException {
        return c.setSavepoint(arg0);
    }

    public void setTransactionIsolation(int arg0) throws SQLException {
        c.setTransactionIsolation(arg0);
    }

    public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
        c.setTypeMap(arg0);
    }

    public <T> T unwrap(Class<T> arg0) throws SQLException {
        return c.unwrap(arg0);
    }
}

class ConnectionPool implements Runnable {

    private int m_InitialConnectionCount = 10;
    private Vector<Connection> m_AvailableConnections = new Vector<Connection>();
    private Vector<Connection> m_UsedConnections = new Vector<Connection>();
    private myDataSourceWP ds;
    private Object synchCheckOut = new Object();
    private Object synchCheckIn = new Object();
    private Object synchAdd = new Object();

    // Constructor
    public ConnectionPool(String urlString, String user, String passwd, String driver, String host, String port, String dbname) throws SQLException {
        makeDS(driver, urlString, user, passwd, host, port, dbname);
        for (int cnt = 0; cnt < m_InitialConnectionCount; cnt++) {
            // Add a new connection to the available list.
            m_AvailableConnections.addElement(getConnection());
        }
    }

    private void makeDS(String driver, String urlString, String user, String pass, String host, String port, String dbname) throws SQLException {
        if (ds == null) {
            ds = new myDataSourceWP(driver, urlString, user, pass, host, port, dbname);
        }
    }

    private Connection getConnection() throws SQLException {
        ZeniPooledConnectionWP rtn = new ZeniPooledConnectionWP(ds.getConnection(), this);
        return rtn;
    }

    public Connection checkout() throws SQLException {
        Connection newConnxn = null;
        synchronized (synchCheckOut) {
            if (!(getAvailableCount() > 0)) {
                try {
                    while (!(getAvailableCount() > 0)) {
                        synchronized (synchCheckIn) {
                            synchCheckIn.wait(500);
                        }
                    }
                    if (getAvailableCount() > 0) {
                        newConnxn = getAvailable();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                newConnxn = getAvailable();
            }
        }
        return newConnxn;
    }

    public void checkin(Connection c) {
        if (c != null) {
            addAvailable(c);
        }
        synchronized (synchCheckIn) {
            synchCheckIn.notifyAll();
        }
    }

    public int getAvailableCount() {
        synchronized (synchAdd) {
            return m_AvailableConnections.size();
        }
    }

    public void addAvailable(Connection c) {
        synchronized (synchAdd) {
            m_UsedConnections.removeElement(c);
            m_AvailableConnections.addElement(c);
        }
    }

    public Connection getAvailable() {
        synchronized (synchAdd) {
            synchronized (synchCheckOut) {
                Connection newConnxn = m_AvailableConnections.lastElement();
                m_UsedConnections.addElement(newConnxn);
                m_AvailableConnections.removeElement(newConnxn);
                return newConnxn;
            }
        }
    }

    public void run() {
        boolean continuar = true;
        try {
            while (continuar) {
                synchronized (this) {
                    while (m_AvailableConnections.size() > m_InitialConnectionCount) {
                        Connection c = (Connection) m_AvailableConnections.lastElement();
                        m_AvailableConnections.removeElement(c);
                        c.close();
                    }
                }
                continuar = false;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class myDataSourceWP implements DataSource {

        DataSource dataSource;

        public myDataSourceWP(String driver, String url, String user, String pass, String host, String port, String dbname) throws SQLException {
            if (driver.equalsIgnoreCase("ORACLE")) {
                dataSource = newOracleDS(user, pass, host, port, dbname);
            } else if (driver.equalsIgnoreCase("ORACLEL")) {
                dataSource = newOracleURLDS(url, user, pass);
            } else if (driver.equalsIgnoreCase("MYSQL")) {
                dataSource = newMySqlDS(user, pass, host, port, dbname);
            } else {
                throw new SQLException("There is no Driver for: '" + driver + "'");
            }
        }

        public synchronized PrintWriter getLogWriter() throws SQLException {
            return dataSource.getLogWriter();
        }

        public synchronized int getLoginTimeout() throws SQLException {
            return dataSource.getLoginTimeout();
        }

        public synchronized void setLogWriter(PrintWriter arg0) throws SQLException {
            dataSource.setLogWriter(arg0);
        }

        public synchronized void setLoginTimeout(int seconds) throws SQLException {
            dataSource.setLoginTimeout(seconds);
        }

        public Connection getConnection() throws SQLException {
            return dataSource.getConnection();
        }

        public Connection getConnection(String username, String password) throws SQLException {
            return dataSource.getConnection(username, password);
        }

        private DataSource newOracleDS(String user, String pass, String host, String port, String dbname) throws SQLException {
            OracleDataSource dsora = new OracleDataSource();
            dsora.setDriverType("thin");
            dsora.setServerName(host);
            dsora.setNetworkProtocol("tcp");
            dsora.setDatabaseName(dbname);
            dsora.setPortNumber(Integer.parseInt(port));
            dsora.setUser(user);
            dsora.setPassword(pass);
            return dsora;
        }

        private DataSource newOracleURLDS(String url, String user, String pass) throws SQLException {
            OracleDataSource dsora = new OracleDataSource();
            dsora.setURL(url);
            dsora.setUser(user);
            dsora.setPassword(pass);
            return dsora;
        }

        private DataSource newMySqlDS(String user, String pass, String host, String port, String dbname) throws SQLException {
            MysqlDataSource dsmysql = new MysqlDataSource();
            dsmysql.setServerName(host);
            dsmysql.setPortNumber(Integer.parseInt(port));
            dsmysql.setDatabaseName(dbname);
            dsmysql.setUser(user);
            dsmysql.setPassword(pass);

            return dsmysql;
        }

        @Override
        public boolean isWrapperFor(Class<?> arg0) throws SQLException {
            return dataSource.isWrapperFor(arg0);
        }

        @Override
        public <T> T unwrap(Class<T> arg0) throws SQLException {
            return dataSource.unwrap(arg0);
        }

    }
}
