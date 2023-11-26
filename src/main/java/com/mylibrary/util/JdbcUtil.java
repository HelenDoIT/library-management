package com.mylibrary.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    //Private constructor
    private JdbcUtil(){}

    //properties
    private static String driverClassName;
    private static String url;
    private static String username ;
    private static String password;


    //Load the configuration file
    static{
        Properties pro = new Properties();
        //get ClassLoader
        ClassLoader classLoader = JdbcUtil.class.getClassLoader();
        //get InputStream
        InputStream input = classLoader.getResourceAsStream("db.properties");
        try {
            pro.load(input);
            driverClassName = pro.getProperty("driverClassName");
            url = pro.getProperty("url");
            username = pro.getProperty("username");
            password = pro.getProperty("password");
            Class.forName(driverClassName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get Connection entity
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);//此处抛出异常, 让方法的调用者处理
        return connection;
    }

    //close resource
    public static void closeResource(Connection connection, PreparedStatement ps, ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
