package model;

import java.sql.Timestamp;

public class QNAreplyDTO {

  private int reply_num;
  private String admin_id, reply_cnt;
  private Timestamp reply_date;
  private int post_num;
  private String post_cnt;
  
public int getReply_num() {
	return reply_num;
}
public void setReply_num(int reply_num) {
	this.reply_num = reply_num;
}
public String getAdmin_id() {
	return admin_id;
}
public void setAdmin_id(String admin_id) {
	this.admin_id = admin_id;
}
public String getReply_cnt() {
	return reply_cnt;
}
public void setReply_cnt(String reply_cnt) {
	this.reply_cnt = reply_cnt;
}
public Timestamp getReply_date() {
	return reply_date;
}
public void setReply_date(Timestamp reply_date) {
	this.reply_date = reply_date;
}
public int getPost_num() {
	return post_num;
}
public void setPost_num(int post_num) {
	this.post_num = post_num;
}
public String getPost_cnt() {
	return post_cnt;
}
public void setPost_cnt(String post_cnt) {
	this.post_cnt = post_cnt;
}


}
