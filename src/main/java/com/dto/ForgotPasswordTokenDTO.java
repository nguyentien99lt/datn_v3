package com.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordTokenDTO {
    @Column(name = "token")
    private String token;
    @Column(name = "password")
    private String password;
}
