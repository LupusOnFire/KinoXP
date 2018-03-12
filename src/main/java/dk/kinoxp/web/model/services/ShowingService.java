package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.Booking;
import dk.kinoxp.web.model.entities.Seat;
import dk.kinoxp.web.model.entities.Showing;
import dk.kinoxp.web.model.enums.SeatStatus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShowingService {
    public ShowingService() {
    }
    public List<Seat> setSeatState(List<Seat> cinemaSeats, List<Booking> bookings) {
        // Create a list for the booked seats
        List<Seat> bookedSeats = new ArrayList<>();

        // Loop through all the bookings and set the booking state
        for (Booking b : bookings) {
            for (Seat s : b.getSeats()) {
                if (b.isPaid()) {
                    s.seatState = SeatStatus.PAID;
                } else {
                    s.seatState = SeatStatus.RESERVED;
                }
                bookedSeats.add(s);
            }
        }

        int bookedSeatIndex = 0;

        // Loop through all the seats from the cinema
        for (int i = 0; i < cinemaSeats.size(); i++) {
            // If the current seat is the same as a booked seat, overwrite the seat
            if (bookedSeatIndex < bookedSeats.size()) {
                if (bookedSeats.get(bookedSeatIndex).getId() == cinemaSeats.get(i).getId()) {
                    cinemaSeats.set(i, bookedSeats.get(bookedSeatIndex));
                    bookedSeatIndex++;
                } // Else set the seat state to empty
            } else {
                cinemaSeats.get(i).seatState = SeatStatus.EMPTY;
            }
        }
        return cinemaSeats;
    }
}
