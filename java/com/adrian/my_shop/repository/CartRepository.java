package com.adrian.my_shop.repository;

import com.adrian.my_shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserEntityEmail(String email);
}
