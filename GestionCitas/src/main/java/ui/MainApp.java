package ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.mycompany.gestioncitas.Servicio.CitaService;
import com.mycompany.gestioncitas.Servicio.MedicoService;
import com.mycompany.gestioncitas.Servicio.PacienteService;

import Repositorio.CitaRepositorioMemoria;
import Repositorio.ICitaRepositorio;

public class MainApp {

    public static void main(String[] args) {
        // 1. Inicializar el Repositorio (en memoria por ahora)
        ICitaRepositorio citaRepositorio = new CitaRepositorioMemoria();

        //Inicializar los Servicios, inyectando las dependencias
        CitaService citaService = new CitaService(citaRepositorio);

        //estos usan listas internas
        PacienteService pacienteService = new PacienteService();
        MedicoService medicoService = new MedicoService();

        // configurar y mostrar la ventana principal
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(pacienteService, medicoService, citaService);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("Sistema de Gestión Clínica");
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // Centrar la ventana
            frame.setVisible(true);
        });
    }

}
