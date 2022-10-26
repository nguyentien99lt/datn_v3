package com.controllers;

import com.dto.ForgotPasswordEmailDTO;
import com.dto.ForgotPasswordTokenDTO;

import com.entities.UserEntity;
import com.repositories.IUserRepository;
import com.resoucre.EmailMessage;
import com.services.EmailSenderService;

import com.services.iml.ImlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.services.iml.EmailSenderSeviceImpl;
import org.springframework.web.bind.annotation.*;




@RestController
@CrossOrigin
public class EmailController {

    private final EmailSenderService emailSenderService;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private EmailSenderSeviceImpl emailSenderSevice;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }


    @PostMapping("/forgot-password")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage, ForgotPasswordEmailDTO req){

        this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage(),emailMessage.getEmail());
        return ResponseEntity.ok("Success");
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestBody ForgotPasswordTokenDTO req) {
        return  emailSenderSevice.resetPassword(req.getToken(), req.getPassword());
    }

}
