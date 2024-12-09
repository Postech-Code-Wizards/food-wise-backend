package br.com.foodWise.foodWise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.foodWise.foodWise")
public class FoodWiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodWiseApplication.class, args);
    }

}
