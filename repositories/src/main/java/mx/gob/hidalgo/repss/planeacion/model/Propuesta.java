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
    private String propuesta;
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;
    @ManyToOne
    @JoinColumn(name = "tema_id", nullable = true)
    private Tema tema;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(String propuesta) {
        this.propuesta = propuesta;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    @Override
    public String toString() {
        return "Propuesta{" +
                "id=" + id +
                ", propuesta='" + propuesta + '\'' +
                ", tema=" + tema + '\'' +
                ", persona='" + persona +
                '}';
    }


}
