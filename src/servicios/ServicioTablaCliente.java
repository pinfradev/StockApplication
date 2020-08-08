/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import java.sql.*;
import modelo.Cliente;

/**
 *
 * @author Fray
 */
public class ServicioTablaCliente {
    private Connection  conn;
    private final String SQL_INSERT = "insert into tblcliente(clinumeroidentificacion,clinombre,cliapellido,clitelefono)"
            + " values(?,?,?,?)";
    private final String SQL_UPDATE = "update tblcliente set clinombre=?, cliapellido=?, clitelefono=? where "
            + "clinumeroidentificacion=?";
    private final String SQL_SELECT = "select clinumeroidentificacion,clinombre,cliapellido,clitelefono from "
            + "tblcliente where clinumeroidentificacion=?";
    private final String SQL_DELETE = "delete from tblcliente where clinumeroidentificacion=?";
    public ServicioTablaCliente() {
        try {
            conn = Conexion.getConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int insert(Cliente cliente) throws SQLException {
        int rows = 0;
        PreparedStatement stmt = null;
        if (cliente.getCedula()!= 0 && cliente.getNombre()!=null &&cliente.getApellido() !=
                null && cliente.getTelefono() != 0) {
            try {
                stmt = conn.prepareStatement(SQL_INSERT);
                int index = 1;
                stmt.setLong(index++, cliente.getCedula());
                stmt.setString(index++, cliente.getNombre());
                stmt.setString(index++, cliente.getApellido());
                stmt.setInt(index, cliente.getTelefono());
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
    
    public int update(Cliente cliente) throws SQLException{
        int rows = 0;
        PreparedStatement stmt = null;
        if (cliente.getCedula()!= 0 && cliente.getNombre()!=null &&cliente.getApellido() !=
                null && cliente.getTelefono() != 0) {
            try {
                stmt = conn.prepareStatement(SQL_UPDATE);
                int index = 1;
                stmt.setString(index++, cliente.getNombre());
                stmt.setString(index++, cliente.getApellido());
                stmt.setInt(index++, cliente.getTelefono());
                stmt.setLong(index, cliente.getCedula());
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
    
    public Cliente consultar(long cedula) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();
        if (cedula != 0) {
            try {
                stmt = conn.prepareStatement(SQL_SELECT);
                stmt.setLong(1, cedula);
                rs = stmt.executeQuery();
                while(rs.next()){
                    long cedulaTemp = rs.getLong(1);
                    String nombreTemp = rs.getString(2);
                    String apellidoTemp = rs.getString(3);
                    int telefonoTemp = rs.getInt(4);
                    cliente = new Cliente(cedulaTemp, nombreTemp, apellidoTemp, telefonoTemp);
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
        return cliente;
    }
    
    public int delete(long cedula) throws SQLException{
        int rows = 0;
        PreparedStatement stmt = null;
        if (cedula != 0) {
            try {
                stmt = conn.prepareStatement(SQL_DELETE);
                stmt.setLong(1, cedula);
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
