package com.cc.bos.web.interceptor;

import com.cc.bos.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class BOSLoginInterceptor extends MethodFilterInterceptor {
    //拦截方法
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //congsession中获取User对象
        HttpSession session = ServletActionContext.getRequest().getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "login";
        }
        return actionInvocation.invoke();
    }
}
