package dk.kinoxp.web.model.repositories;

import dk.kinoxp.web.model.entities.Showing;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Ignore
public class ShowingRepositoryTest {
    @Autowired
    ShowingRepository showingRepository;
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findById() throws Exception {
        Showing showing = new Showing();
        assertEquals(1, showingRepository.findById(1));
        showing.setId(1);

    }
}