package dk.kinoxp.web.model.repositories;

import dk.kinoxp.web.model.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByTelephone(String telephone);
}
