/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lap10467
 */
public class Connecter {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String DataBaseName = "hotel";
    static String DB_URL;
    static String USER = "root";
    static String PASSWORD = "root";
    private static Connection connection = null;

    static {
        Switch_to_Main_Database();
    }

    public static Connection Connect() {

        DB_URL = "jdbc:mysql://127.0.0.1:3306/"
                + DataBaseName
                + "?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connecter.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(Connecter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connection;
    }

    public static void Switch_to_Test_Database() {
        DataBaseName = "hotel";
    }

    public static void Switch_to_Main_Database() {
        DataBaseName = "hotel";
    }

    public static void CloseConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connecter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
