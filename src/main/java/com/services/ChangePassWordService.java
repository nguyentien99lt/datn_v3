package com.services;

import com.clients.requests.ChangePassWord;
import com.entities.UserEntity;
import com.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePassWordService {

    @Autowired
    private IUserRepository userRepository;
    public UserEntity changePassWord(ChangePassWord changePassWord) {

        UserEntity user = userRepository.findbyemail(changePassWord.getEmail());
        System.out.println(user);
        if (user.getEmail().equals(changePassWord.getEmail()) && user.getPassword().equals(changePassWord.getOldPassword())) {

            if (changePassWord.getNewPassWord().equals(changePassWord.getConfirmPassWord())) {
                user.setPassword(changePassWord.getNewPassWord());

                return userRepository.save(user);
            }
        }

        return user;
    }

}
