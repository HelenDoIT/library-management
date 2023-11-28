package com.mylibrary.service;

import com.mylibrary.dao.IBookInfoDao;
import com.mylibrary.domain.BookInfo;
import com.mylibrary.dto.BookInfoDto;

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
    public int add(BookInfo bookInfo);

    /**
     * If books that are currently being borrowed by users, they  cannot be deleted
     * @param id
     * @return
     */
    public BookInfoDto delete(Long  id);

    public List<BookInfo> listAll();

    public BookInfo queryByName(String bookname);
}
