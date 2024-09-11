package com.adrian.my_shop.dto;

import lombok.Data;

@Data
public class CartEntryDto {
    private String productName;
    private String pricePerItem;
    private String quantity;
    private String pricePerSelection;
}
