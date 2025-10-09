package giacomo.u5_s1_l1.repositories;

import giacomo.u5_s1_l1.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    // Esercizio #2 Persistenza: Query Derived
    Pizza findByName(String name);

    // Esercizio #2 Persistenza: Query Derived
    List<Pizza> findByCaloriesLessThan(int calories);
}