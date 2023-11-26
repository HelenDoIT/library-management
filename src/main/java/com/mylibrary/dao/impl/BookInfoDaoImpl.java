package com.mylibrary.dao.impl;

import com.mylibrary.dao.IBookInfoDao;
import com.mylibrary.domain.BookInfo;
import com.mylibrary.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
public class BookInfoDaoImpl implements IBookInfoDao {

    @Override
    public int save(BookInfo bookInfo) throws SQLException {
        String sql = "INSERT INTO book_info (name,author,inventory) VALUES (?,?,?)";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,bookInfo.getName());
            ps.setString(2,bookInfo.getAuthor());
            ps.setInt(3,bookInfo.getInventory());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
                if (ps != null) {
                    ps.close();
                }
                if(connection != null){
                    connection.close();
                }
        }

    }

    @Override
    public int update(BookInfo bookInfo) throws SQLException{
        return 0;
    }

    @Override
    public int delete(Long id) throws SQLException{
        return 0;
    }

    @Override
    public BookInfo queryByKey(Long id) throws SQLException{
        return null;
    }

    @Override
    public BookInfo queryByNameAndAuthor(String name, String author) throws SQLException {
        String sql = "SELECT book_id,`name`,author,inventory FROM book_info WHERE name=? and author = ?;";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,author);
            rs = ps.executeQuery();
            while (rs.next()){
                long bookId = rs.getLong("book_id");
                String name1 = rs.getString("name");
                String author1 = rs.getString("author");
                Integer inventory = rs.getInt("inventory");
                BookInfo bookInfo = new BookInfo(bookId,name1,author1,inventory);
                return bookInfo;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }finally {
            if (ps != null) {
                ps.close();
            }
            if(connection != null){
                connection.close();
            }
        }
    }

    @Override
    public List<BookInfo> listAll() throws SQLException{
        return null;
    }
}
