package edu.forum.hub.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record TopicRequestDto(
        @NotNull
        @JsonAlias("titulo")
        String title,

        @NotNull
        @JsonAlias("mensagem")
        String content,

        @NotNull
        @JsonAlias("autor")
        Long author,

        @NotNull
        @JsonAlias("curso")
        Long course
) { }
