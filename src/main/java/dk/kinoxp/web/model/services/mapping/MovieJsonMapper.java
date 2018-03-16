package dk.kinoxp.web.model.services.mapping;

import dk.kinoxp.web.model.entities.Actor;
import dk.kinoxp.web.model.entities.Movie;
import dk.kinoxp.web.model.repositories.ActorRepository;
import dk.kinoxp.web.model.services.ActorRecycle;
import dk.kinoxp.web.model.services.OmdbClient;

import org.json.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MovieJsonMapper
{

    ActorRepository _actorRepository;
    ActorRecycle actorRecycle;

    public MovieJsonMapper(ActorRepository actorRepository){

        _actorRepository = actorRepository;
        actorRecycle = new ActorRecycle(_actorRepository);

    }

    public Movie mapJsonToMovie(String jsonBody)
    {
        Movie movie = null;

        try
        {
            JSONObject obj = new JSONObject(jsonBody);

            String title = obj.getString("Title");
            String rated = obj.getString("Rated");
            String runtime = obj.getString("Runtime");
            String description = obj.getString("Plot");
            String posterPath = obj.getString("Poster");
            String actors = obj.getString("Actors");

            String[] actorArray = actors.split(",");
            List<Actor> actorList = new LinkedList<>();

            for(int i = 0; i < actorArray.length; i++) {
                if (i == 3)
                    break;
                actorList.add( actorRecycle.findOrCreateActor(actorArray[i]));
            }

            movie = new Movie(title, description, rated, posterPath, runtime, actorList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movie;
    }
}
