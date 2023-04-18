package model;


public class searchDTO {

	private int category_num;
	private String category_menu, category_depth;

	private int item_pay, item_num;
	private String item_img, item_name;


	public int getCategory_num() {
		return category_num;
	}
	public void setCategory_num(int category_num) {
		this.category_num = category_num;
	}
	public String getCategory_menu() {
		return category_menu;
	}
	public void setCategory_menu(String category_menu) {
		this.category_menu = category_menu;
	}
	public String getCategory_depth() {
		return category_depth;
	}
	public void setCategory_depth(String category_depth) {
		this.category_depth = category_depth;
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
