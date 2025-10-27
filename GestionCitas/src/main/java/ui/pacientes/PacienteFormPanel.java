package ui.pacientes;

import javax.swing.*;

import com.mycompany.gestioncitas.Servicio.PacienteService;
import com.mycompany.gestioncitas.Servicio.Validator.ValidationException;

import modelo.Paciente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacienteFormPanel extends JPanel {

    private final PacienteService pacienteService;

    // Componentes de la UI
    private JTextField nombreField;
    private JTextField documentoField;
    private JTextField telefonoField;
    private JTextField direccionField;
    private JButton registrarButton;

    public PacienteFormPanel(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
        initComponents();
        layoutComponents();
        addListeners();
    }

    

    private void initComponents() {
        // Crear campos y etiquetas
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(20);

        JLabel documentoLabel = new JLabel("Documento:");
        documentoField = new JTextField(10);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoField = new JTextField(15);

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionField = new JTextField(30);

        // Btnn de registro
        registrarButton = new JButton("Registrar Paciente");
    }

    private void layoutComponents() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // fila nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(nombreField, gbc);

        // fila documento
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(new JLabel("Documento:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(documentoField, gbc);

        // fila telefono
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(telefonoField, gbc);

        // fila direccion
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        add(new JLabel("Dirección:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(direccionField, gbc);

        // fila btn regitrar
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST; // Alinear a la derecha
        add(registrarButton, gbc);

        // añade un panel vacío al final para empujar todo hacia arriba si hay espacio
        // extra
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weighty = 1.0;
        add(new JPanel(), gbc);
    }

    private void addListeners() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPaciente();
            }
        });
    }

    private void registrarPaciente() {
        // 1. Obtener los datos de los campos
        String nombre = nombreField.getText().trim();
        String documento = documentoField.getText().trim();
        String telefono = telefonoField.getText().trim();
        String direccion = direccionField.getText().trim();

        // 2. Validar campos básicos
        if (nombre.isEmpty() || documento.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Nombre y Documento son campos obligatorios.",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // crear el objeto Paciente
        Paciente nuevoPaciente = new Paciente(null, nombre, documento, telefono, direccion);

        // Intenta registrar el paciente a través del servicio
        try {
            pacienteService.registrarPaciente(nuevoPaciente);
            JOptionPane.showMessageDialog(this,
                    "Paciente '" + nombre + " " + "' registrado exitosamente.",
                    "Registro Exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiarCampos();
        } catch (ValidationException ve) {

            JOptionPane.showMessageDialog(this,
                    ve.getMessage(),
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    "Ocurrió un error al registrar el paciente: " + ex.getMessage(),
                    "Error General",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        nombreField.setText("");
        documentoField.setText("");
        telefonoField.setText("");
        direccionField.setText("");
        nombreField.requestFocus(); // pone el foco en el primer campo
    }
}
