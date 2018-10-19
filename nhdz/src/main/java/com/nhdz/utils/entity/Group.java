package com.nhdz.utils.entity;

import java.util.ArrayList;

public class Group {
	
	private int tid;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	private User user;
	
	private String text;
	
	private String create_time;
	
	private String id;
	
	private int favorite_count;
	
	private int go_detail_count;
	
	private int share_type;
	
	private int is_can_share;
	
	private int comment_count;
	
	private String share_url;
	
	private int user_favorite;
	
	public int getUser_favorite() {
		return user_favorite;
	}
	public void setUser_favorite(int user_favorite) {
		this.user_favorite = user_favorite;
	}
	private ArrayList activity;
	
	public ArrayList getActivity() {
		return activity;
	}
	public void setActivity(ArrayList activity) {
		this.activity = activity;
	}
	private String label;
	
	private String content;
	
	private String category_type;
	
	private String id_str;
	
	private String media_type;
	
	private String  share_count;
	
	private int type;
	
	private int status;
	
	private int has_comments;
	
	private int user_bury;
	
	private String status_desc;
	
	private int user_digg;
	
	private String online_time;
	
	private String category_name;
	
	private String category_visible;
	
	//踩
	private int bury_count;
	
	private String is_anonymous;
	
	//收藏
	private int repin_count;
	//赞
	private int digg_count;
	private int has_hot_comments;
	private int user_repin;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFavorite_count() {
		return favorite_count;
	}
	public void setFavorite_count(int favorite_count) {
		this.favorite_count = favorite_count;
	}
	public int getGo_detail_count() {
		return go_detail_count;
	}
	public void setGo_detail_count(int go_detail_count) {
		this.go_detail_count = go_detail_count;
	}
	public int getShare_type() {
		return share_type;
	}
	public void setShare_type(int share_type) {
		this.share_type = share_type;
	}
	public int getIs_can_share() {
		return is_can_share;
	}
	public void setIs_can_share(int is_can_share) {
		this.is_can_share = is_can_share;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public String getShare_url() {
		return share_url;
	}
	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory_type() {
		return category_type;
	}
	public void setCategory_type(String category_type) {
		this.category_type = category_type;
	}
	public String getId_str() {
		return id_str;
	}
	public void setId_str(String id_str) {
		this.id_str = id_str;
	}
	public String getMedia_type() {
		return media_type;
	}
	public void setMedia_type(String media_type) {
		this.media_type = media_type;
	}
	public String getShare_count() {
		return share_count;
	}
	public void setShare_count(String share_count) {
		this.share_count = share_count;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getHas_comments() {
		return has_comments;
	}
	public void setHas_comments(int has_comments) {
		this.has_comments = has_comments;
	}
	public int getUser_bury() {
		return user_bury;
	}
	public void setUser_bury(int user_bury) {
		this.user_bury = user_bury;
	}
	public String getStatus_desc() {
		return status_desc;
	}
	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}
	public int getUser_digg() {
		return user_digg;
	}
	public void setUser_digg(int user_digg) {
		this.user_digg = user_digg;
	}
	public String getOnline_time() {
		return online_time;
	}
	public void setOnline_time(String online_time) {
		this.online_time = online_time;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_visible() {
		return category_visible;
	}
	public void setCategory_visible(String category_visible) {
		this.category_visible = category_visible;
	}
	public int getBury_count() {
		return bury_count;
	}
	public void setBury_count(int bury_count) {
		this.bury_count = bury_count;
	}
	public String getIs_anonymous() {
		return is_anonymous;
	}
	public void setIs_anonymous(String is_anonymous) {
		this.is_anonymous = is_anonymous;
	}
	public int getRepin_count() {
		return repin_count;
	}
	public void setRepin_count(int repin_count) {
		this.repin_count = repin_count;
	}
	public int getDigg_count() {
		return digg_count;
	}
	public void setDigg_count(int digg_count) {
		this.digg_count = digg_count;
	}
	public int getHas_hot_comments() {
		return has_hot_comments;
	}
	public void setHas_hot_comments(int has_hot_comments) {
		this.has_hot_comments = has_hot_comments;
	}
	public int getUser_repin() {
		return user_repin;
	}
	public void setUser_repin(int user_repin) {
		this.user_repin = user_repin;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	private String group_id;
	private int category_id;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
