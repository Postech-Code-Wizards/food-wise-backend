package br.com.foodwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.foodwise")
public class FoodWiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodWiseApplication.class, args);
    }

}
