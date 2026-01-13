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
    private JComboBox cbRutaRecorrido;
    private JComboBox cbChoferRecorrido;
    private JComboBox cbTipoRecorrido;
    private JComboBox cbBusRecorrido;
    private JComboBox cbReemplazoRecorrido;
    private JCheckBox checkBox1;
    private JButton btnIniciarRecorrido;
    private JButton btnPausar;
    private JButton btnFinalizar;

    private GestionEstudiantes gestorEst;
    private GestionParadas gestorPar;
    private GestionRutas gestorRut;
    private GestionAsignacion gestorAsign;
    private List<Bus> buses;
    private List<Conductor>  conductores;

    public PlataformaEduRoute() {
        inicializarGestores();
        cargarDatosPrecargados();
    }
        private void inicializarGestores() {
            gestorEst = new GestionEstudiantes();
            gestorPar = new GestionParadas();
            gestorRut = new GestionRutas();
            gestorAsign = new GestionAsignacion(gestorEst, gestorPar, gestorRut);
            buses = new ArrayList<>();
            conductores = new ArrayList<>();
        }

        private void cargarDatosPrecargados(){

            conductores.add(new Conductor("C001", "Juan Pérez"));
            conductores.add(new Conductor("C002", "María González"));
            conductores.add(new Conductor("C003", "Pedro Rodríguez"));
            conductores.add(new Conductor("C004", "Ana Martínez"));
            conductores.add(new Conductor("C005", "Luis Sánchez"));
            conductores.add(new Conductor("C006", "Carmen López"));
            conductores.add(new Conductor("C007", "Roberto Torres"));
            conductores.add(new Conductor("C008", "Sandra Morales"));

            buses.add(new Bus("B001", "ABC-123"));
            buses.add(new Bus("B002", "DEF-456"));
            buses.add(new Bus("B003", "GHI-789"));
            buses.add(new Bus("B004", "JKL-012"));
            buses.add(new Bus("B005", "MNO-345"));
            buses.add(new Bus("B006", "PQR-678"));
            buses.add(new Bus("B007", "STU-901"));
            buses.add(new Bus("B008", "VWX-234"));

            for (int i = 0; i < buses.size() && i < conductores.size(); i++) {
                buses.get(i).asignarConductor(conductores.get(i));
            }

            cargarRutasPrecargadas();
            cargarEstudiantesPrecargados();
        }

    private void cargarRutasPrecargadas() {
        // Ruta 1 - Norte
        Ruta ruta1 = new Ruta("R001", "Ruta Norte", "1", "Norte",
                Arrays.asList("Carcelén", "Ponceano", "El Condado", "La Ofelia", "Cotocollao"));
        Parada p1_1 = new Parada("P001", "Carcelén Centro", 5, "Av. Eloy Alfaro y Carcelén");
        Parada p1_2 = new Parada("P002", "Ponceano Alto", 8, "Calle Principal Ponceano");
        Parada p1_3 = new Parada("P003", "El Condado", 10, "Av. Mariscal Sucre y Condado");
        ruta1.agregarParada(p1_1);
        ruta1.agregarParada(p1_2);
        ruta1.agregarParada(p1_3);
        gestorPar.registrarParada(p1_1);
        gestorPar.registrarParada(p1_2);
        gestorPar.registrarParada(p1_3);
        gestorRut.registrarRuta(ruta1);

        // Ruta 2 - Centro-Norte
        Ruta ruta2 = new Ruta("R002", "Ruta Centro-Norte", "2", "Centro-Norte",
                Arrays.asList("La Gasca", "Bellavista", "Iñaquito", "El Batán", "González Suárez"));
        Parada p2_1 = new Parada("P004", "La Gasca", 6, "Av. La Gasca y América");
        Parada p2_2 = new Parada("P005", "Iñaquito", 7, "Av. Naciones Unidas y Iñaquito");
        ruta2.agregarParada(p2_1);
        ruta2.agregarParada(p2_2);
        gestorPar.registrarParada(p2_1);
        gestorPar.registrarParada(p2_2);
        gestorRut.registrarRuta(ruta2);

        // Ruta 3 - Centro
        Ruta ruta3 = new Ruta("R003", "Ruta Centro", "3", "Centro",
                Arrays.asList("La Marín", "San Blas", "La Tola", "Itchimbía", "San Roque"));
        Parada p3_1 = new Parada("P006", "La Marín", 5, "Plaza La Marín");
        Parada p3_2 = new Parada("P007", "San Blas", 8, "Barrio San Blas");
        ruta3.agregarParada(p3_1);
        ruta3.agregarParada(p3_2);
        gestorPar.registrarParada(p3_1);
        gestorPar.registrarParada(p3_2);
        gestorRut.registrarRuta(ruta3);

        // Ruta 4 - Sur
        Ruta ruta4 = new Ruta("R004", "Ruta Sur", "4", "Sur",
                Arrays.asList("Chimbacalle", "El Recreo", "La Magdalena", "Quitumbe", "Guamaní"));
        Parada p4_1 = new Parada("P008", "Chimbacalle", 7, "Av. Maldonado y Chimbacalle");
        Parada p4_2 = new Parada("P009", "El Recreo", 10, "Estación El Recreo");
        ruta4.agregarParada(p4_1);
        ruta4.agregarParada(p4_2);
        gestorPar.registrarParada(p4_1);
        gestorPar.registrarParada(p4_2);
        gestorRut.registrarRuta(ruta4);

        // Ruta 5 - Valle de los Chillos
        Ruta ruta5 = new Ruta("R005", "Ruta Valle Chillos", "5", "Valle de los Chillos",
                Arrays.asList("Conocoto", "San Rafael", "Alangasí"));
        Parada p5_1 = new Parada("P010", "Conocoto", 12, "Plaza Conocoto");
        Parada p5_2 = new Parada("P011", "San Rafael", 15, "Centro San Rafael");
        ruta5.agregarParada(p5_1);
        ruta5.agregarParada(p5_2);
        gestorPar.registrarParada(p5_1);
        gestorPar.registrarParada(p5_2);
        gestorRut.registrarRuta(ruta5);

        // Ruta 6 - Valle de Tumbaco
        Ruta ruta6 = new Ruta("R006", "Ruta Valle Tumbaco", "6", "Valle de Tumbaco",
                Arrays.asList("Cumbayá", "Tumbaco", "Puembo"));
        Parada p6_1 = new Parada("P012", "Cumbayá", 10, "Plaza Cumbayá");
        Parada p6_2 = new Parada("P013", "Tumbaco", 13, "Centro Tumbaco");
        ruta6.agregarParada(p6_1);
        ruta6.agregarParada(p6_2);
        gestorPar.registrarParada(p6_1);
        gestorPar.registrarParada(p6_2);
        gestorRut.registrarRuta(ruta6);

        // Ruta 7 - Noroeste
        Ruta ruta7 = new Ruta("R007", "Ruta Noroeste", "7", "Noroeste",
                Arrays.asList("Pomasqui", "San Antonio de Pichincha", "Calderón"));
        Parada p7_1 = new Parada("P014", "Pomasqui", 18, "Centro Pomasqui");
        Parada p7_2 = new Parada("P015", "Calderón", 15, "Plaza Calderón");
        ruta7.agregarParada(p7_1);
        ruta7.agregarParada(p7_2);
        gestorPar.registrarParada(p7_1);
        gestorPar.registrarParada(p7_2);
        gestorRut.registrarRuta(ruta7);

        // Ruta 8 - Suroriental
        Ruta ruta8 = new Ruta("R008", "Ruta Suroriental", "8", "Suroriental",
                Arrays.asList("Amaguaña", "Alóag", "Tambillo"));
        Parada p8_1 = new Parada("P016", "Amaguaña", 20, "Centro Amaguaña");
        Parada p8_2 = new Parada("P017", "Tambillo", 22, "Plaza Tambillo");
        ruta8.agregarParada(p8_1);
        ruta8.agregarParada(p8_2);
        gestorPar.registrarParada(p8_1);
        gestorPar.registrarParada(p8_2);
        gestorRut.registrarRuta(ruta8);
    }

    private void cargarEstudiantesPrecargados(){
        Estudiante[] estudiantesPrecargados = {
                new Estudiante("E001", "Juan García", "5to A", "Carcelén Alto", "Alta"),
                new Estudiante("E002", "María López", "6to B", "Ponceano", "Media"),
                new Estudiante("E003", "Carlos Ruiz", "7mo A", "El Condado", "Alta"),
                new Estudiante("E004", "Ana Morales", "5to B", "La Gasca", "Media"),
                new Estudiante("E005", "Luis Fernández", "6to A", "Iñaquito", "Baja"),
                new Estudiante("E006", "Carmen Silva", "7mo B", "La Marín", "Alta"),
                new Estudiante("E007", "Roberto Díaz", "5to A", "San Blas", "Media"),
                new Estudiante("E008", "Patricia Vega", "6to B", "Chimbacalle", "Alta")
        };

        for (Estudiante est : estudiantesPrecargados) {
            gestorEst.registrarEstudiante(est);
        }
        gestorAsign.asignarParadaEstudiante("E001", "P001");
        gestorAsign.asignarRutaAEstudiante("E001", "1", "Carcelén");

        gestorAsign.asignarParadaEstudiante("E002", "P002");
        gestorAsign.asignarRutaAEstudiante("E002", "1", "Ponceano");

        gestorAsign.asignarParadaEstudiante("E003", "P003");
        gestorAsign.asignarRutaAEstudiante("E003", "1", "El Condado");

        gestorAsign.asignarParadaEstudiante("E004", "P004");
        gestorAsign.asignarRutaAEstudiante("E004", "2", "La Gasca");

        gestorAsign.asignarParadaEstudiante("E005", "P005");
        gestorAsign.asignarRutaAEstudiante("E005", "2", "Iñaquito");

        gestorAsign.asignarParadaEstudiante("E006", "P006");
        gestorAsign.asignarRutaAEstudiante("E006", "3", "La Marín");

        gestorAsign.asignarParadaEstudiante("E007", "P007");
        gestorAsign.asignarRutaAEstudiante("E007", "3", "San Blas");

        gestorAsign.asignarParadaEstudiante("E008", "P008");
        gestorAsign.asignarRutaAEstudiante("E008", "4", "Chimbacalle");
    }


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

