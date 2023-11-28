package com.mylibrary.service.impl;

import com.mylibrary.dao.IBookInfoDao;
import com.mylibrary.dao.ILendSerialDao;
import com.mylibrary.dao.impl.BookInfoDaoImpl;
import com.mylibrary.dao.impl.LendSerialDaoImpl;
import com.mylibrary.domain.BookInfo;
import com.mylibrary.domain.LendSerial;
import com.mylibrary.dto.BookInfoDto;
import com.mylibrary.service.IBookInfoService;

import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
public class BookInfoServiceImpl implements IBookInfoService {

    IBookInfoDao bookInfoDao = new BookInfoDaoImpl();
    ILendSerialDao lendSerialDao = new LendSerialDaoImpl();

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

    /**
     * If books that are currently being borrowed by users, they  cannot be deleted
     * @param id
     * @return
     */
    @Override
    public BookInfoDto delete(Long id) {
        BookInfoDto bookInfoDto = new BookInfoDto();
        List<LendSerial> lendSerialList = lendSerialDao.queryByBookIdAndStatus(id, 0);
        if(null == lendSerialList || lendSerialList.size() == 0){
            bookInfoDao.logicDelete(id);
            bookInfoDto.setCode(0);
        }else{
            bookInfoDto.setCode(-1);
            bookInfoDto.setErrorMsg("You can not delete this book since it's in lending!");
        }
        return bookInfoDto;
    }

    @Override
    public List<BookInfo> listAll() {
        try {
            return bookInfoDao.listAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BookInfo queryByName(String bookname) {
        try {
            return bookInfoDao.queryByName(bookname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
