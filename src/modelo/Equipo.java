/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author Fray
 */
public class Equipo {
    private String serial;
    private String licencia_LicCodigo;
    private long cliente_ident;
    private String tipo;
    private double memoria;
    private String marca;
    private String ubicacion;
    private String condicion;
    private Date fechaIngreso;
    private Cliente cliente;
    
    public Equipo() {}
    
    public Equipo(String serial, String licencia, Cliente cliente, Date ingreso) {
        this.serial = serial;
        this.licencia_LicCodigo = licencia;
        this.cliente = cliente;
        this.fechaIngreso = ingreso;
        this.ubicacion = "";
        this.condicion = "";
        this.tipo = "";
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getLicencia_LicCodigo() {
        return licencia_LicCodigo;
    }

    public void setLicencia_LicCodigo(String licencia_LicCodigo) {
        this.licencia_LicCodigo = licencia_LicCodigo;
    }

    public long getCliente_ident() {
        return cliente_ident;
    }

    public void setCliente_ident(long cliente_ident) {
        this.cliente_ident = cliente_ident;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMemoria() {
        return memoria;
    }

    public void setMemoria(double memoria) {
        this.memoria = memoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    
}
