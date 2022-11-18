package com.controllers;

import com.dto.ForgotPasswordEmailDTO;
import com.dto.ForgotPasswordTokenDTO;

import com.dto.SendMailResponse;
import com.entities.UserEntity;
import com.repositories.IUserRepository;
import com.resoucre.EmailMessage;
import com.services.EmailSenderService;

import com.services.iml.ImlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.services.iml.EmailSenderSeviceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/forgot-password")
    public ResponseEntity<SendMailResponse<String>> sendEmail(@RequestBody EmailMessage emailMessage, ForgotPasswordEmailDTO req){

        SendMailResponse<String> result = emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage(),emailMessage.getEmail());
       if (result.getMessage().equalsIgnoreCase("Success")) {
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(result);
       } else {
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body(result);
       }
    }

    @PutMapping("/reset-password")
    public SendMailResponse<Boolean> resetPassword(@RequestBody ForgotPasswordTokenDTO req) {
        return  emailSenderService.resetPassword(req.getToken(), req.getPassword());
    }

}
