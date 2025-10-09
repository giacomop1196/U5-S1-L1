package giacomo.u5_s1_l1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class OrderConfig {

    @Value("${pizzeria.cover.charge}")
    private double coverCharge;

    public double getCoverCharge() {
        return coverCharge;
    }
}