package com.mylibrary.handler;

import java.sql.ResultSet;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
public interface IResultSetHandler<T> {
    /**
     * handle ResultSet
     * @param rs
     * @param <T>
     * @return
     * @throws Exception
     */
    <T>T handle(ResultSet rs) throws Exception;
}
