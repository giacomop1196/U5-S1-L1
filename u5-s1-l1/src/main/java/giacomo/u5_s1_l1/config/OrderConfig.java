package giacomo.u5_s1_l1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {

    // Inietta il valore dal file application.properties
    @Value("${pizzeria.cover.charge}")
    private double coverCharge;

    public double getCoverCharge() {
        return coverCharge;
    }
}