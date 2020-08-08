/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import java.sql.*;
import modelo.Licencia;
/**
 *
 * @author Fray
 */
public class ServicioTablaLicencia {
    private Connection  conn;
    private final String SQL_INSERT = "insert into tbllicencia(liccodigo,lictipo,licvencimiento)"
            + " values(?,?,?)";
    private final String SQL_UPDATE = "update tbllicencia set lictipo=?, licvencimiento=? where "
            + "liccodigo=?";
    private final String SQL_SELECT = "select liccodigo,lictipo,licvencimiento from "
            + "tbllicencia where liccodigo=?";
    private final String SQL_DELETE = "delete from tbllicencia where liccodigo=?";
    
    
    public ServicioTablaLicencia() {
        try {
            conn = Conexion.getConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int insert(Licencia licencia) throws SQLException {
        int rows = 0;
        PreparedStatement stmt = null;
        if (licencia.getCodigo() != null && licencia.getTipo()!=null && licencia.getVencimiento() !=
                null) {
            try {
                stmt = conn.prepareStatement(SQL_INSERT);
                int index = 1;
                stmt.setString(index++, licencia.getCodigo());
                stmt.setString(index++, licencia.getTipo());
                stmt.setDate(index, licencia.getVencimiento());
                rows = stmt.executeUpdate();
                System.out.println("Registros insertados: " + rows);
            } finally {
                if (stmt != null) {
                    Conexion.close(stmt);
                }
                if (conn != null) {
                    Conexion.close(conn);
                }
            }
        }
        return rows;
    }
    
    public int update(Licencia licencia) throws SQLException{
        int rows = 0;
        PreparedStatement stmt = null;
        if (licencia.getCodigo() != null && licencia.getTipo()!=null && licencia.getVencimiento() !=
                null) {
            try {
                stmt = conn.prepareStatement(SQL_UPDATE);
                int index = 1;
                stmt.setString(index++, licencia.getTipo());
                stmt.setDate(index++, licencia.getVencimiento());
                stmt.setString(index, licencia.getCodigo());
                rows = stmt.executeUpdate();
                System.out.println("Registros actualizados: " + rows);
            } finally {
                if (stmt != null) {
                    Conexion.close(stmt);
                }
                if (conn != null) {
                    Conexion.close(conn);
                }
            }
        }
        return rows;
    }
    
    public Licencia consultar(String codigo) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Licencia licencia = new Licencia();
        if (codigo != null) {
            try {
                stmt = conn.prepareStatement(SQL_SELECT);
                stmt.setString(1, codigo);
                rs = stmt.executeQuery();
                while(rs.next()){
                    String codigoTemp = rs.getString(1);
                    String tipoTemp = rs.getString(2);
                    Date vencimientoTemp = rs.getDate(3);
                    licencia = new Licencia(codigoTemp, tipoTemp, vencimientoTemp);
                }
                
            } finally {
                if(rs != null) {
                    Conexion.close(rs);
                }
                if (stmt != null) {
                    Conexion.close(stmt);
                }
                if (conn != null) {
                    Conexion.close(conn);
                }
            }
        }
        return licencia;
    }
    
    public int delete(String codigo) throws SQLException{
        int rows = 0;
        PreparedStatement stmt = null;
        if (codigo != null) {
            try {
                stmt = conn.prepareStatement(SQL_DELETE);
                stmt.setString(1, codigo);
                rows = stmt.executeUpdate();
                System.out.println("Registros eliminados: " + rows);
            } finally {
                if (stmt != null) {
                    Conexion.close(stmt);
                }
                if (conn != null) {
                    Conexion.close(conn);
                }
            }
        }
        return rows;
    }
}
