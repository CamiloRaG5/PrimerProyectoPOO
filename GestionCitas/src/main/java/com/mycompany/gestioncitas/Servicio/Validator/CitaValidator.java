package com.mycompany.gestioncitas.Servicio.Validator;

import java.time.LocalDateTime;

import Repositorio.ICitaRepositorio;
import modelo.Cita;

public class CitaValidator implements IValidator<Cita> {

    private final ICitaRepositorio repositorio;

    // Inyectamos el repositorio para poder verificar si ya existe una cita
    public CitaValidator(ICitaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void validar(Cita cita) throws ValidationException {
        // Regla 1: La cita no puede ser nula
        if (cita == null) {
            throw new ValidationException("La cita no puede ser nula.");
        }

        // Regla 2: Paciente y Médico no pueden ser nulos
        if (cita.getPaciente() == null) {
            throw new ValidationException("La cita debe tener un paciente asociado.");
        }
        if (cita.getMedico() == null) {
            throw new ValidationException("La cita debe tener un médico asociado.");
        }

        // Regla 3: La fecha y hora no pueden ser nulas y deben estar en el futuro
        if (cita.getFechaHora() == null) {
            throw new ValidationException("La fecha y hora de la cita son obligatorias.");
        }
        if (cita.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new ValidationException("No se pueden agendar citas en el pasado.");
        }

        // Regla 4: Verificar que no haya una cita duplicada para el mismo médico a la
        // misma hora
        if (repositorio.existePorFechaYMedico(cita.getFechaHora(), cita.getMedico())) {
            throw new ValidationException(
                    "El médico " + cita.getMedico().getNombre() +
                            " ya tiene una cita programada para la fecha " + cita.getFechaHora());
        }
    }
}
