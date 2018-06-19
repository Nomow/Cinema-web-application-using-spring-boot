package me.kursaDarbs.app.custom;

import me.kursaDarbs.app.model.Movie;
import me.kursaDarbs.app.model.Session;

import java.util.ArrayList;
import java.util.List;

public class CinemaMovieSessions {
    private List<Movie> movies;
    private List<List<Session>> sessions;
    private List<List<Double>> price;

    public List<List<Double>> getPriceRange() {
        for(int i = 0; i < sessions.size(); ++i) {
            double min = sessions.get(i).get(0).GetPrice();
            double max = sessions.get(i).get(0).GetPrice();
            for(int j = 1; j < sessions.get(i).size(); ++j) {
                double priceForSession = sessions.get(i).get(j).GetPrice();
                if(priceForSession < min) {
                    min = priceForSession;
                }

                if(priceForSession > max) {
                    max = priceForSession;
                }
            }
            ArrayList<Double> minMaxPair = new ArrayList<>();
            minMaxPair.add(min);
            minMaxPair.add(max);
            price.add(minMaxPair);
        }
        return price;
    }

    public CinemaMovieSessions(List<Session> sessions) {
        movies = new ArrayList<>();
        this.sessions = new ArrayList<List<Session>>();
        this.price  = new ArrayList<List<Double>>();
        GetUniqueMovies(sessions);
        GetMovieSessions(sessions);
        SortSessionsByDateAsc();

    }

    private void GetUniqueMovies(List<Session> sessions) {
        for(int i = 0; i < sessions.size(); ++i) {
            boolean inList = false;
            Movie sessionMovie = sessions.get(i).GetMovie();
            for(int j = 0; j < movies.size(); ++j) {
                Movie uniqueMovies = movies.get(j);
                if(sessionMovie.GetId() == uniqueMovies.GetId()) {
                    inList = true;
                    break;
                }
            }
            if(inList == false) {
                movies.add(sessionMovie);
            }
        }
    }


    private void GetMovieSessions(List<Session> sessions) {
        for(int i = 0; i < movies.size(); ++i) {
            List<Session> temp = new ArrayList<>();
            for(int j = 0; j < sessions.size(); ++j) {
                if(movies.get(i).GetId() == sessions.get(j).GetMovie().GetId()) {
                    temp.add(sessions.get(j));
                }
            }
            this.sessions.add(temp);
        }
    }

    private void SortSessionsByDateAsc() {
        for(int i = 0; i < sessions.size(); ++i) {
            for(int j = 0; j < sessions.get(i).size() - 1; ++j) {
                for(int k = j + 1; k < sessions.get(i).size(); ++k) {
                    if(sessions.get(i).get(j).GetTime().after(sessions.get(i).get(k).GetTime())) {
                        Session temp = sessions.get(i).get(j);
                        sessions.get(i).set(j, sessions.get(i).get(k));
                        sessions.get(i).set(k, temp);
                    }
                }
            }
        }
    }

    public List<Movie> GetMovies() {
        return movies;
    }

    public List<List<Session>> GetSessions() {
        return sessions;
    }


}
