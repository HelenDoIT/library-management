package com.mylibrary.service;

import com.mylibrary.dto.LoginDto;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
public interface IUserInfoService {

    public LoginDto login(String username,String password);

    public LoginDto register(String username,String password,String role);

}
