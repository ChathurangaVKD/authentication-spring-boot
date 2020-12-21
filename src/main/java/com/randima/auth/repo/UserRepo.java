package com.randima.auth.repo;

import com.randima.auth.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    List<UserEntity> getUsersByUserName(String userName);
}
