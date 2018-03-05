package dk.kinoxp.web.model.entities;

import javax.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private int id;

    @Column(name = "row")
    private int row;
    @Column(name = "column")
    private int column;
    @Column(name = "isReserved")
    private boolean isReserved;
    @Column(name = "reservedBy")
    private String reservedBy;
    @Column(name = "cinema_id")
    private int cinemaId;

    public Seat(int row, int column, boolean isReserved, String reservedBy, int cinemaId) {
        this.row = row;
        this.column = column;
        this.isReserved = isReserved;
        this.reservedBy = reservedBy;
        this.cinemaId = cinemaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }
}
