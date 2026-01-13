import java.util.ArrayList;
import java.util.List;

public class Ruta {
private String idRuta;
private String nombreRuta;
private String numeroRuta;
private String zona;
private List<String> sectoresPrincipales;
private List<Parada> paradas;

    public Ruta(String idRuta, String nombreRuta, String numeroRuta, String zona, List<String> sectoresPrincipales) {
        this.idRuta = idRuta;
        this.nombreRuta = nombreRuta;
        this.numeroRuta = numeroRuta;
        this.zona = zona;
        this.sectoresPrincipales = new ArrayList<>(sectoresPrincipales);
        this.paradas = new ArrayList<>();
    }

    public void agregarParada (Parada p){
        paradas.add(p);
    }

    public void eliminarParada(String idParada){
        paradas.removeIf(p -> p.getIdParada().equals(idParada));
    }

    public List<Parada> listarParadas(){
        return new ArrayList<>(paradas);
    }

    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> result = new ArrayList<>();
        for (Parada p :paradas){
            result.addAll(p.listarEstudiantes());
        }
        return result;
    }

    public int calcularTiempoTotal(){
        return paradas.stream().mapToInt(Parada::obtenerTiempoEstimado).sum();
    }

    public String getIdRuta() {
        return idRuta;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public String getNumeroRuta() {
        return numeroRuta;
    }

    public String getZona() {
        return zona;
    }

    public List<String> getSectoresPrincipales() {
        return new ArrayList<>(sectoresPrincipales);
    }

    @Override
    public String toString() {
        return "Ruta{" + numeroRuta + " - " + nombreRuta + " (" + zona + ")}";
    }

}
