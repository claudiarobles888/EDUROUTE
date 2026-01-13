import java.util.ArrayList;
import java.util.List;

public class Bus {
    private String idBus;
    private String placa;
    private boolean disponible;
    private int capacidadMin = 5;
    private int capacidadMax = 12;
    private List<Conductor> conductores;
    private List<Estudiante> estudiantes;
//hola
    public Bus(String idBus, String placa) {
        this.idBus = idBus;
        this.placa = placa;
        this.disponible = true;
        this.conductores = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }

    public boolean agregarEstudiante(Estudiante e){
        if (estudiantes.size() < capacidadMax){
            estudiantes.add(e);
            return true;
        }
        return false;
    }

    public boolean eliminarEstudiante(String idEst){
        return estudiantes.removeIf(e -> e.getIdEst().equals(idEst));
    }

    public List<Estudiante> listarEstudiantes(){
        return new ArrayList<>(estudiantes);
    }

    public boolean capacidadMinimaBusCumplida(){
        return estudiantes.size() >=capacidadMin;
    }


    public boolean asignarConductor(Conductor conductor){
        if (conductores.size() < 2){
            conductores.add(conductor);
            return true;
        }
        return false;
    }


    public List<Conductor> listarConductores(){
        return new ArrayList<>(conductores);
    }

    public boolean estadoDisponible(){
        return disponible;
    }

    public void marcarNoDisponible(){
        disponible = false;
    }

    public void marcarDisponible(){
        disponible = true;
    }

    public String getIdBus() {
        return idBus;
    }

    public String getPlaca() {
        return placa;
    }
    public int getCapacidadMax(){
        return capacidadMax;
    }

    public int getCapacidadActual(){
        return estudiantes.size();
    }

    @Override
    public String toString(){
        return "Bus{" +placa+", id="+idBus+", ocupado="+estudiantes.size()+"}";
    }

}