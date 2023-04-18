package model;

import java.sql.Timestamp;
 
public class ITEMDTO {

	private int category_num;
	private String category_menu, category_depth;
	
	private int item_num, wish_list_code, item_view, item_sales, item_wishlist, item_rebu, item_unicNum, item_pay, item_su, sale_price, total_price, wish_list_price;
	private String item_img, item_name, item_expln, item_option1, item_option2, mem_id;
	private Timestamp item_dateTime;
	
	//내가 쓸것들 item_num,  item_img, item_name, item_expln, item_pay, item_su,  item_option1, item_option2, sale_price, total_price;
	//                  상품번호     상품 이미지       상품명       상품 설명     상품 가격     상품 수      상품 옵션1			상품 옵션2		판매 가격     총판매 가격
	
	public void saleTotal() { //판매 총 가격
		this.sale_price = (int) (this.item_pay); //판매가격=상품가격
		this.total_price = this.sale_price*this.item_su; //총판매가격=판매가격*상품수량
	}
	/*
	@Override
	public String toString() {
		return "payDTO [item_num=" + item_num + ", item_su=" + item_su + ", item_name=" + item_name
				+ ", item_pay=" + item_pay + ", sale_price=" + sale_price
				+ ", total_price=" + total_price + "]";
	}
	*/
	
	
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
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public int getWish_list_code() {
		return wish_list_code;
	}
	public void setWish_list_code(int wish_list_code) {
		this.wish_list_code = wish_list_code;
	}
	public int getItem_view() {
		return item_view;
	}
	public void setItem_view(int item_view) {
		this.item_view = item_view;
	}
	public int getItem_sales() {
		return item_sales;
	}
	public void setItem_sales(int item_sales) {
		this.item_sales = item_sales;
	}
	public int getItem_wishlist() {
		return item_wishlist;
	}
	public void setItem_wishlist(int item_wishlist) {
		this.item_wishlist = item_wishlist;
	}
	public int getItem_rebu() {
		return item_rebu;
	}
	public void setItem_rebu(int item_rebu) {
		this.item_rebu = item_rebu;
	}
	public int getItem_unicNum() {
		return item_unicNum;
	}
	public void setItem_unicNum(int item_unicNum) {
		this.item_unicNum = item_unicNum;
	}
	public int getItem_pay() {
		return item_pay;
	}
	public void setItem_pay(int item_pay) {
		this.item_pay = item_pay;
	}
	public int getItem_su() {
		return item_su;
	}
	public void setItem_su(int item_su) {
		this.item_su = item_su;
	}
	public int getSale_price() {
		return sale_price;
	}
	public void setSale_price(int sale_price) {
		this.sale_price = sale_price;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public int getWish_list_price() {
		return wish_list_price;
	}

	public void setWish_list_price(int wish_list_price) {
		this.wish_list_price = wish_list_price;
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
	public String getItem_expln() {
		return item_expln;
	}
	public void setItem_expln(String item_expln) {
		this.item_expln = item_expln;
	}
	public String getItem_option1() {
		return item_option1;
	}
	public void setItem_option1(String item_option1) {
		this.item_option1 = item_option1;
	}
	public String getItem_option2() {
		return item_option2;
	}
	public void setItem_option2(String item_option2) {
		this.item_option2 = item_option2;
	}
	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public Timestamp getItem_dateTime() {
		return item_dateTime;
	}
	public void setItem_dateTime(Timestamp item_dateTime) {
		this.item_dateTime = item_dateTime;
	}


	public int getPay_total() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
