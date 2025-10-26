package Repositorio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import modelo.Cita;
import modelo.Medico;
import modelo.Paciente;

public interface ICitaRepositorio {

    /**
     * Guarda una nueva cita o actualiza una existente.
     * Si la cita no tiene ID, se le asignará uno nuevo.
     * 
     * @param cita La cita a guardar.
     */
    void guardar(Cita cita);

    /**
     * Busca una cita por su identificador único.
     * 
     * @param id El ID de la cita a buscar.
     * @return Un Optional que contiene la cita si se encuentra, o un Optional vacío
     *         si no.
     */
    Optional<Cita> buscarPorId(Long id);

    /**
     * Devuelve una lista con todas las citas registradas en el sistema.
     * 
     * @return Una lista de todas las citas.
     */
    List<Cita> buscarTodos();

    /**
     * Busca todas las citas asociadas a un paciente específico.
     * 
     * @param paciente El paciente cuyas citas se buscan.
     * @return Una lista de citas del paciente.
     */
    List<Cita> buscarPorPaciente(Paciente paciente);

    /**
     * Busca todas las citas asociadas a un médico específico.
     * 
     * @param medico El médico cuyas citas se buscan.
     * @return Una lista de citas del médico.
     */
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

    // Podríamos añadir más métodos según la necesidad, como:
    // buscarPorFecha(LocalDateTime fecha), etc.

}
