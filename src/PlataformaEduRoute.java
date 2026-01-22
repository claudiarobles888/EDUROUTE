import Estructura.*;
import Negocio.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlataformaEduRoute {
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
    private JButton btnEliminarParada;
    private JButton btnLimpiarParada;
    private JTextField txtIdBus;
    private JTextField txtPlacaBus;
    private JComboBox cbCapacidadBus;
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
    private JButton btnIniciarRecorrido;
    private JButton btnAsignarConductor;
    private JButton btnLiberarConductor;
    private JLabel lblNombreRuta;
    private JLabel lblZonaRuta;
    private JLabel lblNumeroRuta;
    private JLabel lblTotalParadas;
    private JLabel lblTotalEstudiantesRuta;
    private JLabel lblTiempoTotalRuta;
    private JTextArea txtSectoresRuta;
    private JComboBox cbRutaOptimizar;
    private JComboBox cbCriterioOptimizacion;
    private JButton btnCalcularRutaOptima;
    private JButton btnCompararRutas;
    private JButton btnAplicarOptimizacion;
    private JButton btnMostrarEstudiantes;
    private JTextArea txtAMostrarEstudiantes;
    private JTextArea txtAListaParadas;
    private JTextArea txtAListaBuses;
    private JTextArea txtAListaConductores;
    private JButton btnMostrarParada;
    private JTextArea txtARutaActual;
    private JTextArea txtAEstadoRecorrido;
    private JTextArea txtARutaOptimizada;

    private GestionEstudiantes gestorEst;
    private GestionParadas gestorPar;
    private GestionRutas gestorRut;
    private GestionAsignacion gestorAsign;
    private Recorrido recorridoActual;
    private PlanificadorRecorrido planificador;
    private List<Bus> buses;
    private List<Conductor> conductores;
    private Map<String, String> rutaToBus = new HashMap<>();
    private Map<String, String> rutaToConductor = new HashMap<>();



    public PlataformaEduRoute() {
        inicializarGestores();
        cargarCombosZonas();
        cargarDatosPrecargados();
        refrescarCombosAsignacion();
        configurarListeners();
        mostrarRutasConZonas();
        configurarScrollParadas();

    }

    private void configurarScrollParadas() {
        if (txtAListaParadas == null) return;

        txtAListaParadas.setLineWrap(true);
        txtAListaParadas.setWrapStyleWord(true);
    }



    private void inicializarGestores() {
        gestorEst = new GestionEstudiantes();
        gestorPar = new GestionParadas();
        gestorRut = new GestionRutas();
        gestorAsign = new GestionAsignacion(gestorEst, gestorPar, gestorRut);
        buses = new ArrayList<>();
        conductores = new ArrayList<>();
        planificador = new PlanificadorRecorrido();
    }


    private void cargarCombosZonas() {

        DefaultComboBoxModel<String> modeloZonas = new DefaultComboBoxModel<>();
        for (Ruta.Zona zona : Ruta.getTodasLasZonas()) {
            modeloZonas.addElement(zona.getNombre());
        }

        cbRutaAsignada.setModel(modeloZonas);


        if (cbSeleccionRuta != null) {
            cbSeleccionRuta.setModel(modeloZonas);
        }


        cargarRutasEnCombos();
    }


    private void cargarRutasEnCombos() {
        DefaultComboBoxModel<String> modeloRutas = new DefaultComboBoxModel<>();
        List<Ruta> rutas = gestorRut.listarRutas();
        for (Ruta ruta : rutas) {
            modeloRutas.addElement(ruta.getIdRuta());
        }

        if (cbRutaRecorrido != null) {
            cbRutaRecorrido.setModel(modeloRutas);
        }

        if (cbRutaOptimizar != null) {
            cbRutaOptimizar.setModel(modeloRutas);
        }
    }

    private void cargarDatosPrecargados() {

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
        mostrarBuses();
        mostrarConductores();
    }

    private void cargarComboBuses() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Bus b : buses) {
            model.addElement(b.getIdBus());
        }
        comboBox1.setModel(model);
        cbBusRecorrido.setModel(model);
    }

    private void cargarComboConductores() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Conductor c : conductores) {
            model.addElement(c.getIdConductor());
        }
        comboBox2.setModel(model);
        cbChoferRecorrido.setModel(model);
    }

    private void refrescarCombosAsignacion() {
        cargarComboBuses();
        cargarComboConductores();
        cargarRutasEnCombos();
    }

    private void cargarRutasPrecargadas() {
        Ruta ruta1 = new Ruta("R001", "Ruta Norte", "1", Ruta.Zona.NORTE);
        Parada p1_1 = new Parada("P001", "Carcelén Centro", 5, "Av. Eloy Alfaro y Carcelén", "Carcelén");
        Parada p1_2 = new Parada("P002", "Ponceano Alto", 8, "Calle Principal Ponceano", "Ponceano");
        Parada p1_3 = new Parada("P003", "El Condado", 10, "Av. Mariscal Sucre y Condado", "El Condado");

        if (ruta1.agregarParada(p1_1)) gestorPar.registrarParada(p1_1);
        if (ruta1.agregarParada(p1_2)) gestorPar.registrarParada(p1_2);
        if (ruta1.agregarParada(p1_3)) gestorPar.registrarParada(p1_3);
        gestorRut.registrarRuta(ruta1);

        Ruta ruta2 = new Ruta("R002", "Ruta Centro-Norte", "2", Ruta.Zona.CENTRO_NORTE);
        Parada p2_1 = new Parada("P004", "La Gasca", 6, "Av. La Gasca y América", "La Gasca");
        Parada p2_2 = new Parada("P005", "Iñaquito", 7, "Av. Naciones Unidas y Iñaquito", "Iñaquito");

        if (ruta2.agregarParada(p2_1)) gestorPar.registrarParada(p2_1);
        if (ruta2.agregarParada(p2_2)) gestorPar.registrarParada(p2_2);
        gestorRut.registrarRuta(ruta2);

        Ruta ruta3 = new Ruta("R003", "Ruta Centro", "3", Ruta.Zona.CENTRO);
        Parada p3_1 = new Parada("P006", "La Marín", 5, "Plaza La Marín", "La Marín");
        Parada p3_2 = new Parada("P007", "San Blas", 8, "Barrio San Blas", "San Blas");

        if (ruta3.agregarParada(p3_1)) gestorPar.registrarParada(p3_1);
        if (ruta3.agregarParada(p3_2)) gestorPar.registrarParada(p3_2);
        gestorRut.registrarRuta(ruta3);

        // Ruta 4 - Sur
        Ruta ruta4 = new Ruta("R004", "Ruta Sur", "4", Ruta.Zona.SUR);
        Parada p4_1 = new Parada("P008", "Chimbacalle", 7, "Av. Maldonado y Chimbacalle", "Chimbacalle");
        Parada p4_2 = new Parada("P009", "El Recreo", 10, "Estación El Recreo", "El Recreo");

        if (ruta4.agregarParada(p4_1)) gestorPar.registrarParada(p4_1);
        if (ruta4.agregarParada(p4_2)) gestorPar.registrarParada(p4_2);
        gestorRut.registrarRuta(ruta4);

        // Ruta 5 - Valle de los Chillos
        Ruta ruta5 = new Ruta("R005", "Ruta Valle Chillos", "5", Ruta.Zona.VALLES_CHILLOS);
        Parada p5_1 = new Parada("P010", "Conocoto", 12, "Plaza Conocoto", "Conocoto");
        Parada p5_2 = new Parada("P011", "San Rafael", 15, "Centro San Rafael", "San Rafael");

        if (ruta5.agregarParada(p5_1)) gestorPar.registrarParada(p5_1);
        if (ruta5.agregarParada(p5_2)) gestorPar.registrarParada(p5_2);
        gestorRut.registrarRuta(ruta5);

        // Ruta 6 - Valle de Tumbaco
        Ruta ruta6 = new Ruta("R006", "Ruta Valle Tumbaco", "6", Ruta.Zona.VALLES_TUMBACO);
        Parada p6_1 = new Parada("P012", "Cumbayá", 10, "Plaza Cumbayá", "Cumbayá");
        Parada p6_2 = new Parada("P013", "Tumbaco", 13, "Centro Tumbaco", "Tumbaco");

        if (ruta6.agregarParada(p6_1)) gestorPar.registrarParada(p6_1);
        if (ruta6.agregarParada(p6_2)) gestorPar.registrarParada(p6_2);
        gestorRut.registrarRuta(ruta6);

        // Ruta 7 - Noroeste
        Ruta ruta7 = new Ruta("R007", "Ruta Noroeste", "7", Ruta.Zona.NOROESTE);
        Parada p7_1 = new Parada("P014", "Pomasqui", 18, "Centro Pomasqui", "Pomasqui");
        Parada p7_2 = new Parada("P015", "Calderón", 15, "Plaza Calderón", "Calderón");

        if (ruta7.agregarParada(p7_1)) gestorPar.registrarParada(p7_1);
        if (ruta7.agregarParada(p7_2)) gestorPar.registrarParada(p7_2);
        gestorRut.registrarRuta(ruta7);

        // Ruta 8 - Suroriental
        Ruta ruta8 = new Ruta("R008", "Ruta Suroriental", "8", Ruta.Zona.SURORIENTAL);
        Parada p8_1 = new Parada("P016", "Amaguaña", 20, "Centro Amaguaña", "Amaguaña");
        Parada p8_2 = new Parada("P017", "Tambillo", 22, "Plaza Tambillo", "Tambillo");

        if (ruta8.agregarParada(p8_1)) gestorPar.registrarParada(p8_1);
        if (ruta8.agregarParada(p8_2)) gestorPar.registrarParada(p8_2);
        gestorRut.registrarRuta(ruta8);
    }

    private void cargarEstudiantesPrecargados() {
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

    private void configurarListeners() {
        btnRegistrarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEstudiante();
            }
        });

        btnEliminarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
            }
        });

        btnModificarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarEstudiane();
            }
        });

        btnLimpiarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarEstudiante();
            }
        });

        btnMostrarEstudiantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {mostrarEstudiantes();}
        });

        btnbuscarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEstudiante();
            }
        });

        btnRegistrarParada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarParada();
            }
        });

        btnModificarParada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarParada();
            }
        });

        btnEliminarParada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarParada();
            }
        });

        btnLimpiarParada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarParada();
            }
        });

        btnRegistrarBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarBus();
            }
        });

        btnEliminarBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarBus();
            }
        });

        btnRegistrarConductor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarConductor();
            }
        });

        btnEliminarConductor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarConductor();
            }
        });

        btnLiberarConductor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liberarConductor();
            }
        });

        btnAsignarConductor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignarConductor();
            }
        });

        btnIniciarRecorrido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarRecorrido();
            }
        });


        btnMostrarParada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarParadas();
            }
        });


        if (cbRutaRecorrido != null) {
            cbRutaRecorrido.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actualizarInfoRutaSeleccionada();
                }
            });
        }
    }


    private void actualizarInfoRutaSeleccionada() {
        if (cbRutaRecorrido.getSelectedItem() == null) return;

        String idRuta = cbRutaRecorrido.getSelectedItem().toString();
        Ruta ruta = gestorRut.buscarPorId(idRuta);

        if (ruta != null) {
            lblNombreRuta.setText(ruta.getNombreRuta());
            lblZonaRuta.setText(ruta.getZona().getNombre());
            lblNumeroRuta.setText(ruta.getNumeroRuta());
            lblTotalParadas.setText(String.valueOf(ruta.listarParadas().size()));
            lblTotalEstudiantesRuta.setText(String.valueOf(ruta.getTotalEstudiantes()));
            lblTiempoTotalRuta.setText(ruta.calcularTiempoTotal() + " min");

            StringBuilder sectores = new StringBuilder();
            for (String sector : ruta.getSectoresZona()) {
                sectores.append("• ").append(sector).append("\n");
            }
            txtSectoresRuta.setText(sectores.toString());
        }
    }

    private void mostrarRutasConZonas() {
        List<Ruta> rutas = gestorRut.listarRutas();
        StringBuilder sb = new StringBuilder();

        sb.append("=== RUTAS REGISTRADAS ===\n\n");
        for (Ruta ruta : rutas) {
            sb.append("ID: ").append(ruta.getIdRuta()).append("\n");
            sb.append("Número: ").append(ruta.getNumeroRuta()).append("\n");
            sb.append("Nombre: ").append(ruta.getNombreRuta()).append("\n");
            sb.append("Zona: ").append(ruta.getZona().getNombre()).append("\n");
            sb.append("Sectores: ").append(String.join(", ", ruta.getSectoresZona())).append("\n");
            sb.append("Paradas: ").append(ruta.listarParadas().size()).append("\n");
            sb.append("Estudiantes: ").append(ruta.getTotalEstudiantes()).append("\n");
            sb.append("────────────────────────────────────\n\n");
        }

        if (txtARutaActual != null) {
            txtARutaActual.setText(sb.toString());
        }
    }

    private void registrarEstudiante() {
        String idEst = txtIdEstudiante.getText().trim();
        if (idEst.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Ingrese una ID válida.");
            txtIdEstudiante.requestFocus();
            return;
        }

        String nombre = txtNombreEstudiante.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Ingrese un nombre válido.");
            txtNombreEstudiante.requestFocus();
            return;
        }

        String curso = txtCurso.getText().trim();
        if (curso.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Ingrese un curso válido.");
            txtCurso.requestFocus();
            return;
        }

        String direccion = txtDireccion.getText().trim();
        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Ingrese una dirección válida.");
            txtDireccion.requestFocus();
            return;
        }

        String prioridad = (cbPrioridad.getSelectedItem() != null)
                ? cbPrioridad.getSelectedItem().toString()
                : "";
        if (prioridad.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Seleccione prioridad.");
            cbPrioridad.requestFocus();
            return;
        }

        Estudiante nuevoEst = new Estudiante(idEst, nombre, curso, direccion, prioridad);
        boolean registrado = gestorEst.registrarEstudiante(nuevoEst);

        if (registrado) {
            JOptionPane.showMessageDialog(Ventana, "Estudiante registrado exitosamente.");
            limpiarEstudiante();
        } else {
            JOptionPane.showMessageDialog(Ventana, "Error: ID de estudiante ya existe.");
        }
    }

    private void eliminarEstudiante() {
        String idEst = txtIdEstudiante.getText().trim();
        if (idEst.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Ingrese una ID válida.");
            return;
        }

        Estudiante estudiante = gestorEst.buscarPorId(idEst);
        if (estudiante == null) {
            JOptionPane.showMessageDialog(Ventana, "Estudiante no encontrado.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(Ventana, "¿Está seguro de eliminar al estudiante?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean eliminado = gestorEst.eliminarEstudiante(idEst);
            if (eliminado) {
                JOptionPane.showMessageDialog(Ventana, "Estudiante eliminado correctamente.");
                limpiarEstudiante();
            } else {
                JOptionPane.showMessageDialog(Ventana, "Hubo un problema al eliminar el estudiante.");
            }
        }
    }

    private void modificarEstudiane() {
        String idEstudiante = txtIdEstudiante.getText().trim();
        String nuevoNombre = txtNombreEstudiante.getText().trim();
        String nuevoCurso = txtCurso.getText().trim();
        String nuevaDireccion = txtDireccion.getText().trim();
        String nuevaPrioridad = cbPrioridad.getSelectedItem().toString();

        if (idEstudiante.isEmpty() || nuevoNombre.isEmpty() || nuevoCurso.isEmpty() ||
                nuevaDireccion.isEmpty() || nuevaPrioridad.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Por favor, completa todos los campos.");
            return;
        }

        Estudiante estudiante = gestorEst.buscarPorId(idEstudiante);
        if (estudiante == null) {
            JOptionPane.showMessageDialog(Ventana, "No se encontró un estudiante con ese ID.");
            return;
        }

        boolean actualizado = gestorEst.actualizarEstudiante(idEstudiante, nuevoNombre,
                nuevoCurso, nuevaDireccion, nuevaPrioridad);
        if (actualizado) {
            JOptionPane.showMessageDialog(Ventana, "Estudiante modificado correctamente.");
        } else {
            JOptionPane.showMessageDialog(Ventana, "Hubo un error al modificar el estudiante.");
        }
    }

    private void limpiarEstudiante() {
        txtIdEstudiante.setText("");
        txtNombreEstudiante.setText("");
        txtCurso.setText("");
        txtDireccion.setText("");
        cbPrioridad.setSelectedIndex(-1);
        cbRutaEstudiante.setSelectedIndex(-1);
        cbParadaEstudiante.setSelectedIndex(-1);
    }

    private void buscarEstudiante() {
        String idEst = txtIdEstudiante.getText().trim();
        if (idEst.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Ingrese una ID válida.");
            return;
        }

        Estudiante estudiante = gestorEst.buscarPorId(idEst);
        if (estudiante == null) {
            JOptionPane.showMessageDialog(Ventana, "Estudiante no encontrado.");
            limpiarEstudiante();
        } else {
            txtNombreEstudiante.setText(estudiante.getNombre());
            txtCurso.setText(estudiante.getCurso());
            txtDireccion.setText(estudiante.getDireccion());
            cbPrioridad.setSelectedItem(estudiante.getPrioridad());

            if (estudiante.getNumeroRuta() != null) {
                cbRutaEstudiante.setSelectedItem(estudiante.getNumeroRuta());
            }

            if (estudiante.obtenerParada() != null) {
                cbParadaEstudiante.setSelectedItem(estudiante.obtenerParada().getNombreParada());
            }
        }
    }


    private void registrarParada() {
        try {
            String idParada = txtIdParada.getText().trim();
            String nombreParada = txtNombreParada.getText().trim();
            int tiempoEstimado = (Integer) spinTiempoParada.getValue();
            String ubicacion = txtUbicacionParada.getText().trim();
            String zonaSeleccionada = (String) cbRutaAsignada.getSelectedItem();


            if (idParada.isEmpty() || nombreParada.isEmpty() || ubicacion.isEmpty()) {
                JOptionPane.showMessageDialog(Ventana, "Complete todos los campos.");
                return;
            }

            if (zonaSeleccionada == null || zonaSeleccionada.isEmpty()) {
                JOptionPane.showMessageDialog(Ventana, "Seleccione una zona.");
                return;
            }

            Ruta.Zona zona = Ruta.getZonaPorNombre(zonaSeleccionada);
            if (zona == null) {
                JOptionPane.showMessageDialog(Ventana, "Zona no válida.");
                return;
            }


            Parada paradaExistente = gestorPar.buscarPorId(idParada);
            if (paradaExistente != null) {
                JOptionPane.showMessageDialog(Ventana, "Ya existe una parada con ese ID.");
                return;
            }

            List<String> sectores = zona.getSectores();
            String[] sectoresArray = sectores.toArray(new String[0]);

            String sectorSeleccionado = (String) JOptionPane.showInputDialog(
                    Ventana,
                    "Seleccione el sector de la parada:",
                    "Seleccionar Sector - " + zona.getNombre(),
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    sectoresArray,
                    sectoresArray[0]
            );

            if (sectorSeleccionado == null) {
                return;
            }

            Parada nuevaParada = new Parada(idParada, nombreParada, tiempoEstimado,
                    ubicacion, sectorSeleccionado);

            boolean registroExitoso = gestorPar.registrarParada(nuevaParada);

            if (registroExitoso) {
                Ruta rutaDeZona = gestorRut.buscarPorZona(zona);
                if (rutaDeZona != null) {
                    if (rutaDeZona.agregarParada(nuevaParada)) {
                        JOptionPane.showMessageDialog(Ventana,
                                "Parada registrada exitosamente\n" +
                                        "Zona: " + zona.getNombre() + "\n" +
                                        "Sector: " + sectorSeleccionado + "\n" +
                                        "Asignada a Ruta: " + rutaDeZona.getNumeroRuta());
                    } else {
                        JOptionPane.showMessageDialog(Ventana,
                                "Parada registrada pero NO asignada a la ruta\n" +
                                        "El sector no coincide con la validación de la ruta.");
                    }
                } else {
                    JOptionPane.showMessageDialog(Ventana,
                            "Parada registrada pero no hay ruta para la zona " + zona.getNombre());
                }
                limpiarParada();
                mostrarParadas();
            } else {
                JOptionPane.showMessageDialog(Ventana, "Error al registrar la parada.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Ventana, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void modificarParada() {
        try {
            String idParada = txtIdParada.getText().trim();
            if (idParada.isEmpty()) {
                JOptionPane.showMessageDialog(Ventana, "Ingrese una ID válida.");
                return;
            }

            Parada parada = gestorPar.buscarPorId(idParada);
            if (parada == null) {
                JOptionPane.showMessageDialog(Ventana, "Parada no encontrada.");
                return;
            }

            String nombreParada = txtNombreParada.getText().trim();
            if (nombreParada.isEmpty()) {
                JOptionPane.showMessageDialog(Ventana, "Ingrese un nombre válido para la parada.");
                return;
            }

            int tiempoEstimado = (Integer) spinTiempoParada.getValue();
            if (tiempoEstimado <= 0) {
                JOptionPane.showMessageDialog(Ventana, "Ingrese un tiempo estimado válido.");
                return;
            }

            String ubicacion = txtUbicacionParada.getText().trim();
            if (ubicacion.isEmpty()) {
                JOptionPane.showMessageDialog(Ventana, "Ingrese una ubicación válida.");
                return;
            }

            String zonaSeleccionada = (String) cbRutaAsignada.getSelectedItem();
            Ruta.Zona zona = null;
            if (zonaSeleccionada != null && !zonaSeleccionada.isEmpty()) {
                zona = Ruta.getZonaPorNombre(zonaSeleccionada);
            }

            String nuevoSector = parada.getSector();
            if (zona != null) {
                if (!zona.getSectores().contains(parada.getSector())) {
                    List<String> sectores = zona.getSectores();
                    String[] sectoresArray = sectores.toArray(new String[0]);

                    nuevoSector = (String) JOptionPane.showInputDialog(
                            Ventana,
                            "El sector actual no pertenece a la zona seleccionada.\n" +
                                    "Seleccione un nuevo sector:",
                            "Seleccionar Sector",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            sectoresArray,
                            sectoresArray[0]
                    );

                    if (nuevoSector == null) {
                        return;
                    }
                }
            }

            parada.setNombreParada(nombreParada);
            parada.setTiempoEstimado(tiempoEstimado);
            parada.setUbicacion(ubicacion);
            parada.setSector(nuevoSector);

            JOptionPane.showMessageDialog(Ventana, "Parada modificada con éxito.");
            limpiarParada();
            mostrarParadas();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Ventana, "Ocurrió un error al modificar la parada: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void eliminarParada() {
        String idParada = txtIdParada.getText().trim();
        if (idParada.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Ingrese una ID válida.");
            return;
        }

        Parada parada = gestorPar.buscarPorId(idParada);
        if (parada == null) {
            JOptionPane.showMessageDialog(Ventana, "Parada no encontrada.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(Ventana,
                "¿Está seguro de eliminar esta parada?\n" +
                        "ID: " + idParada + "\n" +
                        "Nombre: " + parada.getNombreParada(),
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            gestorPar.eliminarParada(idParada);
            JOptionPane.showMessageDialog(Ventana, "Parada eliminada con éxito.");
            limpiarParada();
            mostrarParadas();
        }
    }

    private void limpiarParada() {
        txtIdParada.setText("");
        txtNombreParada.setText("");
        spinTiempoParada.setValue(0);
        txtUbicacionParada.setText("");
        cbRutaAsignada.setSelectedIndex(0);
    }

    private void registrarBus() {
        try {
            String idBus = txtIdBus.getText().trim();
            String placa = txtPlacaBus.getText().trim();
            String estado = (String) cbEstadoBus.getSelectedItem();

            if (idBus.isEmpty() || placa.isEmpty()) {
                JOptionPane.showMessageDialog(Ventana, "Por favor, complete todos los campos.");
                return;
            }

            if (cbCapacidadBus.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(Ventana, "Seleccione una capacidad.");
                return;
            }

            int capacidad = Integer.parseInt(cbCapacidadBus.getSelectedItem().toString());

            if (capacidad < 5 || capacidad > 50) {
                JOptionPane.showMessageDialog(Ventana, "La capacidad debe estar entre 5 y 50 estudiantes.");
                return;
            }

            for (Bus bus : buses) {
                if (bus.getIdBus().equals(idBus)) {
                    JOptionPane.showMessageDialog(Ventana, "Error: Ya existe un bus con este ID.");
                    return;
                }
            }

            for (Bus bus : buses) {
                if (bus.getPlaca().equals(placa)) {
                    JOptionPane.showMessageDialog(Ventana, "Error: Ya existe un bus con esta placa.");
                    return;
                }
            }

            Bus bus = new Bus(idBus, placa, capacidad);
            buses.add(bus);

            if ("Disponible".equalsIgnoreCase(estado)) bus.marcarDisponible();
            else bus.marcarNoDisponible();

            JOptionPane.showMessageDialog(Ventana,
                    "Bus registrado exitosamente\n" +
                            "ID: " + idBus + "\n" +
                            "Placa: " + placa + "\n" +
                            "Capacidad máxima: " + capacidad);

            mostrarBuses();
            limpiarCamposBus();
            refrescarCombosAsignacion();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(Ventana, "Capacidad inválida. Revise el ComboBox.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Ventana, "Error al registrar bus: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void eliminarBus() {
        String idBus = txtIdBus.getText().trim();

        if (idBus.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Por favor, ingrese el ID del bus a eliminar.");
            return;
        }

        Bus busAEliminar = null;
        for (Bus bus : buses) {
            if (bus.getIdBus().equals(idBus)) {
                busAEliminar = bus;
                break;
            }
        }

        if (busAEliminar != null) {
            buses.remove(busAEliminar);
            JOptionPane.showMessageDialog(Ventana, "Bus eliminado exitosamente.");
            limpiarCamposBus();
            refrescarCombosAsignacion();
        } else {
            JOptionPane.showMessageDialog(Ventana, "Bus no encontrado.");
        }
    }

    private void limpiarCamposBus() {
        txtIdBus.setText("");
        txtPlacaBus.setText("");
        cbCapacidadBus.setSelectedIndex(0);
        cbEstadoBus.setSelectedIndex(0);
    }

    private void asignarRutaABus() {
        if (cbRutaRecorrido.getSelectedItem() == null || cbBusRecorrido.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(Ventana, "Seleccione Ruta y Bus.");
            return;
        }

        String idRuta = cbRutaRecorrido.getSelectedItem().toString().trim();
        String idBus = cbBusRecorrido.getSelectedItem().toString().trim();

        Ruta ruta = gestorRut.buscarPorId(idRuta);
        Bus bus = null;
        for (Bus b : buses) if (b.getIdBus().equals(idBus)) { bus = b; break; }

        if (ruta == null || bus == null) {
            JOptionPane.showMessageDialog(Ventana, "No se encontró la Ruta o el Bus.");
            return;
        }

        for (Map.Entry<String, String> e : rutaToBus.entrySet()) {
            if (e.getValue().equals(idBus) && !e.getKey().equals(idRuta)) {
                JOptionPane.showMessageDialog(Ventana, "Ese bus ya está asignado a otra ruta.");
                return;
            }
        }

        rutaToBus.put(idRuta, idBus);

        JOptionPane.showMessageDialog(Ventana,
                "Ruta asignada al bus\n" +
                        "Ruta: " + ruta.getNombreRuta() + " (" + ruta.getNumeroRuta() + ")\n" +
                        "Bus: " + bus.getIdBus() + " (" + bus.getPlaca() + ")"
        );
    }

    private void registrarConductor() {
        try {
            String idConductor = txtIdConductor.getText().trim();
            String nombreConductor = txtNombreConductor.getText().trim();

            if (idConductor.isEmpty() || nombreConductor.isEmpty()) {
                JOptionPane.showMessageDialog(Ventana, "Por favor, complete todos los campos.");
                return;
            }

            for (Conductor c : conductores) {
                if (c.getIdConductor().equals(idConductor)) {
                    JOptionPane.showMessageDialog(Ventana, "Error: Ya existe un conductor con este ID.");
                    return;
                }
            }

            Conductor conductor = new Conductor(idConductor, nombreConductor);
            conductores.add(conductor);

            JOptionPane.showMessageDialog(Ventana, "Conductor registrado exitosamente.");

            mostrarConductores();
            refrescarCombosAsignacion();
            limpiarCamposConductor();

            txtAListaConductores.setCaretPosition(txtAListaConductores.getDocument().getLength());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Ventana, "Ocurrió un error al registrar el conductor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void eliminarConductor() {
        String idConductor = txtIdConductor.getText().trim();

        if (idConductor.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Por favor, ingrese el ID del conductor a eliminar.");
            return;
        }

        Conductor conductorAEliminar = null;
        for (Conductor conductor : conductores) {
            if (conductor.getIdConductor().equals(idConductor)) {
                conductorAEliminar = conductor;
                break;
            }
        }

        if (conductorAEliminar != null) {
            conductores.remove(conductorAEliminar);
            JOptionPane.showMessageDialog(Ventana, "Conductor eliminado exitosamente.");
            limpiarCamposConductor();
            refrescarCombosAsignacion();
        } else {
            JOptionPane.showMessageDialog(Ventana, "Conductor no encontrado.");
        }
    }

    private void limpiarCamposConductor() {
        txtIdConductor.setText("");
        txtNombreConductor.setText("");
        cbEstadoConductor.setSelectedIndex(0);
    }

    private void asignarConductor() {
        if (comboBox1.getSelectedItem() == null || comboBox2.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(Ventana, "Seleccione Bus y Conductor.");
            return;
        }

        String idBus = comboBox1.getSelectedItem().toString().trim();
        String idConductor = comboBox2.getSelectedItem().toString().trim();

        Bus busAsignado = null;
        for (Bus b : buses) {
            if (b.getIdBus().equals(idBus)) { busAsignado = b; break; }
        }

        Conductor conductorAsignado = null;
        for (Conductor c : conductores) {
            if (c.getIdConductor().equals(idConductor)) { conductorAsignado = c; break; }
        }

        if (busAsignado == null || conductorAsignado == null) {
            JOptionPane.showMessageDialog(Ventana, "No se encontró el Bus o el Conductor.");
            return;
        }

        for (Bus b : buses) {
            for (Conductor c : b.listarConductores()) {
                if (c.getIdConductor().equals(idConductor) && !b.getIdBus().equals(idBus)) {
                    JOptionPane.showMessageDialog(Ventana,
                            "Ese conductor ya está asignado a otro bus (" + b.getIdBus() + ").");
                    return;
                }
            }
        }

        if (busAsignado.asignarConductor(conductorAsignado)) {
            JOptionPane.showMessageDialog(Ventana, "Conductor asignado al bus exitosamente.");
            mostrarBuses();
        } else {
            JOptionPane.showMessageDialog(Ventana, "No se pudo asignar: bus lleno o conductor repetido.");
        }
    }

    private void liberarConductor() {
        if (comboBox1.getSelectedItem() == null || comboBox2.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(Ventana, "Seleccione Bus y Conductor.");
            return;
        }

        String idBus = comboBox1.getSelectedItem().toString().trim();
        String idConductor = comboBox2.getSelectedItem().toString().trim();

        Bus busAsignado = null;
        for (Bus b : buses) {
            if (b.getIdBus().equals(idBus)) { busAsignado = b; break; }
        }

        if (busAsignado == null) {
            JOptionPane.showMessageDialog(Ventana, "No se encontró el bus.");
            return;
        }

        if (busAsignado.removerConductor(idConductor)) {
            JOptionPane.showMessageDialog(Ventana, "Conductor liberado del bus exitosamente.");
            mostrarBuses();
        } else {
            JOptionPane.showMessageDialog(Ventana, "Ese conductor no estaba asignado a este bus.");
        }
    }

    private void iniciarRecorrido() {
        if (cbRutaRecorrido.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(Ventana, "Seleccione una ruta.");
            return;
        }

        String idRuta = cbRutaRecorrido.getSelectedItem().toString().trim();
        Ruta ruta = gestorRut.buscarPorId(idRuta);

        if (ruta == null) {
            JOptionPane.showMessageDialog(Ventana, "Ruta no encontrada.");
            return;
        }

        if (!rutaToBus.containsKey(idRuta)) {
            JOptionPane.showMessageDialog(Ventana, "Esta ruta NO tiene bus asignado.");
            return;
        }

        String idBus = rutaToBus.get(idRuta);

        Bus bus = null;
        for (Bus b : buses) if (b.getIdBus().equals(idBus)) { bus = b; break; }

        if (bus == null) {
            JOptionPane.showMessageDialog(Ventana, "El bus asignado ya no existe.");
            return;
        }

        JOptionPane.showMessageDialog(Ventana,
                "Iniciando recorrido\n" +
                        "Ruta: " + ruta.getNombreRuta() + "\n" +
                        "Bus: " + bus.getIdBus() + " (" + bus.getPlaca() + ")\n" +
                        "Zona: " + ruta.getZona().getNombre()
        );

        Grafo grafo = new Grafo(true);

        for (Parada p : gestorPar.listar()) {
            grafo.añadirParada(p);
        }

        for (Ruta r : gestorRut.listarRutas()) {
            List<Parada> ps = r.listarParadas();
            for (int i = 0; i < ps.size() - 1; i++) {
                Parada origen = ps.get(i);
                Parada destino = ps.get(i + 1);
                grafo.añadirRuta(origen.getIdParada(), destino.getIdParada(), destino.obtenerTiempoEstimado());
            }
        }

        List<Parada> paradasRuta = ruta.listarParadas();
        if (paradasRuta.size() < 2) {
            JOptionPane.showMessageDialog(Ventana, "La ruta seleccionada no tiene suficientes paradas.");
            return;
        }

        String paradaInicio = paradasRuta.get(0).getIdParada();
        String paradaDestino = paradasRuta.get(paradasRuta.size() - 1).getIdParada();

        Grafo.ResultadoCamino res = grafo.caminoMasCorto(paradaInicio, paradaDestino);

        if (!res.existe()) {
            JOptionPane.showMessageDialog(Ventana, "No existe camino entre las paradas seleccionadas.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Camino más corto (costo=").append(res.getCostoTotal()).append("):\n");
        for (String pid : res.getCamino()) sb.append(pid).append("\n");

        JOptionPane.showMessageDialog(Ventana, sb.toString());
    }

    private void pausarRecorrido() {
        if (recorridoActual == null) {
            JOptionPane.showMessageDialog(Ventana, "No hay un recorrido en curso para pausar.");
            return;
        }

        try {
            int respuesta = JOptionPane.showConfirmDialog(
                    Ventana,
                    "¿Desea detener temporalmente el recorrido " + recorridoActual.getIdRecorrido() + "?",
                    "Pausar Simulación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                String motivo = JOptionPane.showInputDialog(Ventana, "Ingrese el motivo de la pausa (opcional):");
                if (motivo == null) motivo = "Pausa manual del usuario";
                if (motivo.trim().isEmpty()) motivo = "Sin motivo especificado";

                recorridoActual.agregarEvento("PAUSA: " + motivo);

                JOptionPane.showMessageDialog(Ventana, "El recorrido ha sido pausado.\nEstado: " + recorridoActual.getEstado());

                btnIniciarRecorrido.setText("Reanudar");
                btnIniciarRecorrido.setEnabled(true);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Ventana, "Error al procesar la pausa: " + ex.getMessage());
        }
    }

    private void finalizarRecorrido() {
        if (recorridoActual == null) {
            JOptionPane.showMessageDialog(Ventana, "Error: No hay ningún recorrido activo para finalizar.");
            return;
        }

        if (recorridoActual.getEstado().equals("Finalizado")) {
            JOptionPane.showMessageDialog(Ventana, "Este recorrido ya ha sido finalizado anteriormente.");
            return;
        }

        try {
            Object busSeleccionado = cbBusRecorrido.getSelectedItem();
            if (busSeleccionado == null) {
                JOptionPane.showMessageDialog(Ventana, "Debe seleccionar el bus que está terminando el recorrido.");
                cbBusRecorrido.requestFocus();
                return;
            }

            String idBus = busSeleccionado.toString();
            Bus bus = null;
            for (Bus b : buses) {
                if (b.getIdBus().equals(idBus)) {
                    bus = b;
                    break;
                }
            }

            if (bus == null) {
                throw new Exception("El bus seleccionado no existe en el sistema.");
            }

            planificador.simularVuelta(recorridoActual, bus, null, null);
            recorridoActual.finalizar();

            String reporte = "REPORTE FINAL\n" +
                    "ID: " + recorridoActual.getIdRecorrido() + "\n" +
                    "Estado: " + recorridoActual.getEstado() + "\n" +
                    "Eventos: " + recorridoActual.getEventos().size();

            JOptionPane.showMessageDialog(Ventana, reporte);
            JOptionPane.showMessageDialog(Ventana, "Recorrido finalizado con éxito.");

            recorridoActual = null;

            btnIniciarRecorrido.setEnabled(true);

        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(Ventana, "Error de datos: Objeto nulo.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Ventana, "Error: " + ex.getMessage());
        }
    }

    private void mostrarEstudiantes() {
        List<Estudiante> estudiantes = gestorEst.listarEstudiante();

        if (estudiantes.isEmpty()) {
            txtAMostrarEstudiantes.setText("No hay estudiantes registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("══════════════════════════════════════════════════════════\n");
        sb.append("                 LISTA DE ESTUDIANTES                    \n");
        sb.append("══════════════════════════════════════════════════════════\n");
        sb.append("Total de estudiantes: ").append(estudiantes.size()).append("\n\n");

        int altaPrioridad = 0;
        int mediaPrioridad = 0;
        int bajaPrioridad = 0;
        int sinRuta = 0;
        int sinParada = 0;

        for (Estudiante estudiante : estudiantes) {
            if ("Alta".equalsIgnoreCase(estudiante.getPrioridad())) {
                altaPrioridad++;
            } else if ("Media".equalsIgnoreCase(estudiante.getPrioridad())) {
                mediaPrioridad++;
            } else if ("Baja".equalsIgnoreCase(estudiante.getPrioridad())) {
                bajaPrioridad++;
            }

            if (estudiante.getNumeroRuta() == null || estudiante.getNumeroRuta().isEmpty()) {
                sinRuta++;
            }
            if (estudiante.obtenerParada() == null) {
                sinParada++;
            }

            sb.append("══════════════════════════════════════════════════════════\n");
            sb.append("ID: ").append(estudiante.getIdEst()).append("\n");
            sb.append("Nombre: ").append(estudiante.getNombre()).append("\n");
            sb.append("Curso: ").append(estudiante.getCurso()).append("\n");
            sb.append("Dirección: ").append(estudiante.getDireccion()).append("\n");
            sb.append("Prioridad: ").append(estudiante.getPrioridad()).append("\n");

            String ruta = (estudiante.getNumeroRuta() != null && !estudiante.getNumeroRuta().isEmpty())
                    ? estudiante.getNumeroRuta() : "No asignada";
            String zona = (estudiante.getZona() != null && !estudiante.getZona().isEmpty())
                    ? estudiante.getZona() : "Sin zona";
            String sector = (estudiante.getSector() != null && !estudiante.getSector().isEmpty())
                    ? estudiante.getSector() : "Sin sector";
            String parada = (estudiante.obtenerParada() != null)
                    ? estudiante.obtenerParada().getNombreParada() : "No asignada";

            sb.append("Ruta: ").append(ruta).append(" (Zona: ").append(zona).append(", Sector: ").append(sector).append(")\n");
            sb.append("Parada: ").append(parada).append("\n");

            sb.append("Estado: ").append(estudiante.isAusente() ? "AUSENTE" : "PRESENTE").append("\n");
            sb.append("══════════════════════════════════════════════════════════\n\n");
        }

        sb.append("\n══════════════════════════════════════════════════════════\n");
        sb.append("                  ESTADÍSTICAS                            \n");
        sb.append("══════════════════════════════════════════════════════════\n");
        sb.append("Prioridades:\n");
        sb.append("   • Alta: ").append(altaPrioridad).append(" estudiantes\n");
        sb.append("   • Media: ").append(mediaPrioridad).append(" estudiantes\n");
        sb.append("   • Baja: ").append(bajaPrioridad).append(" estudiantes\n\n");

        sb.append("Asignaciones pendientes:\n");
        sb.append("   • Sin ruta: ").append(sinRuta).append(" estudiantes\n");
        sb.append("   • Sin parada: ").append(sinParada).append(" estudiantes\n");
        sb.append("══════════════════════════════════════════════════════════\n");

        txtAMostrarEstudiantes.setText(sb.toString());
        txtAMostrarEstudiantes.setCaretPosition(0);
    }

    private void mostrarBuses() {
        if (buses.isEmpty()) {
            txtAListaBuses.setText("No hay buses registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE BUSES ===\n\n");

        for (Bus bus : buses) {
            sb.append("Bus ID: ").append(bus.getIdBus()).append("\n");
            sb.append("Placa: ").append(bus.getPlaca()).append("\n");
            sb.append("Capacidad: ").append(bus.getCapacidadMax()).append(" estudiantes\n");
            sb.append("Estado: ").append(bus.estadoDisponible() ? "DISPONIBLE" : "NO DISPONIBLE").append("\n");

            List<Conductor> conductoresBus = bus.listarConductores();
            if (!conductoresBus.isEmpty()) {
                sb.append("Conductores: ");
                for (int i = 0; i < conductoresBus.size(); i++) {
                    sb.append(conductoresBus.get(i).getNombreConductor());
                    if (i < conductoresBus.size() - 1) sb.append(", ");
                }
                sb.append("\n");
            } else {
                sb.append("Conductores: Ninguno asignado\n");
            }

            sb.append("-------------------\n\n");
        }

        txtAListaBuses.setText(sb.toString());
    }

    private void mostrarParadas() {
        List<Parada> paradas = gestorPar.listar();

        if (paradas.isEmpty()) {
            txtAListaParadas.setText("No hay paradas registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE PARADAS ===\n\n");

        for (Parada parada : paradas) {
            sb.append("ID: ").append(parada.getIdParada()).append("\n");
            sb.append("Nombre: ").append(parada.getNombreParada()).append("\n");
            sb.append("Ubicación: ").append(parada.getUbicacion()).append("\n");
            sb.append("Sector: ").append(parada.getSector()).append("\n");
            sb.append("Tiempo estimado: ").append(parada.getTiempoEstimado()).append(" min\n");
            sb.append("Estudiantes asignados: ").append(parada.listarEstudiantes().size()).append("\n");

            // Mostrar ruta asignada si existe
            if (parada.getRutaAsignada() != null) {
                sb.append("Ruta asignada: ").append(parada.getRutaAsignada().getNumeroRuta())
                        .append(" - ").append(parada.getRutaAsignada().getNombreRuta())
                        .append(" (").append(parada.getRutaAsignada().getZona().getNombre()).append(")\n");
            }

            sb.append("-------------------\n\n");
        }

        txtAListaParadas.setText(sb.toString());
    }

    private int contarTotalEstudiantesEnBuses() {
        int total = 0;
        for (Bus bus : buses) {
            total += bus.listarEstudiantes().size();
        }
        return total;
    }

    private void mostrarConductores() {
        if (conductores.isEmpty()) {
            txtAListaConductores.setText("No hay conductores registrados.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("══════════════════════════════════════════════\n");
        sb.append("           LISTA DE CONDUCTORES              \n");
        sb.append("══════════════════════════════════════════════\n\n");

        for (Conductor conductor : conductores) {
            sb.append("ID Conductor: ").append(conductor.getIdConductor()).append("\n");
            sb.append("Nombre: ").append(conductor.getNombreConductor()).append("\n");
            sb.append("Estado: ")
                    .append(conductor.isDisponible() ? "DISPONIBLE" : "OCUPADO")
                    .append("\n");

            Bus busAsignado = obtenerBusDeConductor(conductor);
            if (busAsignado != null) {
                sb.append("Asignado al bus: ").append(busAsignado.getIdBus())
                        .append(" (").append(busAsignado.getPlaca()).append(")\n");
            } else {
                sb.append("Asignado a bus: Ninguno\n");
            }

            sb.append("══════════════════════════════════════════════\n\n");
        }

        txtAListaConductores.setText(sb.toString());
        txtAListaConductores.setCaretPosition(0);
    }

    private boolean estaAsignadoABus(Conductor conductor) {
        for (Bus bus : buses) {
            for (Conductor c : bus.listarConductores()) {
                if (c.getIdConductor().equals(conductor.getIdConductor())) {
                    return true;
                }
            }
        }
        return false;
    }

    private Bus obtenerBusDeConductor(Conductor conductor) {
        for (Bus bus : buses) {
            for (Conductor c : bus.listarConductores()) {
                if (c.getIdConductor().equals(conductor.getIdConductor())) {
                    return bus;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PlataformaEduRoute");
        frame.setContentPane(new PlataformaEduRoute().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(900, 800);
    }
}