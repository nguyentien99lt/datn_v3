package com.services.iml;

import com.dto.SendMailResponse;
import com.entities.UserEntity;
import com.repositories.IUserRepository;
import com.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class EmailSenderSeviceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;


    @Autowired
    private IUserRepository userRepository;

    public EmailSenderSeviceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public SendMailResponse<Boolean> resetPassword(String token,String password) {

        Optional<UserEntity> userOptional = userRepository.findByToken(token);

        if (!userOptional.isPresent()) {
            return new SendMailResponse<>("Invalid token.", false);
        }




        UserEntity user = userOptional.get();
        user.setPassword(password);
        userRepository.save(user);
        return new SendMailResponse<>("Success.", true);
    }
    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }



    @Override
    public SendMailResponse<String> sendEmail(String to, String subject, String message, String email) {
        String token_mail = generateToken();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("sendmaildatn@gmail.com");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("Thông tin mật khẩu");
        simpleMailMessage.setText("Mời bạn vào đường dẫn http://localhost:8080/user/reset-password?token=" + token_mail + " để đổi mật khẩu");

        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (!userOptional.isPresent()) {
            return new SendMailResponse<>("Email không tồn tại.", null);
        }

        UserEntity user = userOptional.get();
        user.setToken(token_mail);
        user = userRepository.save(user);

        this.mailSender.send(simpleMailMessage);
        return new SendMailResponse<>("Success", user.getToken());
    }
}
