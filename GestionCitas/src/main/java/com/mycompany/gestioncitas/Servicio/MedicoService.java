package com.mycompany.gestioncitas.Servicio;

import java.util.ArrayList;
import java.util.List;

import modelo.Medico;

public class MedicoService {

    private final List<Medico> medicos = new ArrayList<>();
    private Long siguienteId = 1L;

    public void registrarMedico(Medico medico) {
        medico.setId(siguienteId++);
        medicos.add(medico);
    }

    public List<Medico> obtenerTodos() {
        return new ArrayList<>(medicos);
    }

    public Medico buscarPorId(Long id) {
        return medicos.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
