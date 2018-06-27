

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
    public List<Session> findByCinemaId(int id);
    public List<Session> findByCinemaIdAndTimeAfter(int id, Date date);
    public List<Session> findByCinemaIdAndTimeBefore(int id, Date date);
    public Optional<Session> findByIdAndTimeAfter(int id, Date date);
    public List<Session> findByCinemaIdAndMovieIdAndTimeAfter(int cinemaId, int movieId, Date date);
    public List<Session> findByMovieIdAndTimeAfter(int cinemaId, Date date);
    public List<Session> findByHallIdAndTimeAfter(int hallId, Date date);
    public List<Session> findByHallIdAndTimeGreaterThanEqualAndTimeLessThanEqual(int hallId, Date startDate, Date endDate);
}
