package model;

import java.sql.Timestamp;

public class payDTO {
	
	private int pay_num, pay_amt;
	private String mem_id,pay_list, pay_meth;
	private Timestamp pay_date;
	
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
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
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
	public Timestamp getPay_date() {
		return pay_date;
	}
	public void setPay_date(Timestamp pay_date) {
		this.pay_date = pay_date;
	}
	
	
}
