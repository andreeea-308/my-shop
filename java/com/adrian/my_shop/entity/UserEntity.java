package com.adrian.my_shop.entity;

import com.adrian.my_shop.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "users")
@Data
@RequiredArgsConstructor
@NoArgsConstructor

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private LocalDate dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Cart cart;

    @OneToMany(mappedBy = "userEntity")
    private List<Orders> ordersList;
}
