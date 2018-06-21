package me.kursaDarbs.app.repository;

import me.kursaDarbs.app.model.BoughtSeats;
import javax.transaction.Transactional;

import me.kursaDarbs.app.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface BoughtSeatsRepository extends JpaRepository<BoughtSeats, Integer>{
    public List<BoughtSeats> findBySessionId(int id);
}
