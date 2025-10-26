/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author CARG
 */
public class Medico {
    private int idMedico;
    private String nombre;
    private String especialidad;
    
    public Medico(int idMedico, String nombre, String especialidad){
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
    
    public int getId(){
        return idMedico;
    }
    public void setId(int idMedico){
        this.idMedico = idMedico;
    }
    
    public String getNombre(){
    return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getEspecialidad(){
        return especialidad;
    }
    
    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }
    
    @Override
    public String toString() {
        return """
               Medico: 
                "\nMedico:""" + nombre  +
                "\nEspecialidad:" + especialidad;
    }
}
