package com.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_details")
public class ProductDetailEntity {
// TODO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Product_id cannot be null")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @NotNull(message = "Color_id cannot be null")
    @ManyToOne
    @JoinColumn(name = "color_id")
    private ColorEntity color;

    @NotNull(message = "Size_id cannot be null")
    @ManyToOne
    @JoinColumn(name = "size_id")
    private SizeEntity size;

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, message = "Vui lòng nhập tối thiểu 2 kí tự")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Price cannot be null")
    @Column(name = "price")
    private Float price;

    @NotNull(message = "Quantity cannot be null")
    @Column(name = "quantity")
    private Integer quantity;

    @NotBlank(message = "Image cannot be null")
    @Column(name = "image")
    private String image;


    @NotNull(message = "Create_date cannot be null")
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @NotBlank(message = "Description cannot be null")
    @Size(min = 2, message = "Vui lòng nhập tối thiểu 2 kí tự trở lên")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Status cannot be null")
    @Column(name = "status")
    private Integer status = 1;




}
