package dk.kinoxp.web.model.entities;

import javax.persistence.*;

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

    public Movie() {
    }

    public Movie(String title, String description, String age, String posterPath) {
        this.title = title;
        this.description = description;
        this.age = age;
        this.posterPath = posterPath;
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

    public void setAge(String age) {
        this.age = age;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
