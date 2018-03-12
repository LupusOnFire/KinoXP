package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.Booking;
import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.entities.Seat;
import dk.kinoxp.web.model.entities.Showing;

import java.util.List;

public class BookingCreator {

    public Booking createBooking(){
        return new Booking();
    }
    public Booking createBooking(List<Seat> seats, Cinema cinema, Showing showing, String telephone, boolean paid){
        return new Booking(seats, cinema, showing, telephone, paid);
    }
    public Booking createBooking(String telephone){
        return new Booking(telephone);
    }

}
