package Estructura;

import Negocio.Estudiante;
import Negocio.Parada;
import Negocio.Ruta;

public class GestionAsignacion {
    private GestionEstudiantes gestionEstudiantes;
    private GestionParadas gestionParadas;
    private GestionRutas gestionRutas;

    public GestionAsignacion(GestionEstudiantes ge, GestionParadas gp, GestionRutas gr) {
        this.gestionEstudiantes = ge;
        this.gestionParadas = gp;
        this.gestionRutas = gr;
    }

    public boolean asignarParadaEstudiante(String idEst, String idParada){
        Estudiante est = gestionEstudiantes.buscarPorId(idEst);
        Parada parada = gestionParadas.buscarPorId(idParada);

        if (est != null && parada != null){
            parada.agregarEstudiante(est);
        return true;
        }
        return false;
    }

    public boolean asignarRutaAEstudiante(String idEst, String numeroRuta, String sectorSeleccionado){
        Estudiante est = gestionEstudiantes.buscarPorId(idEst);
        Ruta ruta = gestionRutas.buscarPorNumeroRuta(numeroRuta);
        if(est != null && ruta != null){
            if(ruta.getSectoresPrincipales().contains(sectorSeleccionado)){
                gestionEstudiantes.asignarRuta(idEst, numeroRuta, ruta.getZona(), sectorSeleccionado);
                return true;
            } else {
                gestionEstudiantes.asignarRuta(idEst, numeroRuta, ruta.getZona(), sectorSeleccionado);
                return true;
            }
        }
        return false;
    }
}
