package me.kursaDarbs.app.controller;

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
    public ModelAndView GetCinemaList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("movies");
        mav.getModelMap().addAttribute("movies", repository.findAll());
        return mav;
    }
}

