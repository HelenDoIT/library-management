package com.mylibrary.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/29
 */
public class PropertiesUtil {

    public static Boolean daoRealCallMock = false;

    static{
        Properties pro = new Properties();
        //get ClassLoader
        ClassLoader classLoader = JdbcUtil.class.getClassLoader();
        //get InputStream
        InputStream input = classLoader.getResourceAsStream("library.properties");
        try {
            pro.load(input);
            String mock = pro.getProperty("daoRealCallMock", String.valueOf(false));
            if("true".equals(mock)){
                daoRealCallMock = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
