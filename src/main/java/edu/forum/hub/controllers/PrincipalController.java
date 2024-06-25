package edu.forum.hub.controllers;

import edu.forum.hub.controllers.dtos.topicRequestDto;
import edu.forum.hub.controllers.dtos.topicResponseDto;
import edu.forum.hub.services.PrincipalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class PrincipalController {

    @Autowired
    private PrincipalService principalService;

    @PostMapping
    @Transactional
    public ResponseEntity<topicResponseDto> createTopic(@RequestBody @Valid topicRequestDto request, UriComponentsBuilder uriBuilder) {
        topicResponseDto newTopic = principalService.createTopic(request);

        var uri =
                uriBuilder.path("/medicos/{id}").buildAndExpand(newTopic.id()).toUri();

        return ResponseEntity.created(uri).body(newTopic);
    }

}
