/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author CARG
 */
public class Usuario {
    protected long id;
    protected String nombre;
    protected String documento;
    
    public Usuario(long id, String nombre, String documento){
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;      
    }
    
    
}
