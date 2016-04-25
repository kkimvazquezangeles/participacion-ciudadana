package mx.gob.hidalgo.repss.planeacion.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by kkimvazquezangeles on 11/05/15.
 */
//@Entity
public class Objetivos {
    @Id

    @ManyToOne
    @JoinColumn(name = "reporte_id")
    private Reporte reporte;


    private Long id;

    private String objetivoEst;
    private String metaEst;
    private String indicadorEst;

    @OneToMany(mappedBy = "Reporte", fetch = FetchType.EAGER)
   // private List<Tacticas> tacticas;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjetivoEst() {
        return objetivoEst;
    }

    public void setObjetivoEst(String objetivoEst) {
        this.objetivoEst = objetivoEst;
    }

    public String getMetaEst() {
        return metaEst;
    }

    public void setMetaEst(String metaEst) {
        this.metaEst = metaEst;
    }

    public String getIndicadorEst() {
        return indicadorEst;
    }

    public void setIndicadorEst(String indicadorEst) {
        this.indicadorEst = indicadorEst;
    }

/*
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
    */
}
