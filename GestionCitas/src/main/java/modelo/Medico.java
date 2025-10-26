/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author CARG
 */
public class Medico extends Usuario{
    private String matricula;
    
    public Medico(long id, String nombre, String documento, String matricula){
        super(id, nombre, documento);
        this.matricula = matricula;
    }
    
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    
    public String getNombre(){
    return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getmatricula(){
        return matricula;
    }
    
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    
    @Override
    public String toString() {
        return """
               Medico: 
                "\nNombre:""" + nombre  +
                "\nMatricula:" + matricula;
    }
}
