package edu.forum.hub.controllers;

import edu.forum.hub.controllers.dtos.UserRequestDto;
import edu.forum.hub.controllers.dtos.UserResponseDto;
import edu.forum.hub.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto user, UriComponentsBuilder uriBuilder) {
        UserResponseDto newUser = userService.createUser(user);

        var uri =
                uriBuilder.path("/usuarios/{id}").buildAndExpand(newUser.id()).toUri();

        return ResponseEntity.created(uri).body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
