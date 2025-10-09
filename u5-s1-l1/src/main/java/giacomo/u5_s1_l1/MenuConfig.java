package giacomo.u5_s1_l1;

import giacomo.u5_s1_l1.entities.Drink;
import giacomo.u5_s1_l1.entities.Menu;
import giacomo.u5_s1_l1.entities.Pizza;
import giacomo.u5_s1_l1.entities.Topping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MenuConfig {

    // --- 1. BASE INGREDIENTS ---
    @Bean public Topping baseTomato() { return new Topping("tomato", 0.00, 0); }
    @Bean public Topping baseMozzarella() { return new Topping("cheese", 4.99, 1104); }

    // --- 2. EXTRA TOPPINGS ---
    @Bean public Topping ham() { return new Topping("ham", 0.99, 35); }
    @Bean public Topping pineapple() { return new Topping("pineapple", 0.79, 24); }
    @Bean public Topping salami() { return new Topping("salami", 0.99, 86); }
    @Bean public Topping onions() { return new Topping("onions", 0.69, 22); }

    @Bean
    public List<Topping> allToppings(Topping ham, Topping pineapple, Topping salami, Topping onions, Topping baseTomato, Topping baseMozzarella) {
        return List.of(baseTomato, baseMozzarella, ham, pineapple, salami, onions);
    }

    // --- 3. PIZZAS (Combinazioni) ---
    @Bean
    public Pizza margherita(Topping baseTomato, Topping baseMozzarella) {
        return new Pizza("Pizza Margherita (tomato, cheese)", List.of(baseTomato, baseMozzarella));
    }

    @Bean
    public Pizza hawaiian(Topping baseTomato, Topping baseMozzarella, Topping ham, Topping pineapple) {
        return new Pizza("Hawaiian Pizza (tomato, cheese, ham, pineapple)", List.of(baseTomato, baseMozzarella, ham, pineapple));
    }

    @Bean
    public Pizza salamiPizza(Topping baseTomato, Topping baseMozzarella, Topping salami) {
        return new Pizza("Salami Pizza (tomato, cheese, salami)", List.of(baseTomato, baseMozzarella, salami));
    }

    @Bean
    public List<Pizza> allPizzas(Pizza margherita, Pizza hawaiian, Pizza salamiPizza) {
        return List.of(margherita, hawaiian, salamiPizza);
    }

    // --- 4. DRINKS ---
    @Bean public Drink lemonade() { return new Drink("Lemonade (0.33l)", 1.29, 128); }
    @Bean public Drink water() { return new Drink("Water (0.5l)", 1.29, 0); }
    @Bean public Drink wine() { return new Drink("Wine (0.75l, 13%)", 7.49, 607); }

    @Bean
    public List<Drink> allDrinks(Drink lemonade, Drink water, Drink wine) {
        return List.of(lemonade, water, wine);
    }

    // --- 5. FINAL MENU BEAN (Contenitore per la stampa) ---
    @Bean
    public Menu menu(List<Pizza> allPizzas, List<Topping> allToppings, List<Drink> allDrinks) {
        // Filtriamo i topping di base per mostrare nel menu solo gli extra vendibili
        List<Topping> extraToppings = allToppings.stream()
                .filter(t -> t.getName().equals("ham") || t.getName().equals("pineapple") || t.getName().equals("salami") || t.getName().equals("onions"))
                .collect(Collectors.toList());

        return new Menu(allPizzas, extraToppings, allDrinks);
    }
}