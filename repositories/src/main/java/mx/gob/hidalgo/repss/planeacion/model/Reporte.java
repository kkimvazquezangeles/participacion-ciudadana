package mx.gob.hidalgo.repss.planeacion.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by kkimvazquezangeles on 11/05/15.
 */
//@Entity
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="reporte_id_seq")
    @SequenceGenerator(name="reporte_id_seq", sequenceName="reporte_id_seq")

    private Long id;
    private String problematicas;
    private String objetivo;
    private String meta;
    private String indicador;

    @OneToMany(mappedBy = "Reporte", fetch = FetchType.EAGER)
    private List<Objetivos> objetivos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProblematicas() {
        return problematicas;
    }

    public void setProblematicas(String problematicas) {
        this.problematicas = problematicas;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", problematicas='" + problematicas + '\'' +
                ", objetivo='" + objetivo + '\'' +
                ", meta='" + meta + '\'' +
                ", indicador='" + indicador + '\'' +

                '}';
    }

}
