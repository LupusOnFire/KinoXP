package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.Actor;
import dk.kinoxp.web.model.repositories.ActorRepository;

public class ActorRecycle {

    ActorRepository _actorRepository;

    public ActorRecycle(ActorRepository repository){

        this._actorRepository = repository;
    }

    public Actor findOrCreateActor(String actorName){

        Actor actor = _actorRepository.findByName(actorName);

        if (actor == null)
        {
            actor = new Actor(actorName);
            _actorRepository.save(actor);
        }
        return actor;
    }
}
