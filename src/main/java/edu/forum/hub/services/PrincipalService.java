package edu.forum.hub.services;

import edu.forum.hub.controllers.dtos.TopicRequestDto;
import edu.forum.hub.controllers.dtos.TopicResponseDto;
import edu.forum.hub.models.entities.CourseEntity;
import edu.forum.hub.models.entities.TopicEntity;
import edu.forum.hub.models.entities.UserEntity;
import edu.forum.hub.repository.TopicRepository;
import edu.forum.hub.services.validations.ValidationTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrincipalService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private List<ValidationTopic> validations;

    public TopicResponseDto createTopic(TopicRequestDto request) {
        validations.forEach(v -> v.validation(request));

        UserEntity user = userService.getUserEntityById(request.author());
        CourseEntity course =
                courseService.getCourseEntityById(request.course());
        TopicEntity newTopic = new TopicEntity(request.title(),
                request.content(), user, course);
        topicRepository.save(newTopic);

        return new TopicResponseDto(newTopic);
    }

    public Page<TopicResponseDto> getAllTopics(Pageable page) {
        return topicRepository.findAll(page)
                .map(TopicResponseDto::new);
    }
}
