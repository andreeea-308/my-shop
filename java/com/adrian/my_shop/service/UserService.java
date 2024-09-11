package com.adrian.my_shop.service;

import com.adrian.my_shop.dto.UserDto;
import com.adrian.my_shop.entity.UserEntity;
import com.adrian.my_shop.mapper.UserMapper;
import com.adrian.my_shop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(UserDto userDto){
        UserEntity userEntity = userMapper.map(userDto);
        encodePassword(userEntity);
        userRepo.save(userEntity);
    }

    private void encodePassword(UserEntity userEntity) {
        String passwordEncoded = bCryptPasswordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(passwordEncoded);
    }
}
