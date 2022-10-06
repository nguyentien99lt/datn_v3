package com.controllers;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.entities.UserEntity;
import com.services.iml.ImlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private ImlUserService userService;

    @PostMapping("/find-by-page")
    public ResponseEntity<FindByPageResponse<UserEntity>> findByPage(@RequestBody FindByPageRequest finByPageRequest) {

        return new ResponseEntity<>(userService.findByPage(finByPageRequest), HttpStatus.CREATED);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<UserEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return userService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    public Optional<UserEntity> readById(@PathVariable Integer id) {
        return userService.readById(id);
    }

    @PostMapping("/create")
    public UserEntity create(@RequestBody UserEntity user) {
        return userService.create(user);
    }

    @PutMapping("/update")
    public UserEntity update(@RequestBody UserEntity user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public UserEntity delete(@PathVariable Integer id) {
        return userService.delete(id);
    }

    @PostMapping("/{name}")
    public UserEntity findByName(@PathVariable String name){
        return userService.findByName(name);
    }
}
