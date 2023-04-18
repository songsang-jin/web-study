package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class order_checkDAO {

	//1.연결할 클래스 객체선언
	private DBConnectionMgr pool=null;
	private Connection con=null;
	private PreparedStatement pstmt=null; //
	private ResultSet rs=null;//select는 반환값이 있기 때문에 그 반환값을 담기 위함
	private String sql=""; //실행시킬 sql구문 저장용
	
	
	//2. 생성자를 통해서 서로 연결
	public order_checkDAO() {
		try {
			pool=DBConnectionMgr.getInstance(); //DB연결얻어오기
		}catch(Exception e) {
			System.out.println("DB접속 오류=>"+e); //오류확인을 위한 출력문
		}
	}
	
	
	
	// 리스트 DB내용 불러오기 
	private order_checkDTO makeArticleFromResult() throws SQLException {
				// TODO Auto-generated method stub
				order_checkDTO order=new order_checkDTO();
				order.setOrder_num(rs.getString("order_num"));
				order.setMem_id(rs.getString("mem_id"));
				order.setItem_name(rs.getString("item_name"));
				order.setOrder_prcs(rs.getString("order_prcs"));
				order.setOrder_date(rs.getString("order_date"));
				order.setOrder_sta(rs.getString("order_sta"));
				
				return order;
			}
	
	//레코드 수
	public int getOrderCount() {
		int x =0;//초기 레코드 수
		try {	
			con=pool.getConnection();	//연결객체를 얻어오는 
			System.out.println("con="+con); //접속정보 확인 출력문
			sql ="select count(*) from order_check"; 
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery(); //select 문을 사용으로 내용변화가 없으므로 사용됨
			if(rs.next()) { //값이있을경우
				x=rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getOrderCount() 에러발생=>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return x;
	}
	
	
	//3.회원주문정보 목록 불러오기
	public List getAllOrders() {
		ArrayList<order_checkDTO> orders = null;
		System.out.println("getAllOrders메소드에서 ArrayList생성="+orders);
		try {
			con=pool.getConnection();
			sql="select * from order_check order by order_num desc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				orders = new ArrayList();
				do {
					order_checkDTO order=new order_checkDTO();
					order=makeArticleFromResult();
					orders.add(order);
				}while(rs.next());
			}
		}catch(Exception e) {
			System.out.println("getAllOrders() 에러발생="+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return orders;
	}
	
	

	
	//상태 변경
	public int updateStatus (order_checkDTO order) {
		int x=-1;
		try {
			con=pool.getConnection();
			sql="update order_check set order_sta=?,order_prcs=? where order_num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,order.getOrder_sta());
			pstmt.setString(2, order.getOrder_prcs());
			pstmt.setString(3,order.getOrder_num());
			int update=pstmt.executeUpdate();
			System.out.println("updateStatus()성공="+update);
			rs=pstmt.executeQuery();
		}catch(Exception e) {
			System.out.println("updateStatus()오류="+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return x;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
