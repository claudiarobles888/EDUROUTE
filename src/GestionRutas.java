import java.util.ArrayList;
import java.util.List;

public class GestionRutas {

    private List<Ruta> rutas;

    public GestionRutas() {
        this.rutas = new ArrayList<>();
    }

    public boolean registrarRuta(Ruta ruta) {
        if (ruta == null) {
            return false;
        }

        if (buscarPorId(ruta.getIdRuta()) != null ||
                buscarPorNumeroRuta(ruta.getNumeroRuta()) != null) {
            return false;
        }

        rutas.add(ruta);
        return true;
    }

    public Ruta buscarPorId(String idRuta) {
        if (idRuta == null) return null;
        return rutas.stream()
                .filter(r -> r.getIdRuta().equals(idRuta))
                .findFirst()
                .orElse(null);
    }

    public Ruta buscarPorNumeroRuta (String numeroRuta){
        if (numeroRuta == null) return null;
        return rutas.stream()
                .filter(r -> numeroRuta.equals(r.getNumeroRuta()))
                .findFirst()
                .orElse(null);

    }

    public boolean eliminarRuta(String idRuta) {
        return rutas.removeIf(r -> r.getIdRuta().equals(idRuta));
    }

    public List<Ruta> listarRutas() {
        return new ArrayList<>(rutas);
    }

    public boolean agregarParadaARuta(String idRuta, Parada parada) {
        Ruta ruta = buscarPorId(idRuta);
        if (ruta != null && parada != null) {
            ruta.agregarParada(parada);
            return true;
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

    public List<Estudiante> listarEstudiantesDeRuta(String idRuta) {
        Ruta ruta = buscarPorId(idRuta);
        if (ruta != null) {
            return ruta.listarEstudiantes();
        }
        return new ArrayList<>();
    }

    public int obtenerTiempoTotalRuta(String idRuta) {
        Ruta ruta = buscarPorId(idRuta);
        if (ruta != null) {
            return ruta.calcularTiempoTotal();
        }
        return 0;
    }


}


