package model;

import java.sql.Timestamp;
 
public class COMMUNITYDTO {

	private int post_num, post_view, count;
	private String mem_id, admin_id, post_title, post_cnt;
	private Timestamp post_date;
	
	public int getPost_num() {
		return post_num;
	}
	public void setPost_num(int post_num) {
		this.post_num = post_num;
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
		this.mem_id = convert(mem_id);
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = convert(admin_id);
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = convert(post_title);
	}
	public String getPost_cnt() {
		return post_cnt;
	}
	public void setPost_cnt(String post_cnt) {
		this.post_cnt = convert(post_cnt);
	}
	public Timestamp getPost_date() {
		return post_date;
	}
	public void setPost_date(Timestamp post_date) {
		this.post_date = post_date;
	}
	
	private static String convert(String name) {
		 if(name!=null){
	    	   //문자열메서드->replaceAll(1.변경전문자열,2.변경후문자열) 
	    	   name=name.replaceAll("<","&lt");
	    	   name=name.replaceAll(">","&gt");
	    	   //추가 ->eval함수의 ()때문에 (,)
	    	   name=name.replaceAll("\\(","&#40");
	    	   name=name.replaceAll("\\)","&#41");
	    	   //추가2=>"test"<-\"test\" ,\'test\' =>'test'
	    	   name=name.replaceAll("\"","&quot");
	    	   name=name.replaceAll("\'","&apos");
	    	     
	       }else{//name==null
	    	   return null;//입력을 하지 않았따면 더 이상 실행X
	       }  
		 return name;
	}
}


