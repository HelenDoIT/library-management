package com.mylibrary.dao;

import com.mylibrary.domain.UserInfo;


/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
public interface IUserInfoDao {

    public int save(UserInfo userInfo);

    public int updateByKey(Long id,UserInfo userInfo);

    public UserInfo queryByKey(Long id);

    public UserInfo queryByName(String username);

}
