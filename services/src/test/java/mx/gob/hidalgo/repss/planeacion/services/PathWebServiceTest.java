package mx.gob.hidalgo.repss.planeacion.services;

import mx.gob.hidalgo.repss.planeacion.config.TestConfig;
import mx.gob.hidalgo.repss.planeacion.model.OrigenEstadistica;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kkimvazquezangeles on 28/10/15.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class PathWebServiceTest {

    @Autowired
    PathWebService pathWeb;


    @Test
    public void testNullPathArbitro() {
        String path = pathWeb.getValidPathWebFoto(null, FileOrigin.DIR_ADMIN);
        Assert.assertTrue("".equals(path));
    }

}
