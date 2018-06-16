

package me.kursaDarbs.app.repository;
import javax.transaction.Transactional;

import me.kursaDarbs.app.model.Cinema;
import me.kursaDarbs.app.model.Movie;
import me.kursaDarbs.app.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer>{
    public List<Session> findByCinemaIdAndTimeAfter(int id, Date date);
    public Optional<Session> findByIdAndTimeAfter(int id, Date date);
    public List<Session> findByCinemaIdAnAndMovieIdAndTimeAfter(int cinemaId, int movieId, Date date);
}
