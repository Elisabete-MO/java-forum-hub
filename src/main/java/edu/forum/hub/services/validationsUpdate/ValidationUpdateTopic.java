package edu.forum.hub.services.validationsUpdate;

import edu.forum.hub.controllers.dtos.TopicUpdateDto;

public interface ValidationUpdateTopic {
    void validation(long id, TopicUpdateDto request);
}
