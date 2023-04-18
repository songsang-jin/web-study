package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class OrderPayDAO {

  private DBConnectionMgr pool=null; //1)연결할 클래스 객체 선언
	  //공통
  private Connection con=null;
  private PreparedStatement pstmt=null;
  private ResultSet rs=null; //select
  private String sql=""; //실행시킬 SQL구문 저장용

  public OrderPayDAO() {
	try {
	  pool=DBConnectionMgr.getInstance();
	}catch(Exception e) {
		System.out.println("DB 접속 오류="+e);
	}
  }//생성자

  //회원정보 불러오기
  public ArrayList<OrderPayDTO> getOrderMemList(String mem_id){
	 ArrayList<OrderPayDTO> orderList=new ArrayList<>();
	// Timestamp post_date = new Timestamp(new Date().getTime());
	 // int order_num=0;
	 OrderPayDTO order= new OrderPayDTO();
	  try {
		con=pool.getConnection();
  	    sql="select i.*, m.* from item_depth i, mem_depth m where m.mem_id=?";
	    pstmt=con.prepareStatement(sql);

	    pstmt.setString(1, mem_id);
	    System.out.println("getOrderMemList()에서 회원정보 불러오기");
	    rs=pstmt.executeQuery();
	    if(rs.next()) {

	    	order=makeOrderMemResult();
	    	System.out.println("OrderMemList"+order);
	    	orderList.add(order);
	    }
	  }catch (Exception e){
		  System.out.println("getOrderMemList sql 에러: "+e);
	  }finally {
			pool.freeConnection(con, pstmt, rs);
	  }
	  return orderList;
  }

  //주문하기
  public int insertOrder (OrderPayDTO order) {
	  java.util.Date now = new java.util.Date();
	     SimpleDateFormat vans = new SimpleDateFormat("yy/MM/dd");
	  String order_date=LocalDate.now().toString();
	  int check=0;
	  int pay_num=0;
	  try {
		con=pool.getConnection();
		sql="select max(pay_num) from pay";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()) {//보여주는 결과가 있다면
			pay_num=rs.getInt(1)+1;//최대값+1
		}else {
			pay_num=1;//테이블에 한개의 데이터가 없다면
		}
	    sql="insert into pay (pay_num, mem_id, order_date, pay_meth, pay_amt, pay_total, item_name) values(?,?,?,?,?,?,?)";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, pay_num); //주문 번호
		pstmt.setString(2, order.getMem_id()); //주문자 아이디
		pstmt.setString(3, order_date); //주문 날짜
		pstmt.setString(4, order.getPay_meth()); //결제방법
		pstmt.setInt(5, order.getPay_amt()); //결제상품 합계 금액
		pstmt.setInt(6, order.getPay_total()); // 총 결제 가격
		pstmt.setString(7, order.getItem_name()); // 총 결제 가격
		check=1;
		int insert=pstmt.executeUpdate();
	    System.out.println("주문하기 성공 확인: "+insert);
	}catch(Exception e) {
		System.out.println("insertOrder 오류확인 :"+ e);
	}finally {
		pool.freeConnection(con, pstmt, rs);
	}
	  return check;
  }

  //주문 내용 전송하기
  public int getOrderInsert(OrderPayDTO order){
	  java.util.Date now = new java.util.Date();
	     SimpleDateFormat vans = new SimpleDateFormat("yyyyMMdd");
	     String order_num = vans.format(now);
	     String order_date=LocalDate.now().toString();
	    int x=0;
	     try {
	 		con=pool.getConnection();
		     //주문번호 생성을 위한 sequence를 받아옵니다.
	    	 sql = "SELECT seq_ordercode.NEXTVAL seq_num FROM dual";
	    	 pstmt=con.prepareStatement(sql);
	    	 rs = pstmt.executeQuery();
	 	    System.out.println("sql구문을 불러온 건에 대하여");
	    	if (rs.next()) {
	    	 int ordercode = rs.getInt("seq_num");
	    	 // 주문 번호를 생성
	    	 String  trade_code = order_num+"-"+String.format("%04d",  ordercode);
	    	 order_num=trade_code;
	 	    System.out.println("order_num: "+order_num+ ", ordercode"+ordercode);
	    	}
	    	// rs.close();
			//주문번호, 회원 아이디
			//con.setAutoCommit(false); //트랜잭션 처리
			sql = "insert into order_check (order_date, mem_id, item_img, item_su, item_name,  pay_amt, pay_total, item_num, order_num) ";
			sql+= "values(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order_date); //주문 날짜
			pstmt.setString(2, order.getMem_id()); //주문자 아이디
			pstmt.setString(3, order.getItem_img()); //결제방법
			pstmt.setInt(4, order.getItem_su()); //상품 수
			pstmt.setString(5, order.getItem_name()); //상품이름
			pstmt.setInt(6, order.getPay_amt()); //결제상품 합계 금액
			pstmt.setInt(7, order.getPay_total()); // 총 결제 가격
			pstmt.setInt(8, order.getItem_num()); //상품번호
			pstmt.setString(9, order_num);
			int insert=pstmt.executeUpdate();
		    System.out.println("주문 결과 보냈을까?: "+insert);
			//con.commit();
			x = 1;
	    	
		} catch (Exception e) {
			System.out.println("getOrderInsert()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

  //pay에 해당하는 모든 정보
  private OrderPayDTO makeOrderMemResult() throws Exception {
	    OrderPayDTO order = new OrderPayDTO();
	    order.setItem_num(rs.getInt("item_num"));
	    order.setItem_name(rs.getString("item_name"));
	    order.setItem_pay(rs.getInt("item_pay"));
	    order.setItem_su(rs.getInt("item_su"));
	    order.setItem_img(rs.getString("item_img"));
	    order.setMem_id(rs.getString("mem_id"));
	    order.setMem_name(rs.getString("mem_name"));
	    order.setMem_phone(rs.getString("mem_phone"));
	    order.setMem_email(rs.getString("mem_email"));
	    order.setMem_zipcode(rs.getInt("mem_zipcode"));
	    order.setMem_addr1(rs.getString("mem_addr1"));
	    order.setMem_addr2(rs.getString("mem_addr2"));

	    return order;
	}

}
