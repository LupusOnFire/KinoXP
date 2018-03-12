package dk.kinoxp.web.model.repositories;

import dk.kinoxp.web.model.entities.Booking;
import dk.kinoxp.web.model.entities.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByTelephone(String telephone);
    List<Booking> findAllByShowing(Showing showing);
}
