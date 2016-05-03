package mx.gob.hidalgo.repss.planeacion.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Created by kkimvazquezangeles on 29/04/16.
 */
public class Foro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="foro_id_seq")
    @SequenceGenerator(name="foro_id_seq", sequenceName="foro_id_seq")
    private Long id;
    private String foro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForo() {
        return foro;
    }

    public void setForo(String foro) {
        this.foro = foro;
    }

    @Override
    public String toString() {
        return "Foro{" +
                "id=" + id +
                ", foro='" + foro + '\'' +
                '}';
    }
}
