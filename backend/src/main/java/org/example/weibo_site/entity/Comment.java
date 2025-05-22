package org.example.weibo_site.entity;

public class Comment {
    private String username;
    private String avatar;
    private String content;
    private String date;

    public Comment(String username, String avatar, String content, String date) {
        this.username = username;
        this.avatar = avatar;
        this.content = content;
        this.date = date;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
