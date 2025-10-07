package giacomo.u5_s1_l1;

import giacomo.u5_s1_l1.config.OrderConfig;
import giacomo.u5_s1_l1.entities.*;
import giacomo.u5_s1_l1.entities.Table.TableStatus;
import giacomo.u5_s1_l1.entities.Order.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@SpringBootApplication
public class PizzeriaApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PizzeriaApplication.class);

    @Autowired
    private Menu menu;

    @Autowired
    private OrderConfig orderConfig; // Il Bean che contiene il costo del coperto

    public static void main(String[] args) {
        SpringApplication.run(PizzeriaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("******************************************************************");
        logger.info("Esercizio #2: Inizializzazione Applicazione e Gestione Ordini");
        logger.info("******************************************************************");

        // 1. Inizializzazione e stampa del Menu
        menu.printMenu();
        logger.info("Menu inizializzato e stampato.");

        // 2. Creazione di un Tavolo
        Table table1 = new Table(1, 4, TableStatus.FREE);
        table1.setStatus(TableStatus.OCCUPIED);
        logger.info("Tavolo creato e occupato: {}", table1);

        // 3. Creazione di un Ordine
        // Creiamo un ordine con la prima pizza, il primo drink e un topping extra
        MenuItem margherita = menu.getPizzas().get(0);
        MenuItem lemonade = menu.getDrinks().get(0);
        MenuItem ham = menu.getToppings().get(1);

        List<MenuItem> items = List.of(margherita, lemonade, ham);

        int covers = 3;
        Order newOrder = new Order(table1, covers, items);

        // 4. Calcolo del totale
        double coverCharge = orderConfig.getCoverCharge();
        newOrder.calculateTotal(coverCharge);

        // 5. Stampa dell'ordine
        logger.info("*********************** Dettagli Ordine ***********************");
        logger.info("Ordine ID: {}", newOrder.getOrderId());
        logger.info("Tavolo: {} (Coperti: {})", newOrder.getTable().getTableNumber(), newOrder.getCovers());
        logger.info("Stato: {}", newOrder.getState());
        logger.info("Ora acquisizione: {}", newOrder.getAcquisitionTime());
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