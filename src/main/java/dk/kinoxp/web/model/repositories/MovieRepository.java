package dk.kinoxp.web.model.repositories;

import dk.kinoxp.web.model.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByTitle(String title);
    Movie findById(int id);

}
