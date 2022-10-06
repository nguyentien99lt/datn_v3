package com.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_address")
    private String orderAddress;

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name = "received_date")
    @Temporal(TemporalType.DATE)
    private Date receivedDate;

    //    @Pattern(regexp="(09|01[2|6|8|9])+([0-9]{8})\\b", message = "Please provide a valid phone number")
    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private Integer status = 1;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;

    @Column(name = "note")
    private String note;




}
