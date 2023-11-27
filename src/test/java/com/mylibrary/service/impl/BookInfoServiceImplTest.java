package com.mylibrary.service.impl;

import com.mylibrary.dao.impl.BookInfoDaoImpl;
import com.mylibrary.domain.BookInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
class BookInfoServiceImplTest {

    @Mock
    private BookInfoDaoImpl bookInfoDao;
    @InjectMocks
    private BookInfoServiceImpl bookInfoService;
    @Captor
    private ArgumentCaptor<BookInfo> argumentCaptor;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void add() {
        // add not exist book
        Mockito.when(bookInfoDao.queryByNameAndAuthor(ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(null);
        Mockito.when(bookInfoDao.save(argumentCaptor.capture()))
                .thenReturn(1);
        assertEquals(1,bookInfoService.add(new BookInfo()));// params can not be 'argumentCaptor.capture()'

        //add existed book
        BookInfo bookInfo = new BookInfo(1L, "ABC", "AAA", 5);
        Mockito.when(bookInfoDao.queryByNameAndAuthor(ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(bookInfo);
        Mockito.when(bookInfoDao.updateInventory(1L,5,3))
                .thenReturn(8);
        BookInfo bookInfo2 = new BookInfo(null, "ABC", "AAA", 3);
//        argumentCaptor = ArgumentCaptor.forClass(BookInfo.class);
//        Mockito.when(bookInfoService.add(argumentCaptor.capture()))
//                .thenReturn(1);
        assertDoesNotThrow(() -> bookInfoService.add(bookInfo2));
//        assertEquals(1L,argumentCaptor.getValue().getBookId());
//        assertEquals("ABC",argumentCaptor.getValue().getName());
        Mockito.verify(bookInfoDao,Mockito.times(1))
                .updateInventory(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt());
        assertEquals(8,bookInfoDao.updateInventory(1L,5,3));

    }

    @Test
    void delete() {
    }

    @Test
    void listAll() {
    }
}