package com.mycompany.gestioncitas.Servicio;

import java.util.ArrayList;
import java.util.List;

import modelo.Especialidad;

public class EspecialidadService {
    // Simulación de datos en memoria
    private final List<Especialidad> especialidades = new ArrayList<>();
    private Long siguienteId = 1L;

    public EspecialidadService() {
        // Llenar con datos de ejemplo
        especialidades.add(new Especialidad(siguienteId++, "Cardiología"));
        especialidades.add(new Especialidad(siguienteId++, "Pediatría"));
        especialidades.add(new Especialidad(siguienteId++, "Dermatología"));
        especialidades.add(new Especialidad(siguienteId++, "Neurología"));
    }

    public List<Especialidad> obtenerTodos() {
        return new ArrayList<>(especialidades);
    }

    public Especialidad buscarPorId(Long id) {
        return especialidades.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
