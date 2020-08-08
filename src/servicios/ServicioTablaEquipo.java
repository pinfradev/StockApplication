/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;
import java.sql.*;
import modelo.Cliente;
import modelo.Equipo;

public class ServicioTablaEquipo {
    private Connection  conn;
    private final String SQL_INSERT = "insert into tblequipo(equserial,equtipo,equcapacidadmemoria,equmarca,equubicacion,"
            + "equcondicion,equifechaingreso,tbllicencia_liccodigo_fk,tblcliente_clinumeroidentificacion_fk)"
            + " values(?,?,?,?,?,?,?,?,?)";
    private final String SQL_UPDATE = "update tblequipo set equtipo=?,equcapacidadmemoria=?,equmarca=?,"
            + "equubicacion=?, equcondicion=?,equifechaingreso=?,tbllicencia_liccodigo_fk=?,"
            + "tblcliente_clinumeroidentificacion_fk=? where equserial=?";
    private final String SQL_SELECT = "select equserial,equtipo,equcapacidadmemoria,equmarca,equubicacion,"
            + "equcondicion,equifechaingreso,tbllicencia_liccodigo_fk,tblcliente_clinumeroidentificacion_fk, clinombre, cliapellido,"
            + "clitelefono from "
            + "tblequipo a join tblcliente b on a.tblcliente_clinumeroidentificacion_fk = b.clinumeroidentificacion where a.equserial=?";
    private final String SQL_DELETE = "delete from tblequipo where equserial=?";
    public ServicioTablaEquipo() {
        try {
            conn = Conexion.getConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   
    public int insert(Equipo equipo) throws SQLException {
        int rows = 0;
        PreparedStatement stmt = null;
        if (!equipo.getSerial().equals("") && !equipo.getLicencia_LicCodigo().equals("") && equipo.getCliente() !=
                null && equipo.getFechaIngreso() != null) {
            try {
                stmt = conn.prepareStatement(SQL_INSERT);
                int index = 1;
                stmt.setString(index++, equipo.getSerial());
                stmt.setString(index++, equipo.getTipo());
                stmt.setDouble(index++, equipo.getMemoria());
                stmt.setString(index++, equipo.getMarca());
                stmt.setString(index++, equipo.getUbicacion());
                stmt.setString(index++, equipo.getCondicion());
                stmt.setDate(index++, equipo.getFechaIngreso());
                stmt.setString(index++, equipo.getLicencia_LicCodigo());
                stmt.setLong(index, equipo.getCliente_ident());
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
    
    public int update(Equipo equipo) throws SQLException{
        int rows = 0;
        PreparedStatement stmt = null;
        if (!equipo.getSerial().equals("") && !equipo.getLicencia_LicCodigo().equals("") && equipo.getCliente() !=
                null && equipo.getFechaIngreso() != null) {
            try {
                stmt = conn.prepareStatement(SQL_UPDATE);
                int index = 1;
                stmt.setString(index++, equipo.getTipo());
                stmt.setDouble(index++, equipo.getMemoria());
                stmt.setString(index++, equipo.getMarca());
                stmt.setString(index++, equipo.getUbicacion());
                stmt.setString(index++, equipo.getCondicion());
                stmt.setDate(index++, equipo.getFechaIngreso());
                stmt.setString(index++, equipo.getLicencia_LicCodigo());
                stmt.setLong(index++, equipo.getCliente_ident());
                stmt.setString(index, equipo.getSerial());
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
    
    public Equipo consultar(String serial) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Equipo equipo = new Equipo();
        if (serial != null) {
            try {
                stmt = conn.prepareStatement(SQL_SELECT);
                stmt.setString(1, serial);
                rs = stmt.executeQuery();
                while(rs.next()){
                    String serialTemp = rs.getString(1);
                    String tipoTemp = rs.getString(2);
                    double memoriaTemp = rs.getDouble(3);
                    String marcaTemp = rs.getString(4);
                    String ubicacionTemp  = rs.getString(5);
                    String condicionTemp = rs.getString(6);
                    Date fechaTemp = rs.getDate(7);
                    String codigoTemp = rs.getString(8);
                    long cedulaTemp = rs.getLong(9);
                    String nombreTemp = rs.getString(10);
                    String apellidoTemp = rs.getString(11);
                    int telfTemp = rs.getInt(12);
                    equipo = new Equipo(serialTemp, codigoTemp, new Cliente(cedulaTemp, nombreTemp, apellidoTemp, telfTemp), fechaTemp);
                    equipo.setCliente_ident(cedulaTemp);
                    equipo.setMemoria(memoriaTemp);
                    equipo.setTipo(tipoTemp);
                    equipo.setMarca(marcaTemp);
                    equipo.setUbicacion(ubicacionTemp);
                    equipo.setCondicion(condicionTemp);
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
        return equipo;
    }
    
    public int delete(String serial) throws SQLException{
        int rows = 0;
        PreparedStatement stmt = null;
        if (serial != null) {
            try {
                stmt = conn.prepareStatement(SQL_DELETE);
                stmt.setString(1, serial);
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


