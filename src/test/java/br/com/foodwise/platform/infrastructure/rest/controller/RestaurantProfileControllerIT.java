package br.com.foodwise.platform.infrastructure.rest.controller;

import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.PasswordRequest;
import br.com.foodwise.platform.infrastructure.rest.dtos.request.register.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static br.com.foodwise.platform.factory.RequestFactory.buildValidRegisterRestaurantRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Sql(scripts = {"/restaurant/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RestaurantProfileControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InJlc3RhdXJhbnRfdXNlcjA0QGNvZGV3aXphcmRzLmNvbSIsImV4cCI6MTc0MjI2MDgyMX0.5XZNhRillglVFVXmXzrXw86Sw4xii7CEyOWvBboEa8c";
    private static final String TEST_EMAIL = "restaurant_user04@codewizards.com";

    @Test
    void shouldRegisterRestaurantSuccessfully() {
        var registerRestaurantRequest = buildValidRegisterRestaurantRequest();

        given()
            .body(registerRestaurantRequest)
        .when()
            .post("/api/v1/restaurant/register")
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void shouldRetrieveMyProfileSuccessfully() {

        given()
            .header("Authorization", "Bearer " + TOKEN)
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

    @Test
    void shouldRetrieveRestaurantByEmailSuccessfully() {

        given()
            .header("Authorization", "Bearer " + TOKEN)
            .queryParam("email", TEST_EMAIL)
        .when()
            .get("/api/v1/restaurant/retrieve-login")
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

    //todo: Error 500, analisar controller
    @Test
    void shouldRetrieveRestaurantByBusinessNameSuccessfully() {

        var businessName = "Burger King";

        given()
            .header("Authorization", "Bearer " + TOKEN)
            .body("{\"businessName\":\"" + businessName + "\"}")
        .when()
            .get("/api/v1/restaurant")
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

    @Test
    void retrieveRestaurantById_ShouldReturnOkAndResponse() {

        var id = "2";

        given()
            .header("Authorization", "Bearer " + TOKEN)
        .when()
            .get("/api/v1/restaurant/{id}/availability", id)
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("$", hasKey("businessName"))
            .body("$", hasKey("deliveryOrder"))
            .body("businessName", equalTo("Burger King"))
            .body("deliveryOrder", equalTo(true));
    }

    @Test
    void updatePasswordRestaurantUserCredentialsSuccess() throws JsonProcessingException {
        var passwordRequest = new PasswordRequest("12345678", "87654321");
        var request = objectMapper.writeValueAsString(passwordRequest);

        given()
            .header("Authorization", "Bearer " + TOKEN)
            .body(request)
        .when()
            .put("/api/v1/restaurant/updatePassword")
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void updateEmailRestaurantUserSuccess() throws JsonProcessingException {

        var id = "5";
        var userRequest = new UserRequest(TEST_EMAIL, "newPassword");
        var request = objectMapper.writeValueAsString(userRequest);

        given()
            .header("Authorization", "Bearer " + TOKEN)
            .body(request)
        .when()
            .put("/api/v1/restaurant/{id}/updateEmail", id)
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
            .log().all();

    }

    @Test
    void deleteRestaurant() {
        var id = "2";

        given()
            .header("Authorization", "Bearer " + TOKEN)
        .when()
            .delete("/api/v1/restaurant/{id}", id)
        .then()
            .statusCode(HttpStatus.NO_CONTENT.value());

    }
}
