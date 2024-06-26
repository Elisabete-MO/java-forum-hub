package edu.forum.hub.controllers;

import edu.forum.hub.controllers.dtos.TopicRequestDto;
import edu.forum.hub.controllers.dtos.TopicResponseDto;
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

    @GetMapping
    public ResponseEntity<Page<TopicResponseDto>> getAllTopics(@PageableDefault(size=10,
        sort = {"creationDate"}) Pageable page) {
        return ResponseEntity.ok(principalService.getAllTopics(page));
    }

    @GetMapping("/{courseName}")
    public ResponseEntity<List<TopicResponseDto>> getAllTopicsByCourseName(@PathVariable String courseName) {
        return ResponseEntity.ok(principalService.getAllTopicsByCourseName(courseName));
    }

}
