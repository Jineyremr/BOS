package com.cc.bos.dao.impl;

import com.cc.bos.dao.BaseDao;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao {

    private Class<T> entityClass;

    //根据类型注入spring工厂中的会话工厂对象sessionFactory
    @Resource
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
    //根据feu类的构造方法动态获得entityClass
    public BaseDaoImpl(){
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获得父类上声明的泛型数组
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        entityClass = (Class<T>) actualTypeArguments[0];
    }

    @Override
    public void save(Object entity) {
        this.getHibernateTemplate().save(entity);
    }

    @Override
    public void delete(Object entity) {
        this.getHibernateTemplate().delete(entity);
    }

    @Override
    public void update(Object entity) {
        this.getHibernateTemplate().update(entity);
    }

    @Override
    public T findById(Serializable id) {
        return this.getHibernateTemplate().get(entityClass,id);
    }

    @Override
    public List<T> findAll() {
        String hql = "from "+entityClass.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }
}
