package giacomo.u5_s1_l1.entities;

import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Pizza implements MenuItem {
    private final String name;
    private final List<Topping> ingredients;
    private final double price;
    private final int calories;

    public Pizza(String name, List<Topping> ingredients) {
        this.name = name;
        this.ingredients = ingredients;

        // Calcola i totali sommando i valori degli ingredienti
        this.price = ingredients.stream().mapToDouble(Topping::getPrice).sum();
        this.calories = ingredients.stream().mapToInt(Topping::getCalories).sum();
    }

    @Override
    public String getMenuLine() {
        String ingredientNames = ingredients.stream()
                .map(Topping::getName)
                .collect(Collectors.joining(", "));

        return String.format("%s (Ingredients: %s) - Calories: %4d, Price: €%.2f",
                name, ingredientNames, calories, price);
    }
}