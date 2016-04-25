package mx.gob.hidalgo.repss.planeacion.services;

/**
 * Created by kkimvazquezangeles on 21/01/16.
 */
public enum FileOrigin {
    DIR_GENERAL(1),
    DIR_AF_OP(2),
    DIR_GS_SE(3),
    DIR_ADMIN(4),
    DIR_FIN(5),
    DIR_PROS(6),
    DIR_PR_PL(7),
    DIR_VA_SG(8),
    ADMIN(9);

    private final int value;

    FileOrigin(int value) {
        this.value = value;
    }

    public static FileOrigin getEnum(Long value) {
        for (FileOrigin re : FileOrigin.values()) {
            if (re.value == value) {
                return re;
            }
        }
        throw new IllegalArgumentException("Invalid RandomEnum value: " + value);
    }

    public String getPath() {
        switch(this) {
            case DIR_GENERAL:
                return "general/";
            case DIR_AF_OP:
                return "afiliacion/";
            case DIR_GS_SE:
                return "gestion/";
            case DIR_ADMIN:
                return "administracion/";
            case DIR_FIN:
                return "financiamiento/";
            case DIR_PROS:
                return "prospera/";
            case DIR_PR_PL:
                return "prospectiva/";
            case DIR_VA_SG:
                return "evaluacion/";
            case ADMIN:
                return "root/";
            default:
                return null;
        }
    }
}
