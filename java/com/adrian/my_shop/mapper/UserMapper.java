package com.adrian.my_shop.mapper;

import com.adrian.my_shop.dto.UserDto;
import com.adrian.my_shop.entity.Cart;
import com.adrian.my_shop.entity.UserEntity;
import com.adrian.my_shop.enums.UserRole;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapper {
    public UserEntity map(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDto.getFullName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setDateOfBirth(LocalDate.parse(userDto.getDateOfBirth()));
        userEntity.setRole(UserRole.valueOf(userDto.getRole()));
        Cart cart = new Cart();
        userEntity.setCart(cart);
        cart.setUserEntity(userEntity);
        return userEntity;
    }
}
