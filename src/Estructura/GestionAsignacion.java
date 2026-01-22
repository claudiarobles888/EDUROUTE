package Estructura;

import Negocio.Estudiante;
import Negocio.Parada;
import Negocio.Ruta;

public class GestionAsignacion {
    private final GestionEstudiantes gestionEstudiantes;
    private final GestionParadas gestionParadas;
    private final GestionRutas gestionRutas;

    public GestionAsignacion(GestionEstudiantes gestionEstudiantes,
                             GestionParadas gestionParadas,
                             GestionRutas gestionRutas) {
        if (gestionEstudiantes == null || gestionParadas == null || gestionRutas == null) {
            throw new IllegalArgumentException("Los gestores no pueden ser null");
        }
        this.gestionEstudiantes = gestionEstudiantes;
        this.gestionParadas = gestionParadas;
        this.gestionRutas = gestionRutas;
    }

    public boolean asignarParadaEstudiante(String idEst, String idParada) {
        if (idEst == null || idParada == null) return false;

        Estudiante est = gestionEstudiantes.buscarPorId(idEst.trim());
        Parada parada = gestionParadas.buscarPorId(idParada.trim());

        if (est == null || parada == null) return false;


        Parada anterior = est.obtenerParada();
        if (anterior != null && !anterior.getIdParada().equals(parada.getIdParada())) {
            anterior.eliminarEstudiante(est.getIdEst());
        }

        parada.agregarEstudiante(est);
        return true;
    }

    public boolean asignarRutaEstudiante(String idEst, String numeroRuta, String sectorSeleccionado) {
        if (idEst == null || numeroRuta == null || sectorSeleccionado == null) return false;

        Estudiante est = gestionEstudiantes.buscarPorId(idEst.trim());


        Ruta ruta = gestionRutas.buscarPorNumero(numeroRuta.trim());

        if (est == null || ruta == null) return false;

        String sector = sectorSeleccionado.trim();



        boolean sectorValido = ruta.getZona() != null
                && ruta.getZona().getSectores().stream().anyMatch(s -> s.equalsIgnoreCase(sector));if (!sectorValido) return false;

        return gestionEstudiantes.asignarRuta(
                est.getIdEst(),
                ruta.getNumeroRuta(),
                ruta.getZona().getNombre(),
                sector
        );
    }

    public boolean asignarRutaAEstudiante(String idEst, String numeroRuta, String sectorSeleccionado) {
        return asignarRutaEstudiante(idEst, numeroRuta, sectorSeleccionado);
    }
}
