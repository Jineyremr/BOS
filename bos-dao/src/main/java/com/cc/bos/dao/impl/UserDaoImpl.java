package com.cc.bos.dao.impl;

import com.cc.bos.dao.UserDao;
import com.cc.bos.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String hql = "from User u where u.username = ? and u.password = ?";
        List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username, password);
        if(list != null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void excuteUpdate(String s, Object...objects) {
        Session currentSession = this.getSessionFactory().getCurrentSession();
        Query namedQuery = currentSession.getNamedQuery(s);
        int i = 0;
        for (Object object:objects){
            namedQuery.setParameter(i++,object);
        }
        namedQuery.executeUpdate();
    }


}
