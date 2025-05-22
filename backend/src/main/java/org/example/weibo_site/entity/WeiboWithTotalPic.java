package org.example.weibo_site.entity;

public class WeiboWithTotalPic {
    private int id;
    private String content;
    private String[] photos;
    private String uid;
    private String time;
    private int likes;
    private String _avatar;
    private int comments;
    private boolean liked;

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getAvatar() {
        return _avatar;
    }

    public String get_avatar() {
        return _avatar;
    }

    public void set_avatar(String _avatar) {
        this._avatar = _avatar;
    }

    public int getComment() {
        return comments;
    }

    public void setComment(int comment) {
        this.comments = comment;
    }

    public void setAvatar(String avatar) {
        this._avatar = avatar;
    }

    public WeiboWithTotalPic(int id, String content, String[] photos, String uid, String time, int like, String _avatar, int comment,boolean liked) {
        this.id = id;
        this.liked = liked;
        this.comments = comment;
        this.content = content;
        this.photos = photos;
        this.uid = uid;
        this.time = time;
        this.likes = like;
        this._avatar = _avatar;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLike() {
        return likes;
    }

    public void setLike(int like) {
        this.likes = like;
    }
}
