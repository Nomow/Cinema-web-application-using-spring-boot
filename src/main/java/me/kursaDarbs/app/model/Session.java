package me.kursaDarbs.app.model;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table(name = "Session")
public class Session {
    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema_;

    @ManyToOne
    @JoinColumn(name = "movies_id")
    private Movie movie_;

    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall_;

    @Column(name="time")
    private Date time_;

    @Column(name="price_children")
    private double childrenPrice_;

    @Column(name="price_adults")
    private double adultPrice_;

    @Column(name="price_seniors")
    private double seniorPrice_;

    @Column(name="price_handicapped")
    private double HandicappedPrice_;

    public Session(){}

    public Integer GetId() {
        return id_;
    }

    public Cinema GetCinema() {
        return cinema_;
    }

    public Movie GetMovie() {
        return movie_;
    }

    public Hall GetHall() {
        return hall_;
    }


    public Date GetSessionTime() { return time_; }

    public double GetChildrenPrice() {
        return childrenPrice_;
    }

    public double GetAdultPrice() {
        return adultPrice_;
    }

    public double GetSeniorPrice() {
        return seniorPrice_;
    }

    public double GetHandicappedPrice() {
        return HandicappedPrice_;
    }




}