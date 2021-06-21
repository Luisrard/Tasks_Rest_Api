package com.nextline;

import com.nextline.entities.User;
import com.nextline.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller to map the http request of the user interface
 * @author Luis
 */
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UsersRepository usersRepository;
    @GetMapping
    public ResponseEntity<List<User>> getObjects(){
        return ResponseEntity.ok(usersRepository.findAll());
    }
}
