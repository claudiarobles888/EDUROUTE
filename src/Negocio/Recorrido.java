package Negocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Recorrido {
    private String idRecorrido;
    private LocalDate fecha;
    private String zona;
    private String estado;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Ruta ruta;
    private List<String> eventos;

    public Recorrido(String idRecorrido, LocalDate fecha, String zona, Ruta ruta) {
        this.idRecorrido = idRecorrido;
        this.fecha = fecha;
        this.zona = zona;
        this.ruta = ruta;
        this.estado = "Pendiente";
        this.eventos = new ArrayList<>();
    }

    public void inicio(){
        this.horaInicio = LocalTime.now();
        this.estado = "En curso";
        eventos.add("Negocio.Recorrido " + idRecorrido + " iniciado a las " + horaInicio);
    }

    public void finalizar(){
        this.horaFin = LocalTime.now();
        this.estado = "Finalizado";
        eventos.add("Negocio.Recorrido " + idRecorrido + " finalizado a las " + horaFin);
    }

    public void registrarSubida(Estudiante e){
        String msg = "Sube: " + e.getNombre() +
                " en " + (e.obtenerParada() != null ? e.obtenerParada().getNombreParada() : "parada desconocida");
        System.out.println(msg);
        eventos.add(msg);
    }

    public void registrarBajada(Estudiante e){
        String msg = "Baja: " + e.getNombre();
        System.out.println(msg);
        eventos.add(msg);
    }

    public void registrarAusente(Estudiante e){
        String msg = "ATENCIÓN TRANSPORTISTA: Negocio.Estudiante AUSENTE -> " + e.getNombre();
        System.out.println(msg);
        eventos.add(msg);
    }

    public void replanificarAusencia (List<Estudiante> estudiantes){
        eventos.add("Replanificando recorrido por estudiantes ausentes (" + estudiantes.size() + ")");
    }

    public Ruta getRuta() {
        return ruta;
    }

    public List<String> getEventos() {
        return new ArrayList<>(eventos);
    }

    public String getEstado() {
        return estado;
    }

    public String getIdRecorrido() {
        return idRecorrido;
    }
}
