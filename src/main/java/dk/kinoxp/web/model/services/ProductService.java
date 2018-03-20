package dk.kinoxp.web.model.services;

import dk.kinoxp.web.config.Constants;
import dk.kinoxp.web.model.entities.Product;

import java.io.*;

public class ProductService {
    public ProductService() {
    }

    public void initializeImage(Product product) {
        File folder = new File(Constants.PRODUCT_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String path = Constants.PRODUCT_FOLDER + product.getId() + ".jpg";
        System.out.println(product.getImagePath());
        System.out.println(path);
        File file = new File(path);
        if (!file.exists()) {
            try {
                DownloadService downloadService = new DownloadService();
                downloadService.downloadFile(product.getImagePath(), file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
