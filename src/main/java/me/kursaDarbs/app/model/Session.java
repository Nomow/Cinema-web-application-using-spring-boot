package me.kursaDarbs.app.model;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "Session")
public class Session {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @ManyToOne
    @JoinColumn(name = "movies_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @Column(name="time")
    private Date time;

    @Column(name="price")
    private double price;



    @OneToMany(fetch=FetchType.LAZY,mappedBy = "session")
    private List<BoughtSeats> boughtSeats;



    public Session(){}

    public Integer GetId() {
        return id;
    }
    public void SetId(Integer id) { this.id = id; }

    public Cinema GetCinema() {
        return cinema;
    }
    public void SetCinema(Cinema cinema) { this.cinema = cinema; }

    public Movie GetMovie() {
        return movie;
    }
    public void SetMovie(Movie movie) { this.movie = movie; }

    public Hall GetHall() {
        return hall;
    }
    public void SetHall(Hall hall) { this.hall = hall; }

    public List<BoughtSeats> GetBoughtSeats() {
        return boughtSeats;
    }
    public void SetBoughtSeats(List<BoughtSeats> boughtSeats) { this.boughtSeats = boughtSeats; }

    public Date GetTime() { return time; }
    public void SetTime(Date time) { this.time = time; }

    public double GetPrice() {
        return price;
    }
    public void SetTime(double price) { this.price = price; }

}