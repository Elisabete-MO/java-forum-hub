package edu.forum.hub.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.forum.hub.models.entities.CourseEntity;
import edu.forum.hub.models.entities.ReplyEntity;
import edu.forum.hub.models.entities.TopicEntity;
import edu.forum.hub.models.entities.UserEntity;
import java.time.LocalDateTime;
import java.util.List;

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
    public TopicResponseDto(TopicEntity topic) {
        this(topic.getId(), topic.getTitle(), topic.getContent(),
                new UserResponseDto(topic.getUser()),
                new CourseResponseDto(topic.getCourse()),
                topic.getCreationDate(), topic.getStatus());
    }
}
