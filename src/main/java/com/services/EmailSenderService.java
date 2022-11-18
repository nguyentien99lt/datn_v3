package com.services;

import com.dto.SendMailResponse;

public interface EmailSenderService {
    SendMailResponse<String> sendEmail(String to, String subject, String message, String email);

    SendMailResponse<Boolean> resetPassword(String token,String password);
}
