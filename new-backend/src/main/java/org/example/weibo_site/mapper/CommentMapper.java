package org.example.weibo_site.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.weibo_site.entity.Comment_;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
/*
* 从comment表拿到weiboid的所有评论的userid， content， date
* 从user表拿到userid的头像
*/
public interface CommentMapper {
    @Select("select userid, content, `date` from comment where toward=#{weiboid}")
    public List<Comment_> find_exava(String weiboid);

    @Select("select photo from user where id=#{userid}")
    public String findava(String userid);

    @Select("SELECT DISTINCT userid FROM `like` WHERE `weiboid` = #{weiboid}")
    public List<String> findUserIdFromLike(int weiboid);

    @Insert("insert into `like`(userid, weiboid) values(#{userid},#{weiboid});")
    public int whoLike(String userid, int weiboid);
    
    @Update("UPDATE `weibo` SET `likes` = `likes` + 1 WHERE id = #{weiboid};")
    public int addLike(String userid, int weiboid);

    @Insert("Delete from `like` where `userid`=#{userid} and `weiboid`=#{weiboid};")
    public int cwhoLike(String userid, int weiboid);

    @Update("UPDATE `weibo` SET `likes` = `likes` - 1 WHERE id = #{weiboid};")
    public int caddLike(String userid, int weiboid);

    @Insert("insert into comment(content, userid, toward, date) values(#{content}, #{userid}, #{weiboid}, #{date})")
    public int whoComment(String userid, int weiboid, String content, String date);

    @Update("update weibo set comments=comments+1 where id=#{weiboid};")
    public int addComment(int weiboid);
}
