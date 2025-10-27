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

    private final CitaValidator validator;

    public CitaService(ICitaRepositorio repositorio) {
        this.repositorio = repositorio;
        this.validator = new CitaValidator(repositorio);
    }

    public void agendarCita(Cita cita) throws ValidationException {
        validator.validar(cita);
        cita.setEstado("PROGRAMADA");
        repositorio.guardar(cita);
    }

    /**
     * Marca una cita como completada y registra diagnóstico y tratamiento.
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
        repositorio.guardar(cita);
    }

    public void cancelarCita(Long citaId) throws ValidationException {
        Cita cita = repositorio.buscarPorId(citaId)
                .orElseThrow(() -> new ValidationException("La cita no existe"));
        cita.setEstado("CANCELADA");
        repositorio.guardar(cita);
    }

    //obtener citas
    public List<Cita> obtenerTodasCitas() {
        return repositorio.buscarTodos();
    }

    // Consultas de apoyo a la UI

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
