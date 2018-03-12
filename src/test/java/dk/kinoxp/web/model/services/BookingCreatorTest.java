package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.Booking;
import dk.kinoxp.web.model.entities.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookingCreatorTest {

    @Test
    public void createBooking() {
        Booking booking = new Booking("88888888");
        System.out.println(booking.getClass());

        BookingCreator bookingCreator = new BookingCreator();


        assertEquals(bookingCreator.createBooking("88888888").getTelephone(), booking.getTelephone());
    }

}