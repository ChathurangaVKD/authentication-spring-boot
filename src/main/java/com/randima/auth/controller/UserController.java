package com.randima.auth.controller;

import com.randima.auth.model.UserEntity;
import com.randima.auth.service.JwtUserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private JwtUserDetailsService jwtUserDetailsService;

    public UserController(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @PostMapping(path = "/user/create")
    public UserEntity create(@RequestBody UserEntity userEntity){
        return jwtUserDetailsService.createUsers(userEntity);
    }

    @GetMapping(path = "/user/get")
    public List<UserEntity> getAll(){
        return jwtUserDetailsService.getAll();
    }

}
