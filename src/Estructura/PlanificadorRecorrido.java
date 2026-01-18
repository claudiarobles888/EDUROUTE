package Estructura;

import Negocio.*;

import java.util.List;

public class PlanificadorRecorrido {

    public void simularIda(Recorrido recorrido,
                           Bus bus,
                           Conductor choferPrincipal,
                           Conductor choferReemplazo,
                           List<Estudiante> estudiantesAusentes) {

        Conductor choferEnUso = seleccionarChofer(recorrido, choferPrincipal, choferReemplazo);
        if (choferEnUso == null) {
            return;
        }

        if (estudiantesAusentes != null) {
            for (Estudiante e : estudiantesAusentes) {
                e.marcarAusente();
                recorrido.registrarAusente(e);
            }
            recorrido.replanificarAusencia(estudiantesAusentes);
        }

        recorrido.inicio();

        Ruta ruta = recorrido.getRuta();
        if (ruta == null) {
            System.out.println("El recorrido no tiene ruta asociada.");
            return;
        }

        for (Parada parada : ruta.listarParadas()) {
            System.out.println("\nNegocio.Parada: " + parada.getNombreParada());

            for (Estudiante e : parada.listarEstudiantes()) {
                if (e.isAusente()) {
                    continue;
                }

                if (bus.agregarEstudiante(e)) {
                    recorrido.registrarSubida(e);
                } else {
                    String msg = "Negocio.Bus lleno, no pudo subir: " + e.getNombre();
                    System.out.println(msg);
                }
            }
        }

        if (!bus.capacidadMinimaBusCumplida()) {
            System.out.println("Advertencia: no se cumplió capacidad mínima del bus.");
        }

        recorrido.finalizar();
    }


    public void simularVuelta(Recorrido recorrido,
                              Bus bus,
                              Conductor choferPrincipal,
                              Conductor choferReemplazo) {

        Conductor choferEnUso = seleccionarChofer(recorrido, choferPrincipal, choferReemplazo);
        if (choferEnUso == null) {
            return;
        }

        recorrido.inicio();


        for (Estudiante e : bus.listarEstudiantes()) {
            recorrido.registrarBajada(e);
        }


        for (Estudiante e : new java.util.ArrayList<>(bus.listarEstudiantes())) {
            bus.eliminarEstudiante(e.getIdEst());
        }

        recorrido.finalizar();
    }

    private Conductor seleccionarChofer(Recorrido recorrido,
                                        Conductor principal,
                                        Conductor reemplazo) {

        if (principal != null && principal.isDisponible()) {
            principal.conductorNoDisponible();
            String msg = "Chofer principal asignado: " + principal.getNombreConductor();
            System.out.println(msg);
            recorrido.getEventos().add(msg);
            return principal;
        }

        String msgAusente = "Chofer principal ausente o no disponible.";
        System.out.println(msgAusente);

        if (reemplazo != null && reemplazo.isDisponible()) {
            reemplazo.conductorNoDisponible();
            String msgReemp = "Chofer de reemplazo asignado: " + reemplazo.getNombreConductor();
            System.out.println(msgReemp);
            recorrido.getEventos().add(msgAusente);
            recorrido.getEventos().add(msgReemp);
            return reemplazo;
        }

        System.out.println("No hay chofer disponible para el recorrido.");
        recorrido.getEventos().add(msgAusente);
        recorrido.getEventos().add("No hay chofer disponible para el recorrido.");
        return null;
    }
}