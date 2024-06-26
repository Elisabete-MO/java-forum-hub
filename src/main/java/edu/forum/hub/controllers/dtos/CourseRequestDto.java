package edu.forum.hub.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import edu.forum.hub.models.entities.CourseEntity;
import jakarta.validation.constraints.NotNull;

public record CourseRequestDto (
        @NotNull
        @JsonAlias("nome")
        String name,

        @NotNull
        @JsonAlias("categoria")
        String category
) { }
