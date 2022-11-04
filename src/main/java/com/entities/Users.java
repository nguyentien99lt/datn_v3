package com.entities;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String username;
    private String full_name;
    private String password;
    private String address;
    private String phone;
    private String email;
    private String image;
    private int status = 1;
    @Column(name = "token")
    private String token;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_key") }, inverseJoinColumns = {
            @JoinColumn(name = "role_key") })
    private Set<Roles> roles = new HashSet<>();
}
