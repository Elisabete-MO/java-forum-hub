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
        String category
) {
    public CourseResponseDto(CourseEntity course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}
