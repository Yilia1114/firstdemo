package com.example.firstdemo.dao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "account")
@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}



