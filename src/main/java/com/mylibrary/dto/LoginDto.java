package com.mylibrary.dto;

import java.io.Serializable;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
public class LoginDto extends BaseDto {

    private static final long serialVersionUID = -6116489112781263635L;

    private Long userId;
    private String userName;
    private String role;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
