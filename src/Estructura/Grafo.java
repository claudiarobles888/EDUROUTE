package Estructura;

import Negocio.Parada;

import java.util.*;

public class Grafo {
    private Map<String, Parada> paradas;
    private Map<String, Map<String, Integer>> rutas; // Map para guardar las conexiones (parada1 -> parada2 -> tiempo)

    public Grafo() {
        paradas = new HashMap<>();
        rutas = new HashMap<>();
    }

    // Añadir una parada
    public void añadirParada(Parada parada) {
        paradas.put(parada.getIdParada(), parada);
    }

    // Añadir una ruta entre dos paradas con un tiempo estimado
    public void añadirRuta(String idParadaOrigen, String idParadaDestino, int tiempoEstimado) {
        rutas.putIfAbsent(idParadaOrigen, new HashMap<>());
        rutas.get(idParadaOrigen).put(idParadaDestino, tiempoEstimado);
    }

    // Obtener la lista de paradas conectadas
    public Map<String, Integer> obtenerRutasDesdeParada(String idParada) {
        return rutas.getOrDefault(idParada, new HashMap<>());
    }

    public List<String> calcularCaminoMasCorto(String inicio, String destino) {
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(distancias::get));

        for (String parada : paradas.keySet()) {
            distancias.put(parada, Integer.MAX_VALUE);
            predecesores.put(parada, null);
        }

        distancias.put(inicio, 0);
        pq.add(inicio);

        while (!pq.isEmpty()) {
            String actual = pq.poll();

            if (actual.equals(destino)) {
                break;
            }

            for (Map.Entry<String, Integer> vecino : obtenerRutasDesdeParada(actual).entrySet()) {
                String siguienteParada = vecino.getKey();
                int tiempo = vecino.getValue();
                int nuevaDistancia = distancias.get(actual) + tiempo;

                if (nuevaDistancia < distancias.get(siguienteParada)) {
                    distancias.put(siguienteParada, nuevaDistancia);
                    predecesores.put(siguienteParada, actual);
                    pq.add(siguienteParada);
                }
            }
        }

        // Recuperar el camino más corto
        List<String> camino = new ArrayList<>();
        for (String parada = destino; parada != null; parada = predecesores.get(parada)) {
            camino.add(parada);
        }
        Collections.reverse(camino);
        return camino;
    }
}

