package mx.gob.hidalgo.repss.planeacion.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kkimvazquezangeles on 11/05/15.
 */
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="persona_id_seq")
    @SequenceGenerator(name="persona_id_seq", sequenceName="persona_id_seq")
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User admin;
    @ManyToOne
    @JoinColumn(name = "depto_id", nullable = false)
    private Departamento departamento;
    private String nombre;
    private String paterno;
    private String materno;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombreCompleto() {
        return this.nombre + " " + this.paterno + " " + this.materno;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", admin=" + admin +
                ", departamento=" + departamento +
                ", nombre='" + nombre + '\'' +
                ", paterno='" + paterno + '\'' +
                ", materno='" + materno + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
