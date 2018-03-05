package dk.kinoxp.web.model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "showing")
public class Showing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "time")
    private Date time;
    @Column(name = "movie_id")
    private Movie movie;
    @Column(name = "cinemaNo")
    private Cinema cinema;

    public Showing(Date time, Movie movie, Cinema cinema) {
        this.time = time;
        this.movie = movie;
        this.cinema = cinema;
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}
