package com.caliberly.chat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "my_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<ChatMessage> messages;

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

}
