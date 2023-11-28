package com.mylibrary.service.impl;

import com.mylibrary.dao.IBookInfoDao;
import com.mylibrary.dao.ILendSerialDao;
import com.mylibrary.dao.impl.BookInfoDaoImpl;
import com.mylibrary.dao.impl.LendSerialDaoImpl;
import com.mylibrary.domain.BookInfo;
import com.mylibrary.domain.LendSerial;
import com.mylibrary.service.IBookInfoService;
import com.mylibrary.service.ILendSerialService;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
public class LendSerialServiceImpl implements ILendSerialService {

    private ILendSerialDao lendSerialDao = new LendSerialDaoImpl();
    private IBookInfoDao bookInfoDao = new BookInfoDaoImpl();

    @Override
    public int recodeLending(LendSerial lendSerial) {
        BookInfo bookInfo = bookInfoDao.queryByKey(lendSerial.getBookId());
        if(Objects.nonNull(bookInfo) && bookInfo.getInventory() > 0){
            int i = bookInfoDao.updateInventory(lendSerial.getBookId(), bookInfo.getInventory(), -1);
            Assertions.assertEquals(i,1);
            lendSerialDao.save(lendSerial);
        }
        return 0;
    }

    @Override
    public List<LendSerial> queryByUserId(Long userId, Integer lendStatus) {
        List<LendSerial> lendSerialList = lendSerialDao.queryByUserId(userId);
        return lendSerialList;
    }

    @Override
    public int returnBook(Long serial) {
        return lendSerialDao.updateLendStatus(serial,1);
    }
}
