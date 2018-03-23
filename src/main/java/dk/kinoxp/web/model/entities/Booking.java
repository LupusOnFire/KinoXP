package dk.kinoxp.web.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "booking_seat", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "seat_id"))
    private List<Seat> seats;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "showing_id")
    private Showing showing;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "paid")
    private boolean paid;

    public Booking(List<Seat> seats, Showing showing, String telephone, boolean paid) {
        this.seats = seats;
        this.showing = showing;
        this.telephone = telephone;
        this.paid = paid;
    }

    public Booking(){

    }
    public Booking(String telephone){
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Showing getShowing() {
        return showing;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
