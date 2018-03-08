package dk.kinoxp.web.model.entities;

import dk.kinoxp.web.model.repositories.CinemaRepository;
import dk.kinoxp.web.model.repositories.SeatRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CinemaGetSetTest {
    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    SeatRepository seatRepository;

    @Test
    public void getSeats() throws Exception {
//        List<Seat> seats = cinemaRepository.findAllById(23);
//        assertEquals(240, cinemaRepository.findById(1));
    }


}