package Estructura;  // CAMBIADO de "Negocio" a "Estructura"

import Negocio.Estudiante;
import Negocio.Parada;
import java.util.ArrayList;
import java.util.List;

public class GestionEstudiantes {
    private List<Estudiante> estudiantes;

    public GestionEstudiantes() {
        this.estudiantes = new ArrayList<>();
    }

    public boolean registrarEstudiante(Estudiante estudiante) {
        for (Estudiante e : estudiantes) {
            if (e.getIdEst().equals(estudiante.getIdEst())) {
                return false;
            }
        }
        estudiantes.add(estudiante);
        return true;
    }

    public boolean eliminarEstudiante(String idEst) {
        return estudiantes.removeIf(e -> e.getIdEst().equals(idEst));
    }

    public Estudiante buscarPorId(String idEst) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getIdEst().equals(idEst)) {
                return estudiante;
            }
        }
        return null;
    }

    public boolean actualizarEstudiante(String idEst, String nuevoNombre, String nuevoCurso,
                                        String nuevaDireccion, String nuevaPrioridad) {
        Estudiante estudiante = buscarPorId(idEst);
        if (estudiante != null) {
            estudiante.setNombre(nuevoNombre);
            estudiante.setCurso(nuevoCurso);
            estudiante.setDireccion(nuevaDireccion);
            estudiante.setPrioridad(nuevaPrioridad);
            return true;
        }
        return false;
    }

    public List<Estudiante> buscarPorRuta(String numeroRuta) {
        List<Estudiante> resultado = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getNumeroRuta() != null &&
                    estudiante.getNumeroRuta().equals(numeroRuta)) {
                resultado.add(estudiante);
            }
        }
        return resultado;
    }

    public List<Estudiante> buscarPorSector(String sector) {
        List<Estudiante> resultado = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getSector() != null &&
                    estudiante.getSector().equalsIgnoreCase(sector)) {
                resultado.add(estudiante);
            }
        }
        return resultado;
    }

    public boolean asignarRuta(String idEst, String numeroRuta, String zona, String sector) {
        if (idEst == null || numeroRuta == null || zona == null || sector == null) return false;

        Estudiante estudiante = buscarPorId(idEst.trim());
        if (estudiante == null) return false;


        estudiante.setNumeroRuta(numeroRuta.trim());
        estudiante.setZona(zona.trim());
        estudiante.setSector(sector.trim());

        return true;
    }


    public List<Estudiante> listarEstudiante() {
        return new ArrayList<>(estudiantes);
    }

    public int contarEstudiantes() {
        return estudiantes.size();
    }

    public List<Estudiante> getEstudiantes() {
        return new ArrayList<>(estudiantes);
    }
}