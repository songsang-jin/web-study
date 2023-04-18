package model;

import java.sql.Timestamp;
import java.util.Date;

public class OrderCheckDTO {
	
	private int pay_amt,item_su,item_num,pay_num,pay_total;
	//주문번호(결제번호),결제금액,상품수,상품번호,목록번호
	private String mem_id, order_num,item_img, item_name, order_prcs,order_sta,order_date;
	//회원아이디, 상품이미지,상품명,결제여부,제품배송,주문일자,
	public int getPay_amt() {
		return pay_amt;
	}
	public void setPay_amt(int pay_amt) {
		this.pay_amt = pay_amt;
	}
	public int getItem_su() {
		return item_su;
	}
	public void setItem_su(int item_su) {
		this.item_su = item_su;
	}
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public int getPay_num() {
		return pay_num;
	}
	public void setPay_num(int pay_num) {
		this.pay_num = pay_num;
	}
	public int getPay_total() {
		return pay_total;
	}
	public void setPay_total(int pay_total) {
		this.pay_total = pay_total;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
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
	public String getOrder_sta() {
		return order_sta;
	}
	public void setOrder_sta(String order_sta) {
		this.order_sta = order_sta;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	
}