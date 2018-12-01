package com.cc.bos.dao;

import com.cc.bos.entity.User;

public interface UserDao extends BaseDao {

    User findUserByUsernameAndPassword(String username, String password);

    void excuteUpdate(String s, Object...objects);
}
