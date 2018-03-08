package dk.kinoxp.web.model.repositories;

import dk.kinoxp.web.model.entities.Cinema;
import dk.kinoxp.web.model.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    Seat findById(int id);
    Seat findByRow(int row);
    Seat findByColumn(int column);
    Seat findByCinema (Cinema cinema);
}
