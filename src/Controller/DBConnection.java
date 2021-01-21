/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Scoobydo
 */
public class DBConnection {

//    public static String hostName = "DESKTOP-BLC1J0U";
//    public static String sqlInstanceName = "SQLEXPRESS";
//    public static String database = "PLANE_TICKET2";
//    public static String userName = "sa";
//    public static String password = "1234";
//    public static String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" + ";instance=" + sqlInstanceName + ";databaseName=" + database;
//
//    public static Connection open() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            Connection cnn = DriverManager.getConnection(connectionURL, userName, password);
//            System.out.println("thanh cong!");
//            return cnn;
//        } catch (ClassNotFoundException ex) {
//            System.out.println("That bai");
//            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            System.out.println("That baiiii");
//            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
//    public static void close(ResultSet rs, PreparedStatement ps, CallableStatement cstm, Connection cnn) {
//        try {
//            if (rs != null && !rs.isClosed()) {
//                rs.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            if (ps != null && !ps.isClosed()) {
//                ps.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            if (cstm != null && !cstm.isClosed()) {
//                cstm.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        try {
//            if (cnn != null && !cnn.isClosed()) {
//                cnn.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public static void main(String[] args) {
//
//        DBConnection con = null;
//        con.open(); 
//    }
 public static Connection connection;
    public static final String hostName = "DESKTOP-NU1J09R";
    public static final String sqlInstanceName = "SQLEXPRESS";
    public static final String database = "PLANE_TICKET2";

    public static void dbConnect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://Localhost:1433;databaseName=PLANE_TICKET2";
            String Username = "sa";
            String Password = "123";
            connection = DriverManager.getConnection(url, Username, Password);
            System.out.println("\nconnected");
            System.out.println(database);
        } catch (Exception exception) {
            System.out.print(exception);
            System.out.print("can't connect database! ");
        }

    }
    public static int dbExcuteUpdate(String sql) throws SQLException{
        Statement stmt = null;
        int i = 0;
        try {
            dbConnect();
            stmt = connection.createStatement();
              i = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.print(e);
            System.out.print("Can't execute update!");
        }
       dbDisconnect();
       return i;
        }
    public static void dbDisconnect() throws SQLException{
       
        try {
            if(connection != null )
            {
                
                connection.close();
            }
            
        } catch (Exception e) {
                throw e;
        }

        
    }
    public static ResultSet dbExcuteQuery(String sql) throws SQLException{
        Statement stmt = null;
        ResultSet rs = null;
        CachedRowSetImpl crs = null;
        try {
            dbConnect();
            stmt = connection.createStatement();
              rs =  stmt.executeQuery(sql);
              crs = new CachedRowSetImpl();
              crs.populate(rs);
        } catch (SQLException e) {
            System.out.print(e);
            System.out.print("Can't execute!");
        }
       dbDisconnect();
       return crs;
        }
}
