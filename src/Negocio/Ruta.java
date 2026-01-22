package Negocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ruta {


    public enum Zona {
        NORTE("Norte", Arrays.asList("Carcelén", "Ponceano", "El Condado", "La Ofelia", "Cotocollao")),
        CENTRO_NORTE("Centro Norte", Arrays.asList("La Gasca", "Bellavista", "Iñaquito", "El Batán", "González Suárez")),
        CENTRO("Centro", Arrays.asList("La Marín", "San Blas", "La Tola", "Itchimbía", "San Roque")),
        SUR("Sur", Arrays.asList("Chimbacalle", "El Recreo", "La Magdalena", "Quitumbe", "Guamaní")),
        VALLES_CHILLOS("Valle de los Chillos", Arrays.asList("Conocoto", "San Rafael", "Alangasí")),
        VALLES_TUMBACO("Valle de Tumbaco", Arrays.asList("Cumbayá", "Tumbaco", "Puembo")),
        NOROESTE("Noroeste", Arrays.asList("Pomasqui", "San Antonio de Pichincha", "Calderón")),
        SURORIENTAL("Suroriental", Arrays.asList("Amaguaña", "Alóag", "Tambillo"));

        private final String nombre;
        private final List<String> sectores;

        Zona(String nombre, List<String> sectores) {
            this.nombre = nombre;
            this.sectores = sectores;
        }

        public String getNombre() {
            return nombre;
        }

        public List<String> getSectores() {
            return new ArrayList<>(sectores);
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    private String idRuta;
    private String nombreRuta;
    private String numeroRuta;
    private Zona zona;
    private List<Parada> paradas;

    public Ruta(String idRuta, String nombreRuta, String numeroRuta, Zona zona) {
        this.idRuta = idRuta;
        this.nombreRuta = nombreRuta;
        this.numeroRuta = numeroRuta;
        this.zona = zona;
        this.paradas = new ArrayList<>();
    }

    public boolean agregarParada(Parada p) {
        // Validar que la parada pertenezca a esta zona
        if (p.getSector() == null || !zona.getSectores().contains(p.getSector())) {
            return false;
        }
        paradas.add(p);
        p.setRutaAsignada(this);
        return true;
    }

    public void eliminarParada(String idParada) {
        paradas.removeIf(p -> p.getIdParada().equals(idParada));
    }

    public List<Parada> listarParadas() {
        return new ArrayList<>(paradas);
    }

    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> result = new ArrayList<>();
        for (Parada p : paradas) {
            result.addAll(p.listarEstudiantes());
        }
        return result;
    }

    public int calcularTiempoTotal() {
        return paradas.stream().mapToInt(Parada::obtenerTiempoEstimado).sum();
    }

    public int getTotalEstudiantes() {
        return listarEstudiantes().size();
    }

    public List<String> getSectoresZona() {
        return zona.getSectores();
    }

    public boolean puedeAgregarParada(Parada p) {
        return p.getSector() != null && zona.getSectores().contains(p.getSector());
    }

    public static List<Zona> getTodasLasZonas() {
        return Arrays.asList(Zona.values());
    }

    public static Zona getZonaPorNombre(String nombre) {
        for (Zona zona : Zona.values()) {
            if (zona.getNombre().equalsIgnoreCase(nombre)) {
                return zona;
            }
        }
        return null;
    }

    // Getters
    public String getIdRuta() {
        return idRuta;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public String getNumeroRuta() {
        return numeroRuta;
    }

    public Zona getZona() {
        return zona;
    }

    public List<String> getSectoresPrincipales() {
        return getSectoresZona();
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", numeroRuta, nombreRuta, zona.getNombre());
    }

    public String mostrarDetalles() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== DETALLES DE RUTA ===\n");
        sb.append("ID: ").append(idRuta).append("\n");
        sb.append("Nombre: ").append(nombreRuta).append("\n");
        sb.append("Número: ").append(numeroRuta).append("\n");
        sb.append("Zona: ").append(zona.getNombre()).append("\n");
        sb.append("Sectores: ").append(String.join(", ", zona.getSectores())).append("\n");
        sb.append("Tiempo total: ").append(calcularTiempoTotal()).append(" min\n");
        sb.append("Total estudiantes: ").append(getTotalEstudiantes()).append("\n");
        sb.append("Paradas: ").append(paradas.size()).append("\n");

        if (!paradas.isEmpty()) {
            sb.append("\nParadas en orden:\n");
            for (int i = 0; i < paradas.size(); i++) {
                sb.append((i + 1)).append(". ").append(paradas.get(i).toString()).append("\n");
            }
        }

        return sb.toString();
    }
}