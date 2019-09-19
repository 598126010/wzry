package com.bbs.manage.controller;

import com.bbs.domain.ZoneApply;
import com.bbs.service.ZoneApplyService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("zoneApply")
public class ZoneApplyController {
    @Autowired
    ZoneApplyService zoneApplyService;
//    查询所有板块
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") int page, @RequestParam(name = "size",required = true,defaultValue = "5") int size,String zoneName,String userName) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        List<ZoneApply> list=zoneApplyService.findAll(page,size,zoneName,userName);
        PageInfo pageInfo=new PageInfo(list);
        mv.addObject("ZoneApply",pageInfo);
        mv.addObject("zoneName",zoneName);
        mv.addObject("userName",userName);
        mv.setViewName("bankuai");
        return mv;
    }
//    板块开启/关闭
    @RequestMapping("/kaiqi.do")
    public String kaiqi(Integer applyZoneId,Integer status,String zoneName,Integer page){
        try {
            zoneName  = new String(zoneName.getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        zoneApplyService.kaiqi(applyZoneId,status,zoneName);
        return "redirect:/zoneApply/findAll.do?page="+page;
}
}
