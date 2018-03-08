package dk.kinoxp.web.model.repositories;

import dk.kinoxp.web.model.entities.Actor;
import dk.kinoxp.web.model.entities.Movie;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;

    @Test
    public void testSaveAndfindByTitle() {
        Movie movie = new Movie();
        movie.setTitle("Pulp Fiction");
        movie.setDescription("Bretty good movie");
        movie.setAge("18");

        List<Actor> actors = new LinkedList<>();
        actors.add(new Actor("Bruce Willis", ""));
        actors.add(new Actor("Samuel Jackson", ""));

        movie.setActors(actors);

        movieRepository.save(movie);
        movieRepository.flush();

        Movie movieSelect = movieRepository.findByTitle(movie.getTitle());

        Assert.assertEquals(movie.getTitle(), movieSelect.getTitle());
        Assert.assertEquals(movie.getDescription(), movieSelect.getDescription());
        Assert.assertEquals(movie.getAge(), movieSelect.getAge());
        Assert.assertTrue(movie.getActors().size() == movieSelect.getActors().size());
    }
}