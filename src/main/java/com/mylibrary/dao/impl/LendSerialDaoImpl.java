package com.mylibrary.dao.impl;

import com.mylibrary.dao.ILendSerialDao;
import com.mylibrary.domain.LendSerial;
import com.mylibrary.handler.impl.BeanListHandler;
import com.mylibrary.util.JdbcTemplate;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
public class LendSerialDaoImpl implements ILendSerialDao {

    @Override
    public int save(LendSerial serial) {
        String sql  = "INSERT into lend_serial (user_id,book_id,lend_num,lend_status,lend_date) VALUES (?,?,?,?,?);";
        Date lendDate = new Date();
        return JdbcTemplate.update(sql,serial.getUserId(),serial.getBookId(),serial.getLendNum(),0,lendDate);
    }

    @Override
    public int updateLendStatus(Long serialNo,int lendStatus) {
        String sql = "UPDATE lend_serial SET lend_status = ?,return_date = ? WHERE serial_no = ? and lend_status = 0";
        Date returnDate = new Date();
        return JdbcTemplate.update(sql,lendStatus,returnDate,serialNo);
    }


    @Override
    public List<LendSerial> queryByUserId(Long userId) {
        String sql = "SELECT serial_no as serialNo,user_id as userId,book_id as bookId,\n" +
                "lend_num as lendNum,lend_status as lendStatus,lend_date as lendDate,return_date as returnDate\n" +
                " FROM lend_serial WHERE user_id = ? ORDER BY lend_status,lend_date LIMIT 100";
        return JdbcTemplate.queryList(sql,new BeanListHandler<>(LendSerial.class),userId);
    }

    @Override
    public List<LendSerial> queryByBookIdAndStatus(Long bookId,int lendStatus) {
        String sql = "SELECT serial_no as serialNo,user_id as userId,book_id as bookId,\n" +
                "lend_num as lendNum,lend_status as lendStatus,lend_date as lendDate,return_date as returnDate\n" +
                " FROM lend_serial WHERE book_id = ? and lend_status = ?";
        return JdbcTemplate.queryList(sql,new BeanListHandler<>(LendSerial.class),bookId,lendStatus);
    }
}
