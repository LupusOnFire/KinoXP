package dk.kinoxp.web.model.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeatTest {
    Seat seat;
    @Before
    public void setUp() throws Exception {
    seat = new Seat();
    }

    @Test
    public void getId() throws Exception {
    assertEquals(1,seat.getId());
    }

    @Test
    public void setId() throws Exception {
    }

    @Test
    public void getRow() throws Exception {
    }

    @Test
    public void setRow() throws Exception {
    }

    @Test
    public void getColumn() throws Exception {
    }

    @Test
    public void setColumn() throws Exception {
    }

    @Test
    public void getCinema() throws Exception {
    }

}