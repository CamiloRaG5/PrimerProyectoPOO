package com.mycompany.gestioncitas.Servicio;

import java.util.List;

import com.mycompany.gestioncitas.Servicio.Validator.CitaValidator;
import com.mycompany.gestioncitas.Servicio.Validator.ValidationException;

import Repositorio.ICitaRepositorio;
import modelo.Cita;
import modelo.Medico;
import modelo.Paciente;

public class CitaService {

    private final ICitaRepositorio repositorio;
    // Composición de un validador (aplicamos SRP: la validación vive fuera)
    private final CitaValidator validator;

    public CitaService(ICitaRepositorio repositorio) {
        this.repositorio = repositorio;
        this.validator = new CitaValidator(repositorio); // <-- LÍNEA CLAVE
    }

    // --------------------- API pública ---------------------

    /**
     * Agenda una nueva cita.
     * 
     * @throws ValidationException si hay reglas de negocio incumplidas
     */
    public void agendarCita(Cita cita) throws ValidationException {
        validator.validar(cita);
        cita.setEstado("PROGRAMADA");
        repositorio.guardar(cita);
    }

    /**
     * Marca una cita como completada y registra diagnóstico + tratamiento.
     */
    public void completarCita(Long citaId, String diagnostico, String tratamiento)
            throws ValidationException {

        Cita cita = repositorio.buscarPorId(citaId)
                .orElseThrow(() -> new ValidationException("La cita no existe"));

        if (!"PROGRAMADA".equals(cita.getEstado())) {
            throw new ValidationException("Solo se puede completar una cita programada");
        }

        cita.setEstado("COMPLETADA");
        cita.setDiagnostico(diagnostico);
        cita.setTratamiento(tratamiento);
        repositorio.guardar(cita); // persistimos la modificación
    }

    /**
     * Cancela una cita (lógica simple, sin borrado físico).
     */
    public void cancelarCita(Long citaId) throws ValidationException {
        Cita cita = repositorio.buscarPorId(citaId)
                .orElseThrow(() -> new ValidationException("La cita no existe"));
        cita.setEstado("CANCELADA");
        repositorio.guardar(cita);
    }

    // --- Consultas de apoyo a la UI ---

    public List<Cita> obtenerCitasPorPaciente(Paciente paciente) {
        return repositorio.buscarPorPaciente(paciente);
    }

    public List<Cita> obtenerCitasPorMedico(Medico medico) {
        return repositorio.buscarPorMedico(medico);
    }

    public List<Cita> obtenerHistorialClinico(Paciente paciente) {
        return repositorio.buscarPorPaciente(paciente).stream()
                .filter(c -> "COMPLETADA".equals(c.getEstado()))
                .toList();
    }

}
