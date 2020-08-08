/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.Date;

import com.sun.org.apache.xml.internal.serialize.EncodingInfo;

/**
 *
 * @author Fray
 */
public class Licencia {
    private String codigo;
    private String Tipo;
    Date vencimiento;
    
    public Licencia() {
        
    }
    
    public Licencia(String codigo, String tipo, Date vencimiento) {
        this.codigo = codigo;
        this.Tipo = tipo;
        this.vencimiento = vencimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return Tipo;
    }

    public Date getVencimiento() {
        return vencimiento;
    }
    
    
}
