package com.mylibrary.service.impl;

import com.mylibrary.dao.impl.UserInfoDaoImpl;
import com.mylibrary.domain.UserInfo;
import com.mylibrary.dto.LoginDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
class UserInfoServiceImplTest {

    @InjectMocks
    private UserInfoServiceImpl userInfoService;
    @Mock
    private UserInfoDaoImpl userInfoDao;

    @Captor
    private ArgumentCaptor<UserInfo> argumentCaptor;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login() {
        UserInfo userInfo = new UserInfo(1L,"Alice","Al@123","admin");
        Mockito.when(userInfoDao.queryByName(ArgumentMatchers.anyString())).thenReturn(userInfo);
        LoginDto loginDto = userInfoService.login("Alice", "Al@123");
        Assertions.assertEquals(0,loginDto.getCode());
        Assertions.assertEquals("Alice",loginDto.getUserName());

        Mockito.when(userInfoDao.queryByName(ArgumentMatchers.anyString())).thenReturn(userInfo);
        LoginDto loginDto2 = userInfoService.login("Bob", "1111");
        Assertions.assertEquals(-2,loginDto2.getCode());

        Mockito.when(userInfoDao.queryByName(ArgumentMatchers.anyString())).thenReturn(null);
        LoginDto loginDto3 = userInfoService.login("Tom", "2222");
        Assertions.assertEquals(-1,loginDto3.getCode());
    }

    @Test
    void register() {
    }
}