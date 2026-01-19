import Estructura.*;
import Negocio.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
    private JCheckBox chkConsiderarAusencias;
    private JButton btnCalcularRutaOptima;
    private JButton btnCompararRutas;
    private JButton btnAplicarOptimizacion;
    private JButton btnMostrarEstudiantes;


    private GestionEstudiantes gestorEst;
    private GestionParadas gestorPar;
    private GestionRutas gestorRut;
    private GestionAsignacion gestorAsign;
    private Recorrido recorridoActual;
    private PlanificadorRecorrido planificador;
    private List<Bus> buses;
    private List<Conductor> conductores;


    public PlataformaEduRoute() {
        inicializarGestores();
        cargarDatosPrecargados();
        configurarListeners();
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
    }

    private void cargarRutasPrecargadas() {
        // Negocio.Ruta 1 - Norte
        Ruta ruta1 = new Ruta("R001", "Negocio.Ruta Norte", "1", "Norte",
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

        // Negocio.Ruta 2 - Centro-Norte
        Ruta ruta2 = new Ruta("R002", "Negocio.Ruta Centro-Norte", "2", "Centro-Norte",
                Arrays.asList("La Gasca", "Bellavista", "Iñaquito", "El Batán", "González Suárez"));
        Parada p2_1 = new Parada("P004", "La Gasca", 6, "Av. La Gasca y América");
        Parada p2_2 = new Parada("P005", "Iñaquito", 7, "Av. Naciones Unidas y Iñaquito");
        ruta2.agregarParada(p2_1);
        ruta2.agregarParada(p2_2);
        gestorPar.registrarParada(p2_1);
        gestorPar.registrarParada(p2_2);
        gestorRut.registrarRuta(ruta2);

        // Negocio.Ruta 3 - Centro
        Ruta ruta3 = new Ruta("R003", "Negocio.Ruta Centro", "3", "Centro",
                Arrays.asList("La Marín", "San Blas", "La Tola", "Itchimbía", "San Roque"));
        Parada p3_1 = new Parada("P006", "La Marín", 5, "Plaza La Marín");
        Parada p3_2 = new Parada("P007", "San Blas", 8, "Barrio San Blas");
        ruta3.agregarParada(p3_1);
        ruta3.agregarParada(p3_2);
        gestorPar.registrarParada(p3_1);
        gestorPar.registrarParada(p3_2);
        gestorRut.registrarRuta(ruta3);

        // Negocio.Ruta 4 - Sur
        Ruta ruta4 = new Ruta("R004", "Negocio.Ruta Sur", "4", "Sur",
                Arrays.asList("Chimbacalle", "El Recreo", "La Magdalena", "Quitumbe", "Guamaní"));
        Parada p4_1 = new Parada("P008", "Chimbacalle", 7, "Av. Maldonado y Chimbacalle");
        Parada p4_2 = new Parada("P009", "El Recreo", 10, "Estación El Recreo");
        ruta4.agregarParada(p4_1);
        ruta4.agregarParada(p4_2);
        gestorPar.registrarParada(p4_1);
        gestorPar.registrarParada(p4_2);
        gestorRut.registrarRuta(ruta4);

        // Negocio.Ruta 5 - Valle de los Chillos
        Ruta ruta5 = new Ruta("R005", "Negocio.Ruta Valle Chillos", "5", "Valle de los Chillos",
                Arrays.asList("Conocoto", "San Rafael", "Alangasí"));
        Parada p5_1 = new Parada("P010", "Conocoto", 12, "Plaza Conocoto");
        Parada p5_2 = new Parada("P011", "San Rafael", 15, "Centro San Rafael");
        ruta5.agregarParada(p5_1);
        ruta5.agregarParada(p5_2);
        gestorPar.registrarParada(p5_1);
        gestorPar.registrarParada(p5_2);
        gestorRut.registrarRuta(ruta5);

        // Negocio.Ruta 6 - Valle de Tumbaco
        Ruta ruta6 = new Ruta("R006", "Negocio.Ruta Valle Tumbaco", "6", "Valle de Tumbaco",
                Arrays.asList("Cumbayá", "Tumbaco", "Puembo"));
        Parada p6_1 = new Parada("P012", "Cumbayá", 10, "Plaza Cumbayá");
        Parada p6_2 = new Parada("P013", "Tumbaco", 13, "Centro Tumbaco");
        ruta6.agregarParada(p6_1);
        ruta6.agregarParada(p6_2);
        gestorPar.registrarParada(p6_1);
        gestorPar.registrarParada(p6_2);
        gestorRut.registrarRuta(ruta6);

        // Negocio.Ruta 7 - Noroeste
        Ruta ruta7 = new Ruta("R007", "Negocio.Ruta Noroeste", "7", "Noroeste",
                Arrays.asList("Pomasqui", "San Antonio de Pichincha", "Calderón"));
        Parada p7_1 = new Parada("P014", "Pomasqui", 18, "Centro Pomasqui");
        Parada p7_2 = new Parada("P015", "Calderón", 15, "Plaza Calderón");
        ruta7.agregarParada(p7_1);
        ruta7.agregarParada(p7_2);
        gestorPar.registrarParada(p7_1);
        gestorPar.registrarParada(p7_2);
        gestorRut.registrarRuta(ruta7);

        // Negocio.Ruta 8 - Suroriental
        Ruta ruta8 = new Ruta("R008", "Negocio.Ruta Suroriental", "8", "Suroriental",
                Arrays.asList("Amaguaña", "Alóag", "Tambillo"));
        Parada p8_1 = new Parada("P016", "Amaguaña", 20, "Centro Amaguaña");
        Parada p8_2 = new Parada("P017", "Tambillo", 22, "Plaza Tambillo");
        ruta8.agregarParada(p8_1);
        ruta8.agregarParada(p8_2);
        gestorPar.registrarParada(p8_1);
        gestorPar.registrarParada(p8_2);
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
            public void actionPerformed(ActionEvent e) {
// completar boton de accion
            }
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
                limpiarEstudiante();
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

        btnPausar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausarRecorrido();
            }
        });

        btnFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarRecorrido();
            }
        });
    }

    private void registrarEstudiante() {
        String idEst = txtIdEstudiante.getText().trim();
        if (idEst.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Ingrese una ID valida.");
            txtIdEstudiante.requestFocus();
            return;
        }

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

        String prioridad = (cbPrioridad.getSelectedItem() != null)
                ? cbPrioridad.getSelectedItem().toString()
                : "";
        if (prioridad.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Seleccione prioridad.");
            cbPrioridad.requestFocus();
            return;
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
            JOptionPane.showMessageDialog(Ventana, "Negocio.Estudiante no encontrado.");
            return;
        }


        int respuesta = JOptionPane.showConfirmDialog(Ventana, "¿Está seguro de eliminar al estudiante?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            boolean eliminado = gestorEst.eliminarEstudiante(idEst);
            if (eliminado) {
                JOptionPane.showMessageDialog(Ventana, "Negocio.Estudiante eliminado correctamente.");
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

        if (idEstudiante.isEmpty() || nuevoNombre.isEmpty() || nuevoCurso.isEmpty() || nuevaDireccion.isEmpty() || nuevaPrioridad.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Por favor, completa todos los campos.");
            return;
        }

        Estudiante estudiante = gestorEst.buscarPorId(idEstudiante);
        if (estudiante == null) {
            JOptionPane.showMessageDialog(Ventana, "No se encontró un estudiante con ese ID.");
            return;
        }

        boolean actualizado = gestorEst.actualizarEstudiante(idEstudiante, nuevoNombre, nuevoCurso, nuevaDireccion, nuevaPrioridad);
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
            JOptionPane.showMessageDialog(Ventana, "Negocio.Estudiante no encontrado.");
            limpiarEstudiante();
        } else {
            txtNombreEstudiante.setText(estudiante.getNombre());
            txtCurso.setText(estudiante.getCurso());
            txtDireccion.setText(estudiante.getDireccion());
            cbPrioridad.setSelectedItem(estudiante.getPrioridad());
            cbRutaEstudiante.setSelectedItem(estudiante.getNumeroRuta());
            cbParadaEstudiante.setSelectedItem(estudiante.obtenerParada() != null ? estudiante.obtenerParada().getNombreParada() : "Sin asignar");
        }
    }

    private void registrarParada() {
        try {
            String idParada = txtIdParada.getText().trim();
            if (idParada.isEmpty()) {
                JOptionPane.showMessageDialog(Ventana, "Ingrese una ID válida.");
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

            String rutaSeleccionada = (String) cbRutaAsignada.getSelectedItem();
            if (rutaSeleccionada == null || rutaSeleccionada.isEmpty()) {
                JOptionPane.showMessageDialog(Ventana, "Seleccione una ruta válida.");
                return;
            }

            Parada nuevaParada = new Parada(idParada, nombreParada, tiempoEstimado, ubicacion);

            gestorPar.registrarParada(nuevaParada);

            JOptionPane.showMessageDialog(Ventana, "Parada registrada con éxito.");

            limpiarParada();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Ventana, "Ocurrió un error al registrar la parada: " + ex.getMessage());
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
                    JOptionPane.showMessageDialog(Ventana, "Negocio.Parada no encontrada.");
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

                parada.setNombreParada(nombreParada);
                parada.setUbicacion(ubicacion);

                JOptionPane.showMessageDialog(Ventana, "Negocio.Parada modificada con éxito.");


                limpiarParada();

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
            JOptionPane.showMessageDialog(Ventana, "Negocio.Parada no encontrada.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(Ventana, "¿Está seguro de eliminar esta parada?");
        if (confirmacion == JOptionPane.YES_OPTION) {
            gestorPar.eliminarParada(idParada);
            JOptionPane.showMessageDialog(Ventana, "Negocio.Parada eliminada con éxito.");
            limpiarParada();
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
                int capacidad = (Integer) spinCapacidadBus.getValue();
                String estado = (String) cbEstadoBus.getSelectedItem();

                if (idBus.isEmpty() || placa.isEmpty()) {
                    JOptionPane.showMessageDialog(Ventana, "Por favor, complete todos los campos.");
                    return;
                }

                Bus bus = new Bus(idBus, placa);
                buses.add(bus);

                if (estado.equals("Disponible")) {
                    bus.marcarDisponible();
                } else {
                    bus.marcarNoDisponible();
                }

                JOptionPane.showMessageDialog(Ventana, "Bus registrado exitosamente.");
                limpiarCamposBus();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(Ventana, "Ocurrió un error al registrar el bus: " + ex.getMessage());
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
            JOptionPane.showMessageDialog(Ventana, "Negocio.Bus eliminado exitosamente.");
            limpiarCamposBus();
        } else {
            JOptionPane.showMessageDialog(Ventana, "Negocio.Bus no encontrado.");
        }
    }

    private void limpiarCamposBus() {
        txtIdBus.setText("");
        txtPlacaBus.setText("");
        spinCapacidadBus.setValue(5);
        cbEstadoBus.setSelectedIndex(0);
    }


    private void registrarConductor() {
        try {
            String idConductor = txtIdConductor.getText().trim();
            String nombreConductor = txtNombreConductor.getText().trim();

            if (idConductor.isEmpty() || nombreConductor.isEmpty()) {
                JOptionPane.showMessageDialog(Ventana, "Por favor, complete todos los campos.");
                return;
            }

            Conductor conductor = new Conductor(idConductor, nombreConductor);
            conductores.add(conductor);

            JOptionPane.showMessageDialog(Ventana, "Conductor registrado exitosamente.");
            limpiarCamposConductor();
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
            JOptionPane.showMessageDialog(Ventana, "Negocio.Conductor eliminado exitosamente.");
            limpiarCamposConductor();
        } else {
            JOptionPane.showMessageDialog(Ventana, "Negocio.Conductor no encontrado.");
        }
    }

    private void limpiarCamposConductor() {
        txtIdConductor.setText("");
        txtNombreConductor.setText("");
        cbEstadoConductor.setSelectedIndex(0);
    }

    private void asignarConductor() {
        String idBus = txtIdBus.getText().trim();
        String idConductor = txtIdConductor.getText().trim();

        if (idBus.isEmpty() || idConductor.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Por favor, ingrese el ID del bus y el ID del conductor.");
            return;
        }

        Bus busAsignado = null;
        Conductor conductorAsignado = null;

        for (Bus bus : buses) {
            if (bus.getIdBus().equals(idBus)) {
                busAsignado = bus;
                break;
            }
        }

        for (Conductor conductor : conductores) {
            if (conductor.getIdConductor().equals(idConductor)) {
                conductorAsignado = conductor;
                break;
            }
        }

        if (busAsignado != null && conductorAsignado != null) {
            if (busAsignado.asignarConductor(conductorAsignado)) {
                JOptionPane.showMessageDialog(Ventana, "Negocio.Conductor asignado al bus exitosamente.");
            } else {
                JOptionPane.showMessageDialog(Ventana, "El bus ya tiene dos conductores asignados.");
            }
        } else {
            JOptionPane.showMessageDialog(Ventana, "No se encontró el bus o conductor.");
        }
    }

    private void liberarConductor() {
        String idBus = txtIdBus.getText().trim();
        String idConductor = txtIdConductor.getText().trim();

        if (idBus.isEmpty() || idConductor.isEmpty()) {
            JOptionPane.showMessageDialog(Ventana, "Por favor, ingrese el ID del bus y el ID del conductor.");
            return;
        }

        Bus busAsignado = null;
        Conductor conductorAsignado = null;

        for (Bus bus : buses) {
            if (bus.getIdBus().equals(idBus)) {
                busAsignado = bus;
                break;
            }
        }

        for (Conductor conductor : conductores) {
            if (conductor.getIdConductor().equals(idConductor)) {
                conductorAsignado = conductor;
                break;
            }
        }

        if (busAsignado != null && conductorAsignado != null) {
            List<Conductor> conductoresBus = busAsignado.listarConductores();
            if (conductoresBus.contains(conductorAsignado)) {
                conductoresBus.remove(conductorAsignado);
                JOptionPane.showMessageDialog(Ventana, "Negocio.Conductor liberado del bus exitosamente.");
            } else {
                JOptionPane.showMessageDialog(Ventana, "El conductor no está asignado a este bus.");
            }
        } else {
            JOptionPane.showMessageDialog(Ventana, "No se encontró el bus o conductor.");
        }
    }

    private void iniciarRecorrido(){
            String idRuta = cbRutaRecorrido.getSelectedItem().toString();
            String idBus = cbBusRecorrido.getSelectedItem().toString();
            String idConductor = cbChoferRecorrido.getSelectedItem().toString();

            Ruta ruta = gestorRut.buscarPorId(idRuta);
            Bus bus = null;
            Conductor conductor = null;

            for (Bus b : buses) {
                if (b.getIdBus().equals(idBus)) {
                    bus = b;
                    break;
                }
            }

            for (Conductor c : conductores) {
                if (c.getIdConductor().equals(idConductor)) {
                    conductor = c;
                    break;
                }
            }

            if (ruta == null || bus == null || conductor == null) {
                JOptionPane.showMessageDialog(Ventana, "No se pudo encontrar uno de los elementos seleccionados.");
                return;
            }

            // Crear el grafo con las paradas y rutas
            Grafo grafo = new Grafo();

            // Añadir paradas al grafo
            for (Parada p : gestorPar.listar()) {
                grafo.añadirParada(p);
            }

            // Añadir rutas al grafo
            for (Ruta r : gestorRut.listarRutas()) {
                for (int i = 0; i < r.listarParadas().size() - 1; i++) {
                    Parada origen = r.listarParadas().get(i);
                    Parada destino = r.listarParadas().get(i + 1);
                    grafo.añadirRuta(origen.getIdParada(), destino.getIdParada(), destino.obtenerTiempoEstimado());
                    // Suponemos que las rutas son bidireccionales
                    grafo.añadirRuta(destino.getIdParada(), origen.getIdParada(), origen.obtenerTiempoEstimado());
                }
            }

            // Calcular el camino más corto entre dos paradas
            String paradaInicio = "P001"; // Por ejemplo, Carcelén Centro
            String paradaDestino = "P005"; // Por ejemplo, Iñaquito
            List<String> caminoMasCorto = grafo.calcularCaminoMasCorto(paradaInicio, paradaDestino);

            // Mostrar el recorrido
            StringBuilder recorrido = new StringBuilder("Recorrido más corto:\n");
            for (String paradaId : caminoMasCorto) {
                recorrido.append(paradaId).append("\n");
            }

            JOptionPane.showMessageDialog(Ventana, recorrido.toString());
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

                recorridoActual.getEventos().add("PAUSA: " + motivo);

                JOptionPane.showMessageDialog(Ventana, "El recorrido ha sido pausado.\nEstado: " + recorridoActual.getEstado());

                btnPausar.setEnabled(false);
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
            JOptionPane.showMessageDialog(Ventana, "Negocio.Recorrido finalizado con éxito.");

            recorridoActual = null;

            btnIniciarRecorrido.setEnabled(true);
            btnFinalizar.setEnabled(false);
            btnPausar.setEnabled(false);

        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(Ventana, "Error de datos: Objeto nulo.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(Ventana, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PlataformaEduRoute");
        frame.setContentPane(new PlataformaEduRoute().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

