package com.services;

import com.entities.Users;
import com.security.UserPrincipal;

public interface UserService {
    Users createUser(Users user);
    UserPrincipal findByUsername(String username);
    UserPrincipal findByEmail(String email);
}
