package edu.forum.hub.controllers.dtos;

import edu.forum.hub.models.entities.TopicEntity;
import java.time.LocalDateTime;

public record topicResponseDto(
        Long id,
        String title,
        String message,
        String author,
        String course,
        LocalDateTime creationDate,
        boolean status
) {
    public topicResponseDto(TopicEntity topicEntity) {
        this.id = topicEntity.getId();
        this.title = topicEntity.getTitle();
        this.message = topicEntity.getMessage();
        this.author = topicEntity.getAuthor();
        this.course = topicEntity.getCourse();
        this.creationDate = topicEntity.getCreationDate();
        this.status = topicEntity.getStatus();

    }
}
