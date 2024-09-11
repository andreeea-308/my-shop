package com.adrian.my_shop.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String streetAndNumber;
    private String flatAndFloor;
    private String city;
    private String phoneNumber;

    @OneToOne(mappedBy = "address")
    private Orders orders;
}
