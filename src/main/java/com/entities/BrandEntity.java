package com.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "brands")
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, message = "Vui lòng nhập tối thiêu 2 kí tự")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Address cannot be null")
    @Size(min = 5, message = "Vui lòng nhập tối thiểu 5 kí tự")
    @Column(name = "address")
    private String address;

    @NotNull(message = "Status cannot be null")
    @Column(name = "status")
    private Integer status = 1;
}
