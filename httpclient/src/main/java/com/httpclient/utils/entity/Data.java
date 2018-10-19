/**
  * Copyright 2018 bejson.com 
  */
package com.httpclient.utils.entity;
import java.util.List;

/**
 * Auto-generated: 2018-01-05 12:17:57
 */
public class Data {

    @Override
	public String toString() {
		return "JsonRootBean [group=" + group + ", comments=" + comments + ", type=" + type + ", display_time="
				+ display_time + ", online_time=" + online_time + "]";
	}
	private Group group;
    private List<String> comments;
    private int type;
    private long display_time;
    private long online_time;
    public void setGroup(Group group) {
         this.group = group;
     }
     public Group getGroup() {
         return group;
     }

    public void setComments(List<String> comments) {
         this.comments = comments;
     }
     public List<String> getComments() {
         return comments;
     }

    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    public void setDisplay_time(long display_time) {
         this.display_time = display_time;
     }
     public long getDisplay_time() {
         return display_time;
     }

    public void setOnline_time(long online_time) {
         this.online_time = online_time;
     }
     public long getOnline_time() {
         return online_time;
     }

}