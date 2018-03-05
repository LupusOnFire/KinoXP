package dk.kinoxp.web.model.entities;


import javax.persistence.*;

@Entity
@Table(name = "cinema")
public class Cinema {

    @Id
    @Column(name = "cinema_id")
    private int cinema_Id;

    @Column(name = "height")
    private double height;

    @Column (name = "width")
    private double width;


    public Cinema() {
    }

    public Cinema(int cinemaId, double height, double width) {
        this.cinema_Id = cinemaId;
        this.height = height;
        this.width = width;
    }

    public int getId() {
        return cinema_Id;
    }

    public void setId(int cinemaId) {
        this.cinema_Id = cinemaId;
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
