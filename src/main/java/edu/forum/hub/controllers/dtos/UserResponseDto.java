package edu.forum.hub.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.forum.hub.models.entities.UserEntity;

public record UserResponseDto (
        Long id,

        @JsonProperty("nome")
        String name,

        @JsonProperty("email")
        String email
) {

    public UserResponseDto(UserEntity newUser) {
        this(newUser.getId(), newUser.getName(), newUser.getEmail());
    }
}
