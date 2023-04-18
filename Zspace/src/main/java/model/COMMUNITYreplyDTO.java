package model;

import java.sql.*;

public class COMMUNITYreplyDTO {

	//댓글
	private int post_num; //게시판의 게시물 번호
	private String mem_id; //작성자 ID
	private int reply_num; //댓글테이블의 리플 번호
	private String reply_cnt; //댓글 내용
	private Timestamp reply_date; //작성날짜
	
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
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
}
