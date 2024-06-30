package edu.forum.hub.controllers;

import edu.forum.hub.controllers.dtos.AuthenticationData;
import edu.forum.hub.models.entities.UserEntity;
import edu.forum.hub.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid AuthenticationData data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(data.email(),
                        data.password());
        Authentication authentication = manager.authenticate(authenticationToken);

        String tokenJWT =
                tokenService.generateToken((UserEntity) authentication.getPrincipal());

        return ResponseEntity.ok().body(new TokenResponse(tokenJWT));
    }

    private record TokenResponse(String token) {}
}

