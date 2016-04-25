package mx.gob.hidalgo.repss.planeacion.model;

import org.springframework.format.annotation.NumberFormat;

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
    private String nombre;
    private String paterno;
    private String materno;
    @Column(name = "correo_electronico")
    private String correoElectronico;
    private String telefono;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;
    @ManyToOne
    @JoinColumn(name = "localidad_id", nullable = false)
    private Localidad localidad;

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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getNombreCompleto() {
        return this.nombre + " " + this.paterno + " " + this.materno;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", paterno='" + paterno + '\'' +
                ", materno='" + materno + '\'' +
                ", correoElectronico=" + correoElectronico + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaRegistro=" + fechaRegistro + '\'' +
                ", localidad='" + localidad +
                '}';
    }

}
