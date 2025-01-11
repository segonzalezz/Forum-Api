package com.segonzalezz.api.Forohub.domain.response.DTO;

import com.segonzalezz.api.Forohub.domain.topic.Topic;
import com.segonzalezz.api.Forohub.domain.user.User;

import java.time.LocalDateTime;

public record ResponseDataResponse(
        Long id,
        String message,
        Topic topic,
        LocalDateTime creationDate,
        User author,
        String solution
) {
}
