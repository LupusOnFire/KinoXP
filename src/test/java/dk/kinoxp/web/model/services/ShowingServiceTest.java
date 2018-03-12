package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.enums.SeatStatus;
import dk.kinoxp.web.model.entities.*;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class ShowingServiceTest {
    Cinema cinema;
    List<Booking> bookings;

    @Before
    public void setUp() {
        // Create dummydata for test
        cinema = new Cinema();
        cinema.setId(1);
        cinema.setRows(10);
        cinema.setColumns(10);

        CinemaCreator cinemaCreator = new CinemaCreator();
        List<Seat> seats = cinemaCreator.createSeats(cinema, cinema.getRows(), cinema.getColumns());
        for (int i = 0; i < seats.size(); i++) {
            seats.get(i).setId(i);
        }
        cinema.setSeats(seats);

        Seat seat = new Seat();
        seat.setId(1);
        seat.setColumn(1);
        seat.setRow(1);

        Seat seat2 = new Seat();
        seat2.setId(2);
        seat2.setColumn(2);
        seat2.setRow(1);

        List<Seat> bookedSeats = new LinkedList<>();
        bookedSeats.add(seat);
        bookedSeats.add(seat2);

        Booking booking = new Booking(bookedSeats, cinema, new Showing(), "88888888", true);
        bookings = new LinkedList<>();
        bookings.add(booking);
    }

    @Test
    public void getSeatStatus() {
        ShowingService bookingService = new ShowingService();
        List<Seat> availableSeats = bookingService.setSeatState(cinema.getSeats(), bookings);

        int paidCount = 0;
        for (Seat s : availableSeats) {
            if (s.getSeatState() == SeatStatus.PAID) {
                paidCount++;
            }
        }

        assertEquals(2, paidCount);
    }
}