package Repositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import modelo.Cita;
import modelo.Medico;
import modelo.Paciente;

public interface ICitaRepositorio {

    // guarda una nueva cita
    void guardar(Cita cita);

    // busca una cita con su id
    Optional<Cita> buscarPorId(Long id);

    // Devuelve una lista con todas las citas registradas en el sistema.
    List<Cita> buscarTodos();

    // Busca todas las citas asociadas a un paciente específico.
    List<Cita> buscarPorPaciente(Paciente paciente);

    // Busca todas las citas asociadas a un médico específico
    List<Cita> buscarPorMedico(Medico medico);

    /**
     * Verifica si existe una cita para un médico en una fecha y hora específicas.
     * Se considera si la cita existe y no está cancelada.
     * 
     * @param fechaHora La fecha y hora a verificar.
     * @param medico    El médico a verificar.
     * @return true si existe una cita duplicada o solapada, false en caso
     *         contrario.
     */
    boolean existePorFechaYMedico(LocalDateTime fechaHora, Medico medico);

}
