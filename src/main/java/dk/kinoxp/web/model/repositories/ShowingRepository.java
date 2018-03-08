package dk.kinoxp.web.model.repositories;

import dk.kinoxp.web.model.entities.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Integer> {
    Showing findById(int id);
    Showing findByCinemaId(int id);
    Showing findByMovie_Title(String title);
    Showing findByTime(Date time);
}
