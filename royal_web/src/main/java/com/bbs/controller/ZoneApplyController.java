package com.bbs.controller;

import com.bbs.common.CommonCode;
import com.bbs.common.ResponseResult;
import com.bbs.domain.UserInfo;
import com.bbs.domain.ZoneApply;
import com.bbs.service.ZoneApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("zoneApply")
public class ZoneApplyController {

    @Autowired
    private ZoneApplyService zoneApplyService;

    //增加板块
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult add(@RequestBody ZoneApply zoneApply, HttpServletRequest request){
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        zoneApply.setUserName(user.getUserName());
        zoneApply.setStatus(0);
        try {
            zoneApplyService.add(zoneApply);
            return new ResponseResult(CommonCode.SUCCESS);
        }catch (Exception e){
            return new ResponseResult(CommonCode.FAIL);
        }


    }
}
