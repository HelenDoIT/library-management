package com.mylibrary.dao.impl;

import com.mylibrary.dao.IUserInfoDao;
import com.mylibrary.domain.UserInfo;
import com.mylibrary.handler.impl.BeanHandler;
import com.mylibrary.util.JdbcTemplate;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
public class UserInfoDaoImpl implements IUserInfoDao {

    @Override
    public int save(UserInfo userInfo) {
        String sql = "INSERT INTO user_info (`name`,`password`,role) VALUES (?,?,?)";
        return JdbcTemplate.update(sql,userInfo.getName(),userInfo.getPassword(),userInfo.getRole());
    }

    @Override
    public UserInfo queryByName(String username) {
        String sql = "SELECT user_id as userId,`name`,`password`,role FROM user_info WHERE `name` = ?";
        return JdbcTemplate.queryList(sql,new BeanHandler<>(UserInfo.class),username);
    }
}
