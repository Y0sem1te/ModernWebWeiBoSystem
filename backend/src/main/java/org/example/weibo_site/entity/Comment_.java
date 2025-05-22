package org.example.weibo_site.entity;

public class Comment_ {
    private String userid;
    private String content;
    private String date;

    public String getUsername() {
        return userid;
    }

    public void setUsername(String username) {
        this.userid = username;
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
