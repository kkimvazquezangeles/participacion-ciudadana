package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.repositories.PersonaRepository;
import mx.gob.hidalgo.repss.planeacion.services.PersonaService;
import mx.gob.hidalgo.repss.planeacion.services.PathWebService;
import mx.gob.hidalgo.repss.planeacion.model.Persona;
import mx.gob.hidalgo.repss.planeacion.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by kkimvazquezangeles on 22/05/15.
 */
@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    PathWebService pathWebService;

    @Override
    public Map<String, Object> createJugador(Map<String, String> jugadorMap, User admin) {
        Persona persona = convertMapToJugador(jugadorMap);
        persona.setAdmin(admin);
        return convertJugadorToMap(personaRepository.save(persona));
    }

    @Override
    public DeleteStatusEnum deleteJugador(Long idJugador) {
        try {
            personaRepository.delete(idJugador);
        } catch (DataIntegrityViolationException exception){
            return DeleteStatusEnum.VIOLATION;
        }
        return DeleteStatusEnum.OK;
    }

    @Override
    public Map<String, Object> listJugadorByAdmin(User user) {
        return convertJugadorToMap(personaRepository.findByAdmin(user));
    }

    private Map<String, Object> convertJugadorToMap(Persona persona) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, persona.getId());
        map.put(PROPERTY_NOMBRE, persona.getNombre());
        map.put(PROPERTY_PATERNO, persona.getPaterno());
        map.put(PROPERTY_MATERNO, persona.getMaterno());
        map.put(PROPERTY_DEPTO_ID, persona.getDepartamento().getId());
        map.put(PROPERTY_DEPTO_DES, persona.getDepartamento().getNombre());
        map.put(PROPERTY_FECHA_REGISTRO, persona.getFechaRegistro());

        return map;
    }

    private Persona convertMapToJugador(Map<String, String> ligaMap) {
        Persona persona = new Persona();
        if (ligaMap.containsKey(PROPERTY_ID)) {
            persona = this.get(Long.valueOf(ligaMap.get(PROPERTY_ID)));
        }
        persona.setNombre(ligaMap.get(PROPERTY_NOMBRE));
        persona.setPaterno(ligaMap.get(PROPERTY_PATERNO));
        persona.setMaterno(ligaMap.get(PROPERTY_MATERNO));
        persona.setFechaRegistro(new Date(Long.valueOf(ligaMap.get(PROPERTY_FECHA_REGISTRO))));

        return persona;
    }

    private Persona get(Long idJugador){
        return this.personaRepository.findOne(idJugador);
    }
}
