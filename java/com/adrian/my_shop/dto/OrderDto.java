package com.adrian.my_shop.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String streetAndNumber;
    private String flatAndFloor;
    private String city;
    private String phoneNumber;
}
