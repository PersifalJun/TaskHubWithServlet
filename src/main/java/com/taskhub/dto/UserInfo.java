package com.taskhub.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo {
    private String userName;
    private String password;
    private String email;
}
