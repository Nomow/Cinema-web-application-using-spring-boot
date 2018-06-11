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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.kursaDarbs.app.repository.CinemaRepository;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class CinemaController {
    @Autowired CinemaRepository repository;
    @RequestMapping("/get-cinema-data")
    public ModelAndView GetAllCinemas() {
        String view = "index";
        ModelAndView mav = new ModelAndView(view);
        JSONObject obj = new JSONObject();
        int i = 0;
        for (Cinema cinema : repository.findAll()) {
            try {
                obj.put(Integer.toString(i), cinema.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ++i;
        }
        String dataName = "data";
        mav.addObject(dataName, obj.toString());
        return mav;
    }
}

