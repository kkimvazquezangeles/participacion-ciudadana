package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.model.OrigenEstadistica;
import mx.gob.hidalgo.repss.planeacion.services.FileOrigin;
import mx.gob.hidalgo.repss.planeacion.services.PathPhoto;
import mx.gob.hidalgo.repss.planeacion.services.PathWebService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by kkimvazquezangeles on 16/01/16.
 */
@Service
@Profile({"localbmvstorage", "localhomestorage", "test"})
public class PathWebLocalImpl implements PathWebService {

    @Resource
    Environment env;

    @Override
    public String getValidPathWebFoto(String path, FileOrigin fileOrigin) {
        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO);
        pathFull = pathFull + fileOrigin.getPath() + path;
        File file = new File(pathFull);
        if (file == null || !file.exists()) {
            return "";
        }
        return PathPhoto.PHOTO_BASE.getPath() + fileOrigin.getPath() + path;
    }
}
