package Negocio;

import java.util.ArrayList;
import java.util.List;

public class Bus {
    private String idBus;
    private String placa;
    private boolean disponible;
    private int capacidadMin = 5;
    private int capacidadMax;  // NO inicializado con valor fijo
    private List<Conductor> conductores;
    private List<Estudiante> estudiantes;

    // CONSTRUCTOR 1: Con capacidad personalizada (RECOMENDADO)
    public Bus(String idBus, String placa, int capacidadMax) {
        this.idBus = idBus;
        this.placa = placa;
        this.capacidadMax = capacidadMax;  // Asigna la capacidad recibida
        this.disponible = true;
        this.conductores = new ArrayList<>();
        this.estudiantes = new ArrayList<>();

        // Validar capacidad mínima
        if (capacidadMax < capacidadMin) {
            this.capacidadMax = capacidadMin;
            System.out.println("Advertencia: Capacidad ajustada a mínimo " + capacidadMin);
        }
    }

    // CONSTRUCTOR 2: Con capacidad por defecto (12) - para compatibilidad
    public Bus(String idBus, String placa) {
        this(idBus, placa, 12);  // Llama al primer constructor con 12
    }

    // MÉTODOS PARA AGREGAR/ELIMINAR ESTUDIANTES
    public boolean agregarEstudiante(Estudiante e) {
        if (estudiantes.size() < capacidadMax) {
            estudiantes.add(e);
            return true;
        }
        return false;
    }

    public boolean eliminarEstudiante(String idEst) {
        return estudiantes.removeIf(e -> e.getIdEst().equals(idEst));
    }

    public List<Estudiante> listarEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    // MÉTODOS PARA CONDUCTORES
    public boolean asignarConductor(Conductor conductor) {
        if (conductores.size() < 2) {
            conductores.add(conductor);
            return true;
        }
        return false;
    }

    public List<Conductor> listarConductores() {
        return new ArrayList<>(conductores);
    }

    // MÉTODOS DE CAPACIDAD
    public boolean capacidadMinimaBusCumplida() {
        return estudiantes.size() >= capacidadMin;
    }

    public int getCapacidadActual() {
        return estudiantes.size();
    }

    // GETTERS Y SETTERS
    public String getIdBus() {
        return idBus;
    }

    public String getPlaca() {
        return placa;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    // Setter para modificar capacidad después de creado
    public void setCapacidadMax(int capacidadMax) {
        if (capacidadMax >= capacidadMin && capacidadMax >= estudiantes.size()) {
            this.capacidadMax = capacidadMax;
        } else {
            System.out.println("Error: Capacidad no válida. Mínimo " + capacidadMin +
                    " y no menor que estudiantes actuales (" + estudiantes.size() + ")");
        }
    }

    public boolean estadoDisponible() {
        return disponible;
    }

    public void marcarNoDisponible() {
        disponible = false;
    }

    public void marcarDisponible() {
        disponible = true;
    }

    // MÉTODO PARA VER ESTADO DEL BUS
    @Override
    public String toString() {
        return "Bus{" +
                "id='" + idBus + '\'' +
                ", placa='" + placa + '\'' +
                ", estado=" + (disponible ? "Disponible" : "No disponible") +
                ", capacidad=" + estudiantes.size() + "/" + capacidadMax +
                ", conductores=" + conductores.size() +
                '}';
    }
}