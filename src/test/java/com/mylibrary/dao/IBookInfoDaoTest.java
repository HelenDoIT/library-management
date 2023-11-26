package com.mylibrary.dao;

import com.mylibrary.dao.impl.BookInfoDaoImpl;
import com.mylibrary.domain.BookInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
class IBookInfoDaoTest {

    @Spy
    private BookInfoDaoImpl bookInfoDao;
    @Spy
    private PreparedStatement ps;
    @Spy
    private Connection connection;


    @BeforeEach
    void init(){
        System.out.println("open mock");
        MockitoAnnotations.openMocks(this);
    }

    @org.junit.jupiter.api.Test
    void save() throws Exception{
        //Add book
        BookInfo bookInfo = new BookInfo(null,"Clean Code","Robert C. Martin",5);
        Assertions.assertEquals(1,bookInfoDao.save(bookInfo));
        Mockito.verify(bookInfoDao,Mockito.times(1)).save(bookInfo);

        //mock exception
        Mockito.when(bookInfoDao.save(bookInfo)).thenThrow(new SQLException("This is SQLException"));
        try {
            bookInfoDao.save(bookInfo);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("This is SQLException",e.getMessage());
        }
//        Mockito.verify(ps,Mockito.times(0)).close();
//        Mockito.verify(connection,Mockito.times(0)).close();
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
        } catch (SQLException e) {
            Assertions.assertEquals("This is SQLException",e.getMessage());
        }

    }

    @org.junit.jupiter.api.Test
    void update() {
    }

    @org.junit.jupiter.api.Test
    void delete() {
    }

    @org.junit.jupiter.api.Test
    void queryByKey() {
    }

    @org.junit.jupiter.api.Test
    void listAll() {
    }
}