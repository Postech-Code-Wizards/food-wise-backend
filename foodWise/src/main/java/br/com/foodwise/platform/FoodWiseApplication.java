package br.com.foodwise.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.foodwise.platform")
public class FoodwiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodwiseApplication.class, args);
    }

}
