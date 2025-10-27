package ui.medicos;

import javax.swing.*;

import com.mycompany.gestioncitas.Servicio.EspecialidadService;
import com.mycompany.gestioncitas.Servicio.MedicoService;
import com.mycompany.gestioncitas.Servicio.Validator.ValidationException;

import modelo.Especialidad;
import modelo.Medico;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MedicoFormPanel extends JPanel {

    private final MedicoService medicoService;
    private final EspecialidadService especialidadService; // Para obtener la lista de especialidades

    // Componentes de la UI
    private JTextField nombreField;
    private JTextField documentoField;
    private JTextField matriculaField;
    private JComboBox<Especialidad> especialidadComboBox; // ComboBox para seleccionar especialidad
    private JButton registrarButton;

    public MedicoFormPanel(MedicoService medicoService, EspecialidadService especialidadService) {
        this.medicoService = medicoService;
        this.especialidadService = especialidadService; // Inicializar el servicio de especialidades
        initComponents();
        layoutComponents();
        addListeners();
        cargarEspecialidades(); // Cargar las especialidades al inicializar
    }

    private void initComponents() {
        // Crear campos y etiquetas
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(20);
        
        JLabel documentoLabel = new JLabel("Documento:");
        documentoField = new JTextField(10);

        JLabel matriculaLabel = new JLabel("Matrícula:");
        matriculaField = new JTextField(15);

        JLabel especialidadLabel = new JLabel("Especialidad:");
        especialidadComboBox = new JComboBox<>(); // Inicializar el ComboBox

        // Botón de registro
        registrarButton = new JButton("Registrar Médico");
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado
        gbc.fill = GridBagConstraints.HORIZONTAL; // Rellenar horizontalmente

        //Filas
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2; add(nombreField, gbc);


        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1; add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2; add(documentoField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1; add(new JLabel("Matrícula:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 2; add(matriculaField, gbc);

        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1; add(especialidadComboBox, gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 2; add(especialidadComboBox, gbc);

        //Botón Registrar
        gbc.gridx = 1; gbc.gridy = 5; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.EAST;
        add(registrarButton, gbc);

        // Panel vacío para empujar el contenido hacia arriba
        gbc.gridx = 0; gbc.gridy = 6; gbc.weighty = 1.0;
        add(new JPanel(), gbc);
    }

    private void addListeners() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarMedico();
            }
        });
    }

    // Método para cargar las especialidades en el ComboBox
    private void cargarEspecialidades() {
        // Nota: Necesitaremos un servicio de EspecialidadService. Por ahora, lo simulamos.
        // Si no existe, crea una clase simple EspecialidadService con un método obtenerTodos() que devuelva algunas especialidades de prueba.
        List<Especialidad> especialidades = especialidadService.obtenerTodos(); // Asumiendo que este método existe
        especialidadComboBox.removeAllItems(); // Limpiar por si acaso
        for (Especialidad esp : especialidades) {
            especialidadComboBox.addItem(esp); // Añade el objeto Especialidad directamente
        }
    }


    private void registrarMedico() {
        // 1. Obtener los datos de los campos
        String nombre = nombreField.getText().trim();
        String documento = documentoField.getText().trim();
        String matricula = matriculaField.getText().trim();
        Especialidad especialidadSeleccionada = (Especialidad) especialidadComboBox.getSelectedItem();

        // 2. Validar campos básicos
        if (nombre.isEmpty() || documento.isEmpty() || matricula.isEmpty() || especialidadSeleccionada == null) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios.",
                    "Error de Validación",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Crear el objeto Medico
        // El ID se asignará automáticamente en el service/repository
        Medico nuevoMedico = new Medico(null, nombre, documento, matricula, especialidadSeleccionada);

        // 4. Intentar registrar el médico a través del servicio
        try {
            medicoService.registrarMedico(nuevoMedico); // Asumiendo que este método existe en MedicoService
            JOptionPane.showMessageDialog(this,
                    "Médico '" + nombre + "' registrado exitosamente.",
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
                    "Ocurrió un error al registrar el médico: " + ex.getMessage(),
                    "Error General",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        nombreField.setText("");
        documentoField.setText("");
        matriculaField.setText("");
        especialidadComboBox.setSelectedIndex(0); // Seleccionar la primera especialidad por defecto
        nombreField.requestFocus();
    }
}
