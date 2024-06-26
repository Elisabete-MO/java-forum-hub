package edu.forum.hub.services;

import edu.forum.hub.controllers.dtos.CourseResponseDto;
import edu.forum.hub.controllers.dtos.TopicRequestDto;
import edu.forum.hub.controllers.dtos.TopicResponseDto;
import edu.forum.hub.controllers.dtos.UserResponseDto;
import edu.forum.hub.models.entities.CourseEntity;
import edu.forum.hub.models.entities.TopicEntity;
import edu.forum.hub.models.entities.UserEntity;
import edu.forum.hub.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrincipalService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    public TopicResponseDto createTopic(TopicRequestDto request) {
        UserEntity user = userService.getUserEntityById(request.author());
        CourseEntity course =
                courseService.getCourseEntityById(request.course());
        TopicEntity newTopic = new TopicEntity(request.title(),
                request.content(), user, course);
        topicRepository.save(newTopic);

        return new TopicResponseDto(newTopic);
    }
}
