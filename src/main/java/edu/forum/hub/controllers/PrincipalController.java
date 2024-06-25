package edu.forum.hub.controllers;

import edu.forum.hub.controllers.dtos.topicRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class PrincipalController {

    @PostMapping
    @Transactional
    public ResponseEntity createTopic(@RequestBody @Valid topicRequestDto request) {


        return ResponseEntity.ok();
    }

}
