package com.nextline.controllers;

import com.nextline.models.UserDto;
import com.nextline.services.CrudService;
import com.nextline.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to map the http request of the user interface
 * @author Luis
 */
@RestController
@RequestMapping("/users")
public class UserController extends CrudController<UserDto, Object, UserService>{

    public UserController(CrudService<UserDto, Object> service) {
        super(service, UserController.class);
    }

    @GetMapping("/find")
    @Override
    public ResponseEntity<UserDto> findObject(int id) {
        return super.findObject(id);
    }

    @PostMapping
    @Override
    public ResponseEntity<UserDto> saveObject(UserDto requestObject) {
        return super.saveObject(requestObject);
    }

    @PutMapping
    @Override
    public ResponseEntity<UserDto> updateObject(UserDto requestObject) {
        return super.updateObject(requestObject);
    }

    @DeleteMapping
    @Override
    public ResponseEntity<?> deleteObject(int id) {
        return super.deleteObject(id);
    }

    @Override
    protected String getEntityName() {
        return "User";
    }

    @Override
    protected String getEntityPluralName() {
        return "Users";
    }
}
