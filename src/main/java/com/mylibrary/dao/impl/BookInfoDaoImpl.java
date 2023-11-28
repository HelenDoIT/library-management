package com.mylibrary.dao.impl;

import com.mylibrary.dao.IBookInfoDao;
import com.mylibrary.domain.BookInfo;
import com.mylibrary.handler.impl.BeanHandler;
import com.mylibrary.handler.impl.BeanListHandler;
import com.mylibrary.util.JdbcTemplate;
import com.mylibrary.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
public class BookInfoDaoImpl implements IBookInfoDao {

    @Override
    public int save(BookInfo bookInfo) {
        String sql = "INSERT INTO book_info (name,author,inventory) VALUES (?,?,?)";
        return JdbcTemplate.update(sql,bookInfo.getName(),bookInfo.getAuthor(),bookInfo.getInventory());
    }



    @Override
    public int logicDelete(Long id){
        String sql = "UPDATE book_info SET dr =1 where book_id = ? and dr = 0";
        return JdbcTemplate.update(sql,id);
    }

    @Override
    public BookInfo queryByKey(Long id) {
        String sql = "SELECT book_id as bookId,`name`,author,inventory,dr FROM book_info WHERE book_id = ? and dr=0";
        return JdbcTemplate.queryList(sql,new BeanHandler<>(BookInfo.class),id);
    }

    @Override
    public BookInfo queryByNameAndAuthor(String name, String author){
        String sql = "SELECT book_id as bookId,`name`,author,inventory,dr FROM book_info WHERE name=? and author = ? and dr=0;";
        BookInfo bookInfo = JdbcTemplate.queryList(sql, new BeanHandler<BookInfo>(BookInfo.class), name, author);
        return bookInfo;
    }

    @Override
    public List<BookInfo> listAll(){
        String sql = "SELECT book_id as bookId,`name`,author,inventory,dr FROM book_info where dr=0";
         return  JdbcTemplate.queryList(sql, new BeanListHandler<BookInfo>(BookInfo.class));
    }

    @Override
    public int updateInventory(Long bookId, int origInventory, int addInventory) {
        String sql = "UPDATE book_info SET inventory = inventory + ? where book_id = ? and inventory = ? and dr=0";
        return JdbcTemplate.update(sql,addInventory,bookId,origInventory);
    }

    @Override
    public BookInfo queryByName(String bookname) {
        String sql = "SELECT book_id as bookId,`name`,author,inventory,dr FROM book_info WHERE name=? and dr=0;";
        BookInfo bookInfo = JdbcTemplate.queryList(sql, new BeanHandler<BookInfo>(BookInfo.class), bookname);
        return bookInfo;
    }
}
