package com.bbs.controller;

import com.bbs.service.UserOnlineService;
import com.bbs.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("userOnline")
public class UserOnlineController {
    @Autowired
    private UserOnlineService userOnlineService;

    @RequestMapping("viewOnlineUser")
        public ModelAndView onlineUser(UserInfo userInfo){
            ModelAndView mv = new ModelAndView();
            userOnlineService.findOnlineUser(userInfo.getUserId());
            mv.addObject("userinfo",userInfo);
            return mv;
        }
}
