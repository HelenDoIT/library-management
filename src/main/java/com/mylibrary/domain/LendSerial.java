package com.mylibrary.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
public class LendSerial implements Serializable {

    private static final long serialVersionUID = -4113615840320231245L;
    private Long lendSerial;
    private String userId;
    private String bookId;
    private int lendNum;
    private Date lendDate;
    private Date returnDate;

    public LendSerial(){

    }

    public LendSerial(Long lendSerial, String userId, String bookId, int lendNum, Date lendDate, Date returnDate) {
        this.lendSerial = lendSerial;
        this.userId = userId;
        this.bookId = bookId;
        this.lendNum = lendNum;
        this.lendDate = lendDate;
        this.returnDate = returnDate;
    }

    public Long getLendSerial() {
        return lendSerial;
    }

    public void setLendSerial(Long lendSerial) {
        this.lendSerial = lendSerial;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getLendNum() {
        return lendNum;
    }

    public void setLendNum(int lendNum) {
        this.lendNum = lendNum;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
