package com.mylibrary.service;

import com.mylibrary.dao.IBookInfoDao;
import com.mylibrary.domain.BookInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
public interface IBookInfoService {
    /**
     *  Add books,
     *  If a book is already in the system,  merge the inventory
     * @param bookInfo
     * @return
     */
    public int add(BookInfo bookInfo) throws SQLException;

    public int delete(Long  id);

    public List<BookInfo> listAll();
}
