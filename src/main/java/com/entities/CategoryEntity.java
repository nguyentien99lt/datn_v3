package com.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
@DynamicInsert
public class CategoryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2 , max = 30, message = "Vui lòng nhập tối thiểu 2 kí tự trở lên")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Status cannot be null")
    @Column(name = "status")
    private Integer status = 1;



}
