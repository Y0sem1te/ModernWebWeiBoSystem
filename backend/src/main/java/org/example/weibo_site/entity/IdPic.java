package org.example.weibo_site.entity;

public class IdPic {
    String id;
    String avatar;

    public IdPic(String id, String avatar) {
        this.id = id;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
