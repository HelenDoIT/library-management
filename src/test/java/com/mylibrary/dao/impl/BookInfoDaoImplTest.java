package com.mylibrary.dao.impl;

import com.mylibrary.dao.impl.BookInfoDaoImpl;
import com.mylibrary.domain.BookInfo;
import com.mylibrary.util.JdbcTemplate;
import com.mylibrary.util.PropertiesUtil;
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
import java.util.Random;

import static org.mockito.Mockito.*;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
class BookInfoDaoImplTest {

    private boolean callRealFlag = PropertiesUtil.daoRealCallMock;

    @Spy
    private BookInfoDaoImpl bookInfoDao;

    @Captor
    private ArgumentCaptor<BookInfo> argumentCaptor;


    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }


    /**
     *  This will affect the database
     * @throws Exception
     */
    @org.junit.jupiter.api.Test
    void save() throws Exception{
        //Add book This will affect the database
        BookInfo bookInfo = new BookInfo(null,"Clean Code","Robert C. Martin",5);
        if(callRealFlag){
            try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
                Assertions.assertDoesNotThrow(() -> bookInfoDao.save(bookInfo));
                //todo 此处验证了执行了 JdbcTemplate.update()吗? 执行次数?
                jdbcTemplateMock.verify(() -> JdbcTemplate.update(ArgumentMatchers.any(),ArgumentMatchers.any()));
            }
        }

        //Mock static method  mock对象使用完之后需要关闭(用try-with-resource), 因为mock对象是放在ThreadLocal中,
        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.update(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(1);
            Assertions.assertEquals(1, bookInfoDao.save(bookInfo));
        }
    }

    @Test
    void queryByNameAndAuthor() throws Exception{

        if(callRealFlag){
            //query BookInfo (Book is exist)
            String name = "Clean Code";
            String author = "Robert C. Martin";
            BookInfo bookInfo = bookInfoDao.queryByNameAndAuthor(name, author);
            if(Objects.nonNull(bookInfo)){
                System.out.println("queryByNameAndAuthor: get the book");
                Assertions.assertEquals(bookInfo.getName(),name);
                Assertions.assertEquals(bookInfo.getAuthor(),author);
            }
        }

        BookInfo info = new BookInfo(2L,"Clean Code","Robert C. Martin",5);
        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.queryList(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.anyString(),ArgumentMatchers.anyString()))
                    .thenReturn(info);
            BookInfo bookInfo = bookInfoDao.queryByNameAndAuthor("Clean Code", "Robert C. Martin");
            Assertions.assertEquals("Clean Code", bookInfo.getName());
        }
    }

    @Test
    void queryByNameAndAuthorNotExist() throws Exception{
        //query not existed BookInfo
        String name = "Design pattern";
        String author = "Robert C. Martin";
        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.queryList(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(null);
            BookInfo bookInfo1 = bookInfoDao.queryByNameAndAuthor(name, author);
            Assertions.assertTrue(Objects.isNull(bookInfo1));
        }

    }

    @org.junit.jupiter.api.Test
    void updateInventory() {
        if(callRealFlag){
            Assertions.assertDoesNotThrow(() ->bookInfoDao.updateInventory(2L,11,3));
        }

        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.update(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(1);
            int i = bookInfoDao.updateInventory(2L, 11, 3);
            Assertions.assertEquals(1,i);
        }

    }

    @org.junit.jupiter.api.Test
    void delete() {

        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.update(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(1);
            Assertions.assertEquals(1,bookInfoDao.logicDelete(16L));
        }

        if(callRealFlag){
            //call real method
            Assertions.assertDoesNotThrow(() -> bookInfoDao.logicDelete(16L));

        }
    }

    @org.junit.jupiter.api.Test
    void queryByKey() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookId(2L);
        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            //mock 不定参静态类方法,注意参数一定要对上实际参数, 不然默认返回null
            jdbcTemplateMock.when(() -> JdbcTemplate.queryList(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.anyLong())).thenReturn(bookInfo);
            Assertions.assertEquals(2L,bookInfoDao.queryByKey(ArgumentMatchers.anyLong()).getBookId());
        }

        if(callRealFlag){
            //call real method
            BookInfo bookInfo2 = bookInfoDao.queryByKey(1L);
            if(Objects.nonNull(bookInfo2)){
                Assertions.assertEquals(1L,bookInfo2.getBookId());
            }
        }
    }

    @org.junit.jupiter.api.Test
    void listAll() {
        List<BookInfo> bookInfos = new ArrayList<>();
        bookInfos.add(new BookInfo(1L,"Clean Code","Robert C. Martin",5));
        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.queryList(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(bookInfos);
            List<BookInfo> infos = bookInfoDao.listAll();
            Assertions.assertNotNull(infos);
            Assertions.assertEquals(1,infos.size());
            Assertions.assertEquals("Clean Code",infos.get(0).getName());
        }

        if(callRealFlag){
            //call real method
            Mockito.when(bookInfoDao.listAll()).thenReturn(bookInfos);
            Assertions.assertNotNull(bookInfoDao.listAll());
        }
    }

    @Test
    void testQueryByName(){
        BookInfo info = new BookInfo(1L, "Clean Code", "Robert C. Martin", 5);
        try (MockedStatic<JdbcTemplate> jdbcTemplateMock = Mockito.mockStatic(JdbcTemplate.class)) {
            jdbcTemplateMock.when(() -> JdbcTemplate.queryList(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.anyString()))
                    .thenReturn(info);
            BookInfo bookInfo = bookInfoDao.queryByName("Clean Code");
            Assertions.assertEquals("Clean Code",bookInfo.getName());
        }
    }
}