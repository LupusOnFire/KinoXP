package dk.kinoxp.web.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="age_rating")
    private String age;

    @Column(name="poster_path")
    private String posterPath;

    @Column(name="runtime")
    private String runtime;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actors;


    public Movie() {
    }

    public Movie(String title, String description, String age, String posterPath) {
        this.title = title;
        this.description = description;
        this.age = age;
        this.posterPath = posterPath;
    }
    public Movie(String title, String description, String age, String posterPath, String runtime, List<Actor> actors) {
        this.title = title;
        this.description = description;
        this.age = age;
        this.posterPath = posterPath;
        this.runtime = runtime;
        this.actors = actors;
    }



    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAge() {
        return age;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getRuntime() { return runtime; }

    public void setRuntime(String runtime) { this.runtime = runtime; }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
