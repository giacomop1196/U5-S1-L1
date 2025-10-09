package giacomo.u5_s1_l1.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tableNumber;
    private int maxCovers;

    @Enumerated(EnumType.STRING)
    private TableStatus status;

    public enum TableStatus {
        OCCUPIED,
        FREE
    }

    // Costruttore per la logica di business
    public Table(int tableNumber, int maxCovers, TableStatus status) {
        this.tableNumber = tableNumber;
        this.maxCovers = maxCovers;
        this.status = status;
    }
}