package me.kursaDarbs.app.repository;

import me.kursaDarbs.app.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Integer> {
    List<Hall> findByCinemaId(Integer id);
}
