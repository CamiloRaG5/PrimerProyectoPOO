/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author CARG
 */
public class Paciente extends Usuario {
    private String telefono;
    private String direccion;
    
    public Paciente(long id, String nombre, String documento, String telefono, String direccion){
        super(id, nombre, documento);
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    @Override
    public String toString() {
        return """
               Paciente: 
               Id:""" + id +
                "\nNombre:'" + nombre  +
                "\nTelefono:" + telefono +
                "\nDirección:" + direccion +
                "\nDocumento:" + documento;
    }
}

