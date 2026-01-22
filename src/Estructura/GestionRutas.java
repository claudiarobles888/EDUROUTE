package Estructura;  // CAMBIADO de "Negocio" a "Estructura"

import Negocio.Ruta;
import Negocio.Parada;
import Negocio.Estudiante;
import java.util.ArrayList;
import java.util.List;

public class GestionRutas {
    private List<Ruta> rutas;

    public GestionRutas() {
        this.rutas = new ArrayList<>();
    }

    public boolean registrarRuta(Ruta ruta) {
        for (Ruta r : rutas) {
            if (r.getIdRuta().equals(ruta.getIdRuta())) {
                return false;
            }
        }
        rutas.add(ruta);
        return true;
    }

    public boolean eliminarRuta(String idRuta) {
        return rutas.removeIf(r -> r.getIdRuta().equals(idRuta));
    }

    public Ruta buscarPorId(String idRuta) {
        for (Ruta ruta : rutas) {
            if (ruta.getIdRuta().equals(idRuta)) {
                return ruta;
            }
        }
        return null;
    }

    public Ruta buscarPorNumero(String numeroRuta) {
        for (Ruta ruta : rutas) {
            if (ruta.getNumeroRuta().equals(numeroRuta)) {
                return ruta;
            }
        }
        return null;
    }

    public Ruta buscarPorZona(Ruta.Zona zona) {
        for (Ruta ruta : rutas) {
            if (ruta.getZona() == zona) {
                return ruta;
            }
        }
        return null;
    }

    public List<Ruta> buscarRutasPorZona(Ruta.Zona zona) {
        List<Ruta> resultado = new ArrayList<>();
        for (Ruta ruta : rutas) {
            if (ruta.getZona() == zona) {
                resultado.add(ruta);
            }
        }
        return resultado;
    }

    public Ruta buscarPorNombreZona(String nombreZona) {
        Ruta.Zona zona = Ruta.getZonaPorNombre(nombreZona);
        if (zona != null) {
            return buscarPorZona(zona);
        }
        return null;
    }

    public List<Ruta> listarRutas() {
        return new ArrayList<>(rutas);
    }

    public List<String> listarZonasDisponibles() {
        List<String> zonas = new ArrayList<>();
        for (Ruta.Zona zona : Ruta.getTodasLasZonas()) {
            zonas.add(zona.getNombre());
        }
        return zonas;
    }

    public List<Ruta> getRutas() {
        return new ArrayList<>(rutas);
    }

    public boolean actualizarRuta(String idRuta, String nuevoNombre, String nuevoNumero, Ruta.Zona nuevaZona) {

        return false;
    }

    public int contarRutas() {
        return rutas.size();
    }

    public int getTotalEstudiantes() {
        int total = 0;
        for (Ruta ruta : rutas) {
            total += ruta.getTotalEstudiantes();
        }
        return total;
    }

    public int getTotalParadas() {
        int total = 0;
        for (Ruta ruta : rutas) {
            total += ruta.listarParadas().size();
        }
        return total;
    }

    public Parada buscarParadaEnRutas(String idParada) {
        for (Ruta ruta : rutas) {
            for (Parada parada : ruta.listarParadas()) {
                if (parada.getIdParada().equals(idParada)) {
                    return parada;
                }
            }
        }
        return null;
    }

    public Ruta obtenerRutaDeParada(String idParada) {
        for (Ruta ruta : rutas) {
            for (Parada parada : ruta.listarParadas()) {
                if (parada.getIdParada().equals(idParada)) {
                    return ruta;
                }
            }
        }
        return null;
    }

    public boolean puedeAgregarParada(String idRuta, Parada parada) {
        Ruta ruta = buscarPorId(idRuta);
        if (ruta != null) {
            return ruta.puedeAgregarParada(parada);
        }
        return false;
    }

    public boolean agregarParadaARuta(String idRuta, Parada parada) {
        Ruta ruta = buscarPorId(idRuta);
        if (ruta != null) {
            return ruta.agregarParada(parada);
        }
        return false;
    }

    public boolean eliminarParadaDeRuta(String idRuta, String idParada) {
        Ruta ruta = buscarPorId(idRuta);
        if (ruta != null) {
            ruta.eliminarParada(idParada);
            return true;
        }
        return false;
    }

    public List<Estudiante> obtenerEstudiantesDeRuta(String idRuta) {
        Ruta ruta = buscarPorId(idRuta);
        if (ruta != null) {
            return ruta.listarEstudiantes();
        }
        return new ArrayList<>();
    }

    public String generarReporteRuta(String idRuta) {
        Ruta ruta = buscarPorId(idRuta);
        if (ruta != null) {
            return ruta.mostrarDetalles();
        }
        return "Ruta no encontrada";
    }

    public String generarReporteGeneral() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE GENERAL DE RUTAS ===\n\n");
        sb.append("Total de rutas: ").append(contarRutas()).append("\n");
        sb.append("Total de estudiantes: ").append(getTotalEstudiantes()).append("\n");
        sb.append("Total de paradas: ").append(getTotalParadas()).append("\n\n");

        for (Ruta ruta : rutas) {
            sb.append("Ruta: ").append(ruta.getNumeroRuta()).append(" - ").append(ruta.getNombreRuta()).append("\n");
            sb.append("Zona: ").append(ruta.getZona().getNombre()).append("\n");
            sb.append("Estudiantes: ").append(ruta.getTotalEstudiantes()).append("\n");
            sb.append("Paradas: ").append(ruta.listarParadas().size()).append("\n");
            sb.append("Tiempo total: ").append(ruta.calcularTiempoTotal()).append(" min\n");
            sb.append("────────────────────────────────────\n");
        }

        return sb.toString();
    }
}