package dk.kinoxp.web.model.services;

import dk.kinoxp.web.config.Constants;
import dk.kinoxp.web.model.entities.Movie;

import java.io.*;
import java.net.URL;

public class PosterService {
    public PosterService() {
    }

    public void initializePoster(Movie movie) {
        File folder = new File(Constants.POSTER_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String path = Constants.POSTER_FOLDER + movie.getImdbId() + ".jpg";
        File file = new File(path);
        if (!file.exists()) {
            try {
                DownloadService dlService = new DownloadService();
                dlService.downloadFile(movie.getPosterPath(), file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
