import java.util.ArrayList;
import java.util.List;

public class GestionParadas {
    private List<Parada> paradas;

    public GestionParadas(){
        this.paradas = new ArrayList<>();
    }

    // C = Create
    public boolean registrarParada(Parada p){
        if (p == null) return false;
        if (buscarPorId(p.getIdParada()) != null) {
            return false; // evitar duplicados
        }
        paradas.add(p);
        return true;
    }

    // R = Read
    public Parada buscarPorId(String id){
        if (id == null) return null;
        return paradas.stream()
                .filter(p -> p.getIdParada().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Parada buscarPorNombre(String nombre){
        if (nombre == null) return null;
        return paradas.stream()
                .filter(p -> p.getNombreParada().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public List<Parada> listar(){
        return new ArrayList<>(paradas);
    }

    // U = Update
    public boolean actualizarParada(String idParada,
                                    String nuevoNombre,
                                    int nuevoTiempo,
                                    String nuevaUbicacion) {
        Parada p = buscarPorId(idParada);
        if (p != null) {
            p.setNombreParada(nuevoNombre);
            // podrías crear setTiempoEstimado si quieres permitir cambiarlo
            p.setUbicacion(nuevaUbicacion);
            return true;
        }
        return false;
    }

    // D = Delete (solo si no tiene estudiantes)
    public boolean eliminarParada(String idParada){
        Parada p = buscarPorId(idParada);
        if(p != null && p.listarEstudiantes().isEmpty()){
            paradas.remove(p);
            return true;
        }
        return false;
    }
}
