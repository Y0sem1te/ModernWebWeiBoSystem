package org.example.weibo_site.controller;
import org.example.weibo_site.entity.*;
import org.example.weibo_site.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class commentcontroller {
    @Autowired
    private CommentMapper commentmapper;
    @PostMapping("/fetch_comments")
    public List<Comment> fetchcomments(@RequestBody WeiboId weiboid) {
        List<Comment_> exava = commentmapper.find_exava(weiboid.getId());
        List<Comment> comments = new ArrayList<Comment>();
        for(Comment_ c : exava){
            String avatar = commentmapper.findava(c.getUsername());
            comments.add(new Comment(c.getUsername(),"http://127.0.0.1:8088/images/"+avatar,c.getContent(),c.getDate()));
        }
        return comments;
    }
//   看有没有点过赞
    @PostMapping("/dealLike")
    public String deal_like(@RequestBody IntWeiboId weiboid) {
        List<String> userlist = commentmapper.findUserIdFromLike(weiboid.getId());
        boolean liked = false;
        for(String uid : userlist){
            if(uid.equals(weiboid.getUserid())){
                liked = true;
                break;
            }
        }
        if(liked==true) return "success";
        else return "fail";
    }
    //记录点赞，谁给谁点过赞，点赞数加1
    @PostMapping("/addLike")
    public String processLike(@RequestBody IntWeiboId weiboid) {
        int res1 = commentmapper.whoLike(weiboid.getUserid(), weiboid.getId());
        int res2 = commentmapper.addLike(weiboid.getUserid(), weiboid.getId());
        if(res1==1 && res2==1){
            return "success";
        }
        else return "fail";
    }
    @PostMapping("/subLike")
    public String subLike(@RequestBody IntWeiboId weiboid) {
        int res1 = commentmapper.cwhoLike(weiboid.getUserid(), weiboid.getId());
        int res2 = commentmapper.caddLike(weiboid.getUserid(), weiboid.getId());
        if(res1==1 && res2==1){
            return "success";
        }
        else return "fail";
    }
    @PostMapping("/postComment")
    public String postComment(@RequestBody UserComment uct) {
        //comment存储，weibo中的微博id字段的评论数加一
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String date = now.format(formatter);
        int res1 = commentmapper.whoComment(uct.getUserId(), uct.getWeiboId(), uct.getContent(), date);
        int res2 = commentmapper.addComment(uct.getWeiboId());
        if(res1!=0 && res2!=0)return "success";
        else return "fail";
    }
}
