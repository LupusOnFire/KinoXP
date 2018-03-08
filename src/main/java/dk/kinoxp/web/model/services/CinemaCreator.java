package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.entities.Seat;
import dk.kinoxp.web.model.repositories.CinemaRepository;
import dk.kinoxp.web.model.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

public class CinemaCreator {
    private CinemaRepository _cinemaRepository;
    private SeatRepository _seatRepository;

    public CinemaCreator(SeatRepository seatRepository, CinemaRepository cinemaRepository) {
        _seatRepository = seatRepository;
        _cinemaRepository = cinemaRepository;
    }


    public void createCinemaAndSeats(int cinemaId, int rows, int columnCount) {
        List<Seat> seats = new LinkedList<>();
        Cinema cinema = _cinemaRepository.findById(cinemaId);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columnCount; j++) {
                Seat seat = new Seat(i+1, j+1, cinema);
                seats.add(seat);
            }
        }
        _cinemaRepository.save(cinema);
        _seatRepository.saveAll(seats);
    }
}
