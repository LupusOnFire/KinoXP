package dk.kinoxp.web.IntegrationTest;

import dk.kinoxp.web.model.entities.Movie;
import dk.kinoxp.web.model.repositories.MovieRepository;
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
public class MovieRepositoryIntegrationTest {
    @Autowired
    MovieRepository movieRepository;

    @Test
    public void testInsert() {
        Movie movie = new Movie();
        movie.setTitle("Pulp Fiction");
        Movie movieInserted = movieRepository.save(movie);
        movieRepository.flush();

        Assert.assertEquals(movie.getTitle(), movieInserted.getTitle());
    }
}
