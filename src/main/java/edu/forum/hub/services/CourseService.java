package edu.forum.hub.services;

import edu.forum.hub.controllers.dtos.CourseResponseDto;
import edu.forum.hub.controllers.dtos.UserResponseDto;
import edu.forum.hub.exceptions.NotFoundException;
import edu.forum.hub.models.entities.CourseEntity;
import edu.forum.hub.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public void createCourse() {
        // create course
    }

    public void deleteCourse() {
        // delete course
    }

    public CourseResponseDto getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(CourseResponseDto::new)
                .orElseThrow(() -> new NotFoundException("Course not found"));
    }
}
