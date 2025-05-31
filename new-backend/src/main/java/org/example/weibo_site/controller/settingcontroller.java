package org.example.weibo_site.controller;
import org.example.weibo_site.configclass.FileUtils;
import org.example.weibo_site.entity.User_;
import org.example.weibo_site.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
public class settingcontroller {
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/upload_avatar")
    public String upload_image(@RequestParam("avatar") MultipartFile file, @RequestParam("username") String id) throws IOException {
        byte[] bytes=file.getBytes();
        Path path = Paths.get("E:\\java_file\\weibo_site\\src\\main\\resources\\static\\images"+"/");
        String extension= FileUtils.getFileExtension(file);
        String filename = file.getOriginalFilename();
        FileUtils.getFileByBytes(bytes, FileUtils.UPLOAD_FOLDER,filename);
        int res = userMapper.insertPhoto(id, filename);
        return "http://127.0.0.1:8088/images/"+ filename;
    };

    @PostMapping("/update_password")
    public String update_password(@RequestBody User_ user){
        System.out.println("username:"+ user.getUsername()+"newPassword:"+ user.getPassword());
        int res = userMapper.updatePassword(user.getUsername(), user.getPassword());
        return res+"";
    }

    @PostMapping("/upload_banner")
    public String upload_banner(@RequestParam("banner") MultipartFile file, @RequestParam("username") String id) throws IOException {
        System.out.println(id);
        byte[] bytes=file.getBytes();
        Path path = Paths.get("E:\\java_file\\weibo_site\\src\\main\\resources\\static\\images" + "/");
        String extension = FileUtils.getFileExtension(file);
        String filename = file.getOriginalFilename();
        FileUtils.getFileByBytes(bytes, FileUtils.UPLOAD_FOLDER,filename);
        int res = userMapper.updateBanner(id, filename);
        return "http://127.0.0.1:8088/images/"+ filename;
    };
}
