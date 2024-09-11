package com.adrian.my_shop.repository;

import com.adrian.my_shop.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
