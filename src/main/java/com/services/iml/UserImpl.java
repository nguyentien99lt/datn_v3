package com.services.iml;
import com.entities.Token;
import com.entities.Users;
import com.repositories.TokenRepo;
import com.repositories.UserRepo;
import com.security.UserPrincipal;
import com.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepo userRepository;

    @Override
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public UserPrincipal findByUsername(String username) {
        Users user = userRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != user) {
            Set<String> authorities = new HashSet<>();
            if (null != user.getRoles())
                user.getRoles().forEach(r -> {
                    authorities.add(r.getRole_id());
                    r.getPermissions().forEach(p -> authorities.add(p.getAuthor_id()));
                });
            userPrincipal.setUserId(user.getId());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }

    @Override
    public UserPrincipal findByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        UserPrincipal userPrincipal = new UserPrincipal();
        if (null != user) {
            Set<String> authorities = new HashSet<>();
            if (null != user.getRoles())
                user.getRoles().forEach(r -> {
                    authorities.add(r.getRole_id());
                    r.getPermissions().forEach(p -> authorities.add(p.getAuthor_id()));
                });
            userPrincipal.setUserId(user.getId());
            userPrincipal.setUsername(user.getUsername());
            userPrincipal.setPassword(user.getPassword());
            userPrincipal.setAuthorities(authorities);
        }
        return userPrincipal;
    }

}
