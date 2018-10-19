package com.httpclient.utils.entity;

/**
 * Auto-generated: 2018-01-05 12:17:57
 */
public class User {

    private boolean is_following;
    private String avatar_url;
    private long user_id;
    private String name;
    private boolean user_verified;
    public void setIs_following(boolean is_following) {
         this.is_following = is_following;
     }
     public boolean getIs_following() {
         return is_following;
     }

    public void setAvatar_url(String avatar_url) {
         this.avatar_url = avatar_url;
     }
     public String getAvatar_url() {
         return avatar_url;
     }

    public void setUser_id(long user_id) {
         this.user_id = user_id;
     }
     public long getUser_id() {
         return user_id;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setUser_verified(boolean user_verified) {
         this.user_verified = user_verified;
     }
     public boolean getUser_verified() {
         return user_verified;
     }

}