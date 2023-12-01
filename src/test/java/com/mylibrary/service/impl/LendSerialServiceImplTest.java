package com.mylibrary.service.impl;

import com.mylibrary.dao.impl.BookInfoDaoImpl;
import com.mylibrary.dao.impl.LendSerialDaoImpl;
import com.mylibrary.domain.BookInfo;
import com.mylibrary.domain.LendSerial;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
class LendSerialServiceImplTest {

    @Mock
    private LendSerialDaoImpl lendSerialDao;
    @Mock
    private BookInfoDaoImpl bookInfoDao;
    @InjectMocks
    private LendSerialServiceImpl lendSerialService;
    @Captor
    private ArgumentCaptor<LendSerial> argumentCaptor;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void recodeLending() {
        LendSerial lendSerial = new LendSerial(null,1L,22L,1,0,null,null);
        // 打桩
        Mockito.when(bookInfoDao.queryByKey(22L))
                .thenReturn(new BookInfo(22L,"AAA","abc",5));
        Mockito.when(bookInfoDao.updateInventory(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt()))
                .thenReturn(1);
        lendSerialService.recodeLending(lendSerial);//执行要测试的方法
        verify(bookInfoDao,times(1)).updateInventory(22L,5,-1);//验证方法内逻辑执行次数
        verify(lendSerialDao).save(argumentCaptor.capture());//参数捕获
        Assertions.assertEquals(1L,argumentCaptor.getValue().getUserId());
    }

    @Test
    void recodeLendingFail() {
        LendSerial lendSerial = new LendSerial(null,1L,22L,1,0,null,null);
        // 打桩
        Mockito.when(bookInfoDao.queryByKey(22L))
                .thenReturn(null);
        int i = lendSerialService.recodeLending(lendSerial);//执行要测试的方法
        verify(bookInfoDao,times(0)).updateInventory(22L,5,-1);//验证方法内逻辑执行次数
        verify(lendSerialDao,times(0)).save(argumentCaptor.capture());//验证方法内逻辑执行次数
        Assertions.assertEquals(0,i);
    }

    @Test
    void queryByUserId() {
        List<LendSerial> mockReturnList = new ArrayList<>();
        mockReturnList.add(new LendSerial(null,1L,22L,1,0,null,null));
        Mockito.when(lendSerialDao.queryByUserId(ArgumentMatchers.anyLong())).thenReturn(mockReturnList);
        List<LendSerial> serialList = lendSerialService.queryByUserId(1L, 0);
        Assertions.assertEquals(1,serialList.size());
        Assertions.assertEquals(1L,serialList.get(0).getUserId());

    }

    @Test
    void returnBook() {
        Mockito.when(lendSerialDao.updateLendStatus(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt())).thenReturn(1);
        Assertions.assertEquals(1,lendSerialService.returnBook(6L));
    }
}