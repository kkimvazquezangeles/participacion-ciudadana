package mx.gob.hidalgo.repss.planeacion.model;

import javax.persistence.*;

/**
 * Created by kkimvazquezangeles on 21/04/16.
 */
@Entity
public class Propuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="propuesta_id_seq")
    @SequenceGenerator(name="propuesta_id_seq", sequenceName="propuesta_id_seq")
    private Long id;
    private String propuesta1;
    private String propuesta2;
    private String propuesta3;
    private String propuesta4;
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropuesta1() {
        return propuesta1;
    }

    public void setPropuesta1(String propuesta1) {
        this.propuesta1 = propuesta1;
    }

    public String getPropuesta2() {
        return propuesta2;
    }

    public void setPropuesta2(String propuesta2) {
        this.propuesta2 = propuesta2;
    }

    public String getPropuesta3() {
        return propuesta3;
    }

    public void setPropuesta3(String propuesta3) {
        this.propuesta3 = propuesta3;
    }

    public String getPropuesta4() {
        return propuesta4;
    }

    public void setPropuesta4(String propuesta4) {
        this.propuesta4 = propuesta4;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Propuesta{" +
                "id=" + id +
                ", propuesta1='" + propuesta1 + '\'' +
                ", propuesta2='" + propuesta2 + '\'' +
                ", propuesta3='" + propuesta3 + '\'' +
                ", propuesta4=" + propuesta4 + '\'' +
                ", persona='" + persona +
                '}';
    }


}
