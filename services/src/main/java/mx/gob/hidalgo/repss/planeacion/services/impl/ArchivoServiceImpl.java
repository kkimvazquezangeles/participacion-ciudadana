package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.model.Archivo;
import mx.gob.hidalgo.repss.planeacion.model.Departamento;
import mx.gob.hidalgo.repss.planeacion.model.Persona;
import mx.gob.hidalgo.repss.planeacion.model.User;
import mx.gob.hidalgo.repss.planeacion.repositories.ArchivoRepository;
import mx.gob.hidalgo.repss.planeacion.repositories.PersonaRepository;
import mx.gob.hidalgo.repss.planeacion.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by kkimvazquezangeles on 22/05/15.
 */
@Service
public class ArchivoServiceImpl implements ArchivoService {

    @Autowired
    ArchivoRepository archivoRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    StorageImageService storageImageServices;

    @Autowired
    PathWebService pathWeb;

    @Override
    public Map<String, Object> createArchivo(Map<String, String> archivoMap, User admin) {
        Archivo archivo = convertMapToArchivo(archivoMap);
        return convertArchivoToMap(archivoRepository.save(archivo));
    }

    @Override
    public void deleteArchivo(Long idArchivo) {
        Archivo archivo = archivoRepository.findOne(idArchivo);
        storageImageServices.deleteImage(archivo.getNombre(), FileOrigin.getEnum(archivo.getDepartamento().getId()));
        archivoRepository.delete(idArchivo);
    }

    @Override
    public List<Map<String, Object>> listArchivoByDepartamento(Departamento depto) {
        Iterator<Archivo> itArchivo = archivoRepository.findAllByDepartamento(depto).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itArchivo.hasNext()) {
            Archivo archivo = itArchivo.next();
            Map<String, Object> dto = convertArchivoToMap(archivo);
            copy.add(dto);
        }
        return copy;
    }

    private Map<String, Object> convertArchivoToMap(Archivo archivo) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_ID, archivo.getId());
        map.put(PROPERTY_NOMBRE, archivo.getNombre());
        map.put(PROPERTY_RUTA_NOMBRE, pathWeb.getValidPathWebFoto(archivo.getNombre(), FileOrigin.getEnum(archivo.getDepartamento().getId())));
        map.put(PROPERTY_DEPTO_ID, archivo.getDepartamento().getId());
        map.put(PROPERTY_DEPTO_DES, archivo.getDepartamento().getNombre());
        map.put(PROPERTY_USER_ID, archivo.getPersona().getId());
        map.put(PROPERTY_USER_DES, archivo.getPersona().getNombreCompleto());
        map.put(PROPERTY_FECHA_REGISTRO, archivo.getFechaRegistro());

        return map;
    }

    private Archivo convertMapToArchivo(Map<String, String> archivoMap) {
        Archivo archivo = new Archivo();
        if (archivoMap.containsKey(PROPERTY_ID)) {
            archivo = this.get(Long.valueOf(archivoMap.get(PROPERTY_ID)));
        }
        archivo.setNombre(archivoMap.get(PROPERTY_NOMBRE));
        Departamento depto = new Departamento();
        depto.setId(Long.valueOf(archivoMap.get(PROPERTY_DEPTO_ID)));
        archivo.setDepartamento(depto);
        archivo.setFechaRegistro(new Date());

        return archivo;
    }

    private Archivo get(Long idArchivo){
        return this.archivoRepository.findOne(idArchivo);
    }
}
