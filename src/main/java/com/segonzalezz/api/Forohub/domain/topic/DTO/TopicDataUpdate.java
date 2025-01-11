package com.segonzalezz.api.Forohub.domain.topic.DTO;

import com.segonzalezz.api.Forohub.domain.response.Response;
import com.segonzalezz.api.Forohub.domain.topic.Course;
import com.segonzalezz.api.Forohub.domain.user.User;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public record TopicDataUpdate(
        @NotBlank
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        User author,
        Course course,
        List<Response> responses
) {
}
