package com.mylibrary.service.impl;

import com.mylibrary.dao.IBookInfoDao;
import com.mylibrary.dao.ILendSerialDao;
import com.mylibrary.dao.impl.BookInfoDaoImpl;
import com.mylibrary.dao.impl.LendSerialDaoImpl;
import com.mylibrary.domain.BookInfo;
import com.mylibrary.domain.LendSerial;
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
    public int delete(Long id) throws Exception {
        List<LendSerial> lendSerialList = lendSerialDao.queryByBookIdAndStatus(id, 0);
        if(null == lendSerialList || lendSerialList.size() == 0){
            return bookInfoDao.logicDelete(id);
        }else{
            //todo throw BusinessException
            throw new Exception("You can not delete this book since it's in lending!");
        }
    }

    @Override
    public List<BookInfo> listAll() {
        return bookInfoDao.listAll();
    }
}
