package com.example.userservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_client")
public class UserClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String created;
    private String username;
    private String password;
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRole(List<String> role) {
        this.roles = role;
    }

    @PrePersist
    public void addCreated(){
        this.created = LocalDateTime.now().toString();
        if (this.roles == null)
            this.roles = new ArrayList<>();
        this.roles.add("ROLE_USER");
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
