package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.entities.Seat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.List;

import static org.junit.Assert.*;

public class CinemaCreatorTest {
    CinemaCreator cinemaCreator;
    @Before
    public void setUp() {
        cinemaCreator = new CinemaCreator();
    }

    @Test
    public void createSeats() {
        Cinema cinema = new Cinema(1, 0, 0);
        List<Seat> seats = cinemaCreator.createSeats(cinema, 20, 10);
        Assert.assertEquals(200, seats.size());
    }
}