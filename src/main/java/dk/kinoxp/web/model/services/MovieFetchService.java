package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.Movie;
import dk.kinoxp.web.model.repositories.ActorRepository;
import dk.kinoxp.web.model.services.mapping.MovieJsonMapper;

public class MovieFetchService {

    OmdbClient omdbClient;
    MovieJsonMapper movieJsonMapper;
    ActorRepository _actorRepository;

    public MovieFetchService(ActorRepository actorRepository){
        omdbClient = new OmdbClient();
        _actorRepository = actorRepository;
        movieJsonMapper = new MovieJsonMapper(actorRepository);
    }

    public Movie getMovieFromTitle(String title){

        return movieJsonMapper.mapJsonToMovie(omdbClient.getJsonBodyByMovieTitle(title));
    }
}
