package giacomo.u5_s1_l1;

import giacomo.u5_s1_l1.entities.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PizzeriaApplication {

    public static void main(String[] args) {
        // Avvia l'applicazione Spring e ottieni il contesto
        ApplicationContext ctx = SpringApplication.run(PizzeriaApplication.class, args);

        System.out.println("\n*** Pizzeria Project Initialized ***");

        // Richiama il Bean di tipo Menu
        Menu menu = ctx.getBean(Menu.class);

        // Stampa il suo contenuto come richiesto dal punto 1.3
        menu.printMenu();
    }

}