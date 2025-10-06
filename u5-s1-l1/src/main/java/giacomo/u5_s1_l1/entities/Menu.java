package giacomo.u5_s1_l1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Menu {
    private final List<Pizza> pizzas;
    private final List<Topping> toppings;
    private final List<Drink> drinks;

    // Metodo per stampare l'intero menu
    public void printMenu() {
        System.out.println("\n=============================================");
        System.out.println("              ESEMPIO MENÙ                   ");
        System.out.println("=============================================");

        System.out.println("\n--- PIZZAS ---");
        pizzas.forEach(p -> System.out.printf("%-40s Calories: %4d, Price: €%.2f%n",
                p.getName(), p.getCalories(), p.getPrice()));

        System.out.println("\n--- TOPPINGS AGGIUNTIVI ---");
        toppings.forEach(t -> System.out.printf("%-40s Calories: %4d, Price: €%.2f%n",
                t.getName(), t.getCalories(), t.getPrice()));

        System.out.println("\n--- DRINKS ---");
        drinks.forEach(d -> System.out.printf("%-40s Calories: %4d, Price: €%.2f%n",
                d.getName(), d.getCalories(), d.getPrice()));

        System.out.println("=============================================");
    }
}