package giacomo.u5_s1_l1;

import giacomo.u5_s1_l1.entities.Drink;
import giacomo.u5_s1_l1.entities.Menu;
import giacomo.u5_s1_l1.entities.Pizza;
import giacomo.u5_s1_l1.entities.Topping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MenuConfig {

    // --- 1. BASE INGREDIENTS (Toppings che compongono la Margherita) ---
    // Useremo questi per calcolare il prezzo base e le calorie.
    @Bean
    public Topping baseTomato() { return new Topping("tomato", 0.00, 0); }
    // Simulo il prezzo base della Margherita (4.99, 1104 cal) diviso tra Mozzarella e Tomato.
    @Bean
    public Topping baseMozzarella() { return new Topping("cheese", 4.99, 1104); }

    // --- 2. EXTRA TOPPINGS (Aggiunti alla Margherita) ---
    @Bean
    public Topping ham() { return new Topping("ham", 0.99, 35); }
    @Bean
    public Topping pineapple() { return new Topping("pineapple", 0.79, 24); }
    @Bean
    public Topping salami() { return new Topping("salami", 0.99, 86); }

    @Bean
    public List<Topping> extraToppings(Topping ham, Topping pineapple, Topping salami) {
        return List.of(ham, pineapple, salami);
    }

    // --- 3. PIZZAS (Combinazioni) ---
    @Bean
    public Pizza margherita(Topping baseTomato, Topping baseMozzarella) {
        return new Pizza(
                "Pizza Margherita",
                List.of(baseTomato, baseMozzarella)
        );
    }

    @Bean
    public Pizza hawaiian(Topping baseTomato, Topping baseMozzarella, Topping ham, Topping pineapple) {
        return new Pizza(
                "Hawaiian Pizza",
                List.of(baseTomato, baseMozzarella, ham, pineapple)
        );
    }

    @Bean
    public Pizza salamiPizza(Topping baseTomato, Topping baseMozzarella, Topping salami) {
        return new Pizza(
                "Salami Pizza",
                List.of(baseTomato, baseMozzarella, salami)
        );
    }

    @Bean
    public List<Pizza> pizzas(Pizza margherita, Pizza hawaiian, Pizza salamiPizza) {
        return List.of(margherita, hawaiian, salamiPizza);
    }

    // --- 4. DRINKS ---
    @Bean
    public Drink lemonade() { return new Drink("Lemonade (0.33l)", 1.29, 128); }
    @Bean
    public Drink water() { return new Drink("Water (0.5l)", 1.29, 0); }
    @Bean
    public Drink wine() { return new Drink("Wine (0.75l, 13%)", 7.49, 607); }

    @Bean
    public List<Drink> drinks(Drink lemonade, Drink water, Drink wine) {
        return List.of(lemonade, water, wine);
    }

    // --- 5. FINAL MENU BEAN ---
    @Bean
    public Menu menu(List<Pizza> pizzas, List<Topping> extraToppings, List<Drink> drinks) {
        return new Menu(pizzas, extraToppings, drinks);
    }
}