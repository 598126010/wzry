package com.bbs.manage.controller;

import com.bbs.domain.UserInfo;
import com.bbs.service.Manage_UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    Manage_UserService manage_userService;
//分页查询
    @RequestMapping("/findByPage.do")
    public ModelAndView findByPage(@RequestParam(name = "page",required = true,defaultValue = "1") int page, @RequestParam(name = "size",required = true,defaultValue = "5") int size,String userName,Integer role){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list=manage_userService.findByPage(page,size,userName,role);
        PageInfo pageInfo=new PageInfo(list);
        mv.addObject("userInfo",pageInfo);
        mv.addObject("role",role);
        mv.addObject("userName",userName);
        mv.setViewName("yonghu");
        return mv;
    }
//   升级
    @RequestMapping("/promote.do")
    public String shengji(String id,Integer page){
        manage_userService.shengji(id);
        return "redirect:/user/findByPage.do?page="+page;
    }
//    禁言/恢复
    @RequestMapping("/jingyan.do")
    public String jingyan(int userId,int talkStatus,Integer page){
        manage_userService.jingyan(userId,talkStatus);
        return "redirect:/user/findByPage.do?page="+page;
    }

}
