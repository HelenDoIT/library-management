package com.mylibrary.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * @description: UserInfo
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 3597431641616172890L;

    private Long userId;
    private String name;
    private String password;
    private String role;

    public UserInfo(){

    }

    public UserInfo(Long userId, String name, String password, String role) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
