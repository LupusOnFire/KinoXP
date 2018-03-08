package dk.kinoxp.web.model.entities;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "cinema")
public class Cinema {


    @Id
    @Column(name = "cinema_id", nullable = false)
    private int id;

    @Column(name = "height")
    private double height;

    @Column (name = "width")
    private double width;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "seat", joinColumns = @JoinColumn(name = "cinema_id"), inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private List<Seat> seats;


    public Cinema() {
    }

    public Cinema(int id, double height, double width) {
        this.id = id;
        this.height = height;
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
