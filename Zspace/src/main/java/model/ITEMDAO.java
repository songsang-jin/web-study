																																																																																																																																																																																																																																																																																										package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.DBConnectionMgr;


public class ITEMDAO {

  private DBConnectionMgr pool=null; //(DBConnectionMgr)에 접근하기 위해
  private Connection con=null; 
  private PreparedStatement pstmt=null; //SQL실행목적 (변경할 것만 고르기)
  private ResultSet rs=null; //select
  private String sql=""; //실행시킬 SQL구문 저장 목적
   
  //1) 생성자를 통해 연결
  public ITEMDAO() { 
	try {
		pool=DBConnectionMgr.getInstance();
	}catch(Exception e) {
		System.out.println("DB접속 오류: "+e);
	}
  }//생성자
  
  //2) 상품 이미지 출력하기 (썸네일, 작은 이미지)
  //sql="select item_img from item_depth where item_num=?"
  public ITEMDTO getGoodsImage (int item_num) {
	  ITEMDTO goods=null;
	  try {
		con=pool.getConnection();
		sql="select item_img from item_depth where item_num=?"; //item_depth 테이블에서 상품번호별 불러오기
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, item_num);
		rs=pstmt.executeQuery();
		  if(rs.next()) {
			goods= new ITEMDTO();
			goods=makeGoodsFromResult();
		  }
	  }catch(Exception e) {
		  System.out.println("getGoodsImage() 에러: "+e);
	  }finally {
		  pool.freeConnection(con,pstmt,rs);
	  }
	  return goods;
	}
  
  //3) 상품 간단 정보 출력하기(상품명, 판매가, 옵션)
  //sql="select item_name from item_depth where item_num=?"
  //sql="select item_pay from item_depth where item_num=?"
  //sql="select item_option from item_depth where item_num=?"
  public ITEMDTO getGoodsInfo (int item_num) {
	  ITEMDTO goods=null;
	  try {
		con=pool.getConnection();
		sql="select * from item_depth where item_num=?"; //item_depth 테이블에서 상품번호별 불러오기
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, item_num);
		rs=pstmt.executeQuery();
		  if(rs.next()) {
			goods= new ITEMDTO();
			goods=makeGoodsFromResult();
		  }
	  }catch(Exception e) {
		  System.out.println("getGoodsInfo() 에러: "+e);
	  }finally {
		  pool.freeConnection(con,pstmt,rs);
	  }
	  return goods;
	}
  
  //4) 상품 상세 정보 출력하기(이미지)
  //sql="select item_img from item_depth where item_num=?"
  public ITEMDTO getGoodsDetailImage (int item_num) {
	  ITEMDTO goods=null;
	  try {
		con=pool.getConnection();
		sql="select item_img from item_info where item_num=?"; //item_depth 테이블에서 상품번호별 불러오기
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, item_num);
		rs=pstmt.executeQuery();
		  if(rs.next()) {
			goods= new ITEMDTO();
			goods=makeGoodsFromResult();
		  }
	  }catch(Exception e) {
		  System.out.println("getGoodsInfo() 에러: "+e);
	  }finally {
		  pool.freeConnection(con,pstmt,rs);
	  }
	  return goods;
	}
  
  //5) 상품 옵션 선택 후 관심상품에 DB 보내기
  public void insertWish(ITEMDTO wish){
		int wish_list_code = 0; //관심상품번호에 넣은 변수
		
		int number=0; //데이터를 저장하기 위한 게시물번호
		
		System.out.println("WishInsert메서드의 내부 wish_list_code : "+wish_list_code);//0 신규글
		
		try {
			con=pool.getConnection();
			sql="select max(wish_list_code) from wish_list";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				number=rs.getInt(1)+1;//최대값+1
			}else {
				number=1;//테이블에 한개의 데이터가 없다면
			}
			//관심상품에 필요한 정보들 넣기
			sql = "insert into wish_list(wish_list_code, mem_id, item_img, Item_name, wish_list_price, item_su, item_pay) values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wish_list_code);
			pstmt.setString(2, wish.getMem_id());
			pstmt.setString(3, wish.getItem_img());
			pstmt.setString(4, wish.getItem_name());
			pstmt.setInt(5, wish.getWish_list_price());
			pstmt.setInt(6, wish.getItem_su());
			pstmt.setInt(7, wish.getItem_pay());
			
			int insert=pstmt.executeUpdate();
			System.out.println("게시판의 글쓰기 성공유무(insert)=>"+insert);
		} catch (Exception e) {
			System.out.println("WishInsert()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
	}
  /*
  //6)관심상품에 post_num과 mem_id 넣기
  public void insertWish(ITEMDTO wish) {//~(MemberDTO mem)
		//1.article->신규글 인지 답변글인지 확인
		int wish_list_code=wish.getWish_list_code(); //관심상품번호
		
		int number=0;//데이터를 저장하기위한 게시물번호 
		System.out.println("insertWish메서드의 내부 wish_list_code : "+wish_list_code);
		
		try {
			con=pool.getConnection();
			sql="select max(wish_list_code) from wish_list";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				number=rs.getInt(1)+1;//최대값+1
			}else {
				number=1;//테이블에 한개의 데이터가 없다면
			}
			
			//insert sql문
			sql="insert into wish_list(item_img,item_name,item_pay,mem_id,item_num)values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, wish.getItem_img());//웹에서는 이미 데이터저장된 상태(Setter~)
			pstmt.setString(2, wish.getItem_name());
			pstmt.setInt(3, wish.getItem_pay());
			pstmt.setString(4, wish.getMem_id());
			pstmt.setInt(5, wish.getItem_num());
			int insert=pstmt.executeUpdate();
			System.out.println("관심상품 등록 성공유무(insert) : "+insert);
		}catch(Exception e) {
			System.out.println("insertWish() 메서드 에러유발 : "+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
	}
  */
  
//7)관심상품 리스트 보기
  public List<WISHDTO> searchWish(String mem_id) {
		List<WISHDTO> wishlist = new ArrayList();
		int item_num = 0; //회원이 선택한 상품번호를 담을 변수
		try {
			con = pool.getConnection();
			System.out.println("con1 : " + con);
			sql = "select DISTINCT item_num from wish_list where mem_id=?";
			//sql = "select * from wish_list where mem_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs = pstmt.executeQuery(); // 2
			while(rs.next()) {
				item_num = rs.getInt("item_num");
				System.out.println("item_num=>" + item_num);
				if(item_num > 0) {
					//String sql2="select wish_list_code, mem_id, item_img, Item_name, wish_list_price, item_su, item_pay"
							//+ "from wish_list where item_num=?";
					String sql2="select * from item_depth where item_num=?";
					pstmt = con.prepareStatement(sql2);
					pstmt.setInt(1, item_num);
					ResultSet rs2 = pstmt.executeQuery();
					while(rs2.next()) {	
						WISHDTO itemdto = new WISHDTO();
						itemdto.setItem_num(rs2.getInt("item_num")); // 상품번호
						itemdto.setItem_img(rs2.getString("item_img")); // 상품이미지 경로
						itemdto.setItem_name(rs2.getString("item_name")); // 상품이름		
						itemdto.setItem_pay(rs2.getInt("item_pay")); // 상품가격
						wishlist.add(itemdto);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("searchWish()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return wishlist;
	}
  
//8)관심상품 삭제하기

  public int WishDelete(int item_num, String mem_id){
		int check=0; //성공유무
		
		
		try {
			con = pool.getConnection();
			con.setAutoCommit(false); //트랜잭션 처리
			sql = "delete from wish_list where mem_id=? and item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setInt(2, item_num);
			
			pstmt.executeUpdate();
			con.commit();
			check = 1;
		} catch (Exception e) {
			System.out.println("WishDelete()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}
  /*
  public int WishDelete(int item_num) {
		int x = -1;
		try {
			con = pool.getConnection();
			sql = "delete from wish_list where mem_id=? and item_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			int delete = pstmt.executeUpdate();

			System.out.println("관심상품 삭제 성공 확인: " + delete);
		} catch (Exception e) {
			System.out.println("WishDelete() 에러 확인: " + e);
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return x;
	}
	*/
//관심상품 전체삭제하기
	public int WISHDeleteAll(String mem_id) {
		int check = 0; // 성공유무
		try {
			con = pool.getConnection();
			con.setAutoCommit(false); // 트랜잭션 처리
			sql = "delete from wish_list where mem_id=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.executeUpdate();
			con.commit();
			check = 1;
		} catch (Exception e) {
			System.out.println("WishDeleteAll()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt);
		}
		return check;
	}
  
//7) 상품 옵션 선택 후 장바구니로 DB 보내기
  //1.request.setAttribute("post_num", post_num); 같은 형식으로 메모리에 올린다
  //2.메모리에 올라가 있는 상품번호,상품명,판매가,옵션을 결제 페이지로 넘기기
  public int getWishInsert(String mem_id, int item_num){
		int check=0; //성공유무
		
		int wish_list_code=0; //관심상품번호를 넣을 변수
		try {
			con = pool.getConnection();
			//관심상품 번호 조회
			sql = "select nvl(max(wish_list_code),0)+1 as max_num from wish_list";
			//sql = "select nvl(max(pb_num),0)+1 as max_num from ProductBasket";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				wish_list_code = rs.getInt("max_num");
				System.out.println("wish_list_code : " + wish_list_code);
			}
			//관심상품번호,회원아이디,상품번호 넣기
			con.setAutoCommit(false); //트랜잭션 처리
			sql = "insert into wish_list(wish_list_code ,mem_id, item_num) values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, wish_list_code);
			pstmt.setString(2, mem_id);
			pstmt.setInt(3, item_num);
			pstmt.executeUpdate();
			con.commit();
			check = 1;
		} catch (Exception e) {
			System.out.println("WishInsert()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}

//8) 상품 옵션 선택 후 장바구니로 DB 보내기
  //1.request.setAttribute("post_num", post_num); 같은 형식으로 메모리에 올린다
  //2.메모리에 올라가 있는 상품번호,상품명,판매가,옵션을 결제 페이지로 넘기기
  public int getCartInsert(String mem_id, int item_num){
		int check=0; //성공유무
		
		int cart_num = 0; //관심상품번호에 넣은 변수
		try {
			con = pool.getConnection();
			//관심상품 번호 조회
			sql = "select * from cart where cart_num=?";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//wish_list_code = rs.getInt("max_num");
				System.out.println("cart_num - >" + cart_num);
			}
			//관심상품번호,회원아이디,상품번호 넣기
			con.setAutoCommit(false); //트랜잭션 처리
			sql = "insert into cart(cart_num, mem_id, item_num) values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_num);
			pstmt.setString(2, mem_id);
			pstmt.setInt(3, item_num);
			pstmt.executeUpdate();
			con.commit();
			check = 1;
		} catch (Exception e) {
			System.out.println("getCartInsert()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
  }
  
//9) 상품 옵션 선택 후 결제로 DB 보내기
  //1.request.setAttribute("post_num", post_num); 같은 형식으로 메모리에 올린다
  //2.메모리에 올라가 있는 상품번호,상품명,판매가,옵션을 결제 페이지로 넘기기
  public int getPayInsert(String mem_id, int item_num){
		int check=0; //성공유무
		
		int pay_num = 0; //관심상품번호에 넣은 변수
		try {
			con = pool.getConnection();
			//관심상품 번호 조회
			sql = "select * from pay where pay_num=?";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//wish_list_code = rs.getInt("max_num");
				System.out.println("pay_num - >" + pay_num);
			}
			//관심상품번호,회원아이디,상품번호 넣기
			con.setAutoCommit(false); //트랜잭션 처리
			sql = "insert into pay(pay_num, mem_id, item_num) values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pay_num);
			pstmt.setString(2, mem_id);
			pstmt.setInt(3, item_num);
			pstmt.executeUpdate();
			con.commit();
			check = 1;
		} catch (Exception e) {
			System.out.println("getPayInsert()에러유발->" + e);
		} finally {// 3.메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}
  
  
  //9) 중복되는 불러올 set들
  private ITEMDTO makeGoodsFromResult() throws Exception {
	  ITEMDTO goods=new ITEMDTO();
	  
	    goods.setItem_num(rs.getInt("item_num")); // 상품 번호
	    goods.setItem_pay(rs.getInt("item_pay")); //상품 가격
	    goods.setItem_su(rs.getInt("item_su")); //상품 수량
	    goods.setItem_name(rs.getString("item_name")); //상품 이름
	    //goods.setItem_expln(rs.getString("item_expln")); //상품 설명
	    goods.setItem_option1(rs.getString("item_option1")); //상품 옵션
	    //goods.setItem_option2(rs.getString("item_option2")); //상품 옵션
	    goods.setItem_img(rs.getString("item_img")); //상품 이미지(item_info)
	    
	  return goods;
  }
	
}
