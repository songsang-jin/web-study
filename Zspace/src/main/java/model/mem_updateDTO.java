package model;

public class mem_updateDTO {

	
	private String mem_id, mem_pwd, mem_name, mem_phone, mem_email, mem_zipcode, mem_addr1, mem_addr2;
	private int mem_keep;
	
	public int getMem_keep() {
		return mem_keep;
	}
	public void setMem_keep(int mem_keep) {
		this.mem_keep = mem_keep;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = convert(mem_id);
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = convert(mem_pwd);
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = convert(mem_name);
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = convert(mem_phone);
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = convert(mem_email);
	}
	public String getMem_zipcode() {
		return mem_zipcode;
	}
	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = convert(mem_zipcode);
	}

	
	public String getMem_addr1() {
		return mem_addr1;
	}
	public void setMem_addr1(String mem_addr1) {
		this.mem_addr1 = convert(mem_addr1);
	}
	public String getMem_addr2() {
		return mem_addr2;
	}
	public void setMem_addr2(String mem_addr2) {
		this.mem_addr2 = convert(mem_addr2);
	}
	
	//이 클래스에서만 사용하기위해서 접근지정자 private <,>,(,)=>변경메서드
	private static String convert(String name) {
		if(name!=null){
	    	//2.입력받은 문자열중에서 자바스크립트 구문을 실행시킬수 있는 특수기호를 입력X(<,>)
	    	//문자열메서드->replaceAll(1.변경전문자열,2.변경후 문자열)
	    	
	    	name=name.replaceAll("<","&lt");
	    	name=name.replaceAll(">","&gt");
	    	//추가 eval(" " or ' ')
	    	name=name.replaceAll("\\(","&#40");
	    	name=name.replaceAll("\\)","&#41");
	    	//"test"  'test'
	    	name=name.replaceAll("\"","&quot");
	    	name=name.replaceAll("\'","&apos");
	    }else{ //name==null
	    	return null; //입력을 하지 않았다면 더 이상 실행X
	    }
		return name;
	}
	
	
	
	
}
