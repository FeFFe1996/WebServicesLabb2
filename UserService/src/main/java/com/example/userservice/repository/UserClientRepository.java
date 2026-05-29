package com.example.userservice.repository;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.UserClient;
import org.springframework.data.repository.ListCrudRepository;
import java.util.Optional;

public interface UserClientRepository extends ListCrudRepository<UserClient, Long> {
    Optional<UserDto> findByUsername(String username);
}
