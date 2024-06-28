package edu.forum.hub.controllers;

import edu.forum.hub.controllers.dtos.TopicRequestDto;
import edu.forum.hub.controllers.dtos.TopicResponseDto;
import edu.forum.hub.controllers.dtos.TopicUpdateDto;
import edu.forum.hub.services.PrincipalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class PrincipalController {

    @Autowired
    private PrincipalService principalService;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponseDto> createTopic(@RequestBody @Valid TopicRequestDto request, UriComponentsBuilder uriBuilder) {
        TopicResponseDto newTopic = principalService.createTopic(request);

        var uri =
                uriBuilder.path("/medicos/{id}").buildAndExpand(newTopic.id()).toUri();

        return ResponseEntity.created(uri).body(newTopic);
    }

    @GetMapping()
    public ResponseEntity<Page<TopicResponseDto>> getAllTopics(@PageableDefault(size=10,
        sort = {"creationDate"}) Pageable page) {
        return ResponseEntity.ok(principalService.getAllTopics(page));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TopicResponseDto>> getAllTopicsByCourseName(
            @RequestParam("curso") String courseName,
            @RequestParam("ano") String year) {
        return ResponseEntity.ok(principalService.getAllTopicsByCourseNameAndYear(courseName, year));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDto> getTopicById(@PathVariable long id) {
        return ResponseEntity.ok(principalService.getTopicById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicResponseDto> updateTopic (
            @PathVariable long id, @RequestBody @Valid TopicUpdateDto request) {
        return ResponseEntity.ok(principalService.updateTopic(id, request));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteTopic (@PathVariable long id) {
        principalService.deleteTopic(id);
        return ResponseEntity.ok().build();
    }
}
