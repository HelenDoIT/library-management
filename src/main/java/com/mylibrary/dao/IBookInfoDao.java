package com.mylibrary.dao;

import com.mylibrary.domain.BookInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
public interface IBookInfoDao {

    public int save(BookInfo bookInfo) throws SQLException;

    public int update(BookInfo bookInfo) throws SQLException;

    public int delete(Long id) throws SQLException;

    public BookInfo queryByKey(Long id) throws SQLException;

    public BookInfo queryByNameAndAuthor(String name,String author) throws SQLException;

    public List<BookInfo> listAll() throws SQLException;
}
