package edu.forum.hub.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.forum.hub.models.entities.CourseEntity;
import edu.forum.hub.models.entities.ReplyEntity;
import edu.forum.hub.models.entities.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public record topicResponseDto(
        Long id,

        @JsonProperty("titulo")
        String title,

        @JsonProperty("mensagem")
        String content,

        @JsonProperty("autor")
        UserEntity user,

        @JsonProperty("curso")
        CourseEntity course,

        @JsonProperty("dataCriacao")
        LocalDateTime creationDate,

        boolean status,

        @JsonProperty("respostas")
        List<ReplyEntity> replies
) {}
