package com.romantrippel.dictionary.handlers;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    @DisplayName("handleNotFound() returns 404 and message from exception")
    void shouldHandleEntityNotFoundException() {
        var exception = new EntityNotFoundException("Word not found");

        var response = handler.handleNotFound(exception);

        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody()).isEqualTo("Word not found");
    }
}
