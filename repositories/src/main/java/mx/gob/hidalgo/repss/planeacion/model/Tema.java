package mx.gob.hidalgo.repss.planeacion.model;

import javax.persistence.*;

/**
 * Created by kkimvazquezangeles on 29/04/16.
 */
@Entity
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="tema_id_seq")
    @SequenceGenerator(name="tema_id_seq", sequenceName="tema_id_seq")
    private Long id;
    private String tema;
    @ManyToOne
    @JoinColumn(name = "foro_id", nullable = false)
    private Foro foro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Foro getForo() {
        return foro;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }

    @Override
    public String toString() {
        return "Tema{" +
                "id=" + id +
                ", tema='" + tema + '\'' +
                ", foro=" + foro +
                '}';
    }
}
