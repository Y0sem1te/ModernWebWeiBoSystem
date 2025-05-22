package org.example.weibo_site.mapper;

import org.apache.ibatis.annotations.*;
import org.example.weibo_site.entity.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//操作的表名加Mapper
@Mapper
public interface UserMapper {
    //查询所有用户
    @Select("select * from user")
    public List<User>find();

    @Select("select banner,password,photo from user where id=#{userid};")
    public PassPhoto findPassword(String userid);

    @Insert("insert into `user`(`id`, `password`, `photo`,`fans`,`followers`) values(#{username},#{password},#{photo},0,0);")
    public int insert(User user);

    @Select("select COUNT(*) from user where id=#{username};")
    public String findUsername(String username);

    @Insert("UPDATE `user` SET photo = #{avatar} WHERE id = #{id};")
    public int insertPhoto(String id, String avatar);

    @Insert("UPDATE `user` SET banner = #{banner} WHERE id = #{id};")
    public int updateBanner(String id, String banner);

    @Insert("UPDATE `user` SET `password` = #{password} WHERE id = #{username};")
    public int updatePassword(String username, String password);

    @Select("select `banner`, `photo`, `fans`, `followers`,`id` from user where id=#{id}")
    public HomePage findHomePage(String id);

    /*1.user表-followerid粉丝数++，userid关注数++
     * 2.follow表userid关注了followid*/
    @Update("UPDATE user SET followers = followers + 1 WHERE id = #{id};")
    public int AddFollows(String id);

    @Update("UPDATE user SET fans = fans + 1 WHERE id = #{id};")
    public int AddFans(String id);

    @Insert("INSERT INTO follow (userid, followerid) VALUES (#{userid}, #{followerid});")
    public int DealFans(String userid, String followerid);

    //fanxiangcaozuo
    @Update("UPDATE user SET followers = followers - 1 WHERE id = #{id};")
    public int SubFollows(String id);

    @Update("UPDATE user SET fans = fans - 1 WHERE id = #{id};")
    public int SubFans(String id);

    @Insert("DELETE FROM follow WHERE userid = #{userid} AND followerid = #{followerid};")
    public int DeleFans(String userid, String followerid);

    @Select("SELECT CASE WHEN EXISTS (SELECT 1 FROM follow WHERE userid = #{userId} AND followerid = #{followerId}) THEN 1 ELSE 0 END")
    public int checkFollowing(String userId, String followerId);

    @Select("select followerid from follow where userid=#{username}")
    public List<Fello> find_fello_name(String username);

    @Select("select `photo` from `user` where id=#{username}")
    public Avatar findAvatar(String username);
}