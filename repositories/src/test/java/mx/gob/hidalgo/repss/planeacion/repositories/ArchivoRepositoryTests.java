package mx.gob.hidalgo.repss.planeacion.repositories;

import mx.gob.hidalgo.repss.planeacion.config.PersistenceConfig;
import mx.gob.hidalgo.repss.planeacion.model.Archivo;
import mx.gob.hidalgo.repss.planeacion.model.Departamento;
import mx.gob.hidalgo.repss.planeacion.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * Created by kkimvazquezangeles on 25/01/15.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@PropertySource("classpath:application.properties")
public class ArchivoRepositoryTests {

    @Autowired
    ArchivoRepository archivoRepository;

    @Test
    public void testFindByNombreOficial() {
        Departamento departamento = new Departamento();
        departamento.setId(3L);
        Set<Archivo> archivos = archivoRepository.findAllByDepartamento(departamento);
        Assert.assertTrue(archivos.size() == 2);
    }

    @Test
    public void testFindByRutaOficial() {
        String ruta = "C:\\Users\\Zaira_Liz\\Documents\\Plataforma";
        Set<Archivo> archivos = archivoRepository.findAllByRuta(ruta);
        Assert.assertNotNull(archivos);
    }



}