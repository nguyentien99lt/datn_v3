package com.services.iml;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.dto.UserDTO;
import com.entities.UserEntity;
import com.repositories.IUserRepository;
import com.services.IService;

@Service
public class ImlUserService implements IService<UserEntity> {

    private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
    @Autowired
    private IUserRepository userRepository;


    public FindByPageResponse<UserEntity> listAll(UserDTO dto,Integer pageNumber , Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<UserEntity> page = userRepository.find(dto.getName(), dto.getStatus(), dto.getPhone(), pageable);
        List<UserEntity> list = page.getContent();
        FindByPageResponse<UserEntity> reponse = new FindByPageResponse();
        reponse.setPageResponse(list);
        reponse.setPageSize(page.getSize());
        reponse.setCurrentPage(page.getNumber());
        reponse.setTotalPage(page.getTotalPages());
        reponse.setTotalElement(page.getTotalElements());
        return reponse;
    }
    public String forgotPassword(String email) {

        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (!userOptional.isPresent()) {
            return "Invalid email id.";
        }

        UserEntity user = userOptional.get();
        user.setToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());

        user = userRepository.save(user);

        return user.getToken();
    }

    public String resetPassword(String token,String password) {

        Optional<UserEntity> userOptional = userRepository.findByToken(token);

        if (!userOptional.isPresent()) {
            return "Invalid token.";
        }
        LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();
        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";
        }

        UserEntity user = userOptional.get();
        user.setPassword(password);

        userRepository.save(user);

        return "Your password successfully updated.";
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
    public UserEntity create(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        UserEntity user = userRepository.findById(userEntity.getId()).get();
        if (user != null) {
            user.setFullName(userEntity.getFullName());
            user.setName(userEntity.getName());
            user.setImage(userEntity.getImage());
            user.setAddress(userEntity.getAddress());
            user.setEmail(userEntity.getEmail());
            user.setPassword(userEntity.getPassword());
            user.setPhone(userEntity.getPhone());
            user.setStatus(userEntity.getStatus());
            user.setRole(userEntity.getRole());
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public UserEntity delete(Integer id) {
        UserEntity color = userRepository.findById(id).get();
        if (color != null) {
            userRepository.deleteById(id);
            return color;
        } else {
            return null;
        }
    }

    @Override
    public Optional<UserEntity> readById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public FindByPageResponse<UserEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<UserEntity> pageCart = userRepository.findAll(page);
            List<UserEntity> UserEntityList = pageCart.getContent();
            FindByPageResponse<UserEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(UserEntityList);
            reponse.setPageSize(pageCart.getSize());
            reponse.setCurrentPage(pageCart.getNumber());
            reponse.setTotalPage(pageCart.getTotalPages());
            reponse.setTotalElement(pageCart.getTotalElements());
            return reponse;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public FindByPageResponse<UserEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<UserEntity> pageCart = userRepository.findAll(page);
            List<UserEntity> UserEntityList = pageCart.getContent();
            FindByPageResponse<UserEntity> reponse = new FindByPageResponse();
            reponse.setPageResponse(UserEntityList);
            reponse.setPageSize(pageCart.getSize());
            reponse.setCurrentPage(pageCart.getNumber());
            reponse.setTotalPage(pageCart.getTotalPages());
            reponse.setTotalElement(pageCart.getTotalElements());
            return reponse;
        } catch (Exception e) {
            return null;
        }
    }


}
