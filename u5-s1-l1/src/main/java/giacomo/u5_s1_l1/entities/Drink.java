package giacomo.u5_s1_l1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drink implements MenuItem{
    private String name;
    private double price;
    private int calories;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public int getCalories() {
        return this.calories;
    }

    @Override
    public String getMenuLine() {
        return String.format("%-20s Calories: %4d, Price: €%.2f", name, calories, price);
    }
}