/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vista.clientes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cliente;
import servicios.ServicioTablaCliente;
import vista.FormularioPrincipal;

/**
 *
 * @author Fray
 */
public class ControladorClientes implements ActionListener{
    private Control control;
    private InsertarClienteForm insertarClienteForm;
    private ActualizarClienteForm actualizarClienteForm;
    private ConsultarClienteForm consultarClienteForm;
    private EliminarClienteForm eliminarClienteForm;
    public ControladorClientes(Control controlTemp) {
        this.control = controlTemp;
        insertarClienteForm = new InsertarClienteForm();
        actualizarClienteForm = new ActualizarClienteForm();
        consultarClienteForm = new ConsultarClienteForm();
        eliminarClienteForm = new EliminarClienteForm();
        control.formularioClientes.getMenuBtn().addActionListener(this);
        control.formularioClientes.getInsertarBtn().addActionListener(this);
        control.formularioClientes.getUpdateBtn().addActionListener(this);
        control.formularioClientes.getSelectBtn().addActionListener(this);
        control.formularioClientes.getDeleteBtn().addActionListener(this);
        insertarClienteForm.getRegistrarBtn().addActionListener(this);
        insertarClienteForm.getVolverBtn().addActionListener(this);
        actualizarClienteForm.getActualizarBtn().addActionListener(this);
        actualizarClienteForm.getVolverBtn().addActionListener(this);
        consultarClienteForm.getConsultarBtn().addActionListener(this);
        consultarClienteForm.getVolverBtn().addActionListener(this);
        eliminarClienteForm.getEliminarBtn().addActionListener(this);
        eliminarClienteForm.getVolverBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == control.formularioClientes.getInsertarBtn()) {
            control.formularioClientes.setVisible(false);
            if (insertarClienteForm== null) {
                insertarClienteForm = new InsertarClienteForm();
                insertarClienteForm.setVisible(true);
            } else {
                insertarClienteForm.setVisible(true);
            }
            
        }
        if(e.getSource() == control.formularioClientes.getUpdateBtn()) {
            control.formularioClientes.setVisible(false);
            if (actualizarClienteForm== null) {
                actualizarClienteForm = new ActualizarClienteForm();
                actualizarClienteForm.setVisible(true);
            } else {
                actualizarClienteForm.setVisible(true);
            }
            
        }
        if(e.getSource() == control.formularioClientes.getSelectBtn()) {
            control.formularioClientes.setVisible(false);
            if (consultarClienteForm== null) {
                consultarClienteForm = new ConsultarClienteForm();
                consultarClienteForm.setVisible(true);
            } else {
                consultarClienteForm.setVisible(true);
            }
            consultarClienteForm.getCedulaLbl().setText("");
            consultarClienteForm.getApellidoLbl().setText("");
            consultarClienteForm.getNombreLbl().setText("");
            consultarClienteForm.getTelefonoLbl().setText("");
        }
        if(e.getSource() == control.formularioClientes.getDeleteBtn()) {
            control.formularioClientes.setVisible(false);
            if (eliminarClienteForm== null) {
                eliminarClienteForm = new EliminarClienteForm();
                eliminarClienteForm.setVisible(true);
            } else {
                eliminarClienteForm.setVisible(true);
            }
            
        }
        //eventos insertar clientes
        if (e.getSource() == insertarClienteForm.getRegistrarBtn()) {
            long cedula = 0;
            int telf = 0;
            String nombre = "";
            String apellido = "";
            if(insertarClienteForm.getCedula().getText().length() <= 20) {
                try {
                    cedula = Long.parseLong(insertarClienteForm.getCedula().getText());  
                } catch (NumberFormatException nex) {
                    JOptionPane.showMessageDialog(insertarClienteForm.getRootPane(), "Debe ingresar solo numeros en cedula", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(insertarClienteForm.getRootPane(), "Maximo 20 numeros en cedula", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(insertarClienteForm.getNombre().getText().length() <= 45) {
                nombre = insertarClienteForm.getNombre().getText();
            } else {
                JOptionPane.showMessageDialog(insertarClienteForm.getRootPane(), "Maximo 45 caracteres en nombre", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(insertarClienteForm.getApellido().getText().length() <= 45) {
                apellido = insertarClienteForm.getApellido().getText();
            } else {
                JOptionPane.showMessageDialog(insertarClienteForm.getRootPane(), "Maximo 45 caracteres en apellido", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(insertarClienteForm.getTelefono().getText().length() <= 10) {    
                try {
                    telf = Integer.parseInt(insertarClienteForm.getTelefono().getText()); 
                } catch (NumberFormatException nex) {
                    JOptionPane.showMessageDialog(insertarClienteForm, "Debe ingresar solo numeros en el telefono", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(insertarClienteForm.getRootPane(), "Maximo 10 numeros en telefono", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
             if(!nombre.equals("") && !apellido.equals("") && telf != 0) {
                Cliente clienteTemp = new Cliente(cedula, nombre, apellido, telf);
                ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
                try {
                int registroInsert = tablaCliente.insert(clienteTemp);
                JOptionPane.showMessageDialog(insertarClienteForm, "Se insertó " + registroInsert + " cliente(s)", "", 
                JOptionPane.WARNING_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(insertarClienteForm, "No se pudo insertar", "Error", 
                JOptionPane.WARNING_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(insertarClienteForm.getRootPane(), "por favor complete los campos", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        if(e.getSource() == insertarClienteForm.getVolverBtn()) {
            insertarClienteForm.setVisible(false);
            if(control.formularioClientes == null) {
                control.formularioClientes = new FormularioClientes();
                control.formularioClientes.setVisible(true);
            } else {
                control.formularioClientes.setVisible(true);
            }
        }
        //eventos actualizar clientes
        if (e.getSource() == actualizarClienteForm.getActualizarBtn()) {
            long cedula = 0;
            int telf = 0;
            String nombre = "";
            String apellido = "";
            
             if(actualizarClienteForm.getCedula().getText().length() <= 20) {
                try {
                    cedula = Long.parseLong(actualizarClienteForm.getCedula().getText());  
                } catch (NumberFormatException nex) {
                    JOptionPane.showMessageDialog(actualizarClienteForm.getRootPane(), "Debe ingresar solo numeros en cedula", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(actualizarClienteForm.getRootPane(), "Maximo 20 numeros en cedula", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
                Cliente cliente = tablaCliente.consultar(cedula);
                if(cliente.getCedula() == 0){
                    JOptionPane.showMessageDialog(actualizarClienteForm.getRootPane(), "El cliente no se encuentra en la base de datos"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
                }
            } catch (SQLException sqlex) {
                JOptionPane.showMessageDialog(actualizarClienteForm.getRootPane(), "Hubo un error de conexión"
                        + "", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
 
             if(actualizarClienteForm.getNombre().getText().length() <= 45) {
                nombre = actualizarClienteForm.getNombre().getText();
            } else {
                JOptionPane.showMessageDialog(actualizarClienteForm.getRootPane(), "Maximo 45 caracteres en nombre", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(actualizarClienteForm.getApellido().getText().length() <= 45) {
                apellido = actualizarClienteForm.getApellido().getText();
            } else {
                JOptionPane.showMessageDialog(actualizarClienteForm.getRootPane(), "Maximo 45 caracteres en apellido", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            } 
            
            try {
                telf = Integer.parseInt(actualizarClienteForm.getTelefono().getText()); 
            } catch (NumberFormatException nex) {
                JOptionPane.showMessageDialog(actualizarClienteForm.getRootPane(), "Debe ingresar solo numeros en el telefono", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(!nombre.equals("") && !apellido.equals("") && telf != 0) {
                Cliente clienteTemp = new Cliente(cedula, nombre, apellido, telf);
                ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
                try {
                int registroInsert = tablaCliente.update(clienteTemp);
                JOptionPane.showMessageDialog(actualizarClienteForm.getRootPane(), "Se actualizaron " + registroInsert + " cliente(s)", "", 
                JOptionPane.WARNING_MESSAGE);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(actualizarClienteForm, "No se pudo actualizar", "Error", 
                JOptionPane.WARNING_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(actualizarClienteForm.getRootPane(), "por favor complete los campos", "Error", 
                JOptionPane.WARNING_MESSAGE);
                return;
            }
            
        }
        if(e.getSource() == actualizarClienteForm.getVolverBtn()) {
            actualizarClienteForm.setVisible(false);
            if(control.formularioClientes == null) {
                control.formularioClientes = new FormularioClientes();
                control.formularioClientes.setVisible(true);
            } else {
                control.formularioClientes.setVisible(true);
            }
        }
        //Consultar Cliente formulario
        if(e.getSource() == consultarClienteForm.getConsultarBtn()) {
            long cedula = 0;
            if(consultarClienteForm.getCedulaConsultarTF().getText() != "") {
                if(consultarClienteForm.getCedulaConsultarTF().getText().length() <= 20) {
                try {
                    cedula = Long.parseLong(consultarClienteForm.getCedulaConsultarTF().getText());  
                } catch (NumberFormatException nex) {
                    JOptionPane.showMessageDialog(consultarClienteForm.getRootPane(), "Debe ingresar solo numeros en cedula", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                } else {
                    JOptionPane.showMessageDialog(consultarClienteForm.getRootPane(), "Maximo 20 numeros en cedula", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
            ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
            try {
                Cliente cliente = tablaCliente.consultar(cedula);
                if (cliente.getCedula() == 0) {
                    JOptionPane.showMessageDialog(actualizarClienteForm.getRootPane(), "El cliente no se encuentra en la base de datos"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                consultarClienteForm.getCedulaLbl().setText("" + cliente.getCedula());
                consultarClienteForm.getNombreLbl().setText(cliente.getNombre());
                consultarClienteForm.getApellidoLbl().setText(cliente.getApellido());
                consultarClienteForm.getTelefonoLbl().setText("" + cliente.getTelefono());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                
            }
        }
        
        if(e.getSource() == consultarClienteForm.getVolverBtn()) {
            consultarClienteForm.setVisible(false);
            if(control.formularioClientes == null) {
                control.formularioClientes = new FormularioClientes();
                control.formularioClientes.setVisible(true);
            } else {
                control.formularioClientes.setVisible(true);
            }
        }
        //eliminar cliente
        if(e.getSource() == eliminarClienteForm.getEliminarBtn()) {
            long cedula = 0;
            if(eliminarClienteForm.getCedulaEliminarTF().getText() != "") {
                if(eliminarClienteForm.getCedulaEliminarTF().getText().length() <= 20) {
                try {
                    cedula = Long.parseLong(eliminarClienteForm.getCedulaEliminarTF().getText());  
                } catch (NumberFormatException nex) {
                    JOptionPane.showMessageDialog(eliminarClienteForm.getRootPane(), "Debe ingresar solo numeros en cedula", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                } else {
                    JOptionPane.showMessageDialog(eliminarClienteForm.getRootPane(), "Maximo 20 numeros en cedula", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
                    Cliente cliente = tablaCliente.consultar(cedula);
                if(cliente.getCedula() == 0){
                    JOptionPane.showMessageDialog(eliminarClienteForm.getRootPane(), "El cliente no se encuentra en la base de datos"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                } catch (SQLException sqlex) {
                    JOptionPane.showMessageDialog(eliminarClienteForm.getRootPane(), "Hubo un error de conexión"
                        + "", "Error", 
                    JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int confirmacion = JOptionPane.showConfirmDialog(eliminarClienteForm.getRootPane(), "Está seguro de que"
                        + " desea eliminar el cliente con cédula " + cedula + "?", "Eliminación de cliente", 
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if(confirmacion == 0) {
                    try {
                        ServicioTablaCliente tablaCliente = new ServicioTablaCliente();
                        tablaCliente.delete(cedula);
                        JOptionPane.showMessageDialog(eliminarClienteForm.getRootPane(), "se ha borrado el cliente con cedula " + cedula
                        + " exitosamente!");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
                //eventos en formulario de clientes
        if(e.getSource() == control.formularioClientes.getMenuBtn()) {
            control.formularioClientes.setVisible(false);
            if (control.formularioPrincipal == null) {
                control.formularioPrincipal = new FormularioPrincipal();
                control.formularioPrincipal.setVisible(true);
            } else {
                control.formularioPrincipal.setVisible(true);
            }
        }
        
        if(e.getSource() == eliminarClienteForm.getVolverBtn()) {
            eliminarClienteForm.setVisible(false);
            if (control.formularioClientes == null) {
                control.formularioClientes = new FormularioClientes();
                control.formularioClientes.setVisible(true);
            } else {
                control.formularioClientes.setVisible(true);
            }
        }
    }
}
