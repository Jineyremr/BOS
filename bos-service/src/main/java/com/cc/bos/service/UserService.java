package com.cc.bos.service;

import com.cc.bos.entity.User;

public interface UserService {


    /**
     * 登录
     * @param model
     * @return
     */
    User login(User model);

    /**
     * 更改密码
     * @param id
     * @param password
     */
    void editPassword(String id, String password);
}
