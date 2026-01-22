package Negocio;

import java.util.ArrayList;
import java.util.List;

public class Bus {
    private final String idBus;
    private final String placa;
    private boolean disponible;

    private final int capacidadMin = 5;
    private final int capacidadMax;

    private final List<Conductor> conductores;
    private final List<Estudiante> estudiantes;

    public Bus(String idBus, String placa, int capacidadMax) {
        if (idBus == null || idBus.isBlank()) throw new IllegalArgumentException("idBus inválido");
        if (placa == null || placa.isBlank()) throw new IllegalArgumentException("placa inválida");

        if (capacidadMax < capacidadMin || capacidadMax > 50) {
            throw new IllegalArgumentException("capacidadMax debe estar entre " + capacidadMin + " y 50");
        }

        this.idBus = idBus;
        this.placa = placa;
        this.capacidadMax = capacidadMax;

        this.disponible = true;
        this.conductores = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }

    public Bus(String idBus, String placa) {
        this(idBus, placa, 12);
    }

    public boolean agregarEstudiante(Estudiante e) {
        if (e == null) return false;
        if (estudiantes.size() >= capacidadMax) return false;

        boolean existe = estudiantes.stream().anyMatch(x -> x.getIdEst().equals(e.getIdEst()));
        if (existe) return false;

        estudiantes.add(e);
        return true;
    }

    public boolean eliminarEstudiante(String idEst) {
        if (idEst == null) return false;
        return estudiantes.removeIf(e -> idEst.equals(e.getIdEst()));
    }

    public List<Estudiante> listarEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    public boolean capacidadMinimaBusCumplida() {
        return estudiantes.size() >= capacidadMin;
    }

    public boolean asignarConductor(Conductor conductor) {
        if (conductor == null) return false;
        if (conductores.size() >= 2) return false;

        boolean existe = conductores.stream().anyMatch(c -> c.getIdConductor().equals(conductor.getIdConductor()));
        if (existe) return false;

        conductores.add(conductor);
        return true;
    }


    public boolean removerConductor(String idConductor) {
        if (idConductor == null) return false;
        return conductores.removeIf(c -> idConductor.equals(c.getIdConductor()));
    }

    public List<Conductor> listarConductores() {
        return new ArrayList<>(conductores);
    }

    public boolean estadoDisponible() {
        return disponible;
    }

    public void marcarNoDisponible() { disponible = false; }
    public void marcarDisponible() { disponible = true; }

    public String getIdBus() { return idBus; }
    public String getPlaca() { return placa; }
    public int getCapacidadMax() { return capacidadMax; }
    public int getCapacidadActual() { return estudiantes.size(); }

    @Override
    public String toString() {
        return "Bus{" + placa + ", id=" + idBus + ", ocupado=" + estudiantes.size() + "/" + capacidadMax + "}";
    }
}
