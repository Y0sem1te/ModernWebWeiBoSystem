package org.example.weibo_site.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.weibo_site.entity.UploadWeibo;
import org.example.weibo_site.entity.Weibo;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Mapper
public interface WeiboMapper {
    @Select("SELECT * FROM weibo WHERE approved=0")
    public List<Weibo> getdWeiboList();

    @Select("SELECT * FROM weibo WHERE approved=1")
    public List<Weibo>fetchWeibo();

    @Select("select * from weibo where uid=#{username} and approved=1;")
    public List<Weibo>fetchWeiboByPage(String username);

    @Update("UPDATE weibo SET approved = 1 WHERE id = #{wid}")
    public int pass(int wid);

    //faweibo
    @Insert("Insert into weibo(content, tupian, uid, `time`, `video`, approved) values(#{content},#{tupian},#{uid},#{time},#{video},0);")
    public int insertWeibo(String content, String tupian, String uid, String time, String video);
}