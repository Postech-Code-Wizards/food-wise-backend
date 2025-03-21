package br.com.foodwise.platform.application.usecase.token;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpirationDateUseCaseTest {

    @InjectMocks
    private ExpirationDateUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return instant two hours in the future")
    void execute_shouldReturnInstantTwoHoursInTheFuture() {

        LocalDateTime now = LocalDateTime.now();
        Instant expectedInstant = now.toInstant(ZoneOffset.of("-03:00"));

        Instant actualInstant = useCase.execute();

        assertNotNull(actualInstant);
        assertTrue(actualInstant.compareTo(expectedInstant) >= 0);
    }
}