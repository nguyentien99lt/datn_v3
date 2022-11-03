package com.controllers;

import com.clients.requests.ChangePassWord;
import com.entities.UserEntity;
import com.services.ChangePassWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangePassWordController {
@Autowired
    private ChangePassWordService changePassWordService;

    @PutMapping("/changepassword")
    public ResponseEntity<UserEntity> forgotPassWord(@RequestBody ChangePassWord changePassWord)throws Exception {
        try {
            changePassWordService.changePassWord(changePassWord);
            return ResponseEntity.ok().body(changePassWordService.changePassWord(changePassWord));
        }catch (Exception e){
            throw new Exception("Đổi mật khẩu thất bại");
        }
    }
}
