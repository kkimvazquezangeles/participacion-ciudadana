package mx.gob.hidalgo.repss.planeacion.repositories;

import mx.gob.hidalgo.repss.planeacion.model.Archivo;
import mx.gob.hidalgo.repss.planeacion.model.Departamento;
import mx.gob.hidalgo.repss.planeacion.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by kkimvazquezangeles on 25/01/15.
 */
public interface ArchivoRepository extends CrudRepository<Archivo, Long> {
    Set<Archivo> findAllByDepartamento(Departamento depto);
    Set<Archivo> findAllByRuta(String ruta);
}

