package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.model.Localidad;
import mx.gob.hidalgo.repss.planeacion.model.Municipio;
import mx.gob.hidalgo.repss.planeacion.repositories.LocalidadRepository;
import mx.gob.hidalgo.repss.planeacion.repositories.MunicipioRepository;
import mx.gob.hidalgo.repss.planeacion.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by kkimvazquezangeles on 22/04/16.
 */
@Service
public class MunicipioServiceImpl implements MunicipioService{

    @Autowired
    MunicipioRepository municipioRepository;

    @Autowired
    LocalidadRepository localidadRepository;

    @Override
    public List<Map<String, Object>> listMunicipios() {
        Iterator<Municipio> itMunicipio = municipioRepository.findAll().iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itMunicipio.hasNext()) {
            Municipio municipio = itMunicipio.next();
            Map<String, Object> dto = convertMunicipioToMap(municipio);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public List<Map<String, Object>> listMunicipiosPropuesta() {

        return null;
    }

    @Override
    public List<Map<String, Object>> listLocalidadesByMunicipio(Long idMunicipio) {
        Municipio municipio = new Municipio();
        municipio.setId(idMunicipio);
        Iterator<Localidad> itLocalidad = localidadRepository.findAllByMunicipio(municipio).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while(itLocalidad.hasNext()) {
            Localidad localidad = itLocalidad.next();
            Map<String, Object> dto = convertLocalidadToMap(localidad);
            copy.add(dto);
        }

        return copy;
    }

    private Map<String, Object> convertMunicipioToMap(Municipio municipio) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_MUNICIPIO_ID, municipio.getId());
        map.put(PROPERTY_MUNICIPIO, municipio.getMunicipio());
        return map;
    }

    private Map<String, Object> convertLocalidadToMap(Localidad localidad) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_LOCALIDAD_ID, localidad.getId());
        map.put(PROPERTY_LOCALIDAD, localidad.getLocalidad());
        return map;
    }

}
