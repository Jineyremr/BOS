package com.cc.bos.web.action;

import com.cc.bos.entity.User;
import com.cc.bos.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

    //属性驱动 接受页面传来的验证码
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @return
     */

    public String login(){
        //从session中取出生成的验证码
        String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        if(StringUtils.isNotBlank(checkcode) && checkcode.equals(key)){
            User user = userService.login(model);
            if(user != null){
                ServletActionContext.getRequest().getSession().setAttribute("user",user);
                return "home";
            }else{
                this.addActionError("用户名或密码不正确！");
                return LOGIN;
            }
        }else{
            this.addActionError("输入验证码错误！");
            return LOGIN;
        }
    }

    public String editPassword() throws Exception {
        String f = "1";
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        try{
            userService.editPassword(user.getId(),model.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }
        ServletActionContext.getResponse().setContentType("text/html");
        ServletActionContext.getResponse().getWriter().print(f);


        return NONE;
    }


    /**
     * 登出
     * @return
     */
    public String layout(){
        ServletActionContext.getRequest().getSession().invalidate();
        return LOGIN;
    }

}
