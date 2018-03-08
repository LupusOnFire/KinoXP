package dk.kinoxp.web.model.repositories;

import dk.kinoxp.web.model.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Actor findByName(String name);
}
