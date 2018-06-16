package me.kursaDarbs.app.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "Movie")
public class Movie {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="time")
    private Date time;

    @Column(name="director")
    private String director;

    @Column(name="youtube_trailer_url")
    private String trailerUrl;

    @Column(name="imdb_url")
    private String imdbUrl;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MovieGenres", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "genres_id", referencedColumnName = "id"))
    private List<Genres> genres;

    public Movie(){}

    public Integer GetId() {
        return id;
    }

    public String GetName() {
        return name;
    }

    public String GetDescription() { return description; }

    public Date GetTime() {
        return time;
    }

    public String GetDirector() {
        return director;
    }

    public String GetTrailerUrl() {
        return trailerUrl;
    }

    public String GetImdbUrl() {
        return imdbUrl;
    }

    public List<Genres> GetGenres() {
        return genres;
    }






}
