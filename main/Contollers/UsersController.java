package com.example.demo.presentation;

import java.util.Optional;

import com.example.demo.CreateUserDto;
import com.example.demo.UserDto;
import com.example.demo.persistence.User;
import com.example.demo.persistence.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersRepository usersRepository;

    public UsersController(final UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody CreateUserDto dto) {

        final User user = new User();
        user.setUserName(dto.getUserName());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        final User savedUser = usersRepository.save(user);

        final UserDto response = new UserDto();
        response.setId(savedUser.getId());
        response.setUserName(savedUser.getUserName());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    //127.0.0.1:8080/objectives/1
    public ResponseEntity<UserDto> getObjectiveById(@PathVariable("userId") Long id) {

        final Optional<User> userOptional = usersRepository.findById(id);

        if (userOptional.isEmpty()) {
            final UserDto userDto = new UserDto();
            userDto.setError("User with the given id (" + id + ") does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userDto);
        }

        final User user = userOptional.get();

        final UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        return ResponseEntity.ok(userDto);
    }

}
