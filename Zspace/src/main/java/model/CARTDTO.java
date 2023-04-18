package model;

public class CARTDTO {
	 private int cart_num,item_num,item_su, item_pay, pay_total; 
	    private String mem_id,item_name,item_img,mem_name, mem_addr1, mem_addr2, mem_zipcode, mem_phone;
	    
		public String getItem_img() {
			return item_img;
		}
		public void setItem_img(String item_img) {
			this.item_img = item_img;
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
		public int getPay_total() {
			return pay_total;
		}
		public void setPay_total(int pay_total) {
			this.pay_total = pay_total;
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
		public String getMem_id() {
			return mem_id;
		}
		public void setMem_id(String mem_id) {
			this.mem_id = mem_id;
		}
		public String getItem_name() {
			return item_name;
		}
		public void setItem_name(String item_name) {
			this.item_name = item_name;
		}
		public String getMem_name() {
			return mem_name;
		}
		public void setMem_name(String mem_name) {
			this.mem_name = mem_name;
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
		public String getMem_zipcode() {
			return mem_zipcode;
		}
		public void setMem_zipcode(String mem_zipcode) {
			this.mem_zipcode = mem_zipcode;
		}
		public String getMem_phone() {
			return mem_phone;
		}
		public void setMem_phone(String mem_phone) {
			this.mem_phone = mem_phone;
		}
		
}
