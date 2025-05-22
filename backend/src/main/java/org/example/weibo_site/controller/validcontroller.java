package org.example.weibo_site.controller;
import org.example.weibo_site.entity.BannerAvatar1;
import org.example.weibo_site.entity.PassPhoto;
import org.example.weibo_site.entity.User;
import org.example.weibo_site.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class validcontroller {
    @Autowired
    private UserMapper usermapper;

    @PostMapping("/login")
    public BannerAvatar1 login(@RequestBody User user) {
        String uid=user.getUsername();
        String password=user.getPassword();
        String photo=user.getPhoto();
        System.out.println(uid);
        System.out.println(password);
        PassPhoto realpsd = usermapper.findPassword(uid);
        if(realpsd.getPassword()==null)return new BannerAvatar1("-1","");//"-1";
        if(realpsd.getPassword().equals(password)){
            return new BannerAvatar1("http://127.0.0.1:8088/images/"+realpsd.getBanner(), "http://127.0.0.1:8088/images/"+realpsd.getPhoto());
        }
        return new BannerAvatar1("false","");//"false";
    }
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        String uid=user.getUsername();
        String password=user.getPassword();
        String photo=user.getPhoto();
        System.out.println(uid);
        System.out.println(password);
        if(usermapper.findUsername(user.getUsername()).equals("1"))return null;
        else {
            usermapper.insert(user);
            return "success";
        }
    }
}
