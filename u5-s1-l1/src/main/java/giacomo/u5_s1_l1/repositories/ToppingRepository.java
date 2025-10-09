package giacomo.u5_s1_l1.repositories;

import giacomo.u5_s1_l1.entities.Topping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {
    Topping findByName(String name);
}