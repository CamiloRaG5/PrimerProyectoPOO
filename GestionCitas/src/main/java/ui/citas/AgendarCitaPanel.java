package ui.citas;

import javax.swing.*;

import com.mycompany.gestioncitas.Servicio.CitaService;
import com.mycompany.gestioncitas.Servicio.MedicoService;
import com.mycompany.gestioncitas.Servicio.PacienteService;
import com.mycompany.gestioncitas.Servicio.Validator.ValidationException;

import modelo.Cita;
import modelo.Medico;
import modelo.Paciente;

import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AgendarCitaPanel extends JPanel {

    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final CitaService citaService;

    private JComboBox<Paciente> pacienteCombo;
    private JComboBox<Medico> medicoCombo;
    private JSpinner fechaSpinner;
    private JButton agendarButton;

    public AgendarCitaPanel(PacienteService pacienteService, MedicoService medicoService, CitaService citaService) {
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
        this.citaService = citaService;

        initComponents();
        layoutComponents();
        addListeners();
        cargarCombos();
    }

    private void initComponents() {
        pacienteCombo = new JComboBox<>();
        medicoCombo = new JComboBox<>();

        // Spinner para fecha/hora
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE);
        fechaSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(fechaSpinner, "yyyy-MM-dd HH:mm");
        fechaSpinner.setEditor(timeEditor);

        agendarButton = new JButton("Agendar Cita");
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Paciente:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(pacienteCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Médico:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(medicoCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Fecha y hora:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(fechaSpinner, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(agendarButton, gbc);

        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        add(new JPanel(), gbc);
    }

    private void addListeners() {
        agendarButton.addActionListener(e -> {
            try {
                agendarCita();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    private void cargarCombos() {
        pacienteCombo.removeAllItems();
        List<Paciente> pacientes = pacienteService.obtenerTodos();
        for (Paciente p : pacientes)
            pacienteCombo.addItem(p);

        medicoCombo.removeAllItems();
        List<Medico> medicos = medicoService.obtenerTodos();
        for (Medico m : medicos)
            medicoCombo.addItem(m);
    }

    private void agendarCita() {
        Paciente paciente = (Paciente) pacienteCombo.getSelectedItem();
        Medico medico = (Medico) medicoCombo.getSelectedItem();
        Date fechaDate = (Date) fechaSpinner.getValue();
        LocalDateTime fechaHora = LocalDateTime.ofInstant(fechaDate.toInstant(), ZoneId.systemDefault());

        // Validaciones básicas de UI
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un paciente.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (medico == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un médico.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (fechaHora.isBefore(LocalDateTime.now())) {
            JOptionPane.showMessageDialog(this, "La fecha/hora debe ser futura.", "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Cita nueva = new Cita(null, fechaHora, null, paciente, medico);

        try {
            citaService.agendarCita(nueva);
            JOptionPane.showMessageDialog(this, "Cita agendada correctamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
        } catch (ValidationException ve) {
            JOptionPane.showMessageDialog(this, ve.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar la cita: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        if (pacienteCombo.getItemCount() > 0)
            pacienteCombo.setSelectedIndex(0);
        if (medicoCombo.getItemCount() > 0)
            medicoCombo.setSelectedIndex(0);
        fechaSpinner.setValue(new Date());
    }

    // Método público para recargar combos desde afuera si se agregan
    // pacientes/medicos nuevos
    public void refrescarDatos() {
        cargarCombos();
    }
}