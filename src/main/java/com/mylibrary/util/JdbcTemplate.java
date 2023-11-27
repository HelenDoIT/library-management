package com.mylibrary.util;

import com.mylibrary.handler.IResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @description: JDBC Template
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
public class JdbcTemplate {
    /**
     * DML template
     * @param sql
     * @param params
     * @return
     */
    public static int update(String sql, Object... params){
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < params.length; i ++){
                ps.setObject(i + 1, params[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JdbcUtil.closeResource(connection,ps,null);
        }
        return 0;
    }

    public static <T>T queryList(String sql, IResultSetHandler<T> resultSetHandler,Object... params){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = JdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for(int i = 0; i < params.length; i ++){
                ps.setObject(i + 1, params[i]);
            }
            rs = ps.executeQuery();
            return resultSetHandler.handle(rs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            JdbcUtil.closeResource(connection,ps,rs);
        }

    }

}
