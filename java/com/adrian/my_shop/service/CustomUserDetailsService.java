package com.adrian.my_shop.service;

import com.adrian.my_shop.entity.UserEntity;
import com.adrian.my_shop.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);
        if (optionalUserEntity.isEmpty()){
            throw new UsernameNotFoundException(email);
        }
        UserEntity userEntity = optionalUserEntity.get();
        return new User(userEntity.getEmail(), userEntity.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole().name())));
    }
}
