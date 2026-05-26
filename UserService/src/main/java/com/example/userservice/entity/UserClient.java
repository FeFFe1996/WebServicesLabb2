package com.example.userservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class UserClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String created;
    private String username;
    private String password;

    @PrePersist
    public void addCreated(){
        created = LocalDateTime.now().toString();
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
