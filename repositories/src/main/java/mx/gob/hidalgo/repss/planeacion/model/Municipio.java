package mx.gob.hidalgo.repss.planeacion.model;

import javax.persistence.*;

/**
 * Created by kkimvazquezangeles on 21/04/16.
 */
@Entity
public class Municipio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="municipio_id_seq")
    @SequenceGenerator(name="municipio_id_seq", sequenceName="municipio_id_seq")
    private Long id;
    private String municipio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "id=" + id +
                ", municipio='" + municipio +
                '}';
    }

}
