package com.mylibrary.service.impl;

import com.mylibrary.dao.IUserInfoDao;
import com.mylibrary.dao.impl.UserInfoDaoImpl;
import com.mylibrary.domain.UserInfo;
import com.mylibrary.dto.LoginDto;
import com.mylibrary.service.IUserInfoService;

import java.util.Objects;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
public class UserInfoServiceImpl implements IUserInfoService {

    private IUserInfoDao userInfoDao = new UserInfoDaoImpl();

    @Override
    public LoginDto login(String username, String password) {
        LoginDto loginDto = new LoginDto();
        UserInfo userInfo = userInfoDao.queryByName(username);
        if(Objects.nonNull(userInfo)){
            if(password.equals(userInfo.getPassword())){
                loginDto.setCode(0);
                loginDto.setUserName(userInfo.getName());
                loginDto.setRole(userInfo.getRole());
                loginDto.setErrorMsg("login success");
            }else{
                loginDto.setCode(-2);
                loginDto.setErrorMsg("password error");
            }
        }else {
            loginDto.setCode(-1);
            loginDto.setErrorMsg("user not exist");
        }
        return loginDto;
    }

    @Override
    public LoginDto register(String username, String password, String role) {
        LoginDto loginDto = new LoginDto();
        UserInfo existInfo = userInfoDao.queryByName(username);
        if(Objects.nonNull(existInfo)){
            loginDto.setCode(-1);
            loginDto.setErrorMsg("This user already existed, please login");
            return loginDto;
        }
        UserInfo userInfo = new UserInfo(null,username,password,role);
        int i = userInfoDao.save(userInfo);
        if(i == 1){
            loginDto.setCode(0);
            loginDto.setUserName(userInfo.getName());
            loginDto.setRole(userInfo.getRole());
        }else{
            loginDto.setCode(-2);
            loginDto.setErrorMsg("Ops this is an Error");
        }

        return loginDto;
    }
}
