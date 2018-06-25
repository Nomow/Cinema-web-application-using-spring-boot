

package me.kursaDarbs.app.repository;
import javax.transaction.Transactional;

import me.kursaDarbs.app.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
    Movie findByName(String name);
}
