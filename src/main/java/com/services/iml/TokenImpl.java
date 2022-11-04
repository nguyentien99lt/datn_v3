package com.services.iml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Token;
import com.repositories.TokenRepo;
import com.services.TokenService;

@Service
public class TokenImpl implements TokenService {
    @Autowired
    private TokenRepo tokenRepository;

    @Override
    public Token createToken(Token token) {
        return tokenRepository.saveAndFlush(token);
    }

    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
