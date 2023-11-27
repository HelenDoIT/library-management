package com.mylibrary.service.impl;

import com.mylibrary.dao.IBookInfoDao;
import com.mylibrary.dao.impl.BookInfoDaoImpl;
import com.mylibrary.domain.BookInfo;
import com.mylibrary.service.IBookInfoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
public class BookInfoServiceImpl implements IBookInfoService {

    IBookInfoDao bookInfoDao = new BookInfoDaoImpl();

    /**
     *      *  Add books,
     *      *  If a book is already in the system,  merge the inventory
     * @param bookInfo
     * @return
     */
    @Override
    public int add(BookInfo bookInfo) {
        BookInfo info = bookInfoDao.queryByNameAndAuthor(bookInfo.getName(), bookInfo.getAuthor());
        if(Objects.nonNull(info)){
            //merge the inventory
            int inventory = info.getInventory();
            return bookInfoDao.updateInventory(info.getBookId(),inventory,bookInfo.getInventory());
        }else{
            return bookInfoDao.save(bookInfo);
        }
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public List<BookInfo> listAll() {
        return null;
    }
}
