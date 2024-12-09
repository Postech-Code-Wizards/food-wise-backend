package br.com.foodWise.foodWise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FoodWiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodWiseApplication.class, args);
    }

}
