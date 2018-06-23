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

    @Column(name="year")
    private Integer year;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MovieGenres", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "genres_id", referencedColumnName = "id"))
    private List<Genres> genres;

    public Movie(){}
    public Movie(String name, String description, Integer year, Date time, String director, String trailerUrl, String imdbUrl, List<Genres> genres) {
      this.name = name;
      this.description = description;
      this.year = year;
      this.time = time;
      this.director = director;
      this.trailerUrl = trailerUrl;
      this.imdbUrl = imdbUrl;
      this.genres = genres;
    }


    public Integer GetId() {
        return id;
    }
    public void SetId(Integer id) { this.id = id; }

    public String GetName() {
        return name;
    }
    public void SetName(String name) { this.name = name; }

    public String GetDescription() { return description; }
    public void SetDescription(String description) { this.description = description; }

    public Date GetTime() {
        return time;
    }
    public void SetTime(Date time) { this.time = time; }

    public String GetDirector() {
        return director;
    }
    public void SetDirector(String director) { this.director = director; }

    public String GetTrailerUrl() {
        return trailerUrl;
    }
    public void SetTrailer(String trailerUrl) { this.trailerUrl = trailerUrl; }

    public String GetImdbUrl() {
        return imdbUrl;
    }
    public void SetImdbUrl(String imdbUrl) { this.imdbUrl = imdbUrl; }

    public List<Genres> GetGenres() {
        return genres;
    }
    public void SetImdbUrl(List<Genres> genres) { this.genres = genres; }

    public Integer GetYear() {
        return year;
    }
    public void SetYear(Integer year) { this.year = year; }

}
