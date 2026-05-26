package com.example.userservice.repository;

import com.example.userservice.entity.UserClient;
import org.springframework.data.repository.ListCrudRepository;

public interface UserClientRepository extends ListCrudRepository<UserClient, Long> {
}
