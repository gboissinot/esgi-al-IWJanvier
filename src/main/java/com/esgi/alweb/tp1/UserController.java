package com.esgi.alweb.tp1;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@SuppressWarnings("all")
public class UserController {

    @GetMapping(path = "/users", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<UsersResponse> getAll() {

//        var userList = List.of(
//                new User(1, "Gregory"),
//                new User(2, "Janie"),
//                new User(3, "Willy")
//        );
//
//        var usersResponse = new UsersResponse(userList);
//        return ResponseEntity.ok(usersResponse);
        throw new UserException("My Exception");
    }

    @GetMapping(path = "/users/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {

        System.out.println(id);

        var selectedUser = new User(1, "Gregory");
        return ResponseEntity.ok(selectedUser);
    }

    @ExceptionHandler(value = {UserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorObject handleConflict(
            UserException ex, WebRequest request) {
        return new ErrorObject(1, "My Error Message");
    }


}
