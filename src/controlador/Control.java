/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
import vista.*;
import vista.clientes.*;
import vista.equipos.*;
import vista.licencias.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import modelo.*;
import servicios.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Fray
 */
public class Control implements ActionListener{
    FormularioPrincipal formularioPrincipal;
    FormularioClientes formularioClientes;
    FormularioEquipos formularioEquipos;
    FormularioLicencias formularioLicencias;
    private ControladorClientes controladorClientes = null;
    private ControladorEquipos controladorEquipos = null;
    private ControladorLicencias controladorLicencias = null;
    public Control() {
        formularioPrincipal = new FormularioPrincipal();
        formularioClientes = new FormularioClientes();
        formularioEquipos = new FormularioEquipos();
        formularioLicencias = new FormularioLicencias();
        formularioPrincipal.getRegistroClienteBtn().addActionListener(this);
        formularioPrincipal.getEquipoBtn().addActionListener(this);
        formularioPrincipal.getLicenciasBtn().addActionListener(this);
}
    
    public void iniciar() {
        if(formularioPrincipal == null) {
            formularioPrincipal = new FormularioPrincipal();
            formularioPrincipal.setVisible(true);
        } else {
            formularioPrincipal.setVisible(true);
        }
    }
//----------------------------------Eventos----------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        //Eventos formulario principal
        //opcion clientes 
        if(e.getSource() == formularioPrincipal.getRegistroClienteBtn()) {
            formularioPrincipal.setVisible(false);
            if (formularioClientes == null) {
                formularioClientes = new FormularioClientes();
                formularioClientes.setVisible(true);
            } else {
                formularioClientes.setVisible(true);
            }
            if (controladorClientes == null) {
                controladorClientes = new ControladorClientes(this);
            }
        }
        //opcion equipos
        if(e.getSource() == formularioPrincipal.getEquipoBtn()) {
            formularioPrincipal.setVisible(false);
            if (formularioEquipos == null) {
                formularioEquipos = new FormularioEquipos();
                formularioEquipos.setVisible(true);
            } else {
                formularioEquipos.setVisible(true);
            }
            if (controladorEquipos == null) {
                controladorEquipos = new ControladorEquipos(this);
            }
        }
        //Opcion licencias
        if(e.getSource() == formularioPrincipal.getLicenciasBtn()) {
            formularioPrincipal.setVisible(false);
            if (formularioLicencias == null) {
                formularioLicencias = new FormularioLicencias();
                formularioLicencias.setVisible(true);
            } else {
               formularioLicencias.setVisible(true);
            }
            if (controladorLicencias == null) {
                controladorLicencias = new ControladorLicencias(this);
            }
        }

              
    }
}

