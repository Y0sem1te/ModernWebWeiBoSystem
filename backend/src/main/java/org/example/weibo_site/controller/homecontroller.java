package org.example.weibo_site.controller;
import org.example.weibo_site.entity.*;
import org.example.weibo_site.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
public class homecontroller {
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/homepage")
    public HomePage catch_homepage(@RequestBody Username username){
        //System.out.println(username.getId());
        HomePage bnv = userMapper.findHomePage(username.getId());
        bnv.setPhoto("http://127.0.0.1:8088/images/"+bnv.getPhoto());
        bnv.setBanner("http://127.0.0.1:8088/images/"+bnv.getBanner());
        return bnv;
    }
    @PostMapping("/ffollow")
    public String follow(@RequestBody Guan guan){
        /*1.user表-followerid粉丝数++，userid关注数++
        * 2.follow表userid关注了followid*/
        int res1 = userMapper.AddFans(guan.getFollowerid());
        int res2 = userMapper.AddFollows(guan.getUserid());
        int res3 = userMapper.DealFans(guan.getUserid(), guan.getFollowerid());
        if(res1!=0&&res2!=0&&res3!=0){return "success";}
        System.out.println(res2);
        return "fail";
    }
    @PostMapping("/tfollow")
    public String _follow(@RequestBody Guan guan){
        /*1.user表-followerid粉丝数++，userid关注数++
         * 2.follow表userid关注了followid*/
        int res1 = userMapper.SubFans(guan.getFollowerid());
        int res2 = userMapper.SubFollows(guan.getUserid());
        int res3 = userMapper.DeleFans(guan.getUserid(), guan.getFollowerid());
        if(res1!=0&&res2!=0&&res3!=0){return "success";}
        return "fail";
    }
    @GetMapping("/fetch_fello")
    public String fetch_fellos(@RequestParam("id") String id, @RequestParam("fello") String fello){
        int res=userMapper.checkFollowing(id, fello);
        return res+"";
    }
}
