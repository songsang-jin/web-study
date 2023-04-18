package model;

public class OrderPayDTO {

	private String mem_id, mem_name, mem_pwd, mem_email, mem_addr1, mem_addr2, mem_phone;
	private int mem_zipcode;

	private String order_date ;
	private String pay_list, pay_meth,order_num;
	private int pay_num, pay_amt, pay_total;

	private int wish_list_code, cart_num, item_num, item_su, item_pay;
	private String item_img, item_name;

	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_addr1() {
		return mem_addr1;
	}
	public void setMem_addr1(String mem_addr1) {
		this.mem_addr1 = mem_addr1;
	}
	public String getMem_addr2() {
		return mem_addr2;
	}
	public void setMem_addr2(String mem_addr2) {
		this.mem_addr2 = mem_addr2;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public int getMem_zipcode() {
		return mem_zipcode;
	}
	public void setMem_zipcode(int mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}

	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getPay_list() {
		return pay_list;
	}
	public void setPay_list(String pay_list) {
		this.pay_list = pay_list;
	}
	public String getPay_meth() {
		return pay_meth;
	}
	public void setPay_meth(String pay_meth) {
		this.pay_meth = pay_meth;
	}
	public int getPay_num() {
		return pay_num;
	}
	public void setPay_num(int pay_num) {
		this.pay_num = pay_num;
	}
	public int getPay_amt() {
		return pay_amt;
	}
	public void setPay_amt(int pay_amt) {
		this.pay_amt = pay_amt;
	}
	public int getPay_total() {
		return pay_total;
	}
	public void setPay_total(int pay_total) {
		this.pay_total = pay_total;
	}

	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public int getWish_list_code() {
		return wish_list_code;
	}
	public void setWish_list_code(int wish_list_code) {
		this.wish_list_code = wish_list_code;
	}
	public int getCart_num() {
		return cart_num;
	}
	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
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
