package com.mylibrary.service;

import com.mylibrary.domain.LendSerial;

import java.util.List;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
public interface ILendSerialService {

    public int recodeLending(LendSerial lendSerial);

    public List<LendSerial> queryByUserId(Long userId,Integer lendStatus);

    public int returnBook(Long serial);

}
