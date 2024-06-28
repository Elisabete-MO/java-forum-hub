package edu.forum.hub.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record UserRequestDto(
        @NotNull
        @JsonAlias("nome")
        String name,

        @NotNull
        @JsonAlias("email")
        String email,

        @NotNull
        @JsonAlias("senha")
        String password
) {
}
