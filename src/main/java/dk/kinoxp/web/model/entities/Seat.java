package dk.kinoxp.web.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private int id;

    @Column(name = "row_no")
    private int row;
    @Column(name = "column_no")
    private int column;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    public Seat (){

    }

    public Seat(int row, int column, Cinema cinema) {
        this.row = row;
        this.column = column;
        this.cinema = cinema;
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

    public void setColumn(int position) {
        this.column = position;
    }

    public Cinema getCinema() {
        return cinema;
    }
}
