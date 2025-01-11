package com.segonzalezz.api.Forohub.domain.response.DTO;

import com.segonzalezz.api.Forohub.domain.response.Response;
import com.segonzalezz.api.Forohub.domain.topic.Topic;
import com.segonzalezz.api.Forohub.domain.user.User;

import java.time.LocalDateTime;

public record ResponseDataList(Long id, String message, Topic topic, LocalDateTime creationDate, User author, String solution) {
    public ResponseDataList(Response response){
        this(response.getId(), response.getMessage(), response.getTopic(), response.getCreationDate(), response.getAuthor(), response.getSolution());
    }
}
