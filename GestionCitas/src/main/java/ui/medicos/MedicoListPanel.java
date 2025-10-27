package ui.medicos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.mycompany.gestioncitas.Servicio.MedicoService;

import modelo.Especialidad;
import modelo.Medico;

import java.awt.*;
import java.util.List;

public class MedicoListPanel extends JPanel {

    private final MedicoService medicoService;
    private JTable medicoTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;

    // Columnas para la tabla de médicos
    private static final String[] COLUMNAS = {"ID", "Nombre", "Documento", "Matrícula", "Especialidad"};

    public MedicoListPanel(MedicoService medicoService) {
        this.medicoService = medicoService;
        initComponents();
        layoutComponents();
        cargarDatosTabla(); 
    }

    private void initComponents() {
        tableModel = new DefaultTableModel(COLUMNAS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // No permitir edición en la tabla
            }
        };
        medicoTable = new JTable(tableModel);
        medicoTable.setFillsViewportHeight(true);
        medicoTable.getTableHeader().setReorderingAllowed(false);
        medicoTable.setRowHeight(25);

        scrollPane = new JScrollPane(medicoTable);
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Carga los datos de los médicos desde el servicio y los actualiza en la tabla.
     */
    public void cargarDatosTabla() {
        // Limpiar tabla
        tableModel.setRowCount(0); 

        List<Medico> medicos = medicoService.obtenerTodos(); 

        for (Medico medico : medicos) {
            Especialidad especialidad = medico.getEspecialidad();
            String nombreEspecialidad = (especialidad != null) ? especialidad.getNombre() : "Sin asignar";

            Object[] rowData = {
                    medico.getId(),
                    medico.getNombre(),
                    medico.getDocumento(),
                    medico.getMatricula(),

                    // Mostrar el nombre de la especialidad
                    nombreEspecialidad 
            };
            tableModel.addRow(rowData);
        }
    }

    //refrescar datos
    public void refrescarTabla() {
        cargarDatosTabla();
    }
}
