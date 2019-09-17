package com.bbs.controller;


import com.bbs.domain.UserInfo;
import com.bbs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登入
     * @param userName
     * @param userPass
     * @param request
     * @param model
     * @return
     */
    @PostMapping("/userLogin.do")
    @ResponseBody
    public String userLogin(@Param("userName")String userName, @Param("userPass")String userPass, HttpServletRequest request, Model model){
        UserInfo user = new UserInfo();
        user.setUserName(userName);
        user.setUserPass(userPass);
        UserInfo u = userService.login(user);
        if(u == null){
            //登录失败
            return "false";
        }else{
            //登录成功
            //request.getSession().setAttribute("username",u.getUsername());
            userService.updateLoginStatus(u.getUserId(), 1);
            request.getSession().setAttribute("user",u);
            System.out.println(u);
            return "true";
        }
    }

    /**
     * 用户注销
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/userExist.do")
    public void userExist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        if(userInfo != null){
            userService.updateLoginStatus(userInfo.getUserId(),0);
        }
        request.getSession().removeAttribute("user");

        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    //跳转登入
    @RequestMapping("/findRegister.do")
    public String findRedister(){
        return "register";
    }

}
