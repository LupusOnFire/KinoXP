package dk.kinoxp.web.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cinema")
public class Cinema {

    @Column(name = "cinema_id")
    private int cinemaId;

    @Column(name = "height")
    private double height;

    @Column (name = "width")
    private double width;


    public Cinema() {
    }

    public Cinema(int cinemaId, double height, double width) {
        this.cinemaId = cinemaId;
        this.height = height;
        this.width = width;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
