package giacomo.u5_s1_l1.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    private int tableNumber;
    private int maxCovers;
    private TableStatus status;

    public enum TableStatus {
        OCCUPIED,
        FREE
    }
}