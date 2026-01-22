package Estructura;

import Negocio.Parada;
import Negocio.Estudiante;
import Negocio.Ruta;
import java.util.ArrayList;
import java.util.List;

public class GestionParadas {
    private List<Parada> paradas;

    public GestionParadas() {
        this.paradas = new ArrayList<>();
    }

    public boolean registrarParada(Parada parada) {
        for (Parada p : paradas) {
            if (p.getIdParada().equals(parada.getIdParada())) {
                return false;
            }
        }
        paradas.add(parada);
        return true;
    }

    public boolean eliminarParada(String idParada) {
        return paradas.removeIf(p -> p.getIdParada().equals(idParada));
    }

    public Parada buscarPorId(String idParada) {
        for (Parada parada : paradas) {
            if (parada.getIdParada().equals(idParada)) {
                return parada;
            }
        }
        return null;
    }

    public List<Parada> buscarPorNombre(String nombre) {
        List<Parada> resultado = new ArrayList<>();
        for (Parada parada : paradas) {
            if (parada.getNombreParada().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(parada);
            }
        }
        return resultado;
    }

    public List<Parada> buscarPorSector(String sector) {
        List<Parada> resultado = new ArrayList<>();
        for (Parada parada : paradas) {
            if (parada.getSector() != null &&
                    parada.getSector().equalsIgnoreCase(sector)) {
                resultado.add(parada);
            }
        }
        return resultado;
    }

    public List<Parada> buscarPorZona(Ruta.Zona zona) {
        List<Parada> resultado = new ArrayList<>();
        for (Parada parada : paradas) {
            if (parada.getSector() != null &&
                    zona.getSectores().contains(parada.getSector())) {
                resultado.add(parada);
            }
        }
        return resultado;
    }

    public List<Parada> buscarPorNombreZona(String nombreZona) {
        Ruta.Zona zona = Ruta.getZonaPorNombre(nombreZona);
        if (zona != null) {
            return buscarPorZona(zona);
        }
        return new ArrayList<>();
    }

    public boolean actualizarParada(String idParada, String nuevoNombre,
                                    int nuevoTiempo, String nuevaUbicacion,
                                    String nuevoSector) {
        Parada parada = buscarPorId(idParada);
        if (parada != null) {
            parada.setNombreParada(nuevoNombre);
            parada.setTiempoEstimado(nuevoTiempo);
            parada.setUbicacion(nuevaUbicacion);
            parada.setSector(nuevoSector);
            return true;
        }
        return false;
    }

    public List<Parada> listar() {
        return new ArrayList<>(paradas);
    }

    public int contarParadas() {
        return paradas.size();
    }

    public List<Parada> getParadas() {
        return new ArrayList<>(paradas);
    }
}