package com.mylibrary.service.impl;

import com.mylibrary.dao.impl.UserInfoDaoImpl;
import com.mylibrary.domain.UserInfo;
import com.mylibrary.dto.LoginDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
class UserInfoServiceImplTest {

    @Mock
    private UserInfoDaoImpl userInfoDao;
    @InjectMocks
    private UserInfoServiceImpl userInfoService;

    @Captor
    private ArgumentCaptor<UserInfo> argumentCaptor;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginSuccess() {
        UserInfo userInfo = new UserInfo(1L,"Alice","Al@123","admin");
        when(userInfoDao.queryByName(ArgumentMatchers.anyString())).thenReturn(userInfo);
        LoginDto loginDto = userInfoService.login("Alice", "Al@123");
        Assertions.assertEquals(0,loginDto.getCode());
        Assertions.assertEquals("Alice",loginDto.getUserName());
        Assertions.assertEquals("login success",loginDto.getErrorMsg());

    }

    @Test
    void loginPasswordError(){
        UserInfo userInfo = new UserInfo(1L,"Alice","Al@123","admin");
        when(userInfoDao.queryByName(ArgumentMatchers.anyString())).thenReturn(userInfo);
        LoginDto loginDto2 = userInfoService.login("Alice", "1111");
        Assertions.assertEquals(-2,loginDto2.getCode());
        Assertions.assertEquals("password error",loginDto2.getErrorMsg());
    }

    @Test
    void loginNotExist(){
        when(userInfoDao.queryByName(ArgumentMatchers.anyString())).thenReturn(null);
        LoginDto loginDto3 = userInfoService.login("Tom", "2222");
        Assertions.assertEquals(-1,loginDto3.getCode());
        Assertions.assertEquals("user not exist",loginDto3.getErrorMsg());
    }

    @Test
    void registerSuccess() {
        when(userInfoDao.queryByName(ArgumentMatchers.anyString())).thenReturn(null);
        when(userInfoDao.save(argumentCaptor.capture())).thenReturn(1);
        LoginDto registerDto = userInfoService.register("AAA", "AA@aaa", "user");
        Assertions.assertEquals(0,registerDto.getCode());
    }

    @Test
    void registerFail() {
        when(userInfoDao.queryByName(ArgumentMatchers.anyString())).thenReturn(new UserInfo());
        LoginDto registerDto = userInfoService.register("AAA", "AA@aaa", "user");
        Assertions.assertEquals(-1,registerDto.getCode());
        Assertions.assertEquals("This user already existed, please login",registerDto.getErrorMsg());
        verify(userInfoDao,never()).save(argumentCaptor.capture());
    }

    @Test
    void registerError() {
        when(userInfoDao.queryByName(ArgumentMatchers.anyString())).thenReturn(null);
        when(userInfoDao.save(argumentCaptor.capture())).thenReturn(0);
        LoginDto registerDto = userInfoService.register("AAA", "AA@aaa", "user");
        Assertions.assertEquals(-2,registerDto.getCode());
        Assertions.assertEquals("Ops this is an Error",registerDto.getErrorMsg());
        verify(userInfoDao,times(1)).save(argumentCaptor.capture());
    }
}