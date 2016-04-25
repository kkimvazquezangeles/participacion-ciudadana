package mx.gob.hidalgo.repss.planeacion.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kkimvazquezangeles on 25/01/16.
 */
@Entity
@Table(	name = "USER_TOKEN" )
public class UserToken {
    @Id
    @Column(name = "token", nullable = false, length = 45)
    private String token;
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User user;
    @Enumerated(EnumType.STRING)
    private TipoToken tipo;
    @Column(name = "fecha_vigencia")
    private Date fechaVigencia;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    public Date getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Date fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    public UserToken() {
    }

    public UserToken(User user, String token, TipoToken tipo, Date fechaVigencia) {
        this.user = user;
        this.token = token;
        this.tipo = tipo;
        this.fechaVigencia = fechaVigencia;
    }
}
