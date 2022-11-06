package com.entities;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(length = 1000)
    private String token;

    @Column(name = "token_exp_date")
    private Date tokenExpDate;
}
