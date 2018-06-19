package me.kursaDarbs.app.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import me.kursaDarbs.app.custom.CinemaMovieSessions;
import me.kursaDarbs.app.model.Cinema;

import me.kursaDarbs.app.model.Session;
import me.kursaDarbs.app.repository.SessionRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import me.kursaDarbs.app.repository.CinemaRepository;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class CinemaController {

    // post method to get all cinema data
    @Autowired CinemaRepository cinemaRepository;
    @RequestMapping(value = "/cinemas", method = RequestMethod.POST)
    public @ResponseBody
    String GetCinemaData() {
        JSONObject obj = new JSONObject();
        int i = 0;
        for (Cinema cinema : cinemaRepository.findAll()) {
            try {
                obj.put(Integer.toString(i), cinema.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ++i;
        }
        return obj.toString();
    }

    @RequestMapping(value = "/cinemas", method = RequestMethod.GET)
    public ModelAndView GetCinemaList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("cinemas");
        mav.getModelMap().addAttribute("cinemas", cinemaRepository.findAll());
        mav.getModelMap().addAttribute("pageTitle", "cinemas");
        return mav;
    }

    @Autowired SessionRepository sessionRepository;
    @RequestMapping(value = "/cinema/{id}", method = RequestMethod.GET)
    public ModelAndView GetCinema(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();
        Optional<Cinema> cinemaRepo = cinemaRepository.findById(id);
        Date currentDate = Calendar.getInstance().getTime();
        if(cinemaRepo.isPresent()) {
            List<Session> sessions = sessionRepository.findByCinemaIdAndTimeAfter(id, currentDate);
            CinemaMovieSessions moviesBySession = new CinemaMovieSessions(sessions);
            mav.setViewName("cinema");
            // stores information about 1 cinema
            mav.getModelMap().addAttribute("cinema", cinemaRepo.get());
            // stores information in arrayList about movies currently airing

            mav.getModelMap().addAttribute("movies", moviesBySession.GetMovies());
            // stores information of sessions for each movie in ArrayList of ArrayList
            List<List<Session>> movieSessions = moviesBySession.GetSessions();
            mav.getModelMap().addAttribute("sessions", movieSessions);
            mav.getModelMap().addAttribute("sessionPriceRanges", moviesBySession.getPriceRange());
            mav.getModelMap().addAttribute("pageTitle", cinemaRepo.get().GetName());


//            for(int i = 0; i < movieSessions.size(); ++i) {
//                for(int j = 0; j < movieSessions.get(i).size(); ++j) {
//                    System.out.println(movieSessions.get(i).get(j).GetTime());
//                }
//                System.out.println("=====================");
//            }

        }
        return mav;
    }



}

