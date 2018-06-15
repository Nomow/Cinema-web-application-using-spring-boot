package me.kursaDarbs.app.controller;

import java.util.List;
import java.util.Optional;

import me.kursaDarbs.app.model.Cinema;

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
        return mav;
    }

    @RequestMapping(value = "/cinema/{id}", method = RequestMethod.GET)
    public ModelAndView GetCinema(@PathVariable("id") int id) {
        ModelAndView mav = new ModelAndView();
        Optional<Cinema> cinemaRepo = cinemaRepository.findById(id);
        if(cinemaRepo.isPresent()) {
            mav.setViewName("cinema");
            mav.getModelMap().addAttribute("cinema", cinemaRepo.get());
        }
        return mav;
    }



}

