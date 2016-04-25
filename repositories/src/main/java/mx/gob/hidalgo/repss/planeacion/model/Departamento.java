package mx.gob.hidalgo.repss.planeacion.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kkimvazquezangeles on 11/05/15.
 */
@Entity
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="departamento_id_seq")
    @SequenceGenerator(name="departamento_id_seq", sequenceName="departamento_id_seq")
    private Long id;
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
