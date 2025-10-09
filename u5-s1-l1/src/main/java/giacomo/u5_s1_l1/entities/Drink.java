package giacomo.u5_s1_l1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Transient;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drink implements MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private int calories;

    // Costruttore per creare i Beans senza ID
    public Drink(String name, double price, int calories) {
        this.name = name;
        this.price = price;
        this.calories = calories;
    }

    @Transient
    @Override
    public String getMenuLine() {
        return String.format("%-20s Calories: %4d, Price: €%.2f", name, calories, price);
    }
}