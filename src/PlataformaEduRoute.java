import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlataformaEduRoute{
    private JPanel Ventana;
    private JTabbedPane tabbedPane1;
    private JTextField txtIdEstudiante;
    private JTextField txtNombreEstudiante;
    private JTextField txtCurso;
    private JTextField txtDireccion;
    private JComboBox cbPrioridad;
    private JButton btnRegistrarEstudiante;
    private JComboBox cbRutaEstudiante;
    private JComboBox cbParadaEstudiante;
    private JButton btnEliminarEstudiante;
    private JButton btnModificarEstudiante;
    private JButton btnLimpiarEstudiante;
    private JButton btnbuscarEstudiante;
    private JComboBox cbSeleccionRuta;
    private JTextField txtIdParada;
    private JTextField txtNombreParada;
    private JSpinner spinTiempoParada;
    private JTextField txtUbicacionParada;
    private JComboBox cbRutaAsignada;
    private JButton btnRegistrarParada;
    private JButton btnModificarParada;
    private JButton eliminarParadaButton;
    private JButton btnLimpiarParada;
    private JTextField txtIdBus;
    private JTextField txtPlacaBus;
    private JSpinner spinCapacidadBus;
    private JComboBox cbEstadoBus;
    private JButton btnRegistrarBus;
    private JButton btnEliminarBus;
    private JTextField txtIdConductor;
    private JComboBox cbEstadoConductor;
    private JTextField txtNombreConductor;
    private JButton btnRegistrarConductor;
    private JButton btnEliminarConductor;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JComboBox comboBox7;
    private JCheckBox checkBox1;
    private JButton btnIniciarRecorrido;
    private JButton pausarButton;
    private JButton finalizarButton;

    private GestionEstudiantes gestorEst;
    private GestionParadas gestorPar;
    private GestionRutas gestorRut;
    private GestionAsignacion gestorAsign;
    private List<Bus> buses;
    private List<Conductor>  conductores;

    public PlataformaEduRoute(){

        btnRegistrarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idEst = txtIdEstudiante.getText().trim();
                if (idEst.isEmpty()) {
                    JOptionPane.showMessageDialog(Ventana, "Ingrese una ID valida.");
                    txtIdEstudiante.requestFocus();
                    return;}

                String nombre = txtIdEstudiante.getText().trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(Ventana, "Ingrese un nombre valido.");
                    txtIdEstudiante.requestFocus();
                    return;
                }

                String curso = txtCurso.getText().trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(Ventana, "Ingrese un curso valido.");
                    txtIdEstudiante.requestFocus();
                    return;
                }

                String direccion = txtDireccion.getText().trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(Ventana, "Ingrese una direccion valida.");
                    txtDireccion.requestFocus();
                    return;
                }

                String prioridad =(cbPrioridad.getSelectedItem() != null)
                        ? cbPrioridad.getSelectedItem().toString()
                        : "";
                if (prioridad.isEmpty()) {
                    JOptionPane.showMessageDialog(Ventana, "Seleccione prioridad.");
                    cbPrioridad.requestFocus();
                    return;
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PlataformaEduRoute");
        frame.setContentPane(new PlataformaEduRoute().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

