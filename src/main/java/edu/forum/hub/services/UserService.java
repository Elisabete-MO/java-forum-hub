package edu.forum.hub.services;

import edu.forum.hub.controllers.dtos.UserRequestDto;
import edu.forum.hub.controllers.dtos.UserResponseDto;
import edu.forum.hub.exceptions.NotFoundException;
import edu.forum.hub.models.entities.UserEntity;
import edu.forum.hub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto user) {
        UserEntity newUser = new UserEntity(user);
        userRepository.save(newUser);
        return new UserResponseDto(newUser);
    }

    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDto::new)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public UserEntity getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
