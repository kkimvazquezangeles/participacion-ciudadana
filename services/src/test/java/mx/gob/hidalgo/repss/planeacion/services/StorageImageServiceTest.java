package mx.gob.hidalgo.repss.planeacion.services;

import mx.gob.hidalgo.repss.planeacion.config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * Created by kkimvazquezangeles on 21/01/16.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class StorageImageServiceTest {

    @Autowired
    StorageImageService storageImageService;

    @Test
    public void testWriteFileNull() {
        String serveerPath = "./src/test/resources/img/prueba.png";
        boolean result;
        result = storageImageService.writeImage(null, serveerPath, "", FileOrigin.DIR_ADMIN);
        Assert.assertTrue(result == false);
    }

    @Test
    public void testWriteDeleteFilePathLogoValid() {
        String name = "prueba.png";
        String serveerPath ="./src/test/resources/img/photo/equipo/";
        byte[] contenido = "Hallo World".getBytes();
        boolean result = true;
        result = storageImageService.writeImage(contenido, name, "", FileOrigin.DIR_AF_OP);
        storageImageService.deleteImage(name, FileOrigin.DIR_AF_OP);
        File dir = new File(serveerPath + name);
        if (dir.exists()) {
            Assert.assertTrue(false);
        } else  {
            Assert.assertTrue(result == true);
        }
    }

    @Test
    public void testWriteDeleteFilePathFotoValid() {
        String name = "prueba.png";
        String serveerPath ="./src/test/resources/img/photo/jugador/";
        byte[] contenido = "Hallo World".getBytes();
        boolean result = true;
        result = storageImageService.writeImage(contenido, name, "", FileOrigin.DIR_ADMIN);
        storageImageService.deleteImage(name, FileOrigin.DIR_ADMIN);
        File dir = new File(serveerPath + name);
        if (dir.exists()) {
            Assert.assertTrue(false);
        } else  {
            Assert.assertTrue(result == true);
        }
    }
}
