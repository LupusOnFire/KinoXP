package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.Booking;
import dk.kinoxp.web.model.entities.Seat;
import dk.kinoxp.web.model.entities.Showing;
import dk.kinoxp.web.model.enums.SeatStatus;

import java.util.*;

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
                    s.setSeatState(SeatStatus.PAID);
                } else {
                    s.setSeatState(SeatStatus.RESERVED);
                }
                bookedSeats.add(s);
            }
        }


        // Order booked seats
        bookedSeats.sort(Comparator.comparingInt(Seat::getId));


        int bookedSeatIndex = 0;

        // Loop through all the seats from the cinema
        for (int i = 0; i < cinemaSeats.size(); i++) {
            // If the current seat is the same as a booked seat, set the seat state
            if (bookedSeatIndex < bookedSeats.size()) {
                if (bookedSeats.get(bookedSeatIndex).getId() == cinemaSeats.get(i).getId()) {
                    cinemaSeats.get(i).setSeatState(bookedSeats.get(bookedSeatIndex).getSeatState());
                    bookedSeatIndex++;
                    // Else set seat state as empty
                } else {
                    cinemaSeats.get(i).setSeatState(SeatStatus.EMPTY);
                }
            } else {
                cinemaSeats.get(i).setSeatState(SeatStatus.EMPTY);
            }
        }
        return cinemaSeats;
    }

    public int bookedSeatsCount(List<Seat> cinemaSeats, List<Booking> bookings) {
        int count = 0;
        for (Booking b : bookings) {
            count += b.getSeats().size();
        }
        return count;
    }
}
