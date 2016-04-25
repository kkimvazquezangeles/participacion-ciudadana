package mx.gob.hidalgo.repss.planeacion.model;

import javax.persistence.*;

/**
 * Created by kkimvazquezangeles on 21/04/16.
 */
@Entity
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="localidad_id_seq")
    @SequenceGenerator(name="localidad_id_seq", sequenceName="localidad_id_seq")
    private Long id;
    private String localidad;
    @ManyToOne
    @JoinColumn(name = "municipio_id", nullable = false)
    private Municipio municipio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    @Override
    public String toString() {
        return "Localidad{" +
                "id=" + id +
                ", localidad='" + localidad + '\'' +
                ", municipio='" + municipio +
                '}';
    }


}
