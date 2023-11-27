package com.mylibrary.handler.impl;

import com.mylibrary.handler.IResultSetHandler;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

/**
 * @description: BeanHandler
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
public class BeanHandler<T> implements IResultSetHandler<T> {

    private Class<T> type;

    public BeanHandler(Class<T> type){
        this.type=type;
    }

    @Override
    public <T> T handle(ResultSet rs) throws Exception {
        PropertyDescriptor[] pds = Introspector.getBeanInfo(type, Object.class).getPropertyDescriptors();
        T obj = (T) type.newInstance();
        if(rs.next()){
            for (PropertyDescriptor pd : pds) {
                String name = pd.getName();
                Object value = rs.getObject(name);
                pd.getWriteMethod().invoke(obj, value);
            }
        }
        return obj;
    }
}
