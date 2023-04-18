package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminOrderCheckDAO {

	//연결할 클래스 객체선언
	private DBConnectionMgr pool=null;
	private Connection con=null;
	private PreparedStatement pstmt=null; 
	private ResultSet rs=null;
	private String sql="";
	
	
	//생성자를 통해서 서로 연결
	public AdminOrderCheckDAO() {
		try {
			pool=DBConnectionMgr.getInstance(); 
		}catch(Exception e) {
			System.out.println("DB접속 오류=>"+e); 
		}
	}
	
	
	
	// 리스트 DB내용 불러오기 
	private AdminOrderCheckDTO makeArticleFromResult() throws SQLException {
			
		
				AdminOrderCheckDTO order=new AdminOrderCheckDTO();
				order.setOrder_num(rs.getInt("order_num"));
				order.setMem_id(rs.getString("mem_id"));
				order.setItem_name(rs.getString("item_name"));
				order.setOrder_prcs(rs.getString("order_prcs"));
				order.setOrder_date(rs.getTimestamp("order_date"));
				order.setOrder_sta(rs.getString("order_sta"));
				
				return order;
			}
	
	//레코드 수
	public int getOrderCount() {
		int x =0;
		try {	
			con=pool.getConnection();	
			
			sql ="select count(*) from order_check"; 
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception e) {
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return x;
	}
	
	
	//회원주문정보 목록 불러오기
	public List getAllOrders() {
		ArrayList<AdminOrderCheckDTO> orders = null;
		try {
			con=pool.getConnection();
			sql="select * from order_check order by order_num desc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				orders = new ArrayList();
				do {
					AdminOrderCheckDTO order=new AdminOrderCheckDTO();
					order=makeArticleFromResult();
					orders.add(order);
				}while(rs.next());
			}
		}catch(Exception e) {
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return orders;
	}
	
	

	
	//상태 변경
	public int updateStatus (AdminOrderCheckDTO order) {
		int update=0;
		try {
			con=pool.getConnection();
			sql="update order_check set order_sta=?,order_prcs=? where order_num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,order.getOrder_sta());
			pstmt.setString(2, order.getOrder_prcs());
			pstmt.setInt(3,order.getOrder_num());
			update=pstmt.executeUpdate();
			rs=pstmt.executeQuery();
		}catch(Exception e) {
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return update;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
