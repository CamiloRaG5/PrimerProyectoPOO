package ui.pacientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mycompany.gestioncitas.Servicio.PacienteService;

import modelo.Paciente;

import java.awt.*;
import java.util.List;

public class PacienteListPanel extends JPanel {

    private final PacienteService pacienteService;
    private JTable pacienteTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    // columnas que mostraremos en la tabla
    private static final String[] COLUMNAS = { "ID", "Nombre", "Documento", "Teléfono", "Dirección" };

    public PacienteListPanel(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
        initComponents();
        layoutComponents();
        cargarDatosTabla();
    }

    private void initComponents() {
        // Crear el modelo de tabla con las columnas definidas
        tableModel = new DefaultTableModel(COLUMNAS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Crear la tabla con el modelo
        pacienteTable = new JTable(tableModel);
        pacienteTable.setFillsViewportHeight(true);

        // mantener formato en tabla
        pacienteTable.getTableHeader().setReorderingAllowed(false);
        pacienteTable.setRowHeight(25);

        // creamos el JScrollPane para que la tabla sea scrollable si hay muchos datos
        scrollPane = new JScrollPane(pacienteTable);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Carga los datos de los pacientes desde el servicio y los actualiza en la
     * tabla.
     */
    public void cargarDatosTabla() {
        // Limpiar el modelo de tabla
        tableModel.setRowCount(0);

        // Obtener la lista de pacientes desde el servicio
        List<Paciente> pacientes = pacienteService.obtenerTodos();

        // Añadir cada paciente a una nueva fila del modelo de tabla
        for (Paciente paciente : pacientes) {
            Object[] rowData = {
                    paciente.getId(),
                    paciente.getNombre(),
                    paciente.getDocumento(),
                    paciente.getTelefono(),
                    paciente.getDireccion()
            };
            tableModel.addRow(rowData);
        }
    }

    public void refrescarTabla() {
        cargarDatosTabla();
    }
}
