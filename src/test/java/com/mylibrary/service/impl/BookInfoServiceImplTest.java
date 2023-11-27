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

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
class BookInfoServiceImplTest {

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

    @Test
    void add() {
        // add not exist book
        Mockito.when(bookInfoDao.queryByNameAndAuthor(ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(null);
        Mockito.when(bookInfoDao.save(argumentCaptor.capture()))
                .thenReturn(1);
        assertEquals(1,bookInfoService.add(new BookInfo(null,"XYZ","abc",2)));// params can not be 'argumentCaptor.capture()'
        assertEquals("XYZ",argumentCaptor.getValue().getName());
        assertEquals("abc",argumentCaptor.getValue().getAuthor());

        //add existed book
        BookInfo bookInfo = new BookInfo(1L, "ABC", "AAA", 5);
        Mockito.when(bookInfoDao.queryByNameAndAuthor(ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                .thenReturn(bookInfo);
        Mockito.when(bookInfoDao.updateInventory(1L,5,3))
                .thenReturn(8);
        BookInfo bookInfo2 = new BookInfo(null, "ABC", "AAA", 3);

        assertDoesNotThrow(() -> bookInfoService.add(bookInfo2));
        Mockito.verify(bookInfoDao,Mockito.times(1))
                .updateInventory(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt());
        assertEquals(8,bookInfoDao.updateInventory(1L,5,3));

    }

    @Test
    void delete() throws Exception {

        Mockito.when(lendSerialDao.queryByBookIdAndStatus(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt()))
                .thenReturn(null);
        Mockito.when(bookInfoDao.logicDelete(ArgumentMatchers.anyLong()))
                .thenReturn(1);
        assertDoesNotThrow(() -> bookInfoService.delete(1L));
        Assertions.assertEquals(1,bookInfoService.delete(1L));
        //mock delete fail
        List<LendSerial> lendSerialList = new ArrayList<>();
        lendSerialList.add(new LendSerial());
        Mockito.when(lendSerialDao.queryByBookIdAndStatus(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt()))
                .thenReturn(lendSerialList);
        try {
            bookInfoService.delete(1L);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("You can not delete this book since it's in lending!",e.getMessage());
        }
    }

    @Test
    void listAll() {
        List<BookInfo> bookInfoList = new ArrayList<>();
        bookInfoList.add(new BookInfo());
        bookInfoList.add(new BookInfo());
        Mockito.when(bookInfoDao.listAll()).thenReturn(bookInfoList);
        Assertions.assertEquals(2,bookInfoService.listAll().size());

        Mockito.when(bookInfoDao.listAll()).thenReturn(null);
        assertDoesNotThrow(() -> bookInfoService.listAll());
    }
}