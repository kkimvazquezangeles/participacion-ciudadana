package mx.gob.hidalgo.repss.planeacion.controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 26/04/16.
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    public DataSource dataSource;

    @RequestMapping(value = "/propuesta", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt1(
            HttpServletResponse response,
            @RequestParam(value = "idMunicipio") Long idMunicipio,
            @RequestParam(value = "idLocalidad") Long idLocalidad) throws JRException, IOException, SQLException {
        InputStream jasperStream = new ClassPathResource("propuesta.jasper").getInputStream();
        Map<String,Object> params = new HashMap<>();
        params.put("carreraid", idMunicipio);
        params.put("periodoid", idLocalidad);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=AsignacionDeTutores.pdf");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }
}
