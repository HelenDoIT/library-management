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

    public int update(LendSerial serial);

    public LendSerial queryOne(LendSerial serial);

    public List<LendSerial> queryByUserId(Long userId);
}
