import java.util.ArrayList;
import java.util.List;

public class Parada {
private String idParada;
private String nombreParada;
private int tiempoEstimado;
private String ubicacion;
private List<Estudiante> estudiantes;

    public Parada(String idParada, String nombreParada, int tiempoEstimado, String ubicacion) {
        this.idParada = idParada;
        this.nombreParada = nombreParada;
        this.tiempoEstimado = tiempoEstimado;
        this.ubicacion = ubicacion;
        this.estudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante e){
        if(!estudiantes.contains(e)){
            estudiantes.add(e);
            e.asignarParada(this);
        }
    }

    public void eliminarEstudiante(String idEstudiante){
        estudiantes.removeIf(e -> e.getIdEst().equals(idEstudiante));
    }

    public List<Estudiante> listarEstudiantes(){
        return new ArrayList<>(estudiantes);
    }

    public int obtenerTiempoEstimado(){
        return tiempoEstimado;
    }
    public String getIdParada() {
        return idParada;
    }

    public void setIdParada(String idParada) {
        this.idParada = idParada;
    }

    public String getNombreParada() {
        return nombreParada;
    }

    public void setNombreParada(String nombreParada) {
        this.nombreParada = nombreParada;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Parada" +
                "\nidParada=" + idParada +
                "\nnombreParada=" + nombreParada +
                "\ntiempoEstimado=" + tiempoEstimado +
                "\nubicacion=" + ubicacion +
                "\nestudiantes=" + estudiantes.size();
    }
}