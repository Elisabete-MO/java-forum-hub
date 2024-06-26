package edu.forum.hub.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.forum.hub.models.entities.CourseEntity;
import edu.forum.hub.models.entities.TopicEntity;

import java.util.List;

public record CourseResponseDto(
        Long id,

        @JsonProperty("nome")
        String name,

        @JsonProperty("categoria")
        String category,

        @JsonProperty("descricao")
        List<TopicEntity> topics
) {
    public CourseResponseDto(CourseEntity course) {
        this(course.getId(), course.getName(), course.getCategory(), course.getTopics());
    }

    public CourseEntity toEntity() {
        return new CourseEntity(this.id, this.name, this.category, this.topics);
    }
}
