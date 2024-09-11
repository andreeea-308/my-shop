package com.adrian.my_shop.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "cart")
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "cart")
    private List<CartEntry> cartEntryList;

    @OneToOne
    private UserEntity userEntity;

}
