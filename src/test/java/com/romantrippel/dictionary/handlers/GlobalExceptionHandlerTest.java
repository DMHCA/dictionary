package com.romantrippel.dictionary.handlers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    @DisplayName("handleBadRequest() returns 400 and exception message")
    void shouldHandleIllegalArgumentException() {
        var e = new IllegalArgumentException("Bad input");
        var response = handler.handleBadRequest(e);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isEqualTo("Bad input");
    }

    @Test
    @DisplayName("handleValidationException() returns 400 and error map")
    void shouldHandleValidationErrors() {
        var bindException = new BindException(new Object(), "target");
        bindException.addError(new FieldError("target", "word", "Word must not be blank"));

        var validationException = new MethodArgumentNotValidException(null, bindException);

        var response = handler.handleValidationException(validationException);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).containsEntry("word", "Word must not be blank");
    }

    @Test
    @DisplayName("handleAll() returns 500 and 'Internal error'")
    void shouldHandleGenericException() {
        var e = new RuntimeException("Oops");

        var response = handler.handleAll(e);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Internal error");
    }
}
