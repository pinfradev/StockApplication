/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vista.equipos.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.*;
import servicios.*;
import vista.FormularioPrincipal;
/**
 *
 * @author Fray
 */
public class ControladorEquipos implements ActionListener{
    private Control control;
    private InsertarEquipoForm insertarEquipoForm;
    private ActualizarEquipoForm actualizarEquipoForm;
    private ConsultarEquipoForm consultarEquipoForm;
    private EliminarEquipoForm eliminarEquipoForm;
    public ControladorEquipos(Control controlTemp) {
        this.control = controlTemp;
        insertarEquipoForm  = new InsertarEquipoForm();
        actualizarEquipoForm = new ActualizarEquipoForm();
        consultarEquipoForm = new ConsultarEquipoForm();
        eliminarEquipoForm = new EliminarEquipoForm();
        control.formularioEquipos.getMenuBtn().addActionListener(this);
        control.formularioEquipos.getInsertarBtn().addActionListener(this);
        control.formularioEquipos.getUpdateBtn().addActionListener(this);
        control.formularioEquipos.getSelectBtn().addActionListener(this);
        control.formularioEquipos.getDeleteBtn().addActionListener(this);
        insertarEquipoForm.getRegistrarBtn().addActionListener(this);
        insertarEquipoForm.getVolverBtn().addActionListener(this);
        actualizarEquipoForm.getActualizarBtn().addActionListener(this);
        actualizarEquipoForm.getVolverBtn().addActionListener(this);
        consultarEquipoForm.getConsultarBtn().addActionListener(this);
        consultarEquipoForm.getVolverBtn().addActionListener(this);
        eliminarEquipoForm.getEliminarBtn().addActionListener(this);
        eliminarEquipoForm.getVolverBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == control.formularioEquipos.getInsertarBtn()) {
            control.formularioEquipos.setVisible(false);
            if (insertarEquipoForm== null) {
                insertarEquipoForm = new InsertarEquipoForm();
                insertarEquipoForm.setVisible(true);
            } else {
                insertarEquipoForm.setVisible(true);
            }
            
        }
        if(e.getSource() == control.formularioEquipos.getUpdateBtn()) {
            control.formularioEquipos.setVisible(false);
            if (actualizarEquipoForm == null) {
                actualizarEquipoForm = new ActualizarEquipoForm();
                actualizarEquipoForm.setVisible(true);
            } else {
                actualizarEquipoForm.setVisible(true);
            }
            
        }
        if(e.getSource() == control.formularioEquipos.getSelectBtn()) {
            control.formularioEquipos.setVisible(false);
            if (consultarEquipoForm == null) {
                consultarEquipoForm = new ConsultarEquipoForm();
                consultarEquipoForm.setVisible(true);
            } else {
                consultarEquipoForm.setVisible(true);
            }
            consultarEquipoForm.getSerialLbl().setText("");
            consultarEquipoForm.getLicenciaLbl().setText("");
            consultarEquipoForm.getTipoLbl().setText("");
            consultarEquipoForm.getMemoriaLbl().setText("");
            consultarEquipoForm.getMarcaLbl().setText("");
            consultarEquipoForm.getUbicacionLbl().setText("");
            consultarEquipoForm.getCondicionLbl().setText("");
            consultarEquipoForm.getFechaIngesoLbl().setText("");
            consultarEquipoForm.getNombreClienteLbl().setText("");
            consultarEquipoForm.getApellidoCLienteLbl().setText("");
            consultarEquipoForm.getCedulaClienteLbl().setText("");;
            consultarEquipoForm.getTelefonoClienteLbl().setText("");
        }
        if(e.getSource() == control.formularioEquipos.getDeleteBtn()) {
            control.formularioEquipos.setVisible(false);
            if (eliminarEquipoForm == null) {
                eliminarEquipoForm = new EliminarEquipoForm();
                eliminarEquipoForm.setVisible(true);
            } else {
                eliminarEquipoForm.setVisible(true);
            }
            
        }
        //eventos insertar equipos
        if (e.getSource() == insertarEquipoForm.getRegistrarBtn()) {
            String serial = "";
            String licencia = "";
            long cedulaCliente = 0;
            String tipo = "";
            double memoria = 0;
            String marca = "";
            String ubicacion = "";
            String condicion = "";
            String fechaString = null;
            String dia = null;
            String year = null;
            String mes = null;
            Date ingreso = null;
            Cliente cliente = new Cliente();
            Licencia licenciaConsultar = null;
            
            if(insertarEquipoForm.getSerial().getText().length() <= 20) {
                serial = insertarEquipoForm.getSerial().getText();  
            } else {
                JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "Maximo 20 caracteres en serial", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if(insertarEquipoForm.getCodigoLicencia().getText().length() <= 20) {
                licencia = insertarEquipoForm.getCodigoLicencia().getText();  
            } else {
                JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "Maximo 20 caracteres en la licencia", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
        
        
            if(insertarEquipoForm.getCedulaCliente().getText().length() <= 20) {
                try {
                    cedulaCliente = Long.parseLong(insertarEquipoForm.getCedulaCliente().getText());  
                } catch (NumberFormatException nex) {
                    JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "Debe ingresar solo numeros en cedula", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "Maximo 20 numeros en cedula", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(insertarEquipoForm.getTipoEquipo().getText().length() <= 20) {
                tipo = insertarEquipoForm.getTipoEquipo().getText();
            } else {
                JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "Maximo 20 caracteres en el tipo", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
                
            try {
                memoria = Double.parseDouble(insertarEquipoForm.getMemoria().getText()); 
            } catch (NumberFormatException nex) {
                JOptionPane.showMessageDialog(insertarEquipoForm, "Debe ingresar solo numeros en la memoria", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
             }
            if(insertarEquipoForm.getMarca().getText().length() <= 20) {
                marca = insertarEquipoForm.getMarca().getText();
            } else {
                JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "Maximo 15 caracteres en la marca", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(insertarEquipoForm.getUbicacion().getText().length() <= 20) {
                ubicacion = insertarEquipoForm.getUbicacion().getText();
            } else {
                JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "Maximo 40 caracteres en la ubicacion", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            condicion = insertarEquipoForm.getCondicion().getSelectedItem().toString();
            
            //obteniendo fecha de vencimiento
            try {
                dia = insertarEquipoForm.getDia().getSelectedItem().toString();
                mes = insertarEquipoForm.getMes().getSelectedItem().toString();
                year = insertarEquipoForm.getYear().getText();
                fechaString = year +"-"+ mes +"-" +dia;
                ingreso = Date.valueOf(fechaString);
            } catch (IllegalArgumentException timeEx) {
                JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "Fecha incorrecta", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            //buscar cliente 
            try {
                ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
                cliente = tablaCliente.consultar(cedulaCliente);
                if(cliente.getCedula() == 0){
                    JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "El cliente no se encuentra en la base de datos"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
                }
            } catch (SQLException sqlex) {
                JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "Hubo un error de conexión"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            //buscar licencia
            try {
                ServicioTablaLicencia tablaLicencia = new ServicioTablaLicencia();
                licenciaConsultar = new Licencia();
                licenciaConsultar = tablaLicencia.consultar(licencia);
                if(licenciaConsultar.getCodigo() == null){
                    JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "La licencia no se encuentra en la base de datos"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
                }
            } catch (SQLException sqlex) {
                JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "Hubo un error de conexión"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(!serial.equals("") && !licencia.equals("")  && ingreso != null && cedulaCliente != 0) {
                Equipo equipo = new Equipo(serial, licencia, cliente, ingreso);
                ServicioTablaEquipo tablaEquipo = new ServicioTablaEquipo();
                if (ubicacion != "") {
                    equipo.setUbicacion(ubicacion);
                }
                if (condicion != "") {
                    equipo.setCondicion(condicion);
                }
                if ( cedulaCliente != 0) {
                    equipo.setCliente_ident(cedulaCliente);
                }
                if (tipo != "") 
                    equipo.setTipo(tipo);
                if (memoria != 0) {
                    equipo.setMemoria(memoria);
                }
                if (marca != "") 
                    equipo.setMarca(marca);
                try {
                    
                int registroInsert = tablaEquipo.insert(equipo);
                JOptionPane.showMessageDialog(insertarEquipoForm, "Se insertaron" + registroInsert + " equipos", "", 
                JOptionPane.WARNING_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(insertarEquipoForm, "No se pudo insertar", "Error", 
                JOptionPane.WARNING_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "por favor complete los campos", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        if(e.getSource() == insertarEquipoForm.getVolverBtn()) {
            insertarEquipoForm.setVisible(false);
            if(control.formularioEquipos == null) {
                control.formularioEquipos = new FormularioEquipos();
                control.formularioEquipos.setVisible(true);
            } else {
                control.formularioEquipos.setVisible(true);
            }
        }
        //eventos actualizar equipos
        if (e.getSource() == actualizarEquipoForm.getActualizarBtn()) {
            String serial = "";
            String licencia = "";
            long cedulaCliente = 0;
            String tipo = "";
            double memoria = 0;
            String marca = "";
            String ubicacion = null;
            String condicion = null;
            String fechaString = null;
            String dia = null;
            String year = null;
            String mes = null;
            Date vencimiento = null;
            Cliente cliente = new Cliente();
            Licencia licenciaConsultar = null;
            
            if(actualizarEquipoForm.getSerial().getText().length() <= 20) {
                serial = actualizarEquipoForm.getSerial().getText();  
            } else {
                JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "Maximo 20 numeros en serial", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            //Buscar equipo
            ServicioTablaEquipo tablaEquipo = new ServicioTablaEquipo();
            try {
                Equipo equipo = tablaEquipo.consultar(serial);
                if (equipo.getSerial() == null) {
                    JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "El equipo no se encuentra en la base de datos"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "Error de conexión"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    ex.printStackTrace();
                    return;
            }
                
            
            if(actualizarEquipoForm.getCodigoLicencia().getText().length() <= 20) {
                licencia = actualizarEquipoForm.getCodigoLicencia().getText();  
            } else {
                JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "Maximo 20 caracteres en la licencia", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(actualizarEquipoForm.getCedulaCliente().getText().length() <= 20) {
                try {
                    cedulaCliente = Long.parseLong(actualizarEquipoForm.getCedulaCliente().getText());  
                } catch (NumberFormatException nex) {
                    JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "Debe ingresar solo numeros en cedula", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "Maximo 20 numeros en cedula", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(actualizarEquipoForm.getTipoEquipo().getText().length() <= 20) {
                tipo = actualizarEquipoForm.getTipoEquipo().getText();
            } else {
                JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "Maximo 20 caracteres en el tipo", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
                
            try {
                memoria = Double.parseDouble(actualizarEquipoForm.getMemoria().getText()); 
            } catch (NumberFormatException nex) {
                JOptionPane.showMessageDialog(actualizarEquipoForm, "Debe ingresar solo numeros en la memoria", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
             }
            if(actualizarEquipoForm.getMarca().getText().length() <= 20) {
                marca = actualizarEquipoForm.getMarca().getText();
            } else {
                JOptionPane.showMessageDialog(insertarEquipoForm.getRootPane(), "Maximo 15 caracteres en la marca", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(actualizarEquipoForm.getUbicacion().getText().length() <= 20) {
                ubicacion = actualizarEquipoForm.getUbicacion().getText();
            } else {
                JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "Maximo 40 caracteres en la ubicacion", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            condicion = actualizarEquipoForm.getCondicion().getSelectedItem().toString();
            
            //obteniendo fecha de vencimiento
            try {
                dia = actualizarEquipoForm.getDia().getSelectedItem().toString();
                mes = actualizarEquipoForm.getMes().getSelectedItem().toString();
                year = actualizarEquipoForm.getYear().getText();
                fechaString = year +"-"+ mes +"-" +dia;
                vencimiento = Date.valueOf(fechaString);
            } catch (IllegalArgumentException timeEx) {
                JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "Fecha incorrecta", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            //buscar cliente 
            try {
                ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
                cliente = tablaCliente.consultar(cedulaCliente);
                if(cliente.getCedula() == 0){
                    JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "El cliente no se encuentra en la base de datos"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
                }
            } catch (SQLException sqlex) {
                JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "Hubo un error de conexión"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            //buscar licencia
            try {
                ServicioTablaLicencia tablaLicencia = new ServicioTablaLicencia();
                licenciaConsultar = new Licencia();
                licenciaConsultar = tablaLicencia.consultar(licencia);
                if(licenciaConsultar.getCodigo() == null){
                    JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "La licencia no se encuentra en la base de datos"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
                }
            } catch (SQLException sqlex) {
                JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "Hubo un error de conexión"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(!serial.equals("") && !licencia.equals("")  && vencimiento != null && cedulaCliente != 0) {
                Equipo equipo = new Equipo(serial, licencia, cliente, vencimiento);
                tablaEquipo = new ServicioTablaEquipo();
                if (!ubicacion.equals("")) {
                    equipo.setUbicacion(ubicacion);
                }
                if (!condicion.equals("")) {
                    equipo.setCondicion(condicion);
                }
                if ( cedulaCliente != 0) {
                    equipo.setCliente_ident(cedulaCliente);
                }
                if (!tipo.equals("")) 
                    equipo.setTipo(tipo);
                if (memoria != 0) {
                    equipo.setMemoria(memoria);
                }
                if (!marca.equals("")) 
                    equipo.setMarca(marca);
                try {
                int registroInsert = tablaEquipo.update(equipo);
                JOptionPane.showMessageDialog(actualizarEquipoForm, "Se actualizaron" + registroInsert + " equipos", "", 
                JOptionPane.WARNING_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(actualizarEquipoForm, "No se pudo atualizar", "Error", 
                JOptionPane.WARNING_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "por favor complete los campos", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            
        }
        if(e.getSource() == actualizarEquipoForm.getVolverBtn()) {
            actualizarEquipoForm.setVisible(false);
            if(control.formularioEquipos == null) {
                control.formularioEquipos = new FormularioEquipos();
                control.formularioEquipos.setVisible(true);
            } else {
                control.formularioEquipos.setVisible(true);
            }
        }
        //Consultar equipo formulario
        if(e.getSource() == consultarEquipoForm.getConsultarBtn()) {
            String serial = null;
            if(consultarEquipoForm.getSerialConsultarTF().getText() != "") {
                if(consultarEquipoForm.getCedulaConsultarTF().getText().length() <= 20) {
               
                    serial = consultarEquipoForm.getSerialConsultarTF().getText();
                
                } else {
                    JOptionPane.showMessageDialog(consultarEquipoForm.getRootPane(), "Maximo 20 caracteres en serial", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
            ServicioTablaEquipo tablaEquipo = new ServicioTablaEquipo();
            try {
                Equipo equipo = tablaEquipo.consultar(serial);
                if (equipo.getSerial() == null) {
                    JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "El equipo no se encuentra en la base de datos"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                consultarEquipoForm.getSerialLbl().setText(equipo.getSerial());
                consultarEquipoForm.getLicenciaLbl().setText(equipo.getLicencia_LicCodigo());
                consultarEquipoForm.getTipoLbl().setText(equipo.getTipo());
                consultarEquipoForm.getMemoriaLbl().setText(""+equipo.getMemoria());
                consultarEquipoForm.getMarcaLbl().setText(equipo.getMarca());
                consultarEquipoForm.getUbicacionLbl().setText(equipo.getUbicacion());
                consultarEquipoForm.getCondicionLbl().setText(equipo.getCondicion());
                consultarEquipoForm.getFechaIngesoLbl().setText(equipo.getFechaIngreso().toString());
                consultarEquipoForm.getCedulaClienteLbl().setText(""+equipo.getCliente_ident());
                consultarEquipoForm.getNombreClienteLbl().setText(equipo.getCliente().getNombre());
                consultarEquipoForm.getApellidoCLienteLbl().setText(equipo.getCliente().getApellido());
                consultarEquipoForm.getTelefonoClienteLbl().setText(""+equipo.getCliente().getTelefono());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(actualizarEquipoForm.getRootPane(), "Error de conexión"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    ex.printStackTrace();
                    return;
                }
                
            }
        }
        
        if(e.getSource() == consultarEquipoForm.getVolverBtn()) {
            consultarEquipoForm.setVisible(false);
            if(control.formularioEquipos == null) {
                control.formularioEquipos = new FormularioEquipos();
                control.formularioEquipos.setVisible(true);
            } else {
                control.formularioEquipos.setVisible(true);
            }
        }
        //eliminar equipo
        if(e.getSource() == eliminarEquipoForm.getEliminarBtn()) {
            String serial = null;
           if(!eliminarEquipoForm.getSerialEliminarTF().getText().equals("")) {
                if(eliminarEquipoForm.getSerialEliminarTF().getText().length() <= 20) {
               
                    serial = eliminarEquipoForm.getSerialEliminarTF().getText();
                
                } else {
                    JOptionPane.showMessageDialog(eliminarEquipoForm.getRootPane(), "Maximo 20 caracteres en serial", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                 ServicioTablaEquipo tablaEquipo = new ServicioTablaEquipo();
                 try {
                Equipo equipo = tablaEquipo.consultar(serial);
                if (equipo.getSerial() == null) {
                    JOptionPane.showMessageDialog(eliminarEquipoForm.getRootPane(), "El equipo no se encuentra en la base de datos"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                } catch (SQLException sqlex) {
                    JOptionPane.showMessageDialog(eliminarEquipoForm.getRootPane(), "Hubo un error de conexión"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int confirmacion = JOptionPane.showConfirmDialog(eliminarEquipoForm.getRootPane(), "Está seguro que"
                        + " desea eliminar el equipo con serial " + serial + "?", "Eliminación de equipo", 
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if(confirmacion == 0) {
                    try {
                        tablaEquipo = new ServicioTablaEquipo();
                        tablaEquipo.delete(serial);
                        JOptionPane.showMessageDialog(eliminarEquipoForm.getRootPane(), "se ha borrado el equipo"
                                + " con serial " + serial
                        + " exitosamente!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
                //eventos en formulario de equipo
        if(e.getSource() == control.formularioEquipos.getMenuBtn()) {
            control.formularioEquipos.setVisible(false);
            if (control.formularioPrincipal == null) {
                control.formularioPrincipal = new FormularioPrincipal();
                control.formularioPrincipal.setVisible(true);
            } else {
                control.formularioPrincipal.setVisible(true);
            }
        }
        
        if(e.getSource() == eliminarEquipoForm.getVolverBtn()) {
            eliminarEquipoForm.setVisible(false);
            if (control.formularioEquipos == null) {
                control.formularioEquipos = new FormularioEquipos();
                control.formularioEquipos.setVisible(true);
            } else {
                control.formularioEquipos.setVisible(true);
            }
        }
    }
}
