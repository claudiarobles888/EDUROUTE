package Estructura;
import Negocio.Estudiante;

public class GestionEstudiantes {
    private Nodo inicio; // Puntero al primer elemento

    // CLASE INTERNA: El "corazón" de la estructura
    private class Nodo {
        Estudiante estudiante;
        Nodo siguiente;
        Nodo(Estudiante e) { this.estudiante = e; }
    }

    public boolean registrarEstudiante(Estudiante e) {
        if (buscarPorId(e.getIdEst()) != null) return false; // Evita duplicados
        Nodo nuevo = new Nodo(e);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo aux = inicio;
            while (aux.siguiente != null) aux = aux.siguiente;
            aux.siguiente = nuevo;
        }
        return true;
    }

    public Estudiante buscarPorId(String id) {
        Nodo aux = inicio;
        while (aux != null) {
            if (aux.estudiante.getIdEst().equals(id)) return aux.estudiante;
            aux = aux.siguiente;
        }
        return null;
    }

    // Método para que la interfaz pueda listar (reemplaza al listarEstudiante anterior)
    public java.util.List<Estudiante> listarEstudiante() {
        java.util.List<Estudiante> listaAux = new java.util.ArrayList<>();
        Nodo aux = inicio;
        while (aux != null) {
            listaAux.add(aux.estudiante);
            aux = aux.siguiente;
        }
        return listaAux;
    }
}