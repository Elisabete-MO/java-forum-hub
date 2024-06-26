package edu.forum.hub.services.validations;

import edu.forum.hub.controllers.dtos.TopicRequestDto;
import edu.forum.hub.exceptions.ValidationException;
import edu.forum.hub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationDuplicateDataTopic implements ValidationTopic {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void validation(TopicRequestDto request) {
        boolean titleExists = topicRepository.existsByTitle(request.title());
        boolean messageExists =
                topicRepository.existsByContent(request.content());
        if (titleExists && messageExists) {
            throw new ValidationException("This topic already exists in the " +
                    "database.");
        }
    }
}
