package me.kursaDarbs.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import me.kursaDarbs.app.model.Cinema;

import me.kursaDarbs.app.repository.MovieRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import me.kursaDarbs.app.repository.CinemaRepository;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class MovieController {

    @Autowired
    MovieRepository repository;


    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public ModelAndView GetMovieList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("movies");
        mav.getModelMap().addAttribute("movies", repository.findAll());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now)); //2016/11/16 12:08:43


        return mav;
    }
}

