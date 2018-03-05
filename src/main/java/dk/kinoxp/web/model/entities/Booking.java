package dk.kinoxp.web.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "seat_id")
    private Seat seat;
    @Column(name = "cinema_id")
    private Cinema cinema;
    @Column(name = "showing")
    private Showing showing;
    @Column(name = "telephone")
    private String telephone;

    public Booking(int id, Seat seat, Cinema cinema, Showing showing, String telephone) {
        this.id = id;
        this.seat = seat;
        this.cinema = cinema;
        this.showing = showing;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
