/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_access;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Victo
 */
public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;

    private ConnectionPool() {

        try {

            InitialContext ic = new InitialContext(); // provides access to context.xml (retrives the values)

            // this data source is coming from context.xml resource 
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/userdb"); // looking up component (comp) enviroment (env) variable is jdbc/app

        } catch (NamingException exception) {
            System.out.println(exception);
        }
    }

    public static synchronized ConnectionPool getInstance() {

        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;

    }

    /**
     * 
     * @return a single connection from the pool
     */
    public Connection getConnection() {

        try {
            return dataSource.getConnection(); // get a connection from pool
        } catch (SQLException exception) {

            System.out.println(exception);
            return null;
        }
    }

    /**
     *  closes or frees the connection, this object will go back in the pool in a sleep state 
     * @param connection object 
     */
    public void freeConnection(Connection connection) {

        try {
            connection.close(); 
        } catch (SQLException exception) {

            System.out.println(exception);
      
        }
    }

}
