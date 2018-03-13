package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.*;
import dk.kinoxp.web.model.repositories.ActorRepository;

import java.util.List;

public class ActorCreator
{
    public ActorCreator() {
    }

    public Actor createActor(String name)
    {
        return new Actor(name);
    }

    public Actor findByName(String name)
    {
        return new Actor();
    }

}
