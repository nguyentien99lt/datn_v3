package com.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, max = 30 ,message = "Vui lòng nhập tối thiểu 2 kí tự trở lên")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "FullName cannot be null")
    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 8, message = "Vui lòng nhập tối thiểu 8 kí tự")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "Address cannot be null")
    @Size(min = 5, message = "Vui lòng nhập tối thiểu 5 kí tự")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "PhoneNumber cannot be null")
    @Size(min = 9, message = "Vui lòng nhấp tối thiểu 9 kí tự")
    @Pattern(regexp = "^09\\d{8}$", message = "Sai định dạng số điện thoại")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Email cannot be null")
    @Email(message = "Email sai định dạng")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Image cannot be null")
    @Column(name = "image")
    private String image;

    @NotNull(message = "Status cannot be null")
    @Column(name = "status")
    private Integer status = 1;

    @NotNull(message = "role_id cannot be null")
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;


}
