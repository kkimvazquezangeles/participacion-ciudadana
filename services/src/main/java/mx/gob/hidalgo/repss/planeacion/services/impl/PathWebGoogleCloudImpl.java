package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.model.OrigenEstadistica;
import mx.gob.hidalgo.repss.planeacion.services.FileOrigin;
import mx.gob.hidalgo.repss.planeacion.services.PathPhoto;
import mx.gob.hidalgo.repss.planeacion.services.PathWebService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kkimvazquezangeles on 16/01/16.
 */
@Service
@Profile("googlestorage")
public class PathWebGoogleCloudImpl implements PathWebService {

    static final String PROPERTY_STATIC_GOOGLE_PATH_WEB = "lupa.service.google.pathWeb";

    @Resource
    Environment env;

    @Override
    public String getValidPathWebFoto(String path, FileOrigin fileOrigin) {
        String pathFull = env.getRequiredProperty(PROPERTY_STATIC_GOOGLE_PATH_WEB);
        pathFull = pathFull + fileOrigin.getPath() + path;
        return pathFull;
    }
}
