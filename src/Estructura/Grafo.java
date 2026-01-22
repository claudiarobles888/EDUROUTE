package Estructura;

import Negocio.Parada;

import java.util.*;

public class Grafo {

    private final boolean bidireccional;

    private final Map<String, Parada> paradas = new HashMap<>();
    private final Map<String, Map<String, Integer>> ady = new HashMap<>();

    public Grafo() {
        this(true);
    }

    public Grafo(boolean bidireccional) {
        this.bidireccional = bidireccional;
    }


    private static void requireNonNull(Object o, String msg) {
        if (o == null) throw new IllegalArgumentException(msg);
    }

    private void requireParadaExiste(String id) {
        if (!paradas.containsKey(id)) {
            throw new IllegalArgumentException("La parada '" + id + "' no existe en el grafo");
        }
    }

    private static void requirePesoValido(int peso) {
        if (peso < 0) throw new IllegalArgumentException("El peso/tiempo no puede ser negativo");
    }


    public boolean existeParada(String idParada) {
        return idParada != null && paradas.containsKey(idParada);
    }

    public void agregarParada(Parada parada) {
        requireNonNull(parada, "La parada no puede ser null");
        requireNonNull(parada.getIdParada(), "idParada no puede ser null");

        String id = parada.getIdParada();
        paradas.put(id, parada);
        ady.putIfAbsent(id, new HashMap<>());
    }

    public void añadirParada(Parada parada) {
        agregarParada(parada);
    }

    public boolean eliminarParada(String idParada) {
        if (idParada == null || !paradas.containsKey(idParada)) return false;


        paradas.remove(idParada);


        ady.remove(idParada);


        for (Map<String, Integer> vecinos : ady.values()) {
            vecinos.remove(idParada);
        }

        return true;
    }

    public Parada obtenerParada(String idParada) {
        return paradas.get(idParada);
    }

    public Set<String> listarIdsParadas() {
        return Collections.unmodifiableSet(paradas.keySet());
    }


    public void agregarRuta(String origen, String destino, int tiempo) {
        requireNonNull(origen, "origen no puede ser null");
        requireNonNull(destino, "destino no puede ser null");
        requirePesoValido(tiempo);
        requireParadaExiste(origen);
        requireParadaExiste(destino);

        ady.putIfAbsent(origen, new HashMap<>());
        ady.get(origen).put(destino, tiempo);

        if (bidireccional) {
            ady.putIfAbsent(destino, new HashMap<>());
            ady.get(destino).put(origen, tiempo);
        }
    }

    public void añadirRuta(String origen, String destino, int tiempo) {
        agregarRuta(origen, destino, tiempo);
    }

    public boolean eliminarRuta(String origen, String destino) {
        if (origen == null || destino == null) return false;
        Map<String, Integer> v = ady.get(origen);
        if (v == null || !v.containsKey(destino)) return false;

        v.remove(destino);

        if (bidireccional) {
            Map<String, Integer> v2 = ady.get(destino);
            if (v2 != null) v2.remove(origen);
        }
        return true;
    }

    public Map<String, Integer> obtenerVecinos(String idParada) {
        Map<String, Integer> v = ady.get(idParada);
        if (v == null) return Collections.emptyMap();
        return Collections.unmodifiableMap(v);
    }

    public boolean esBidireccional() {
        return bidireccional;
    }


    public static final class ResultadoCamino {
        private final List<String> camino;
        private final long costoTotal;

        public ResultadoCamino(List<String> camino, long costoTotal) {
            this.camino = Collections.unmodifiableList(camino);
            this.costoTotal = costoTotal;
        }

        public List<String> getCamino() { return camino; }
        public long getCostoTotal() { return costoTotal; }

        public boolean existe() {
            return !camino.isEmpty();
        }

        @Override
        public String toString() {
            return "ResultadoCamino{camino=" + camino + ", costoTotal=" + costoTotal + "}";
        }
    }

    private static final class NodeDist {
        final String id;
        final long dist;

        NodeDist(String id, long dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    public ResultadoCamino caminoMasCorto(String inicio, String destino) {
        if (inicio == null || destino == null) return new ResultadoCamino(List.of(), Long.MAX_VALUE);
        if (!existeParada(inicio) || !existeParada(destino)) return new ResultadoCamino(List.of(), Long.MAX_VALUE);
        if (inicio.equals(destino)) return new ResultadoCamino(List.of(inicio), 0);

        final long INF = Long.MAX_VALUE / 4;

        Map<String, Long> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();

        for (String id : paradas.keySet()) {
            dist.put(id, INF);
            prev.put(id, null);
        }
        dist.put(inicio, 0L);

        PriorityQueue<NodeDist> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.dist));
        pq.add(new NodeDist(inicio, 0L));

        while (!pq.isEmpty()) {
            NodeDist actual = pq.poll();

            if (actual.dist != dist.get(actual.id)) continue;

            if (actual.id.equals(destino)) break;

            for (Map.Entry<String, Integer> e : obtenerVecinos(actual.id).entrySet()) {
                String vecino = e.getKey();
                int peso = e.getValue();

                long nueva = actual.dist + peso;
                if (nueva < dist.get(vecino)) {
                    dist.put(vecino, nueva);
                    prev.put(vecino, actual.id);
                    pq.add(new NodeDist(vecino, nueva));
                }
            }
        }

        long costo = dist.get(destino);
        if (costo >= INF) return new ResultadoCamino(List.of(), Long.MAX_VALUE);

        LinkedList<String> camino = new LinkedList<>();
        for (String at = destino; at != null; at = prev.get(at)) {
            camino.addFirst(at);
        }

        return new ResultadoCamino(camino, costo);
    }


    public List<String> calcularCaminoMasCorto(String inicio, String destino) {
        return caminoMasCorto(inicio, destino).getCamino();
    }
}