package model;

import java.sql.Timestamp;

public class FAQboardDTO {
	
	private int post_num, item_num, post_view, count;
	private String mem_id, admin_id, post_title, post_cnt, item_img, rated ;
	private Timestamp post_date;
	private String post_id; //추가 각 게시판 글번호증가
	
	private int ref;//글 그룹번호(=게시물번호와 비슷)
	private int re_step;//답변글의 순서를 지정(=같은 그룹의 답변글 순서)
	//댓글
	private int replay_num;
	private String replay_cnt;
	private Timestamp replay_date;
	
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
	}
	
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public int getPost_view() {
		return post_view;
	}
	public void setPost_view(int post_view) {
		this.post_view = post_view;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_cnt() {
		return post_cnt;
	}
	public void setPost_cnt(String post_cnt) {
		this.post_cnt = post_cnt;
	}
	public String getItem_img() {
		return item_img;
	}
	public void setItem_img(String item_img) {
		this.item_img = item_img;
	}
	public String getRated() {
		return rated;
	}
	public void setRated(String rated) {
		this.rated = rated;
	}
	public Timestamp getPost_date() {
		return post_date;
	}
	public void setPost_date(Timestamp post_date) {
		this.post_date = post_date;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getReply_num() {
		return replay_num;
	}
	public void setReply_num(int replay_num) {
		this.replay_num = replay_num;
	}
	public String getReply_cnt() {
		return replay_cnt;
	}
	public void setReply_cnt(String replay_cnt) {
		this.replay_cnt = replay_cnt;
	}
	public Timestamp getReply_date() {
		return replay_date;
	}
	public void setReply_date(Timestamp replay_date) {
		this.replay_date = replay_date;
	}
// 추가
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}


}
