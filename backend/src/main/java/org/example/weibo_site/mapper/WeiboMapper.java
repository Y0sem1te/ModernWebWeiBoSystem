package org.example.weibo_site.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.weibo_site.entity.UploadWeibo;
import org.example.weibo_site.entity.Weibo;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Mapper
public interface WeiboMapper {
    @Select("SELECT * FROM weibo ORDER BY RAND() LIMIT 5;")
    public List<Weibo>fetchWeibo();

    //faweibo
    @Insert("Insert into weibo(content, tupian, uid, `time`) values(#{content},#{tupian},#{uid},#{time});")
    public int insertWeibo(String content, String tupian, String uid, String time);
}