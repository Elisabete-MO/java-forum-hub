package edu.forum.hub.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.forum.hub.models.entities.TopicEntity;

import java.time.LocalDateTime;

public record TopicResponseDto(
        Long id,

        @JsonProperty("titulo")
        String title,

        @JsonProperty("mensagem")
        String content,

        @JsonProperty("autor")
        UserResponseDto user,

        @JsonProperty("curso")
        CourseResponseDto course,

        @JsonProperty("dataCriacao")
        LocalDateTime creationDate,

        boolean status
) {
    public TopicResponseDto(TopicEntity newTopic) {
        this(newTopic.getId(), newTopic.getTitle(), newTopic.getContent(),
                new UserResponseDto(newTopic.getUser()),
                new CourseResponseDto(newTopic.getCourse()),
                newTopic.getCreationDate(), newTopic.getStatus());
    }
}