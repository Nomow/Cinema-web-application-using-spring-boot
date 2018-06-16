package me.kursaDarbs.app.custom;

import me.kursaDarbs.app.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionProcessing {
    private List<List<Boolean>> seats;
    private Hall hall;
    private Cinema cinema;
    private Movie movie;
    private Date time;
    private double price;


    public SessionProcessing(Session session) {
        seats = new ArrayList<List<Boolean>>();
        FillSeats(session);
        hall = session.GetHall();
        cinema = session.GetCinema();
        movie = session.GetMovie();
        time = session.GetTime();
        price = session.GetPrice();
    }

    private void FillSeats(Session session) {
        int rows = session.GetHall().GetRows();
        int cols = session.GetHall().GetCols();

        for(int i = 0; i < rows; ++i) {
            seats.add(new ArrayList<Boolean>());
            for(int j = 0; j < cols; ++j) {
                seats.get(i).add(false);
            }
        }
        FillBoughtSeats(session);
    }

    private void FillBoughtSeats(Session session) {
        List<BoughtSeats> boughtSeats = session.GetBoughtSeats();
        for(int i = 0; i < boughtSeats.size(); ++ i) {
            int row = boughtSeats.get(i).GetRow();
            int col = boughtSeats.get(i).GetCol();
            seats.get(row).set(col, true);
        }
    }

    public List<List<Boolean>> GetSeats() {
        return seats;
    }

    public Hall GetHall() {
        return hall;
    };


    public Cinema GetCinema() {
        return cinema;
    }

    public Movie GetMovie() {
        return movie;
    }

    public Date GetTime() {
        return time;
    }

    public double GetPrice() {
        return price;
    }





}
