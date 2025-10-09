package giacomo.u5_s1_l1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "pizzeria_order")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;

    @Transient // La lista di MenuItem non viene persistita (troppo complessa per questo esercizio)
    private List<MenuItem> items;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    private int covers;
    private LocalTime acquisitionTime = LocalTime.now();
    private double totalAmount;

    public enum OrderState {
        IN_PROGRESS,
        READY,
        SERVED
    }

    // Costruttore per la logica di business
    public Order(Table table, int covers, List<MenuItem> items) {
        this.table = table;
        this.covers = covers;
        this.items = items;
        this.state = OrderState.IN_PROGRESS;
    }

    // Logica di business per il calcolo
    public double calculateTotal(double coverCharge) {
        double itemsTotal = items.stream().mapToDouble(MenuItem::getPrice).sum();
        double coversTotal = covers * coverCharge;
        this.totalAmount = itemsTotal + coversTotal;
        return this.totalAmount;
    }
}