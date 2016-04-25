package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.services.FileOrigin;
import mx.gob.hidalgo.repss.planeacion.services.PathPhoto;
import mx.gob.hidalgo.repss.planeacion.services.PathWebService;
import mx.gob.hidalgo.repss.planeacion.services.StorageImageService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;

/**
 * Created by kkimvazquezangeles on 15/01/16.
 */
@Service
@Profile({"localbmvstorage", "localhomestorage", "test"})
public class StorageLocalImpl implements StorageImageService {

    @Resource
    Environment env;

    @Override
    public boolean writeImage(byte[] file, String path, String contetType, FileOrigin fileOrigin) {
        return writeFile(file, getValidPathAbsolute(fileOrigin.getPath()) + path);
    }

    @Override
    public void deleteImage(String logo, FileOrigin fileOrigin) {
        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO)
                + fileOrigin.getPath() + logo;
        File dir = new File(pathFull);
        if (dir.exists())
            dir.delete();
    }

    private boolean writeFile(byte[] file, String path)  {
        if (file == null || path == null || path.isEmpty()) {
            return false;
        }

        File serverFile = new File(path);
        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            stream.write(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private String getValidPathAbsolute(String base) {
        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO)
                + base;
        File dir = new File(pathFull);
        if (!dir.exists())
            dir.mkdirs();

        return dir.getAbsolutePath()
                + File.separator;
    }
}
