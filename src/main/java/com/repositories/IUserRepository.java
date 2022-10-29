package com.repositories;

import com.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "select * from users where username like concat('%', :username, '%') and status = :status and phone like concat('%', :phone, '%')", nativeQuery = true)
    Page<UserEntity> find (@Param("username") String name, Integer status, String phone, Pageable pageable);

    @Query(value = "SELECT * FROM  users u WHERE u.email = ?", nativeQuery = true)
    UserEntity findbyemail(String email);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByToken(String token);

}


