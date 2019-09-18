package com.bbs.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UpvoteDao {

    @Select("select isUpvote from bbs_upvote_table where upvoteArticleId=#{articleId} and upvoteUserName=#{userName}")
    Integer findIsUpvote(@Param("articleId") String articleId, @Param("userName") String userName);

    @Update("update bbs_upvote_table set isUpvote = #{i} where upvoteArticleId=#{articleId} and upvoteUserName=#{userName}")
    void changeIsUpvote(@Param("articleId") String articleId, @Param("userName") String userName, @Param("i") Integer i);

    @Insert("insert into bbs_upvote_table (upvoteUserName,upvoteArticleId,isUpvote) values (#{userName},#{articleId},#{i})")
    void addUpvote(@Param("articleId") String articleId, @Param("userName") String userName, @Param("i") Integer i);

}
