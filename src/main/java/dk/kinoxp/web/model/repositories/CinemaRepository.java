package dk.kinoxp.web.model.repositories;

import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    Cinema findById(int id);
}
