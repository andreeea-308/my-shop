package com.adrian.my_shop.repository;

import com.adrian.my_shop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
}
