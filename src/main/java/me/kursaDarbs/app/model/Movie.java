package me.kursaDarbs.app.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "Movie")
public class Movie {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_;

    @Column(name="name")
    private String name_;

    @Column(name="description")
    private String description_;

    @Column(name="time")
    private Date time_;

    @Column(name="director")
    private String director_;

    @Column(name="trailer")
    private String trailerUrl_;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "MovieGenres", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "genres_id", referencedColumnName = "id"))
    private Set<Genres> genres_;

    public Movie(){}

    public Integer GetId() {
        return id_;
    }

    public String GetName() {
        return name_;
    }

    public String GetDescription() { return description_; }

    public Date GetTime() {
        return time_;
    }

    public String GetDirector() {
        return director_;
    }

    public String GetTrailer() {
        return trailerUrl_;
    }


    @Override
    public String toString() {
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id", id_);
            jsonObj.put("name", name_);
            jsonObj.put("description", description_);
            jsonObj.put("time", time_);
            jsonObj.put("director", director_);
            jsonObj.put("trailer", trailerUrl_);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj.toString();
    }





}
