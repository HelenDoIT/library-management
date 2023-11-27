package com.mylibrary.handler.impl;

import com.mylibrary.handler.IResultSetHandler;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: BeanListHandler
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
public class BeanListHandler<T> implements IResultSetHandler<List<T>> {

    private Class<T> type;

    public BeanListHandler(Class<T> type){
        this.type=type;
    }

    @Override
    public List<T> handle(ResultSet rs) throws Exception {
        PropertyDescriptor[] pds = Introspector.getBeanInfo(type, Object.class).getPropertyDescriptors();
        List<T> list = new ArrayList<T>();
        while(rs.next()){
            T obj = (T) type.newInstance();
            for (PropertyDescriptor pd : pds) {
                String name = pd.getName();
                Object value = rs.getObject(name);
                pd.getWriteMethod().invoke(obj, value);
            }
            list.add(obj);
        }
        return list;
    }

}
