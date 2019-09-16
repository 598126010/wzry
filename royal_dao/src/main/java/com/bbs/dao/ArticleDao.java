package com.bbs.dao;

import com.bbs.domain.Reply;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
@Repository
public interface ArticleDao {
    @Insert("insert into bbs_reply_table(replyContent,replyUserName) values(#{replyContent},#{replyUserName})")
    void saveComment(Reply comment);
}
