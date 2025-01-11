package com.segonzalezz.api.Forohub.domain.response.DTO;

import com.segonzalezz.api.Forohub.domain.topic.Topic;
import com.segonzalezz.api.Forohub.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ResponseRegistrationData (
        @NotBlank(message = "The response message is required.")
        String message,

        @NotNull(message = "The topic is required.")
        Topic topic,

        @NotNull(message = "The creation date is required.")
        LocalDateTime creationDate,

        @NotNull(message = "The author is required.")
        User author,

        @NotBlank(message = "The solution status is required.")
        String solution
){
}
