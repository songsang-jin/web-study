package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CARTDAO {

	private DBConnectionMgr pool = null; // (DBConnectionMgr)에 접근하기 위해
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // select
	private String sql = ""; // 실행시킬 SQL구문 저장 목적

	public CARTDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류: " + e);
		}
	}

	// db보내기
	public void insertCart(CARTDTO cart) {
		int cart_num = 0; // 장바구니번호에 넣은 변수

		int number = 0; // 데이터를 저장하기 위한 게시물번호

		System.out.println("cartInsert메서드의 내부 cart_num : " + cart_num);// 0 신규글

		try {
			con = pool.getConnection();
			sql = "select max(cart_num) from cart";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				number = rs.getInt(1) + 1;// 최대값+1
			} else {
				number = 1;// 테이블에 한개의 데이터가 없다면
			}
			// 장바구니에 필요한 정보들 넣기
			sql = "insert into cart(cart_num, mem_id, item_img, Item_name, item_su, item_pay) values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_num);
			pstmt.setString(2, cart.getMem_id());
			pstmt.setString(3, cart.getItem_img());
			pstmt.setString(4, cart.getItem_name());
			pstmt.setInt(5, cart.getItem_su());
			pstmt.setInt(6, cart.getItem_pay());

			int insert = pstmt.executeUpdate();
			System.out.println("장바구니 성공유무(insert)=>" + insert);
		} catch (Exception e) {
			System.out.println("CartInsert()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// (1)상품상세페이지에서 장바구니로 db넣기
	public int getCartInsert(String mem_id, int item_num) {
		int check = 0;// 성공유무
		int cart_num = 0; // 장바구니번호를 넣을 변수
		try {
			con = pool.getConnection();
			// 장바구니 번호 조회
			sql = "select nvl(max(cart_num),0)+1 as max_num from cart";
			// sql = "select nvl(max(pb_num),0)+1 as max_num from ProductBasket";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 안에 레코드가 있다면 +1
				cart_num = rs.getInt("max_num");
				System.out.println("cart_num" + cart_num);
			}
			// insert
			con.setAutoCommit(false);
			sql = "insert into cart(cart_num,mem_id,item_num) values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_num);
			pstmt.setString(2, mem_id);
			pstmt.setInt(3, item_num);
			pstmt.executeUpdate();
			con.commit();
			check = 1;
		} catch (Exception e) {
			System.out.println("CartInsert()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}

	// 장바구니 목록보기
	public List<CARTDTO> searchCART(String mem_id) throws Exception {
		List<CARTDTO> cartlist = new ArrayList();
		int item_num = 0;// 회원이 선탯한 상품번호를 담을 변수
		try {
			con = pool.getConnection();
			sql = "SELECT DISTINCT item_num from cart where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				item_num = rs.getInt("item_num");
				System.out.println("item_num=>" + item_num);
				if (item_num > 0) {
					// String sql2="select wish_list_code, mem_id, item_img, Item_name,
					// wish_list_price, item_su, item_pay"
					// + "from wish_list where item_num=?";
					String sql2 = "select * from item_depth where item_num=?";
					pstmt = con.prepareStatement(sql2);
					pstmt.setInt(1, item_num);
					ResultSet rs2 = pstmt.executeQuery();
					while (rs2.next()) {
						CARTDTO itemdto = new CARTDTO();
						itemdto.setItem_num(rs2.getInt("item_num")); // 상품번호
						itemdto.setItem_img(rs2.getString("item_img")); // 상품이미지 경로
						itemdto.setItem_name(rs2.getString("item_name")); // 상품이름
						itemdto.setItem_pay(rs2.getInt("item_pay")); // 상품가격
						itemdto.setItem_su(rs2.getInt("item_su"));
						cartlist.add(itemdto);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return cartlist;
	}

	// 장바구니 상품별 삭제하기
	public int CARTDelete(int item_num, String mem_id) {
		int check = 0; // 성공유무
		try {
			con = pool.getConnection();
			con.setAutoCommit(false); // 트랜잭션 처리
			sql = "delete from cart where item_num=? and mem_id=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			pstmt.setString(2, mem_id);
			pstmt.executeUpdate();
			con.commit();
			check = 1;
		} catch (Exception e) {
			System.out.println("CartDelete()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt);
		}
		return check;
	}
     //장바구니 전체삭제하기
	public int CARTDeleteAll(String mem_id) {
		int check = 0; // 성공유무
		try {
			con = pool.getConnection();
			con.setAutoCommit(false); // 트랜잭션 처리
			sql = "delete from cart where mem_id=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.executeUpdate();
			con.commit();
			check = 1;
		} catch (Exception e) {
			System.out.println("CartDeleteAll()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt);
		}
		return check;
	}
}