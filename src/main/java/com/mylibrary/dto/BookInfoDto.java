package com.mylibrary.dto;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
public class BookInfoDto extends BaseDto{

    private Long bookId;
    private String name;
    private String author;
    private int inventory;

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
}
