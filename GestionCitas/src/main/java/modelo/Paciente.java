/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author CARG
 */
public class Paciente {
    private int id;
    private String nombre;
    private int edad;
    private String documento;
    
    public Paciente(int id, String nombre, int edad, String documento) {
    this.id = id;
    this.nombre = nombre;
    this.edad = edad;
    this.documento = documento;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNombre(){
    return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public int getEdad(){
        return edad;
    }
    
    public void setEdad(int edad){
        this.edad = edad;
    }
    
    public String getDocumento(){
        return documento;
    }
    
    public void setDocumento(String documento){
        this.documento = documento;
    }
    
    @Override
    public String toString() {
        return """
               Paciente: 
               id:""" + id +
                "\nnombre:'" + nombre  +
                "\nedad:" + edad +
                "\ndocumento:" + documento;
    }
}

