package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.entities.Seat;
import dk.kinoxp.web.model.repositories.CinemaRepository;
import dk.kinoxp.web.model.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class CinemaCreator {
    public CinemaCreator() {
    }


    public List<Seat> createSeats(Cinema cinema, int rows, int columnCount) {
        List<Seat> seats = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columnCount; j++) {
                Seat seat = new Seat(i+1, j+1, cinema);
                seats.add(seat);
            }
        }
        return seats;
    }
}
