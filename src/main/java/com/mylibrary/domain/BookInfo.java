package com.mylibrary.domain;

import java.io.Serializable;

/**
 * @description: BookInfo
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
public class BookInfo implements Serializable {

    private static final long serialVersionUID = 635839085783446755L;

    private Long bookId;
    private String name;
    private String author;
    private int inventory;
    private int dr;

    public BookInfo(){

    }

    public BookInfo(Long bookId, String name, String author, int inventory) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.inventory = inventory;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }
}
