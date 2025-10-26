package modelo;

import java.time.LocalDateTime;

public class Cita {

    private Long id;
    private LocalDateTime fechaHora;
    private String estado; // OPCIONES: "PROGRAMADA", "COMPLETADA", "CANCELADA"
    private String diagnostico;
    private String tratamiento;
    private Paciente paciente;
    private Medico medico;

    public Cita(Long id, LocalDateTime fechaHora, String estado, Paciente paciente, Medico medico) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.paciente = paciente;
        this.medico = medico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
