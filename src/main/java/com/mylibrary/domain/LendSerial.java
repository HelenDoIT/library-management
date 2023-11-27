package com.mylibrary.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
public class LendSerial implements Serializable {

    private static final long serialVersionUID = -4113615840320231245L;
    private Long serialNo;
    private Long userId;
    private Long bookId;
    private int lendNum;
    private int lendStatus;
    private LocalDateTime lendDate;
    private LocalDateTime returnDate;

    public LendSerial(){

    }

    public LendSerial(Long serialNo, Long userId, Long bookId, int lendNum, int lendStatus, LocalDateTime lendDate, LocalDateTime returnDate) {
        this.serialNo = serialNo;
        this.userId = userId;
        this.bookId = bookId;
        this.lendNum = lendNum;
        this.lendStatus = lendStatus;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getLendNum() {
        return lendNum;
    }

    public void setLendNum(int lendNum) {
        this.lendNum = lendNum;
    }

    public int getLendStatus() {
        return lendStatus;
    }

    public void setLendStatus(int lendStatus) {
        this.lendStatus = lendStatus;
    }

    public LocalDateTime getLendDate() {
        return lendDate;
    }

    public void setLendDate(LocalDateTime lendDate) {
        this.lendDate = lendDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
