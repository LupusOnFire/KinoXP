package dk.kinoxp.web.model.entity;

import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.repositories.CinemaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CinemaSeatTest {
    @Autowired
    CinemaRepository cinemaRepository;

    @Test
    public void getSeats() throws Exception {
        Cinema cinema = cinemaRepository.findById(1);
        Assert.assertEquals(240, cinema.getSeats().size());
    }
}
