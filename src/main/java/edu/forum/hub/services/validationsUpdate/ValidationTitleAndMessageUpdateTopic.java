package edu.forum.hub.services.validationsUpdate;

import edu.forum.hub.controllers.dtos.TopicUpdateDto;
import edu.forum.hub.exceptions.ValidationException;
import edu.forum.hub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationTitleAndMessageUpdateTopic implements ValidationUpdateTopic {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void  validation(long id, TopicUpdateDto request) {
        boolean titleExists = topicRepository.existsByTitle(request.title());
        boolean messageExists =
                topicRepository.existsByContent(request.content());
        if (titleExists && messageExists) {
            throw new ValidationException("This topic '" + request.title() +
                    "' and message '" + request.content() +
                    "' already exists in the database.");
        }
    }
}
