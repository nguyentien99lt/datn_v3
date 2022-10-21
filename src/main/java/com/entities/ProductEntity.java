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
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, message = "Vui lòng nhập tối thiểu 2 kí tự")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Status cannot be null")
    @Column(name = "status")
    private Integer status = 1;

    @NotNull(message = "Category_id cannot be null")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


    @NotNull(message = "Brand_id cannot be null")
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;


}
