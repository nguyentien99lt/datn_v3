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

import com.entities.Token;
import com.entities.Users;
import com.security.JwtUtil;
import com.security.UserPrincipal;
import com.services.TokenService;
import com.services.UserService;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        UserPrincipal userPrincipal = userService.findByEmail(user.getEmail());
        if (null == user || !new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
        }
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        tokenService.createToken(token);
        return ResponseEntity.ok(token.getToken());
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('USERS_READ')")
    public ResponseEntity hello(){
        return ResponseEntity.ok("hello");
    }

    @RequestMapping("/login/form")
    public String loginForm(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập!");
        return "security/login";
    }

    @RequestMapping("/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Đăng nhập thành công!");
        return "home";
    }

    @RequestMapping("/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập!");
        return "security/login/error";
    }

    @RequestMapping("/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("message", "Không có quyền truy xuất!");
        return "security/login";
    }

    @RequestMapping("/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "Bạn đã đăng xuất!");
        return "security/login";
    }

    @CrossOrigin("*")
    @ResponseBody
    @RequestMapping("/security/authentication")
    public Object getAuthentication(HttpSession session) {
        return session.getAttribute("authentication");
    }
}
