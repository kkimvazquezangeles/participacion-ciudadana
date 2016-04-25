package mx.gob.hidalgo.repss.planeacion.services.impl;

import mx.gob.hidalgo.repss.planeacion.services.google.GoogleCloudStorage;
import mx.gob.hidalgo.repss.planeacion.services.FileOrigin;
import mx.gob.hidalgo.repss.planeacion.services.PathPhoto;
import mx.gob.hidalgo.repss.planeacion.services.PathWebService;
import mx.gob.hidalgo.repss.planeacion.services.StorageImageService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Created by kkimvazquezangeles on 15/01/16.
 */
@Service
@Profile("googlestorage")
public class StorageGoogleCloudImpl implements StorageImageService {

    static final String PROPERTY_STATIC_GOOGLE_BUCKET_NAME = "lupa.service.google.bucketName";
    static final String PROPERTY_STATIC_GOOGLE_IMAGE_CONTENT_TYPE = "image/*";

    @Resource
    Environment env;

    @Override
    public boolean writeImage(byte[] file, String logo, String contentType, FileOrigin fileOrigin) {
        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO) +
                fileOrigin.getPath() + logo;
        return writeFile(file, pathFull, contentType);
    }

    @Override
    public void deleteImage(String logo, FileOrigin fileOrigin) {
        String pathFull = env.getRequiredProperty(PathWebService.PROPERTY_STATIC_FILE_PHOTO) +
                fileOrigin.getPath() + logo;
        deleteFile(pathFull);
    }

    private void deleteFile(String path){
        try {
            GoogleCloudStorage.deleteObject(path, env.getRequiredProperty(PROPERTY_STATIC_GOOGLE_BUCKET_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    private boolean writeFile(byte[] file, String path, String contentType)  {
        try {
            GoogleCloudStorage.uploadStream(
                    path, contentType,
                    new ByteArrayInputStream(file),
                    env.getRequiredProperty(PROPERTY_STATIC_GOOGLE_BUCKET_NAME));
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
