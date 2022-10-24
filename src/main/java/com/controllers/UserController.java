package com.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.dto.UserDTO;
import com.entities.UserEntity;
import com.services.iml.ImlUserService;

@RestController
@RequestMapping("/admin/user")
@CrossOrigin
public class UserController {

    @Autowired
    private ImlUserService userService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<UserEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return userService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<UserEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return userService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/read-by-id/{id}")
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

    @PostMapping("/search")
    public FindByPageResponse<UserEntity> viewUser(
            @RequestBody UserDTO dto,
            @RequestParam (name = "pageNumber", defaultValue = "0") Integer pageNumber
            ,@RequestParam (name = "pageSize", defaultValue = "2") Integer pageSize  ) {
        return	userService.listAll(dto, pageNumber, pageSize);
    }
}
