package com.services.iml;

import java.util.*;

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
