package com.mylibrary.dao.impl;

import com.mylibrary.domain.UserInfo;
import com.mylibrary.util.JdbcTemplate;
import com.mylibrary.util.PropertiesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/29
 */
class UserInfoDaoImplTest {

    private boolean callRealFlag = PropertiesUtil.daoRealCallMock;

    @Spy
    private UserInfoDaoImpl userInfoDao;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        UserInfo userInfo = new UserInfo("AAA","Aa@132","user");
        if(callRealFlag){
            Assertions.assertEquals(1,userInfoDao.save(userInfo));
        }

        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.update(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(),ArgumentMatchers.any()))
                    .thenReturn(1);
            int save = userInfoDao.save(userInfo);
            Assertions.assertEquals(save,1);
        }

    }

    @Test
    void queryByName() {
        UserInfo userInfo = userInfoDao.queryByName("AAA");
        if(callRealFlag) {
            Assertions.assertEquals("AAA",userInfo.getName());
        }

        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.queryList(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any()))
                    .thenReturn(userInfo);
            UserInfo info = userInfoDao.queryByName("AAA");
            Assertions.assertEquals(info.getName(),"AAA");
        }

    }
}