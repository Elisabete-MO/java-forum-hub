package edu.forum.hub.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.forum.hub.models.entities.ProfileEntity;
import edu.forum.hub.models.entities.ReplyEntity;
import edu.forum.hub.models.entities.TopicEntity;
import edu.forum.hub.models.entities.UserEntity;
import java.util.List;

public record UserResponseDto (
        Long id,

        @JsonProperty("nome")
        String name,

        @JsonProperty("email")
        String email,

        @JsonProperty("perfis")
        List<ProfileEntity> profiles,

        @JsonProperty("respostas")
        List<ReplyEntity> replies,

        @JsonProperty("topicos")
        List<TopicEntity> topics
) {
    public UserResponseDto (UserEntity user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getProfiles(),
                user.getReplies(), user.getTopics());
    }

    public UserEntity toEntity() {
        return new UserEntity(this.id, this.name, this.email, null,
                this.profiles,
                this.replies, this.topics);
    }
}
