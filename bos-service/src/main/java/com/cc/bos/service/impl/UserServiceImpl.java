package com.cc.bos.service.impl;

import com.cc.bos.dao.UserDao;
import com.cc.bos.entity.User;
import com.cc.bos.service.UserService;
import com.cc.bos.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(User model) {
        String password = MD5Utils.md5(model.getPassword());
        User user = userDao.findUserByUsernameAndPassword(model.getUsername(),password);
        return user;
    }

    @Override
    public void editPassword(String id, String password) {
        password = MD5Utils.md5(password);
        userDao.excuteUpdate("user.editpassword",password,id);
    }
}
