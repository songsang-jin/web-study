package model;

import java.sql.Timestamp;
 
public class WISHDTO {

	private int wish_list_code, wish_list_price;
	private int item_su;
	private int item_pay;
	private int item_num;
	
	private String mem_id, item_img, item_name;
	
	
	public int getWish_list_code() {
		return wish_list_code;
	}
	public void setWish_list_code(int wish_list_code) {
		this.wish_list_code = wish_list_code;
	}
	public int getWish_list_price() {
		return wish_list_price;
	}
	public void setWish_list_price(int wish_list_price) {
		this.wish_list_price = wish_list_price;
	}
	public int getItem_su() {
		return item_su;
	}
	public void setItem_su(int item_su) {
		this.item_su = item_su;
	}
	public int getItem_pay() {
		return item_pay;
	}
	public void setItem_pay(int item_pay) {
		this.item_pay = item_pay;
	}
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
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
	
}
