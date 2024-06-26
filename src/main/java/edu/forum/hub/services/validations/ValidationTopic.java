package edu.forum.hub.services.validations;

import edu.forum.hub.controllers.dtos.TopicRequestDto;

public interface ValidationTopic {
    void validation(TopicRequestDto request);
}
