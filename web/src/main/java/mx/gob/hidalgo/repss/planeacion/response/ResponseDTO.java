package mx.gob.hidalgo.repss.planeacion.response;

/**
 * Created by kkimvazquezangeles on 9/04/15.
 */
public class ResponseDTO {
    public static final String CODE_SUCCESS                  = "success";
    public static final String CODE_ERROR                    = "error";

    private String code;
    private String message;

    public ResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
