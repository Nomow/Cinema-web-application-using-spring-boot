

package me.kursaDarbs.app.repository;
import javax.transaction.Transactional;

import me.kursaDarbs.app.model.Movie;
import me.kursaDarbs.app.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Integer>{
}
