package giacomo.u5_s1_l1;

import giacomo.u5_s1_l1.config.OrderConfig;
import giacomo.u5_s1_l1.entities.*;
import giacomo.u5_s1_l1.entities.Table.TableStatus;
import giacomo.u5_s1_l1.repositories.PizzaRepository;
import giacomo.u5_s1_l1.repositories.ToppingRepository;
import giacomo.u5_s1_l1.repositories.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class PizzeriaApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PizzeriaApplication.class);

    @Autowired private Menu menu;
    @Autowired private OrderConfig orderConfig;

    // Iniezione dei Beans per il salvataggio
    @Autowired private List<Pizza> allPizzas;
    @Autowired private List<Topping> allToppings;
    @Autowired private List<Drink> allDrinks;

    // Iniezione dei Repository per la persistenza e le query
    @Autowired private PizzaRepository pizzaRepo;
    @Autowired private ToppingRepository toppingRepo;
    @Autowired private DrinkRepository drinkRepo;

    public static void main(String[] args) {
        SpringApplication.run(PizzeriaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("******************************************************************");
        logger.info("Avvio Pizzeria Application: Configurazione Menu e Persistenza");
        logger.info("******************************************************************");

        // 1. Salvataggio delle Entity nel database (Esercizio #3 Persistenza)
        logger.info("-> Salvataggio Beans in PostgreSQL...");
        // Filtriamo i Beans che non hanno ID (significa che non sono ancora stati salvati)
        toppingRepo.saveAll(allToppings.stream().filter(t -> t.getId() == null).collect(Collectors.toList()));
        drinkRepo.saveAll(allDrinks.stream().filter(d -> d.getId() == null).collect(Collectors.toList()));
        pizzaRepo.saveAll(allPizzas.stream().filter(p -> p.getId() == null).collect(Collectors.toList()));
        logger.info("-> Dati menu salvati con successo.");


        // 2. Esecuzione delle Query (Esercizio #4 Persistenza)
        logger.info("\n--- Esecuzione Query JPA (findByName, findByCaloriesLessThan) ---");

        // Query 1: Trova Pizza per nome
        String targetPizzaName = "Hawaiian Pizza (tomato, cheese, ham, pineapple)";
        Pizza foundPizza = pizzaRepo.findByName(targetPizzaName);
        logger.info("Query 1: Trovata Pizza per nome: {}", foundPizza != null ? foundPizza.getName() : "Nessuna");

        // Query 2: Trova Pizze con meno di 1150 calorie
        List<Pizza> lightPizzas = pizzaRepo.findByCaloriesLessThan(1150);
        logger.info("Query 2: Trovate {} pizze con meno di 1150 calorie.", lightPizzas.size());
        lightPizzas.forEach(p -> logger.info("  -> {} ({} Cal)", p.getName(), p.getCalories()));

        logger.info("\n--- Stampa Menu Completo (Esercizio #1) ---");
        menu.printMenu();


        // 3. Creazione e Stampa di un Ordine (Esercizio #2 Ordini)
        logger.info("\n--- Creazione e Stampa Ordine ---");

        // Inizializza Tavolo
        Table table1 = new Table(1, 4, TableStatus.FREE);
        table1.setStatus(TableStatus.OCCUPIED);

        // Leggiamo i dati dal DB per l'ordine (coerenza)
        Pizza orderPizza = pizzaRepo.findByName("Pizza Margherita (tomato, cheese)");
        Drink orderDrink = drinkRepo.findAll().stream().filter(d -> d.getName().contains("0.33l")).findFirst().orElse(null);
        Topping orderTopping = toppingRepo.findByName("pineapple"); // Topping extra

        // Popola l'ordine
        List<MenuItem> items = List.of(orderPizza, orderDrink, orderTopping);

        int covers = 3;
        Order newOrder = new Order(table1, covers, items);

        // Calcolo del totale
        double coverCharge = orderConfig.getCoverCharge();
        newOrder.calculateTotal(coverCharge);

        // Stampa a video dell'ordine utilizzando il logger
        logger.info("*********************** Dettagli Ordine ***********************");
        logger.info("Ordine ID: {}", newOrder.getOrderId());
        logger.info("Tavolo: {} (Coperti: {}) - Stato: {}", newOrder.getTable().getTableNumber(), newOrder.getCovers(), newOrder.getState());
        logger.info("Costo Coperto (per persona): €{}", coverCharge);

        logger.info("\n--- Elementi Ordinati ---");
        newOrder.getItems().forEach(item ->
                logger.info("  -> {} (Cal: {}, Pr: €{})",
                        item.getName(), item.getCalories(), String.format("%.2f", item.getPrice())));

        logger.info("\n--- Totale ---");
        logger.info("Totale Ordine (Inclusi coperti): €{}", String.format("%.2f", newOrder.getTotalAmount()));
        logger.info("***************************************************************");
    }
}