package edu.forum.hub.services;

import edu.forum.hub.controllers.dtos.topicRequestDto;
import edu.forum.hub.controllers.dtos.topicResponseDto;
import edu.forum.hub.models.entities.TopicEntity;
import edu.forum.hub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class PrincipalService {

    @Autowired
    private TopicRepository topicRepository;

    public topicResponseDto createTopic(topicRequestDto request, UriComponentsBuilder uriBuilder) {
        TopicEntity newTopic = new TopicEntity(request);
        topicRepository.save(newTopic);

        URI uri =
                uriBuilder.path("/topicos/{id}").buildAndExpand(newTopic.getId()).toUri();

        return new topicResponseDto(newTopic);
    }
}
