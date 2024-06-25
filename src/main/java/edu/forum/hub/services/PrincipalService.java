package edu.forum.hub.services;

import edu.forum.hub.controllers.dtos.topicRequestDto;
import edu.forum.hub.controllers.dtos.topicResponseDto;
import edu.forum.hub.models.entities.TopicEntity;
//import edu.forum.hub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrincipalService {

//    @Autowired
//    private TopicRepository topicRepository;

    public topicResponseDto createTopic(topicRequestDto request) {
        // buscar usuario pelo id e curso pelo id e criar um novo tópico
        TopicEntity newTopic = new TopicEntity(request);
//        topicRepository.save(newTopic);

        return new topicResponseDto(newTopic.getId(), newTopic.getTitle(),
                newTopic.getContent(), newTopic.getUser(),
                newTopic.getCourse(), newTopic.getCreationDate(),
                newTopic.getStatus(), newTopic.getReplies());
    }
}
