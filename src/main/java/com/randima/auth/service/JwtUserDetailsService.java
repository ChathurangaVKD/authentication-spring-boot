package com.randima.auth.service;

import java.util.ArrayList;
import java.util.List;

import com.randima.auth.model.UserEntity;
import com.randima.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder bcryptEncoder;
    private UserRepo userRepo;

    public JwtUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserEntity> userEntityList = userRepo.getUsersByUserName(username);
        UserEntity userEntity = userEntityList.get(0);
        return new org.springframework.security.core.userdetails.User(userEntity.getUserName(), userEntity.getPassword(),
                new ArrayList<>());
    }

    public UserEntity createUsers(UserEntity userEntity) {
        this.persist(userEntity);
        return userRepo.save(userEntity);
    }

    private UserEntity persist(UserEntity userEntity) {
        if (userEntity.getPassword() != null) {
            userEntity.setPassword(bcryptEncoder.encode(userEntity.getPassword()));
        }
        return userEntity;
    }

    public List<UserEntity> getAll(){
        return userRepo.findAll();
    }

}

