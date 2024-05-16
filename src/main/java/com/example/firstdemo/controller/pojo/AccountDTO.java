package com.example.firstdemo.controller.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {
    private Long id;
    private String username;
    private String password;
}
