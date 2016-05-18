package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.model.Foro;
import mx.gob.hidalgo.repss.planeacion.model.Tema;
import mx.gob.hidalgo.repss.planeacion.repositories.ForoRepository;
import mx.gob.hidalgo.repss.planeacion.repositories.TemaRepository;
import mx.gob.hidalgo.repss.planeacion.services.ForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 17/05/16.
 */
@Service
public class ForoServiceImpl implements ForoService {

    @Autowired
    ForoRepository foroRepository;

    @Autowired
    TemaRepository temaRepository;

    @Override
    public List<Map<String, Object>> listForos() {
        Iterator<Foro> itForo = foroRepository.findAll().iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itForo.hasNext()) {
            Foro foro = itForo.next();
            Map<String, Object> dto = convertForoToMap(foro);
            copy.add(dto);
        }
        return copy;
    }

    @Override
    public List<Map<String, Object>> listTemasByForo(Long idForo) {
        Foro foro = new Foro();
        foro.setId(idForo);
        Iterator<Tema> itTema = temaRepository.findAllByForo(foro).iterator();
        List<Map<String, Object>> copy = new ArrayList<>();
        while (itTema.hasNext()) {
            Tema tema = itTema.next();
            Map<String, Object> dto = convertTemaToMap(tema);
            copy.add(dto);
        }
        return copy;
    }

    private Map<String, Object> convertForoToMap(Foro foro) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_FORO_ID, foro.getId());
        map.put(PROPERTY_FORO, foro.getForo());
        return map;
    }

    private Map<String, Object> convertTemaToMap(Tema tema) {
        Map<String, Object> map = new HashMap<>();
        map.put(PROPERTY_TEMA_ID, tema.getId());
        map.put(PROPERTY_TEMA, tema.getTema());
        return map;
    }
}
