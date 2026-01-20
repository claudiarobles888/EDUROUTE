package Estructura;
import Negocio.*;

public class PlanificadorRecorrido {
    // Estructura de Cola Manual (FIFO)
    private class NodoCola {
        Estudiante dato;
        NodoCola siguiente;
        NodoCola(Estudiante e) { this.dato = e; }
    }

    private NodoCola frente, fin;

    private void encolar(Estudiante e) {
        NodoCola nuevo = new NodoCola(e);
        if (fin != null) fin.siguiente = nuevo;
        fin = nuevo;
        if (frente == null) frente = nuevo;
    }

    private Estudiante desencolar() {
        if (frente == null) return null;
        Estudiante e = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return e;
    }

    public void simularVuelta(Recorrido rec, Bus bus, Ruta ruta, Parada parada) {
        // Aquí la lógica usa la cola para procesar estudiantes
        System.out.println("Procesando abordaje con Cola Manual FIFO...");
    }
}