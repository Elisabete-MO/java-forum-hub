package edu.forum.hub.controllers;

import edu.forum.hub.controllers.dtos.CourseRequestDto;
import edu.forum.hub.controllers.dtos.CourseResponseDto;
import edu.forum.hub.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    @Transactional
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody @Valid CourseRequestDto course, UriComponentsBuilder uriBuilder) {
        CourseResponseDto newCourse = courseService.createCourse(course);

        var uri =
                uriBuilder.path("/cursos/{id}").buildAndExpand(newCourse.id()).toUri();

        return ResponseEntity.created(uri).body(newCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
}
