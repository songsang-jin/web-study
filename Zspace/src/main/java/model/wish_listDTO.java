package model;

public class wish_listDTO {

	private int wish_list_code, wish_list_price;
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
