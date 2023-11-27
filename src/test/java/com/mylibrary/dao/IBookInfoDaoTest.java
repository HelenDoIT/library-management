package com.mylibrary.dao;

import com.mylibrary.dao.impl.BookInfoDaoImpl;
import com.mylibrary.domain.BookInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
class IBookInfoDaoTest {

    @Mock
    private BookInfoDaoImpl bookInfoDaoMock;
    @Spy
    private BookInfoDaoImpl bookInfoDao;

    @Captor
    private ArgumentCaptor<BookInfo> argumentCaptor;


    @BeforeEach
    void init(){
        System.out.println("open mock");
        MockitoAnnotations.openMocks(this);
    }

    /**
     *  This will affect the database
     * @throws Exception
     */
    @org.junit.jupiter.api.Test
    void save() throws Exception{
        //Add book
        BookInfo bookInfo = new BookInfo(null,"Clean Code","Robert C. Martin",5);
        Assertions.assertEquals(1,bookInfoDao.save(bookInfo));
        Mockito.verify(bookInfoDao,Mockito.times(1)).save(bookInfo);

        Mockito.when(bookInfoDaoMock.save(argumentCaptor.capture())).thenReturn(1);
        Assertions.assertEquals(1, bookInfoDaoMock.save(argumentCaptor.capture()));
        //Mockito.verify(bookInfoDao).save(argumentCaptor.capture());

        //mock exception
        Mockito.when(bookInfoDaoMock.save(argumentCaptor.capture())).thenThrow(new SQLException("This is SQLException"));
        try {
            bookInfoDaoMock.save(argumentCaptor.capture());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("This is SQLException",e.getMessage());
        }
    }

    @Test
    void queryByNameAndAuthor() throws Exception{
        //query BookInfo (Book is exist)
        String name = "Clean Code";
        String author = "Robert C. Martin";
        BookInfo bookInfo = bookInfoDao.queryByNameAndAuthor(name, author);
        Assertions.assertEquals(bookInfo.getName(),name);
        Assertions.assertEquals(bookInfo.getAuthor(),author);
        Mockito.verify(bookInfoDao,Mockito.times(1)).queryByNameAndAuthor(name,author);

        //query not existed BookInfo
        name = "Design pattern";
        BookInfo bookInfo1 = bookInfoDao.queryByNameAndAuthor(name, author);
        Assertions.assertTrue(Objects.isNull(bookInfo1));

        //mock exception
        Mockito.when(bookInfoDao.queryByNameAndAuthor(name,author)).thenThrow(new SQLException("This is SQLException"));
        try {
            bookInfoDao.queryByNameAndAuthor(name,author);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("This is SQLException",e.getMessage());
        }

    }

    @org.junit.jupiter.api.Test
    void updateInventory() {
        //Assertions.assertEquals(1, bookInfoDao.updateInventory(2L,11,3));

        Mockito.when(bookInfoDao.updateInventory(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt()))
                .thenReturn(1);
        Assertions.assertEquals(1,bookInfoDao.updateInventory(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt(),ArgumentMatchers.anyInt()));

        Mockito.when(bookInfoDao.updateInventory(1L,5,3))
                .thenReturn(8);
        Assertions.assertEquals(8,bookInfoDao.updateInventory(1L,5,3));

        //mock exception
        Mockito.when(bookInfoDao.updateInventory(1L,5,3)).thenThrow(new SQLException("This is an update SQLException"));
        try {
            bookInfoDao.updateInventory(1L,5,3);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals(e.getMessage(),"This is an update SQLException");
        }
    }

    @org.junit.jupiter.api.Test
    void delete() {
    }

    @org.junit.jupiter.api.Test
    void queryByKey() {
    }

    @org.junit.jupiter.api.Test
    void listAll() {
        List<BookInfo> bookInfos = new ArrayList<>();
        bookInfos.add(new BookInfo(1L,"Clean Code","Robert C. Martin",5));
        Mockito.when(bookInfoDaoMock.listAll()).thenReturn(bookInfos);
        List<BookInfo> infos = bookInfoDaoMock.listAll();
        Assertions.assertNotNull(infos);
        Assertions.assertEquals(1,infos.size());
        Assertions.assertEquals("Clean Code",infos.get(0).getName());

        //call real method
        Assertions.assertNotNull(bookInfoDao.listAll());
    }
}