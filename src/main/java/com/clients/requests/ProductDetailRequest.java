package com.clients.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailRequest {
    private String name;
    private Integer status;
    private String color;
    private Float price;
    private String brandName;
    private Integer size;
}
