package giacomo.u5_s1_l1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Transient;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
public class Pizza implements MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private int calories;

    // Usata solo durante la creazione del Bean
    @Transient
    private List<Topping> ingredients;

    // Costruttore usato dalla configurazione per calcolare i valori finali
    public Pizza(String name, List<Topping> ingredients) {
        this.name = name;
        this.ingredients = ingredients;

        // Calcola i totali sommando i valori degli ingredienti
        this.price = ingredients.stream().mapToDouble(Topping::getPrice).sum();
        this.calories = ingredients.stream().mapToInt(Topping::getCalories).sum();
    }

    @Transient
    @Override
    public String getMenuLine() {
        // La logica di stampa è basata sugli ingredienti presenti nel Bean
        String ingredientNames = ingredients != null ? ingredients.stream()
                .map(Topping::getName)
                .collect(Collectors.joining(", ")) : "N/A";

        return String.format("%s (Ingredients: %s) - Calories: %4d, Price: €%.2f",
                name, ingredientNames, calories, price);
    }
}