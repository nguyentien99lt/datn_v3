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
@Table(name = "colors")

public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Color cannot be null")
    @Size(min = 2 , message = "Vui lòng nhập tối thiểu 2 kí tự")
    @Column(name = "color")
    private String color;

    @NotNull(message = "Status cannot be null")
    @Column(name = "status")
    private Integer status = 1;
}
