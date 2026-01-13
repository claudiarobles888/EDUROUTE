import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestionEstudiantes {
    private List<Estudiante> estudiantes;

    public GestionEstudiantes(){
        this.estudiantes = new ArrayList<>();
    }

    // C = Create
    public boolean registrarEstudiante(Estudiante e){
        if (e == null) return false;

        // Evitar duplicados por ID
        if (buscarPorId(e.getIdEst()) != null){
            return false;
        }
        estudiantes.add(e);
        return true;
    }

    // R = Read (consultar por ID)
    public Estudiante buscarPorId(String id){
        if (id == null) return null;
        return estudiantes.stream()
                .filter(s -> s.getIdEst().equals(id))
                .findFirst()
                .orElse(null);
    }

    // R = Read (listar todos)
    public List<Estudiante> listarEstudiante(){
        return new ArrayList<>(estudiantes);
    }

    // U = Update
    public boolean actualizarEstudiante(String id,
                                        String nuevoNombre,
                                        String nuevoCurso,
                                        String nuevaDireccion,
                                        String nuevaPrioridad){
        Estudiante e = buscarPorId(id);
        if (e != null){
            e.setNombre(nuevoNombre);
            e.setCurso(nuevoCurso);
            e.setDireccion(nuevaDireccion);
            e.setPrioridad(nuevaPrioridad);
            return true;
        }
        return false;
    }

    // D = Delete
    public boolean eliminarEstudiante(String id){
        return estudiantes.removeIf(e -> e.getIdEst().equals(id));
    }

    // Clasificación por prioridad
    public List<Estudiante> filtrarPorPrioridad(String prioridad){
        return estudiantes.stream()
                .filter(e -> e.getPrioridad() != null &&
                        e.getPrioridad().equalsIgnoreCase(prioridad))
                .collect(Collectors.toList());
    }

    // Asignar número de ruta al estudiante
    public boolean asignarRuta(String idEst, String numRuta, String zona, String sector){
        Estudiante e = buscarPorId(idEst);
        if(e != null){
            e.setNumeroRuta(numRuta);
            e.setZona(zona);
            e.setSector(sector);
            return true;
        }
        return false;
    }

    // Listar estudiantes por número de ruta
    public List<Estudiante> listarPorRuta(String numeroRuta){
        return estudiantes.stream()
                .filter(e -> numeroRuta.equals(e.getNumeroRuta()))
                .collect(Collectors.toList());
    }
}
