package com.cc.bos.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    protected static final String LIST = "list";


    //模型对象
    protected T model;
    @Override
    public T getModel() {
        return model;
    }

    //构造方法中动态获取实体类型，通过反射创建model对象
    public BaseAction(){
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //根据父类字节码对象获取泛型字节码对象数组
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> clazz = (Class<T>) actualTypeArguments[0];
        try {
            model = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
