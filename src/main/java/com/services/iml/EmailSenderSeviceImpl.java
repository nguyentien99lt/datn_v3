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

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
    @Autowired
    private IUserRepository userRepository;

    public EmailSenderSeviceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public SendMailResponse<Boolean> resetPassword(String token,String password) {

        Optional<UserEntity> userOptional = userRepository.findByToken(token);

        if (!userOptional.isPresent()) {
//            return "Invalid token.";
            return new SendMailResponse<>("Invalid token.", false);
        }

        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();
        if (isTokenExpired(tokenCreationDate)) {
//            return "Token expired.";
            return new SendMailResponse<>("Token expired.", false);
        }


        UserEntity user = userOptional.get();
        user.setPassword(password);
        userRepository.save(user);
//        userRepository.delete(user);
//        return "Success";
        return new SendMailResponse<>("Success.", true);
    }
    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }
    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {
        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
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
        user.setTokenCreationDate(LocalDateTime.now());
        user = userRepository.save(user);

        this.mailSender.send(simpleMailMessage);

//        return user.getToken();
        return new SendMailResponse<>("Success", user.getToken());
    }
}
