package me.kursaDarbs.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.kursaDarbs.app.model.Cinema;


@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer>{

}

