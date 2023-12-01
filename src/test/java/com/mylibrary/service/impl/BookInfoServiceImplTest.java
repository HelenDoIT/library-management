package com.mylibrary.service.impl;

import com.mylibrary.dao.impl.BookInfoDaoImpl;
import com.mylibrary.dao.impl.LendSerialDaoImpl;
import com.mylibrary.domain.BookInfo;
import com.mylibrary.domain.LendSerial;
import com.mylibrary.dto.BookInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
class BookInfoServiceImplTest{

    @Mock
    private BookInfoDaoImpl bookInfoDao;
    @Mock
    private LendSerialDaoImpl lendSerialDao;
    @InjectMocks
    private BookInfoServiceImpl bookInfoService;
    @Captor
    private ArgumentCaptor<BookInfo> argumentCaptor;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    //测试案例: 一个案例对应一个测试方法

    @Test
    void addNotExist() {
        // add not exist book
        when(bookInfoDao.queryByNameAndAuthor(ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(null);
        when(bookInfoDao.save(argumentCaptor.capture()))
                .thenReturn(1);
        assertEquals(1,bookInfoService.add(new BookInfo(null,"XYZ","abc",2)));// params can not be 'argumentCaptor.capture()'
        verify(bookInfoDao,times(1)).queryByNameAndAuthor(ArgumentMatchers.anyString(),ArgumentMatchers.anyString());
        verify(bookInfoDao,times(1)).save(argumentCaptor.capture());
        verify(bookInfoDao,never()).updateInventory(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt());
        assertEquals("XYZ",argumentCaptor.getValue().getName());
        assertEquals("abc",argumentCaptor.getValue().getAuthor());

    }

    @Test
    void addExisted(){
        //add existed book
        when(bookInfoDao.queryByNameAndAuthor(ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(new BookInfo(1L, "ABC", "AAA", 5));
        BookInfo bookInfo2 = new BookInfo(null, "ABC", "AAA", 3);
        assertDoesNotThrow(() -> bookInfoService.add(bookInfo2));
        //验证 执行了更新数量方法, 没有执行保存方法
        verify(bookInfoDao,times(1))
                .updateInventory(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt());
        verify(bookInfoDao,times(0)).save(argumentCaptor.capture());
    }

    @Test
    void delete() throws Exception {
        //delete success
        when(lendSerialDao.queryByBookIdAndStatus(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt()))
                .thenReturn(null);
        assertDoesNotThrow(() -> bookInfoService.delete(1L));
        verify(bookInfoDao,times(1)).logicDelete(ArgumentMatchers.anyLong());
        Assertions.assertEquals(0,bookInfoService.delete(1L).getCode());

    }

    @Test
    void deleteFail(){
        //mock delete fail
        List<LendSerial> lendSerialList = new ArrayList<>();
        lendSerialList.add(new LendSerial());
        when(lendSerialDao.queryByBookIdAndStatus(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt()))
                .thenReturn(lendSerialList);
        BookInfoDto infoDto = assertDoesNotThrow(() -> bookInfoService.delete(1L));
        verify(bookInfoDao,never()).logicDelete(ArgumentMatchers.anyLong()); //如果案例放在delete success后面, 这个验证将会失败, 有别的方案处理?
        Assertions.assertEquals("You can not delete this book since it's in lending!",infoDto.getErrorMsg());
    }


    @Test
    void listAll() {
        List<BookInfo> bookInfoList = new ArrayList<>();
        bookInfoList.add(new BookInfo());
        bookInfoList.add(new BookInfo());
        when(bookInfoDao.listAll()).thenReturn(bookInfoList);
        Assertions.assertEquals(2,bookInfoService.listAll().size());

        when(bookInfoDao.listAll()).thenReturn(null);
        assertDoesNotThrow(() -> bookInfoService.listAll());

    }

    @Test
    void queryByName() {
        BookInfo bookInfo = new BookInfo(1L, "ABC", "AAA", 5);
        when(bookInfoDao.queryByName(ArgumentMatchers.anyString())).thenReturn(bookInfo);
        Assertions.assertEquals("ABC",bookInfoService.queryByName("ABC").getName());

    }


}