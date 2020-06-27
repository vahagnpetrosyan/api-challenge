package com.jazva.challenge.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * The type Api error.
 */
@Getter
@Setter
public class ApiError {
    private HttpStatus status;
    private int statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    /**
     * Instantiates a new Api error.
     *
     * @param status       the status
     * @param message      the message
     * @param debugMessage the debug message
     */
    public ApiError(HttpStatus status, String message, String debugMessage) {
        this();
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.debugMessage = debugMessage;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            return String.format("{\n\"status\": %s,\n\"statusCode\": %s,\n\"timestamp\": %s,\n\"message\": %s,\n\"debugMessage\": %s   \n}",
                    status, statusCode, timestamp, message, debugMessage);
        }
    }
}
