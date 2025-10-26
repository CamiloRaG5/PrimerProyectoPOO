/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CARG
 */
public class Paciente extends Usuario {

    private String telefono;
    private String direccion;
    private List<Cita> citas;

    public Paciente(int id, String nombre, String documento, String telefono, String direccion) {
        super(id, nombre, documento);
        this.telefono = telefono;
        this.direccion = direccion;
        this.citas = new ArrayList<>();
    }

    

    // Getters y setters
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public void agregarCita(Cita cita) {
        this.citas.add(cita);
    }
}
