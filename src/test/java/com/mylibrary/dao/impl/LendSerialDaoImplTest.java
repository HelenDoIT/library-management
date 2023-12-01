package com.mylibrary.dao.impl;

import com.mylibrary.domain.LendSerial;
import com.mylibrary.util.JdbcTemplate;
import com.mylibrary.util.PropertiesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
class LendSerialDaoImplTest {

    private boolean callRealFlag = PropertiesUtil.daoRealCallMock;

    @Spy
    private LendSerialDaoImpl lendSerialDao;
    @Captor
    private ArgumentCaptor<LendSerial> argumentCaptor;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        LendSerial lendSerial = new LendSerial(null,1L,22L,1,0,null,null);
        if(callRealFlag){
            //Below will real call database
            Assertions.assertEquals(1,lendSerialDao.save(lendSerial));
        }

        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.update(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any(),
                    ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any()))
                    .thenReturn(1);
            Assertions.assertEquals(1,lendSerialDao.save(lendSerial));
        }
    }

    @Test
    void updateLendStatus() {
        LendSerial lendSerial = new LendSerial();
        lendSerial.setSerialNo(1L);
        lendSerial.setLendStatus(1);

        if(callRealFlag){
            assertDoesNotThrow(() -> lendSerialDao.updateLendStatus(1L, 1));
        }

        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.update(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.anyLong()))
                    .thenReturn(1);
            int i = lendSerialDao.updateLendStatus(1L, 1);
            Assertions.assertEquals(1,i);
        }
    }

    @Test
    void queryByUserId() {

        if(callRealFlag){
            List<LendSerial> lendSerialList = lendSerialDao.queryByUserId(1L);
            if(null != lendSerialList && lendSerialList.size() > 0 ){
                Assertions.assertEquals(1L,lendSerialDao.queryByUserId(1L).get(0).getUserId());
            }
        }

        List<LendSerial> lendSerialList = new ArrayList<>();
        lendSerialList.add(new LendSerial(null,1L,22L,1,0,null,null));
        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.queryList(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.anyLong()))
                    .thenReturn(lendSerialList);
            List<LendSerial> lendSerialList1 = lendSerialDao.queryByUserId(1L);
            Assertions.assertEquals(22L,lendSerialList1.get(0).getBookId());
        }

    }

    @Test
    void queryByBookIdAndStatus() {
        if(callRealFlag){
            List<LendSerial> lendSerialList = lendSerialDao.queryByBookIdAndStatus(1L,0);
            if(null != lendSerialList && lendSerialList.size() > 0 ){
                Assertions.assertEquals(1L,lendSerialDao.queryByUserId(1L).get(0).getBookId());
            }
        }

        List<LendSerial> lendSerialList = new ArrayList<>();
        lendSerialList.add(new LendSerial(null,1L,22L,1,0,null,null));
        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.queryList(ArgumentMatchers.any(),ArgumentMatchers.any()
                    ,ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt()))
                    .thenReturn(lendSerialList);
            Assertions.assertEquals(22L,lendSerialDao.queryByBookIdAndStatus(1L,0).get(0).getBookId());
        }
    }
}