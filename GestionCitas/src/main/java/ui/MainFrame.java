package ui;

import javax.swing.*;
import com.mycompany.gestioncitas.Servicio.CitaService;
import com.mycompany.gestioncitas.Servicio.EspecialidadService;
import com.mycompany.gestioncitas.Servicio.MedicoService;
import com.mycompany.gestioncitas.Servicio.PacienteService;

import ui.medicos.MedicoFormPanel;
import ui.medicos.MedicoListPanel;
import ui.pacientes.PacienteFormPanel;
import ui.pacientes.PacienteListPanel;

import java.awt.*;

public class MainFrame extends JFrame {

    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final CitaService citaService;
    private MedicoFormPanel medicoFormPanel;
    private MedicoListPanel medicoListPanel;
    private EspecialidadService especialidadService;

    // Panel principal donde se mostrará el contenido de cada sección
    private JPanel contentPanel;
    private PacienteFormPanel pacienteFormPanel;
    private PacienteListPanel pacienteListPanel;

    // Menús
    private JMenuBar menuBar;
    private JMenu pacientesMenu;
    private JMenu medicosMenu;
    private JMenu citasMenu;
    private JMenu ayudaMenu;

    public MainFrame(PacienteService pacienteService, MedicoService medicoService, CitaService citaService,
            EspecialidadService especialidadService) {
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
        this.citaService = citaService;
        this.especialidadService = especialidadService;

        initComponents();
        layoutComponents();
        addListeners();
    }

    private void initComponents() {
        // Configuración de la barra de menú
        menuBar = new JMenuBar();

        pacientesMenu = new JMenu("Pacientes");
        medicosMenu = new JMenu("Médicos");
        citasMenu = new JMenu("Citas");
        ayudaMenu = new JMenu("Ayuda");

        pacienteFormPanel = new PacienteFormPanel(pacienteService);
        pacienteListPanel = new PacienteListPanel(pacienteService);
        medicoFormPanel = new MedicoFormPanel(medicoService, especialidadService);
        medicoListPanel = new MedicoListPanel(medicoService);

        // Items de menú para Pacientes
        JMenuItem registrarPacienteItem = new JMenuItem("Registrar Paciente");
        JMenuItem listarPacientesItem = new JMenuItem("Listar Pacientes");
        pacientesMenu.add(registrarPacienteItem);
        pacientesMenu.add(listarPacientesItem);

        // Items de menú para Médicos
        JMenuItem registrarMedicoItem = new JMenuItem("Registrar Médico");
        JMenuItem listarMedicosItem = new JMenuItem("Listar Médicos");
        medicosMenu.add(registrarMedicoItem);
        medicosMenu.add(listarMedicosItem);

        // Items de menú para Citas
        JMenuItem agendarCitaItem = new JMenuItem("Agendar Cita");
        JMenuItem verAgendaItem = new JMenuItem("Ver Agenda");
        JMenuItem completarCitaItem = new JMenuItem("Completar Cita");
        citasMenu.add(agendarCitaItem);
        citasMenu.add(verAgendaItem);
        citasMenu.add(completarCitaItem);

        // Item de menú para Ayuda
        JMenuItem acercaDeItem = new JMenuItem("Acerca de");
        ayudaMenu.add(acercaDeItem);

        // Añadir menús a la barra
        menuBar.add(pacientesMenu);
        menuBar.add(medicosMenu);
        menuBar.add(citasMenu);
        menuBar.add(ayudaMenu);

        // Panel de contenido principal
        contentPanel = new JPanel(new CardLayout());
    }

    private void layoutComponents() {
        setJMenuBar(menuBar);
        // Añadir el panel de contenido al centro
        add(contentPanel, BorderLayout.CENTER);
    }

    private void addListeners() {
        // Listeners para Pacientes
        JMenuItem registrarPacienteItem = pacientesMenu.getItem(0);
        JMenuItem listarPacientesItem = pacientesMenu.getItem(1);
        registrarPacienteItem.addActionListener(e -> showPanel(pacienteFormPanel));
        listarPacientesItem.addActionListener(e -> showPanel(pacienteListPanel));

        pacientesMenu.getItem(1).addActionListener(e -> showPanel(new JPanel()));

        // --- Listeners para Médicos ---
        JMenuItem registrarMedicoItem = medicosMenu.getItem(0); // "Registrar Médico"
        JMenuItem listarMedicosItem = medicosMenu.getItem(1); // "Listar Médicos"
        registrarMedicoItem.addActionListener(e -> showPanel(medicoFormPanel)); // Mostrar panel de registro de médico
        listarMedicosItem.addActionListener(e -> showPanel(medicoListPanel)); // Mostrar panel de lista de médicos

        // Listeners para Citas
        JMenuItem agendarCitaItem = citasMenu.getItem(0); // "Agendar Cita"
        JMenuItem verAgendaItem = citasMenu.getItem(1); // "Ver Agenda"
        JMenuItem completarCitaItem = citasMenu.getItem(2); // "Completar Cita"

        agendarCitaItem.addActionListener(e -> showPanel(new JPanel())); // TODO: Crear AgendarCitaPanel
        verAgendaItem.addActionListener(e -> showPanel(new JPanel())); // TODO: Crear AgendaPanel
        completarCitaItem.addActionListener(e -> showPanel(new JPanel())); // TODO: Crear CompletarCitaPanel

        // Listener para Ayuda
        ayudaMenu.getItem(0).addActionListener(e -> showMessageDialog(
                "Sistema de Gestión Clínica"));
    }

    // muestra unn nuevo panel
    private void showPanel(JPanel panel) {
        CardLayout cl = (CardLayout) contentPanel.getLayout();
        contentPanel.removeAll();
        contentPanel.add(panel); // Añadir el nuevo panel
        cl.show(contentPanel, "content"); // Mostrar el contenido
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }

}
