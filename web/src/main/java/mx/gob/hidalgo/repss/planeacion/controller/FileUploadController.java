package mx.gob.hidalgo.repss.planeacion.controller;

import mx.gob.hidalgo.repss.planeacion.model.User;
import mx.gob.hidalgo.repss.planeacion.services.*;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kkimvazquezangeles on 30/10/15.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    StorageImageService storageImageServices;

    @Autowired
    ArchivoService archivoService;

    @RequestMapping(value = "/upload/generic", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addFileGeneric(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam("idDepto") Long idDepto, @RequestParam("username") String username) throws IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {

            if (!(request instanceof StandardMultipartHttpServletRequest)){
                throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
            }
        }

        StandardMultipartHttpServletRequest dmhsRequest = (StandardMultipartHttpServletRequest) request ;
        MultipartFile file = (MultipartFile) dmhsRequest.getFile("fileupload");

        Map<String, Object> result = new HashMap<>();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                FileOrigin fileOrigin = FileOrigin.getEnum(idDepto);

                storageImageServices.writeImage(bytes, file.getOriginalFilename(), file.getContentType(), fileOrigin);
                User user = new User();
                user.setUsername(username);
                Map<String, String> archivoMap = new HashMap<>();
                archivoMap.put(ArchivoService.PROPERTY_NOMBRE, file.getOriginalFilename());
                archivoMap.put(ArchivoService.PROPERTY_DEPTO_ID, String.valueOf(idDepto));
                result = archivoService.createArchivo(archivoMap, user);

                result.put("result", "success");
            } catch (Exception e) {
                result.put("result", "fail");
            }
        } else {
            result.put("result", "empty");
        }

        return result;
    }

    @RequestMapping(value = "/delete/foto", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> removeFileFoto(@RequestParam("key") Long idJugador,
                                              @RequestParam("logo") String foto, @RequestParam("origin") String origin)
            throws IOException {
        Map<String, String> result = new HashMap<>();

        FileOrigin fileOrigin = FileOrigin.valueOf(origin);
        storageImageServices.deleteImage(foto, fileOrigin);

        result.put("result", "success");
        result.put("defaultname", PathPhoto.JUGADOR_DEFAULT.getPath());
        return result;
    }

    private String getValidNameLogo(String path, Long idEquipo) {
        String extension=path.substring(path.lastIndexOf("."));
        return idEquipo + extension;
    }
}
