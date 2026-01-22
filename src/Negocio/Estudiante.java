package Negocio;

public class Estudiante {
    private String idEst;
    private String nombre;
    private String curso;
    private String direccion;
    private String prioridad;
    private String numeroRuta;
    private String zona;
    private String sector;
    private Parada paradaAsignada;
    private boolean ausente;

    public Estudiante(String idEst, String nombre, String curso, String direccion, String prioridad) {
        this.idEst = idEst;
        this.nombre = nombre;
        this.curso = curso;
        this.direccion = direccion;
        this.prioridad = prioridad;
        this.ausente = false;
        this.paradaAsignada = null;
        this.numeroRuta = null;
        this.zona = null;
        this.sector = null;
    }

    // Getters y Setters
    public String getIdEst() {
        return idEst;
    }

    public String getIdEstudiante() {
        return idEst;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getNumeroRuta() {
        return numeroRuta;
    }

    public void setNumeroRuta(String numeroRuta) {
        this.numeroRuta = numeroRuta;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Parada obtenerParada() {
        return paradaAsignada;
    }

    public void asignarParada(Parada parada) {
        this.paradaAsignada = parada;
        if (parada != null) {
            this.sector = parada.getSector();
        }
    }

    public boolean isAusente() {
        return ausente;
    }

    public void setAusente(boolean ausente) {
        this.ausente = ausente;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s) - Prioridad: %s",
                idEst, nombre, curso, prioridad);
    }
}