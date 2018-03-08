package dk.kinoxp.web.model.repositories;

import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {
    List<Seat> findAllById(int id);
    Cinema findById(int id);
}
