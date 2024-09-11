package com.adrian.my_shop.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String price;
    private String quantity;
    private String category;
    private String description;

    @ToString.Exclude
    private String image;

}
