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
    public UserResponseDto (UserEntity user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
