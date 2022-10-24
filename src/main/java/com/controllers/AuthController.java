package com.controllers;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.extern.slf4j.Slf4j;

import com.entities.Token;
import com.entities.Users;
import com.security.JwtUtil;
import com.security.UserPrincipal;
import com.services.TokenService;
import com.services.UserService;

@Slf4j
@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Users create(@RequestBody Users user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
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

    @RequestMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập!");
        return "Vui lòng đăng nhập!";
    }

    @RequestMapping("/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Đăng nhập thành công!");
        return "Đăng nhập thành công!";
    }

    @RequestMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập!");
        return "Sai thông tin đăng nhập!";
    }

    @RequestMapping("/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("message", "Không có quyền truy xuất!");
        return "Không có quyền truy cập!";
    }

    @RequestMapping("/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "Bạn đã đăng xuất!");
        return "Bạn đã đăng xuất!";
    }

    @CrossOrigin("*")
    @ResponseBody
    @RequestMapping("/security/authentication")
    public Object getAuthentication(HttpSession session) {
        return session.getAttribute("authentication");
    }
}
