package model;

import java.sql.Timestamp;

public class AdminOrderCheckDTO {

	private int order_num;
	private String mem_id,item_img,item_name,order_prcs,order_sta;
	private Timestamp order_date;
	
	
	public String getOrder_sta() {
		return order_sta;
	}
	
	public void setOrder_sta(String order_sta) {
		this.order_sta =order_sta;
	}
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getItem_img() {
		return item_img;
	}
	public void setItem_img(String item_img) {
		this.item_img = item_img;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getOrder_prcs() {
		return order_prcs;
	}
	public void setOrder_prcs(String order_prcs) {
		this.order_prcs = order_prcs;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}


	
}
