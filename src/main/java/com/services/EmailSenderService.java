package com.services;

public interface EmailSenderService {
    String sendEmail(String to, String subject, String message,String email);
}
