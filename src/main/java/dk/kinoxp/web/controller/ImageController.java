package dk.kinoxp.web.controller;

import dk.kinoxp.web.config.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Controller
public class ImageController {
    @RequestMapping(value = "/files/images/posters/{imageId}.jpg")
    @ResponseBody
    public byte[] getPoster(@PathVariable String imageId) throws IOException {
        String path = Constants.POSTER_FOLDER + imageId + ".jpg";
        BufferedImage image = ImageIO.read(new File(path));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", bos);
        return bos.toByteArray();
    }

    @RequestMapping(value = "/files/images/products/{id}.jpg")
    @ResponseBody
    public byte[] getImage(@PathVariable String id) throws IOException {
        String path = Constants.PRODUCT_FOLDER + id + ".jpg";
        BufferedImage image = ImageIO.read(new File(path));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", bos);
        return bos.toByteArray();
    }
}


