package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.DBConnectionMgr;

public class OrderCheckDAO {
	
	private DBConnectionMgr pool = null;
	private Connection con = null;
	private PreparedStatement pstmt = null; // SQL실행목적 (변경할 것만 고르기)
	private ResultSet rs = null; // select
	private String sql = " "; // 실행시킬 SQL구문 저장 목적

	// 사용테이블 order_table
	// 0) 생성자를 통해 연결
	public OrderCheckDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류: " + e);
		}
	}

	private OrderCheckDTO makeOrdercheckResult() throws Exception {
		OrderCheckDTO order = new OrderCheckDTO();
		
		order.setOrder_num(rs.getString("order_num"));//주문번호
		order.setMem_id(rs.getString("mem_id"));//회원 id
		order.setOrder_date(rs.getString("order_date"));//날짜범
		order.setItem_name(rs.getString("item_name"));// String
		order.setItem_img(rs.getString("item_img"));
		order.setOrder_prcs(rs.getString("order_prcs"));
		order.setOrder_sta(rs.getString("order_sta"));
		order.setPay_amt(rs.getInt("pay_amt"));
		order.setItem_su(rs.getInt("item_su"));	
		order.setItem_num(rs.getInt("item_num"));
		order.setPay_num(rs.getInt("pay_num"));
		order.setPay_total(rs.getInt("pay_total"));
		

		System.out.println(order+"이게뭘까 결과값임");
		return order;
	}

	//orderCheck에 필요한 자료 불러오기<OrderCheckDTO>
	public List orderCheck(String mem_id) {// 매서드명 생성
		List<OrderCheckDTO> orderList = new ArrayList();// 공간만들기
		try {
			sql="SELECT  * FROM Order_Check where mem_id=? Order BY order_num DESC";
			
			con = pool.getConnection();
			System.out.println("con->" + con);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				OrderCheckDTO ocdto = new OrderCheckDTO();
				ocdto = makeOrdercheckResult();
				System.out.println("검색된 ocdto=>"+ocdto);
				orderList.add(ocdto);
			}
		} catch (Exception e) {
			System.out.println("order_check()에러유발->" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return orderList;
	}
	
}
