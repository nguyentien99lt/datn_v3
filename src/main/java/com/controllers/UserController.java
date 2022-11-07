package com.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.repositories.ICartRepository;
import com.repositories.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.entities.CartEntity;
import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.dto.UserDTO;
import com.entities.UserEntity;
import com.services.iml.ImlUserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private ImlUserService userService;
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private ICartRepository cartRepo;

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
    public ResponseEntity<Optional<UserEntity>> readById(@PathVariable Integer id) throws Exception {

        try {
            userService.readById(id);
            return ResponseEntity.ok().body(userService.readById(id));
        }catch (Exception e){
            throw  new Exception("Bản ghi không tồn tại");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<UserEntity> create( @Valid @RequestBody UserEntity user) throws  Exception {
        try {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.create(user);
            int userId = user.getId();
            CartEntity cart = new CartEntity();
            cart.setUser(userRepo.findById(userId).get());
            cartRepo.save(cart);
            userService.create(user);
            return ResponseEntity.ok().body(user);
        }catch (Exception e){
            throw new Exception("Create Failed");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UserEntity> update( @Valid @RequestBody UserEntity user) throws Exception {
        try {
            userService.update(user);
            return ResponseEntity.ok().body(user);
        }catch (Exception e){
            throw  new Exception("Update Failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserEntity> delete(@PathVariable Integer id) throws Exception {
        try {
            userService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            throw new Exception("Delete Failed");
        }
    }

    @PostMapping("/search")
    public FindByPageResponse<UserEntity> viewUser(
            @RequestBody UserDTO dto,
            @RequestParam (name = "pageNumber", defaultValue = "0") Integer pageNumber
            ,@RequestParam (name = "pageSize", defaultValue = "2") Integer pageSize  ) {
        return	userService.listAll(dto, pageNumber, pageSize);
    }
}
