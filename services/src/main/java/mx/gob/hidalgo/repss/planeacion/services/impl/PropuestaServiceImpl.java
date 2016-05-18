package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.model.Localidad;
import mx.gob.hidalgo.repss.planeacion.model.Persona;
import mx.gob.hidalgo.repss.planeacion.model.Propuesta;
import mx.gob.hidalgo.repss.planeacion.model.Tema;
import mx.gob.hidalgo.repss.planeacion.repositories.PersonaRepository;
import mx.gob.hidalgo.repss.planeacion.repositories.PropuestaRepository;
import mx.gob.hidalgo.repss.planeacion.services.PropuestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kkimvazquezangeles on 22/04/16.
 */
@Service
public class PropuestaServiceImpl implements PropuestaService{

    @Autowired
    PropuestaRepository propuestaRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public Map<String, Object> createPropuesta(Map<String, String> propuestaMap) {
        Propuesta propuesta = convertMapToPropuesta(propuestaMap);
        personaRepository.save(propuesta.getPersona());
        propuesta = propuestaRepository.save(propuesta);
        Map<String, Object> result = convertPropuestaToMap(propuesta);
        if (propuestaMap.containsKey(PROPERTY_PROPUESTA2) && !propuestaMap.get(PROPERTY_PROPUESTA2).trim().equals("")){
            Propuesta pros = new Propuesta();
            Tema tema = new Tema();
            tema.setId(Long.valueOf(propuestaMap.get(PROPERTY_ID_TEMA2)));
            pros.setPersona(propuesta.getPersona());
            pros.setPropuesta(propuestaMap.get(PROPERTY_PROPUESTA2));
            pros.setTema(tema);
            propuestaRepository.save(pros);
            result.put(PROPERTY_PROPUESTA2, propuestaMap.get(PROPERTY_PROPUESTA2));
        }
        if (propuestaMap.containsKey(PROPERTY_PROPUESTA3) && !propuestaMap.get(PROPERTY_PROPUESTA3).trim().equals("")){
            Propuesta pros = new Propuesta();
            Tema tema = new Tema();
            tema.setId(Long.valueOf(propuestaMap.get(PROPERTY_ID_TEMA3)));
            pros.setPersona(propuesta.getPersona());
            pros.setPropuesta(propuestaMap.get(PROPERTY_PROPUESTA3));
            pros.setTema(tema);
            propuestaRepository.save(pros);
            result.put(PROPERTY_PROPUESTA3, propuestaMap.get(PROPERTY_PROPUESTA3));
        }
        if (propuestaMap.containsKey(PROPERTY_PROPUESTA4) && !propuestaMap.get(PROPERTY_PROPUESTA4).trim().equals("")){
            Propuesta pros = new Propuesta();
            Tema tema = new Tema();
            tema.setId(Long.valueOf(propuestaMap.get(PROPERTY_ID_TEMA4)));
            pros.setPersona(propuesta.getPersona());
            pros.setPropuesta(propuestaMap.get(PROPERTY_PROPUESTA4));
            pros.setTema(tema);
            propuestaRepository.save(pros);
            result.put(PROPERTY_PROPUESTA4, propuestaMap.get(PROPERTY_PROPUESTA4));
        }
        return result;
    }

    @Override
    public Map<String, Object> listPropuestasByMunicipio(Long idMunicipio) {
        return null;
    }

    @Override
    public Map<String, Object> listPropuestaByLocalidades(Long idLocalidad) {
        return null;
    }

    @Override
    public List<Map> listTotalByMunicipio() {
        return propuestaRepository.findLimitLideresPuntosByTorneo();
    }


    private Map<String, Object> convertPropuestaToMap(Propuesta propuesta) {
        Map<String, Object> map = new HashMap<>();

        map.put(PROPERTY_PERSONA_ID, propuesta.getPersona().getId());
        map.put(PROPERTY_NOMBRE, propuesta.getPersona().getNombre());
        map.put(PROPERTY_PATERNO, propuesta.getPersona().getMaterno());
        map.put(PROPERTY_MATERNO, propuesta.getPersona().getMaterno());
        map.put(PROPERTY_ID_MUNICIPIO, propuesta.getPersona().getLocalidad().getMunicipio());
        map.put(PROPERTY_ID_LOCALIDAD, propuesta.getPersona().getLocalidad());
        map.put(PROPERTY_CORREO, propuesta.getPersona().getCorreoElectronico());
        map.put(PROPERTY_TELEFONO, propuesta.getPersona().getTelefono());
        map.put(PROPERTY_ID_TEMA, propuesta.getTema());

        map.put(PROPERTY_ID, propuesta.getId());
        map.put(PROPERTY_PROPUESTA1, propuesta.getPropuesta());
        /*map.put(PROPERTY_PROPUESTA2, propuesta.getPropuesta2());
        map.put(PROPERTY_PROPUESTA3, propuesta.getPropuesta3());
        map.put(PROPERTY_PROPUESTA4, propuesta.getPropuesta4());*/

        return map;
    }

    private Propuesta convertMapToPropuesta(Map<String, String> propuestaMap) {
        Propuesta propuesta = new Propuesta();
        Persona persona = new Persona();

        Localidad localidad = new Localidad();

        Tema tema = new Tema();

        if (propuestaMap.containsKey(PROPERTY_PERSONA_ID)) {
            persona.setId(Long.valueOf(propuestaMap.get(PROPERTY_PERSONA_ID)));
        }
        persona.setNombre(propuestaMap.get(PROPERTY_NOMBRE));
        persona.setPaterno(propuestaMap.get(PROPERTY_PATERNO));
        persona.setMaterno(propuestaMap.get(PROPERTY_MATERNO));
        persona.setTelefono(propuestaMap.get(PROPERTY_TELEFONO));
        persona.setCorreoElectronico(propuestaMap.get(PROPERTY_CORREO));

        localidad.setId(Long.valueOf(propuestaMap.get(PROPERTY_ID_LOCALIDAD)));
        persona.setLocalidad(localidad);

        propuesta.setPersona(persona);
        if (propuestaMap.containsKey(PROPERTY_ID)) {
            propuesta.setId(Long.valueOf(propuestaMap.get(PROPERTY_ID)));
        }

        if (propuestaMap.containsKey(PROPERTY_ID_TEMA) && !propuestaMap.get(PROPERTY_ID_TEMA).trim().isEmpty()) {
            tema.setId(Long.valueOf(propuestaMap.get(PROPERTY_ID_TEMA)));
            propuesta.setTema(tema);
        }

        propuesta.setPropuesta(propuestaMap.get(PROPERTY_PROPUESTA1));
        /*propuesta.setPropuesta2(propuestaMap.get(PROPERTY_PROPUESTA2));
        propuesta.setPropuesta3(propuestaMap.get(PROPERTY_PROPUESTA3));
        propuesta.setPropuesta4(propuestaMap.get(PROPERTY_PROPUESTA4));*/

        return propuesta;
    }

}
