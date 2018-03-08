package dk.kinoxp.web.model.services.dto;

import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.entities.Movie;

import javax.persistence.*;
import java.util.Date;

public class ShowingDto {
    private int id;
    private Date time;
    private int movieId;
    private int cinemaId;

    public ShowingDto(int id, Date time, int movieId, int cinemaId) {
        this.id = id;
        this.time = time;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
    }
    public ShowingDto(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }
}
