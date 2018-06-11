package me.kursaDarbs.app.repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import me.kursaDarbs.app.model.Cinema;
@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Integer>{
}
