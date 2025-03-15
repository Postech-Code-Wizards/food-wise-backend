package br.com.foodwise.platform.infrastructure.rest.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = {"/restaurant/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RestaurantProfileControllerIT {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InJlc3RhdXJhbnRfdXNlcjAyQGNvZGV3aXphcmRzLmNvbSIsImV4cCI6MTc0MjA2ODUzNn0.Wi3cISjL4TXzHRUZj4xDBBorFMFPX1FiEA8_axbKqhk";

    @Test
    void shouldRetrieveMyProfileSuccessfully() {

        given()
            .header("Authorization", "Bearer " + token)
        .when()
            .get("/api/v1/restaurant/my-profile")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasKey("businessName"))
            .body("$", hasKey("description"))
            .body("$", hasKey("businessHours"))
            .body("$", hasKey("deliveryRadius"))
            .body("businessName", equalTo("Burger King"))
            .body("description", equalTo("Description"))
            .body("businessHours", equalTo("09:00-18:00"));
    }
}
