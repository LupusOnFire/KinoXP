package dk.kinoxp.web.model.services;

import dk.kinoxp.web.model.entities.Actor;
import dk.kinoxp.web.model.entities.Movie;

public class MovieCreator
{
    public MovieCreator() {
    }

    public Movie createMovie(String title, String description, String age, String posterPath)
    {
        return new Movie(title, description, age, posterPath);
    }

    public Movie findByName(String title)
    {
        return new Movie();
    }
}
