package giacomo.u5_s1_l1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Order {
    private UUID orderId = UUID.randomUUID();
    private Table table;
    private List<MenuItem> items; // Contiene Pizze, Drink, ecc.
    private OrderState state;
    private int covers; // Numero di coperti per l'ordine
    private LocalTime acquisitionTime = LocalTime.now();
    private double totalAmount;

    public enum OrderState {
        IN_PROGRESS,
        READY,
        SERVED
    }

    public Order(Table table, int covers, List<MenuItem> items) {
        this.table = table;
        this.covers = covers;
        this.items = items;
        this.state = OrderState.IN_PROGRESS;
    }

    // Metodo per calcolare il totale (sarà chiamato nel CommandLineRunner)
    public double calculateTotal(double coverCharge) {
        double itemsTotal = items.stream().mapToDouble(MenuItem::getPrice).sum();
        double coversTotal = covers * coverCharge;
        this.totalAmount = itemsTotal + coversTotal;
        return this.totalAmount;
    }
}