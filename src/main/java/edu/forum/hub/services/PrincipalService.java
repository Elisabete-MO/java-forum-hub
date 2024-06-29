package edu.forum.hub.services;

import edu.forum.hub.controllers.dtos.TopicRequestDto;
import edu.forum.hub.controllers.dtos.TopicResponseDto;
import edu.forum.hub.controllers.dtos.TopicUpdateDto;
import edu.forum.hub.exceptions.NotFoundException;
import edu.forum.hub.models.entities.CourseEntity;
import edu.forum.hub.models.entities.TopicEntity;
import edu.forum.hub.models.entities.UserEntity;
import edu.forum.hub.repository.TopicRepository;
import edu.forum.hub.services.validationsCreate.ValidationCreateTopic;
import edu.forum.hub.services.validationsUpdate.ValidationUpdateTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrincipalService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private List<ValidationCreateTopic> validationsCreate;

    @Autowired
    private List<ValidationUpdateTopic> validationsUpdate;

    public TopicResponseDto createTopic(TopicRequestDto request) {
        validationsCreate.forEach(v -> v.validation(request));

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

    public List<TopicResponseDto> getAllTopicsByCourseNameAndYear(String courseName, String year) {
        int yearInt = Integer.parseInt(year);
        return topicRepository.findByCourseNameAndYear(courseName, yearInt)
                .stream()
                .map(TopicResponseDto::new)
                .collect(Collectors.toList());
    }

    public TopicResponseDto getTopicById(Long id) {
        return topicRepository.findById(id)
                .map(TopicResponseDto::new)
                .orElseThrow(() -> new NotFoundException("Topic " + id +
                        " not found"));
    }

    public TopicEntity getTopicEntityById(Long id) {
        return  topicRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Topic " + id +
                        " not found"));
    }

    public TopicResponseDto updateTopic(long id, TopicUpdateDto request) {
        for (ValidationUpdateTopic v : validationsUpdate) {
            v.validation(id, request);
        }

        TopicEntity topic = getTopicEntityById(id);

        topic.setStatus(Boolean.valueOf("true"));
        topicRepository.save(topic);

        return new TopicResponseDto(topic);
    }

    public void deleteTopic(long id) {
        TopicEntity topic = getTopicEntityById(id);
        topicRepository.deleteById(topic.getId());
    }
}
