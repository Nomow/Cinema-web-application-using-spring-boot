

package me.kursaDarbs.app.repository;
import me.kursaDarbs.app.model.PaymentSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSystemRepository extends JpaRepository<PaymentSystem, Integer>{
}
