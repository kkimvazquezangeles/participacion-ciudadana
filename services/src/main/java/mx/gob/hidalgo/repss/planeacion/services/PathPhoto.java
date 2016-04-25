package mx.gob.hidalgo.repss.planeacion.services;

/**
 * Created by kkimvazquezangeles on 27/10/15.
 */
public enum PathPhoto {
    PHOTO_BASE,
    JUGADOR_BASE,
    EQUIPO_BASE,
    ARBITRO_BASE,
    JUGADOR_DEFAULT,
    ARBITRO_DEFAULT,
    EQUIPO_DEFAULT,
    EQUIPO_DEFAULT_LOCAL,
    EQUIPO_DEFAULT_VISITA;

    public String getPath() {
        switch(this) {
            case PHOTO_BASE:
                return "photo/";
            case JUGADOR_BASE:
                return "jugador/";
            case ARBITRO_BASE:
                return "arbitro/";
            case EQUIPO_BASE:
                return "equipo/";
            case JUGADOR_DEFAULT:
                return "photo/jugador/jugador-default.png";
            case ARBITRO_DEFAULT:
                return "photo/arbitro/arbitro-default.png";
            case EQUIPO_DEFAULT:
                return "photo/equipo/equipo-default.png";
            case EQUIPO_DEFAULT_LOCAL:
                return "photo/equipo/equipo-local-default.png";
            case EQUIPO_DEFAULT_VISITA:
                return "photo/equipo/equipo-visita-default.png";
            default:
                return null;
        }
    }
}
