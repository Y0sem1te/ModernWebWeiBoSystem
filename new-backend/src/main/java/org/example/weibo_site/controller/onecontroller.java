package org.example.weibo_site.controller;
import org.example.weibo_site.configclass.FileUtils;
import org.example.weibo_site.entity.*;
import org.example.weibo_site.mapper.CommentMapper;
import org.example.weibo_site.mapper.UserMapper;
import org.example.weibo_site.mapper.WeiboMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin
public class onecontroller {
    @Autowired
    private WeiboMapper weiboMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentmapper;

    private List<WeiboWithTotalPic> weiboList;

    @PostMapping("/purposely_fetch")
    public List<WeiboWithTotalPic> purposely_fetch(@RequestBody Username username){
        List<Weibo> weibos = weiboMapper.fetchWeiboByPage(username.getId());
        List<WeiboWithTotalPic> wtp = new ArrayList<>();
        this.weiboList = wtp;
        for (Weibo weibo : weibos) {
            weibo.setVideo("http://127.0.0.1:8088/images/"+weibo.getVideo());
            String[] files = weibo.getTupian().split(",");
            for (int j = 0; j < files.length; j++) {
                files[j] = "http://127.0.0.1:8088/images/" + files[j];
            }
            String uid = weibo.getUid();
            PassPhoto passPhoto = userMapper.findPassword(uid);
            String avatar = "http://127.0.0.1:8088/images/" + passPhoto.getPhoto();

            boolean liked = false;
            List<String> userlist = commentmapper.findUserIdFromLike(weibo.getId());
            if (userlist != null) {
                for (String cuid : userlist) {
                    if (cuid == null) continue;
                    if (cuid.equals(username.getId())) {
                        liked = true;
                        break;
                    }
                }
            }
            wtp.add(new WeiboWithTotalPic(weibo.getId(), weibo.getContent(), files, weibo.getUid(), weibo.getTime(), weibo.getLike(), avatar, weibo.getComment(), liked, weibo.getVideo(),weibo.getApproved()));
        }
        return wtp;
    }

    @PostMapping("/fetch_weibo")
    public List<WeiboWithTotalPic> fetch_weibo(@RequestBody Username username){
        List<Weibo> weibos = weiboMapper.fetchWeibo();
        List<WeiboWithTotalPic> wtp = new ArrayList<>();
        for (Weibo weibo : weibos) {
            weibo.setVideo("http://127.0.0.1:8088/images/"+weibo.getVideo());
            String[] files = weibo.getTupian().split(",");
            for (int j = 0; j < files.length; j++) {
                files[j] = "http://127.0.0.1:8088/images/" + files[j];
            }
            String uid = weibo.getUid();
            PassPhoto passPhoto = userMapper.findPassword(uid);
            String avatar = "http://127.0.0.1:8088/images/" + passPhoto.getPhoto();

            boolean liked = false;
            List<String> userlist = commentmapper.findUserIdFromLike(weibo.getId());
            if (userlist != null) {
                for (String cuid : userlist) {
                    if (cuid == null) continue;
                    if (cuid.equals(username.getId())) {
                        liked = true;
                        break;
                    }
                }
            }
            wtp.add(new WeiboWithTotalPic(weibo.getId(), weibo.getContent(), files, weibo.getUid(), weibo.getTime(), weibo.getLike(), avatar, weibo.getComment(), liked, weibo.getVideo(),weibo.getApproved()));
        }
        return wtp;
    }
    @PostMapping("/upload_weibo")
    public String uploadWeibo( @RequestParam("content")String content,
                               @RequestParam("uid")String uid,
                               @RequestParam("time")String time,
                               @RequestParam(value="images[]", required = false) MultipartFile[] files,
                               @RequestParam(value="video", required = false) MultipartFile video
    ) throws IOException{
        List<String> urls=new ArrayList<>();
        int success=0;
        String filenames="", videoname="";
        int flag=0;
        String[] checklist = {".jpg", ".jpeg", ".png", ".gif", ".jfif"};
        String[] checklist2 = {".mp4", ".mov", ".avi", ".mpg", ".mpeg", ".mpg", ".mpg", ".flv"};
        Path path1 = Paths.get("E:\\java_file\\weibo_site\\src\\main\\resources\\static\\images" + "/");
        if(files != null) {
            for (MultipartFile file : files) {
                byte[] bytes = file.getBytes();
                String extension = FileUtils.getFileExtension(file);
                // 文件上传格式检查
                int f1 = 0;
                for(String it:checklist){
                    if (it.equals(extension.toLowerCase())) {
                        f1 = 1;
                        break;
                    }
                }
                //后缀正确再保存
                if(f1 == 0) continue;
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
        //处理视频
        if(video != null) {
            byte[] bytes = video.getBytes();
            String extension = FileUtils.getFileExtension(video);
            int f1 = 0;
            for(String it:checklist2){
                if (it.equals(extension.toLowerCase())) {
                    f1 = 1;
                    break;
                }
            }
            if(f1 == 1){
                String filename = video.getOriginalFilename();
                FileUtils.getFileByBytes(bytes, FileUtils.UPLOAD_FOLDER, filename);
                videoname += filename;
            }
        }
        if(weiboMapper.insertWeibo(content,filenames,uid,time, videoname)!=0)success++;
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
    @PostMapping("/pass")
    public String pass(@RequestBody WeiboId weiboid){
        int res = weiboMapper.pass(Integer.parseInt(weiboid.getId()));
        if(res == 0)return "fail";
        return "pass";
    }

    @PostMapping("/review")
    public List<WeiboWithTotalPic> review(@RequestBody Username username){
        List<Weibo> weibos = weiboMapper.getdWeiboList();
        List<WeiboWithTotalPic> wtp = new ArrayList<>();
        for (Weibo weibo : weibos) {
            weibo.setVideo("http://127.0.0.1:8088/images/"+weibo.getVideo());
            String[] files = weibo.getTupian().split(",");
            for (int j = 0; j < files.length; j++) {
                files[j] = "http://127.0.0.1:8088/images/" + files[j];
            }
            String uid = weibo.getUid();
            PassPhoto passPhoto = userMapper.findPassword(uid);
            String avatar = "http://127.0.0.1:8088/images/" + passPhoto.getPhoto();

            boolean liked = false;
            List<String> userlist = commentmapper.findUserIdFromLike(weibo.getId());
            if (userlist != null) {
                for (String cuid : userlist) {
                    if (cuid == null) continue;
                    if (cuid.equals(username.getId())) {
                        liked = true;
                        break;
                    }
                }
            }
            wtp.add(new WeiboWithTotalPic(weibo.getId(), weibo.getContent(), files, weibo.getUid(), weibo.getTime(), weibo.getLike(), avatar, weibo.getComment(), liked, weibo.getVideo(),weibo.getApproved()));
        }
        return wtp;
    }
}
