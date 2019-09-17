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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

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

    /**
     * 更新用户信息
     * @param userId
     * @param email
     * @param upload
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload.do")
    public String userInfoChange(Integer userId, String email, MultipartFile upload, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(email != null){
            userService.updateUserEmail(userId,email);
        }

        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        String filename = upload.getOriginalFilename();
        if(filename == null || "".equals(filename)){
            UserInfo user = new UserInfo();
            user.setUserId(userId);
            UserInfo u = userService.findUserInfo(user);
            request.getSession().setAttribute("user",u);
            return "userInfo";
        }

        String uuid = UUID.randomUUID().toString().replace("-", "");
        int i = filename.lastIndexOf(".");
        filename = filename.substring(i);
        filename = uuid+filename;
        upload.transferTo(new File(path,filename));
        String saveName = "uploads/"+filename;
        userService.updateUserPicture(userId,saveName);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        UserInfo u = userService.findUserInfo(userInfo);
        request.getSession().setAttribute("user",u);

        return "userInfo";
    }

    /**
     * 查找用户头像
     * @param userId
     * @return
     */
    @RequestMapping("/findUserPicture.do")
    @ResponseBody
    public String findUserPicture(String userId){
        String path = userService.findUserPicture(userId);
        return path;
    }

    /**
     * 邮箱校验
     * @param email
     * @param response
     * @return
     */
    @RequestMapping("/checkUserEmail.do")
    @ResponseBody
    public String checkUserEmail(String email ,HttpServletResponse response){
        response.setContentType("UTF-8");
        Boolean b = userService.checkUserEmail(email);
        if(b){
            //该邮箱已被注册,不能使用
            return "error";
        }else{
            //该邮箱未被注册,可以使用
            return "success";
        }
    }

    /**
     * 展示用户信息
     * @param request
     * @return
     */
    @RequestMapping("/findUserInfo.do")
    public String findUserInfo(HttpServletRequest request){
        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
        UserInfo userInfo1 = userService.findById(userInfo.getUserId());
        request.getSession().setAttribute("user",userInfo1);
        return "userInfo";
    }

    @RequestMapping("/findUserPwd.do")
    public String findUserPwd(){
        return "userPwd";
    }

}
