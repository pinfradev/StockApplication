/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import vista.licencias.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.*;
import servicios.ServicioTablaLicencia;
import vista.*;
/**
 *
 * @author Fray
 */
public class  ControladorLicencias implements ActionListener{
    private Control control;
    private InsertarLicenciaForm insertarLicenciaForm;
    private ActualizarLicenciaForm actualizarLicenciaForm;
    private ConsultarLicenciaForm consultarLicenciaForm;
    private EliminarLicenciaForm eliminarLicenciaForm;
    public ControladorLicencias(Control controlTemp) {
        this.control = controlTemp;
        insertarLicenciaForm = new InsertarLicenciaForm();
        actualizarLicenciaForm = new ActualizarLicenciaForm();
        consultarLicenciaForm = new ConsultarLicenciaForm();
        eliminarLicenciaForm = new EliminarLicenciaForm();
        control.formularioLicencias.getMenuBtn().addActionListener(this);
        control.formularioLicencias.getInsertarBtn().addActionListener(this);
        control.formularioLicencias.getUpdateBtn().addActionListener(this);
        control.formularioLicencias.getSelectBtn().addActionListener(this);
        control.formularioLicencias.getDeleteBtn().addActionListener(this);
        insertarLicenciaForm.getRegistrarBtn().addActionListener(this);
        insertarLicenciaForm.getVolverBtn().addActionListener(this);
        actualizarLicenciaForm.getActualizarBtn().addActionListener(this);
        actualizarLicenciaForm.getVolverBtn().addActionListener(this);
        consultarLicenciaForm.getConsultarBtn().addActionListener(this);
        consultarLicenciaForm.getVolverBtn().addActionListener(this);
        eliminarLicenciaForm.getEliminarBtn().addActionListener(this);
        eliminarLicenciaForm.getVolverBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == control.formularioLicencias.getInsertarBtn()) {
            control.formularioLicencias.setVisible(false);
            if (insertarLicenciaForm== null) {
                insertarLicenciaForm = new InsertarLicenciaForm();
                insertarLicenciaForm.setVisible(true);
            } else {
                insertarLicenciaForm.setVisible(true);
            }
            
        }
        if(e.getSource() == control.formularioLicencias.getUpdateBtn()) {
            control.formularioLicencias.setVisible(false);
            if (actualizarLicenciaForm== null) {
                actualizarLicenciaForm = new ActualizarLicenciaForm();
                actualizarLicenciaForm.setVisible(true);
            } else {
                actualizarLicenciaForm.setVisible(true);
            }
            
        }
        if(e.getSource() == control.formularioLicencias.getSelectBtn()) {
            control.formularioLicencias.setVisible(false);
            if (consultarLicenciaForm == null) {
                consultarLicenciaForm = new ConsultarLicenciaForm();
                consultarLicenciaForm.setVisible(true);
            } else {
                consultarLicenciaForm.setVisible(true);
            }
            consultarLicenciaForm.getCodigoLbl().setText("");
            consultarLicenciaForm.getFechaLbl().setText("");
            consultarLicenciaForm.getTipoLbl().setText("");
        }
        if(e.getSource() == control.formularioLicencias.getDeleteBtn()) {
            control.formularioLicencias.setVisible(false);
            if (eliminarLicenciaForm == null) {
                eliminarLicenciaForm = new EliminarLicenciaForm();
                eliminarLicenciaForm.setVisible(true);
            } else {
                eliminarLicenciaForm.setVisible(true);
            }
            
        }
        //eventos insertar clientes
        if (e.getSource() == insertarLicenciaForm.getRegistrarBtn()) {
            String codigo = "";
            String tipo = "";
            String fechaString = null;
            String dia = null;
            String year = null;
            String mes = null;
            Date vencimiento = null;
            if(insertarLicenciaForm.getCodigoLicencia().getText().length() <= 20) {
                codigo = insertarLicenciaForm.getCodigoLicencia().getText();  
                
            } else {
                JOptionPane.showMessageDialog(insertarLicenciaForm.getRootPane(), "Maximo 20 caracteres en el codigo", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(insertarLicenciaForm.getTipoLicencia().getText().length() <= 20) {
                tipo = insertarLicenciaForm.getTipoLicencia().getText();
            } else {
                JOptionPane.showMessageDialog(insertarLicenciaForm.getRootPane(), "Maximo 20 carcateres en tipo", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            //obteniendo fecha de vencimiento
            try {
                dia = insertarLicenciaForm.getDia().getSelectedItem().toString();
                mes = insertarLicenciaForm.getMes().getSelectedItem().toString();
                year = insertarLicenciaForm.getYear().getText();
                fechaString = year +"-"+ mes +"-" +dia;
                vencimiento = Date.valueOf(fechaString);
            } catch (IllegalArgumentException timeEx) {
                JOptionPane.showMessageDialog(insertarLicenciaForm.getRootPane(), "Fecha incorrecta", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
           
           if(!codigo.equals("") && !tipo.equals("")  && vencimiento != null) {
                Licencia licencia = new Licencia(codigo, tipo, vencimiento);
                ServicioTablaLicencia tablaLicencia = new ServicioTablaLicencia();
                try {
                int registroInsert = tablaLicencia.insert(licencia);
                JOptionPane.showMessageDialog(insertarLicenciaForm, "Se insertaron" + registroInsert + " licencias", "", 
                JOptionPane.WARNING_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(insertarLicenciaForm, "No se pudo insertar", "Error", 
                JOptionPane.WARNING_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(insertarLicenciaForm.getRootPane(), "por favor complete los campos", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        if(e.getSource() == insertarLicenciaForm.getVolverBtn()) {
            insertarLicenciaForm.setVisible(false);
            if(control.formularioLicencias == null) {
                control.formularioLicencias = new FormularioLicencias();
                control.formularioLicencias.setVisible(true);
            } else {
                control.formularioLicencias.setVisible(true);
            }
        }
        //eventos actualizar clientes
        if (e.getSource() == actualizarLicenciaForm.getActualizarBtn()) {
            String codigo = "";
            String tipo = "";
            String fechaString = null;
            String dia = null;
            String year = null;
            String mes = null;
            Date vencimiento = null;
            
             if(actualizarLicenciaForm.getCodigoLicencia().getText().length() <= 20) {
                 codigo = actualizarLicenciaForm.getCodigoLicencia().getText();  
                
            } else {
                JOptionPane.showMessageDialog(actualizarLicenciaForm.getRootPane(), "Maximo 20 numeros en codigo", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                ServicioTablaLicencia tablaLicencia = new ServicioTablaLicencia();
                Licencia licencia = new Licencia();
                licencia = tablaLicencia.consultar(codigo);
                if(licencia.getCodigo() == null){
                    JOptionPane.showMessageDialog(actualizarLicenciaForm.getRootPane(), "La licencia no se encuentra en la base de datos"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
                }
            } catch (SQLException sqlex) {
                JOptionPane.showMessageDialog(actualizarLicenciaForm.getRootPane(), "Hubo un error de conexión"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
 
             if(actualizarLicenciaForm.getTipoLicencia().getText().length() <= 20) {
                tipo = actualizarLicenciaForm.getTipoLicencia().getText();
            } else {
                JOptionPane.showMessageDialog(actualizarLicenciaForm.getRootPane(), "Maximo 20 carcateres en tipo", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            try {
                dia = actualizarLicenciaForm.getDia().getSelectedItem().toString();
                mes = actualizarLicenciaForm.getMes().getSelectedItem().toString();
                year = actualizarLicenciaForm.getYear().getText();
                fechaString = year +"-"+ mes + "-" + dia;
                vencimiento = Date.valueOf(fechaString);
            } catch (IllegalArgumentException timeEx) {
                JOptionPane.showMessageDialog(insertarLicenciaForm.getRootPane(), "Fecha incorrecta", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(!codigo.equals("") && !tipo.equals("")  && vencimiento != null) {
                Licencia licencia = new Licencia(codigo, tipo, vencimiento);
                ServicioTablaLicencia tablaLicencia = new ServicioTablaLicencia();
                try {
                int registroInsert = tablaLicencia.update(licencia);
                JOptionPane.showMessageDialog(insertarLicenciaForm, "Se actualizaron" + registroInsert + "licencias", "", 
                JOptionPane.WARNING_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(insertarLicenciaForm, "No se pudo actualizar", "Error", 
                JOptionPane.WARNING_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(insertarLicenciaForm.getRootPane(), "por favor complete los campos", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
        }    
         
        if(e.getSource() == actualizarLicenciaForm.getVolverBtn()) {
            actualizarLicenciaForm.setVisible(false);
            if(control.formularioLicencias == null) {
                control.formularioLicencias = new FormularioLicencias();
                control.formularioLicencias.setVisible(true);
            } else {
                control.formularioLicencias.setVisible(true);
            }
        }
        //Consultar Licencia formulario
        if(e.getSource() == consultarLicenciaForm.getConsultarBtn()) {
           String codigoConsultar = null;
            if(consultarLicenciaForm.getConsultarCodigoTF().getText() != "") {
                if(consultarLicenciaForm.getConsultarCodigoTF().getText().length() <= 20) {
                    codigoConsultar = consultarLicenciaForm.getConsultarCodigoTF().getText();
                } else {
                    JOptionPane.showMessageDialog(consultarLicenciaForm.getRootPane(), "Maximo 20 caracteres en codigo", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
            ServicioTablaLicencia tablaLicencia = new ServicioTablaLicencia();
            try {
                Licencia licencia = tablaLicencia.consultar(codigoConsultar);
                if (licencia.getCodigo() == null) {
                    JOptionPane.showMessageDialog(actualizarLicenciaForm.getRootPane(), "La licencia no se encuentra en la base de datos"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                consultarLicenciaForm.getCodigoLbl().setText(licencia.getCodigo());
                consultarLicenciaForm.getTipoLbl().setText(licencia.getTipo());
                consultarLicenciaForm.getFechaLbl().setText(licencia.getVencimiento().toString());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
            }
        }
        
        if(e.getSource() == consultarLicenciaForm.getVolverBtn()) {
            consultarLicenciaForm.setVisible(false);
            if(control.formularioLicencias == null) {
                control.formularioLicencias = new FormularioLicencias();
                control.formularioLicencias.setVisible(true);
            } else {
                control.formularioLicencias.setVisible(true);
            }
        }
        //eliminar licencia
        if(e.getSource() == eliminarLicenciaForm.getEliminarBtn()) {
            String codigo = null;
            if(eliminarLicenciaForm.getCodigoEliminarTF().getText() != "") {
                if(eliminarLicenciaForm.getCodigoEliminarTF().getText().length() <= 20) {
                    codigo = eliminarLicenciaForm.getCodigoEliminarTF().getText();
                } else {
                    JOptionPane.showMessageDialog(eliminarLicenciaForm.getRootPane(), "Maximo 20 caracteres en codigo", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    ServicioTablaLicencia tablaLicencia = new ServicioTablaLicencia();
                    Licencia licencia = tablaLicencia.consultar(codigo);
                if(licencia.getCodigo() == null){
                    JOptionPane.showMessageDialog(eliminarLicenciaForm.getRootPane(), "La licencia no se encuentra en la base de datos"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                } catch (SQLException sqlex) {
                    JOptionPane.showMessageDialog(eliminarLicenciaForm.getRootPane(), "Hubo un error de conexión"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int confirmacion = JOptionPane.showConfirmDialog(eliminarLicenciaForm.getRootPane(), "Está seguro que"
                        + " desea eliminar la licencia con código" + codigo + "?", "Eliminación de Licencia", 
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if(confirmacion == 0) {
                    try {
                        ServicioTablaLicencia tablaLicencia = new ServicioTablaLicencia();
                        tablaLicencia.delete(codigo);
                        JOptionPane.showMessageDialog(eliminarLicenciaForm.getRootPane(), "se ha borrado la licencia con"
                                + " código" + codigo
                        + " exitosamente!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
                //eventos en formulario de clientes
        if(e.getSource() == control.formularioLicencias.getMenuBtn()) {
            control.formularioLicencias.setVisible(false);
            if (control.formularioPrincipal == null) {
                control.formularioPrincipal = new FormularioPrincipal();
                control.formularioPrincipal.setVisible(true);
            } else {
                control.formularioPrincipal.setVisible(true);
            }
        }
        
        if(e.getSource() == eliminarLicenciaForm.getVolverBtn()) {
            eliminarLicenciaForm.setVisible(false);
            if (control.formularioLicencias == null) {
                control.formularioLicencias = new FormularioLicencias();
                control.formularioLicencias.setVisible(true);
            } else {
                control.formularioLicencias.setVisible(true);
            }
        }
    }
}
