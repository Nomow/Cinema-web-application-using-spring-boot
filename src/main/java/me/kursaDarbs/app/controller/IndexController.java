package me.kursaDarbs.app.controller;

import me.kursaDarbs.app.model.Cinema;
import me.kursaDarbs.app.repository.CinemaRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    @Autowired CinemaRepository cinemaRepository;
    @RequestMapping("/")
    public ModelAndView Index() {
        ModelAndView mav = new ModelAndView();
        Pageable count = new PageRequest(0, 3);
        Page<Cinema> cinemas = cinemaRepository.findAll(count);
        List<Cinema> cinemaList = cinemas.getContent();
        mav.setViewName("home");
        mav.getModelMap().addAttribute("cinemas", cinemaList);
        mav.getModelMap().addAttribute("pageTitle", "Cinema booking system");

        return mav;
    }
}

