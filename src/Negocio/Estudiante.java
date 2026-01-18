package Negocio;

public class Estudiante {
    private String idEst;
    private String nombre;
    private String curso;
    private String direccion;
    private String prioridad;
    private Parada paradaAsignada;
    private boolean ausente;
    private String numeroRuta;
    private String zona;
    private String sector;

    public Estudiante(String idEst, String nombre, String curso, String direccion, String prioridad) {
        this.idEst = idEst;
        this.nombre = nombre;
        this.curso = curso;
        this.direccion = direccion;
        this.prioridad = prioridad;
        this.ausente = false;
    }

    public void asignarParada(Parada parada){
        this.paradaAsignada = parada;
    }

    public Parada obtenerParada(){
        return paradaAsignada;
    }

    public void marcarAusente(){
        this.ausente = true;
    }

    public void marcarPresente(){ this.ausente = false; }

    public String getIdEst() {
        return idEst;
    }

    public void setId(String idEst) {
        this.idEst = idEst;
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

    public boolean isAusente() {
        return ausente;
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

    public String getSector() {
        return sector;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Negocio.Estudiante" +
                "\nid=" + idEst +
                "\nnombre=" + nombre +
                "\ncurso=" + curso +
                "\ndireccion=" + direccion +
                "\nprioridad=" + prioridad +
                "\nnumero de ruta=" + numeroRuta +
                "\nzona=" + zona +
                "\nsector=" + sector +
                "\nparada=" + (paradaAsignada != null ? paradaAsignada.getNombreParada(): "Sin asignar");
    }
}
