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

    public void printMenu() {
        System.out.println("\n=============================================");
        System.out.println("              MENU PIZZERIA                  ");
        System.out.println("=============================================");

        System.out.println("\n--- PIZZAS ---");
        System.out.printf("%-40s %8s %8s%n", "PIZZAS", "Calories", "Price");
        pizzas.forEach(p -> System.out.printf("%-40s %8d %8.2f%n",
                p.getName(), p.getCalories(), p.getPrice()));

        System.out.println("\n--- TOPPINGS AGGIUNTIVI ---");
        System.out.printf("%-40s %8s %8s%n", "TOPPINGS", "Calories", "Price");
        toppings.forEach(t -> System.out.printf("%-40s %8d %8.2f%n",
                t.getName(), t.getCalories(), t.getPrice()));

        System.out.println("\n--- DRINKS ---");
        System.out.printf("%-40s %8s %8s%n", "DRINKS", "Calories", "Price");
        drinks.forEach(d -> System.out.printf("%-40s %8d %8.2f%n",
                d.getName(), d.getCalories(), d.getPrice()));

        System.out.println("=============================================");
    }
}