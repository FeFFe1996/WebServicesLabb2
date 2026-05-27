package com.example.messageservice.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class MessageTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;
    private String topicName;
    private String createdAt;
    private String createdBy;
    private String description;

    @OneToMany(mappedBy = "topicId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Messages> messagesList;

    @PrePersist
    public void createdAt(){
        this.createdAt = LocalDateTime.now().toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<Messages> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<Messages> messagesList) {
        this.messagesList = messagesList;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Messages> getMesssagesList() {
        return messagesList;
    }

    public void setMesssagesList(List<Messages> messagesList) {
        this.messagesList = messagesList;
    }
}
