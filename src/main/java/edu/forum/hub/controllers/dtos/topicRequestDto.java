package edu.forum.hub.controllers.dtos;

import jakarta.validation.constraints.NotNull;

public record topicRequestDto(
        @NotNull
        String title,

        @NotNull
        String message,

        @NotNull
        String author,

        @NotNull
        String course
) {}
