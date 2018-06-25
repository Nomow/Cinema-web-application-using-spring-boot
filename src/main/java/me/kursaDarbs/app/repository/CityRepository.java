package me.kursaDarbs.app.repository;

import me.kursaDarbs.app.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
