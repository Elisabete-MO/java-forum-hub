package edu.forum.hub.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record CourseRequestDto (
        @NotNull
        @JsonAlias("nome")
        String name,


        @NotNull
        @JsonAlias("categoria")
        String category
) { }
