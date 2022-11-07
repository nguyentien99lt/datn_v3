package com.controllers;

import com.entities.CartEntity;
import com.repositories.ICartRepository;
import com.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Token;
import com.entities.Users;
import com.security.JwtUtil;
import com.security.UserPrincipal;
import com.services.TokenService;
import com.services.UserService;
@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private IUserRepository userRepo;
    @Autowired
    private ICartRepository cartRepo;

    @PostMapping("/register")
    public Users create(@RequestBody Users user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.createUser(user);
        int userId = user.getId();
        CartEntity cart = new CartEntity();
        cart.setUser(userRepo.findById(userId).get());
        cartRepo.save(cart);
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());
        if (null == user || !new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tài khoản hoặc mật khẩu không chính xác");
        }
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        tokenService.createToken(token);
        return ResponseEntity.ok(token.getToken());
    }
}
