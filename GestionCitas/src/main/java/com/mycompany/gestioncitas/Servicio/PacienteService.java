package com.mycompany.gestioncitas.Servicio;

import java.util.ArrayList;
import java.util.List;

import modelo.Paciente;

public class PacienteService {

    private final List<Paciente> pacientes = new ArrayList<>();
    private Long siguienteId = 1L;

    public void registrarPaciente(Paciente paciente) {
        paciente.setId(siguienteId++);
        pacientes.add(paciente);
    }

    public List<Paciente> obtenerTodos() {
        return new ArrayList<>(pacientes);
    }

    public Paciente buscarPorId(Long id) {
        return pacientes.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
