public class Conductor {
    private String idConductor;
    private String nombreConductor;
    private boolean disponible;

    public Conductor(String idConductor, String nombreConductor) {
        this.idConductor = idConductor;
        this.nombreConductor = nombreConductor;
        this.disponible = true;
    }

    public void conductorDisponible(){
        disponible = true;
    }

    public void conductorNoDisponible(){
        disponible = false;
    }

    public boolean isDisponible(){
        return disponible;
    }

    public String getIdConductor() {
        return idConductor;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    @Override
    public String toString() {
        return  nombreConductor + "("+idConductor+")";
    }
}
