package Repositorio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import modelo.Cita;
import modelo.Medico;
import modelo.Paciente;

public class CitaRepositorioMemoria implements ICitaRepositorio {

    // Usamos un Map para almacenar las citas, con el ID como clave
    private final Map<Long, Cita> citas = new HashMap<>();
    private Long siguienteId = 1L; // Para autoincrementar IDs

    @Override
    public void guardar(Cita cita) {
        if (cita.getId() == null) {
            cita.setId(generarSiguienteId());
        }
        // Si la cita ya tiene un paciente asociado en la entidad, la agregamos también
        // a la lista del paciente
        // Esto simula una relación bidireccional o una forma de mantener la
        // consistencia
        if (cita.getPaciente() != null) {
            cita.getPaciente().agregarCita(cita);
        }
        citas.put(cita.getId(), cita);
    }

    @Override
    public Optional<Cita> buscarPorId(Long id) {
        return Optional.ofNullable(citas.get(id));
    }

    @Override
    public List<Cita> buscarTodos() {
        // Devolvemos una nueva lista para evitar modificaciones externas directas
        return new ArrayList<>(citas.values());
    }

    @Override
    public List<Cita> buscarPorPaciente(Paciente paciente) {
        return citas.values().stream()
                .filter(cita -> cita.getPaciente() != null && cita.getPaciente().equals(paciente))
                .collect(Collectors.toList());
    }

    @Override
    public List<Cita> buscarPorMedico(Medico medico) {
        return citas.values().stream()
                .filter(cita -> cita.getMedico() != null && cita.getMedico().equals(medico))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorFechaYMedico(LocalDateTime fechaHora, Medico medico) {
        return citas.values().stream()
                .anyMatch(cita -> cita.getMedico() != null && cita.getMedico().equals(medico) &&
                        cita.getFechaHora() != null && cita.getFechaHora().equals(fechaHora) &&
                        !"CANCELADA".equals(cita.getEstado()) // No consideramos citas canceladas como duplicadas
                );
    }

    // Método auxiliar para generar IDs únicos
    private synchronized Long generarSiguienteId() {
        return siguienteId++;
    }

}
