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

    public int save(BookInfo bookInfo);

    public int logicDelete(Long id);

    public BookInfo queryByKey(Long id);

    public BookInfo queryByNameAndAuthor(String name,String author);

    public List<BookInfo> listAll();

    public int updateInventory(Long bookId, int origInventory, int addInventory);
}
