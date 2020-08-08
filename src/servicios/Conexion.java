/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Fray
 */
public class Conexion {
    private static final String DRIVER_JDBC = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost/proyecto?useSSL=false";
    private static String user = "root";
    private static String pass = "1234";
    private static Driver driver = null;
    public static synchronized Connection getConexion() throws SQLException{
        if (driver == null) {
            try {
            Class jdbcDriverClass = Class.forName(DRIVER_JDBC);
            driver = (Driver) jdbcDriverClass.newInstance();
            DriverManager.registerDriver(driver);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos", "Error de conexion", JOptionPane.WARNING_MESSAGE);
                System.out.println("Error al cargar driver");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(url,user,pass);
    }
    
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqlex) {
                sqlex.printStackTrace();
        }
        
    }
    
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
    
    public static void close(Connection conn) {
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }
}
