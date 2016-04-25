package mx.gob.hidalgo.repss.planeacion.services;

/**
 * Created by kkimvazquezangeles on 15/01/16.
 */
public interface StorageImageService {
    public boolean writeImage(byte[] file, String logo, String contentType, FileOrigin fileOrigin);

    void deleteImage(String path, FileOrigin fileOrigin);
}
