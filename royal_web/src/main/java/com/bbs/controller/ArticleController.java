package com.bbs.controller;

import com.bbs.domain.Reply;
import com.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("reply")
    public String reply(Reply reply){
        articleService.saveComment(reply);
        return null;
    }
}
