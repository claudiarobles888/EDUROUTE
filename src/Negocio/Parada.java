package Negocio;

import java.util.ArrayList;
import java.util.List;

public class Parada {
    private String idParada;
    private String nombre;
    private int tiempoEstimado;
    private String ubicacion;
    private String sector;
    private List<Estudiante> estudiantes;
    private Ruta rutaAsignada;

    public Parada(String idParada, String nombre, int tiempoEstimado, String ubicacion, String sector) {
        this.idParada = idParada;
        this.nombre = nombre;
        this.tiempoEstimado = tiempoEstimado;
        this.ubicacion = ubicacion;
        this.sector = sector;
        this.estudiantes = new ArrayList<>();
        this.rutaAsignada = null;
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public void eliminarEstudiante(String idEstudiante) {
        estudiantes.removeIf(e -> e.getIdEstudiante().equals(idEstudiante));
    }

    public List<Estudiante> listarEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    public int obtenerTiempoEstimado() {
        return tiempoEstimado;
    }

    
    public String getIdParada() {
        return idParada;
    }

    public String getNombreParada() {
        return nombre;
    }

    public void setNombreParada(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }


    public Ruta getRutaAsignada() {
        return rutaAsignada;
    }

    public void setRutaAsignada(Ruta rutaAsignada) {
        this.rutaAsignada = rutaAsignada;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (Sector: %s, Ubicación: %s, Tiempo: %d min)",
                idParada, nombre, sector, ubicacion, tiempoEstimado);
    }
}