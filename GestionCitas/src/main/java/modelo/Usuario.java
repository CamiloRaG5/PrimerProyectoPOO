
package modelo;

/**
 *
 * @author CARG
 */
public class Usuario {
    protected Long id;
    protected String nombre;
    protected String documento;

    public Usuario(Long id, String nombre, String documento) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
    }

    // Getters y setters agregados
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return getNombre().trim() + (documento != null ? " (Documento: " + documento + ")" : "");
    }
}
