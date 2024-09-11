package com.adrian.my_shop.repository;

import com.adrian.my_shop.entity.CartEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartEntryRepository extends JpaRepository<CartEntry, Integer> {
    List<CartEntry> findByCartUserEntityEmail(String userEmail);
}
