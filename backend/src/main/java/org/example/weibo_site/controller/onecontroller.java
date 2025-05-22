package org.example.weibo_site.controller;
import org.example.weibo_site.configclass.FileUtils;
import org.example.weibo_site.entity.*;
import org.example.weibo_site.mapper.CommentMapper;
import org.example.weibo_site.mapper.UserMapper;
import org.example.weibo_site.mapper.WeiboMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@RestController
@CrossOrigin
public class onecontroller {
    @Autowired
    private WeiboMapper weiboMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentmapper;
    @PostMapping("/fetch_weibo")
    public List<WeiboWithTotalPic> fetch_weibo(@RequestBody Username username){
        List<Weibo> weibos = weiboMapper.fetchWeibo();
        //System.out.println(username.getId());
        List<WeiboWithTotalPic> wtp = new ArrayList<WeiboWithTotalPic>();
        for(int i=0; i<weibos.size(); i++){
            Weibo weibo = weibos.get(i);
            String[] files=weibo.getTupian().split(",");
            for(int j=0; j<files.length; j++){
                files[j]="http://127.0.0.1:8088/images/"+files[j];
            }
            String uid=weibo.getUid();
            PassPhoto passPhoto=userMapper.findPassword(uid);
            String avatar="http://127.0.0.1:8088/images/"+passPhoto.getPhoto();

            boolean liked=false;
            List<String> userlist = commentmapper.findUserIdFromLike(weibo.getId());
            if(userlist!=null) {
                for (String cuid : userlist) {
                    if(cuid==null) continue;
                    if (cuid.equals(username.getId())) {
                        liked = true;
                        break;
                    }
                }
            }
            wtp.add(new WeiboWithTotalPic(weibo.getId(),weibo.getContent(),files,weibo.getUid(),weibo.getTime(),weibo.getLike(),avatar,weibo.getComment(),liked));
        }
        return wtp;
    }
    @PostMapping("/upload_weibo")
    public String uploadWeibo( @RequestParam("content")String content,  @RequestParam("uid")String uid,  @RequestParam("time")String time, @RequestParam(value="images[]", required = false) MultipartFile[] files) throws IOException{
        List<String> urls=new ArrayList<>();
        int success=0;
        String filenames="";
        int flag=0;
        if(files!=null&&files.length>0) {
            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("E:\\java_file\\weibo_site\\src\\main\\resources\\static\\images" + "/");
                String extension = FileUtils.getFileExtension(file);
                String filename = file.getOriginalFilename();
                FileUtils.getFileByBytes(bytes, FileUtils.UPLOAD_FOLDER, filename);
                if (flag == 0) {
                    filenames += filename;
                    flag = 1;
                } else {
                    filenames = filenames + "," + filename;
                }
            }
        }
        if(weiboMapper.insertWeibo(content,filenames,uid,time)!=0)success++;
        return success+"";
    }
    @PostMapping("/find_fellos")
    public List<IdPic> find_fellos(@RequestBody Username username){
        List<Fello> names = userMapper.find_fello_name(username.getId());
        List<IdPic> idPics = new ArrayList<>();
        for(Fello fello:names){
            Avatar avatar=userMapper.findAvatar(fello.getFollowerid());
            idPics.add(new IdPic(fello.getFollowerid(), "http://127.0.0.1:8088/images/"+avatar.getPhoto()));
        }
        return idPics;
    }
}
