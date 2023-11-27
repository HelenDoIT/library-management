package com.mylibrary.dao;

import com.mylibrary.domain.LendSerial;

import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
public interface ILendSerialDao {

    public int save(LendSerial serial);

    public int updateLendStatus(Long serialNo,int lendStatus);

    public List<LendSerial> queryByUserId(Long userId);

    public List<LendSerial> queryByBookIdAndStatus(Long bookId,int lendStatus);
}
