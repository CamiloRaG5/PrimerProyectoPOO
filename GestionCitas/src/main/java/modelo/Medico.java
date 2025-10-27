
package modelo;

/**
 *
 * @author CARG
 */
public class Medico extends Usuario {
    private String matricula;
    private Especialidad especialidad;

    public Medico(Long id, String nombre, String documento, String matricula, Especialidad especialidad) {
        super(id, nombre, documento);
        this.matricula = matricula;
        this.especialidad = especialidad;
    }

    // Getters y setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    
}
