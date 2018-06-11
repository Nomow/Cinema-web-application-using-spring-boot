package me.kursaDarbs.app.controller;

import java.util.List;
import java.util.Optional;

import me.kursaDarbs.app.model.Cinema;
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



@RestController
public class CinemaController {
    @Autowired CinemaRepository repository;
    @RequestMapping("/cinemas")
    public List<Cinema>  GetAllCinemas() {
        List<Cinema> cinemaList = repository.findAll();

        return cinemaList;
    }
}

