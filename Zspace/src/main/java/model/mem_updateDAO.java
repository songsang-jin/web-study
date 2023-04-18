package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class mem_updateDAO {

	private DBConnectionMgr pool=null;
	private Connection con=null;
	private PreparedStatement pstmt=null; //
	private ResultSet rs=null;//select는 반환값이 있기 때문에 그 반환값을 담기 위함
	private String sql=""; //실행시킬 sql구문 저장용
	
	
	public mem_updateDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("DB접속 오류="+e);
		}
	}
	
	
	//리스트 db내용 불러오기
	private mem_updateDTO makeArticleFromResult() throws SQLException {
		mem_updateDTO article=new mem_updateDTO();
		
		article.setMem_id(rs.getString("mem_id"));
		article.setMem_pwd(rs.getString("mem_pwd"));
		article.setMem_name(rs.getString("mem_name"));
		article.setMem_phone(rs.getString("mem_phone"));
		article.setMem_email(rs.getString("mem_email"));
		article.setMem_zipcode(rs.getString("mem_zipcode"));
		article.setMem_addr1(rs.getString("mem_addr1"));
		article.setMem_addr2(rs.getString("mem_addr2"));
		//order.setMem_keep(rs.getInt("mem_keep"));
		

		return article;
	}
	
	
	
	
	//글 상세보기
	public mem_updateDTO getArticle(String mem_id) {
		mem_updateDTO article = null;
		System.out.println("getArticle(mem_id)="+mem_id);
		
		try {
			con=pool.getConnection();
			sql ="select * from mem_depth where mem_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article = makeArticleFromResult();
			}
			System.out.println("getArticle(SQL)="+rs);
			System.out.println("getArticle()="+article);
		}catch(Exception e) {
			System.out.println("getArticle=>"+e);
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		return article;
	}
	
	
	
		
	//상태 변경
		public int updateMemState (mem_updateDTO order) {
			int x=-1;
			try {
				con=pool.getConnection();
				sql="update mem_depth set mem_pwd=?,mem_addr1=?,mem_addr2=?,mem_phone=?,mem_email=?,mem_zipcode=? where mem_id = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,order.getMem_pwd());
				pstmt.setString(2, order.getMem_addr1());
				pstmt.setString(3, order.getMem_addr2());
				pstmt.setString(4,order.getMem_phone());
				pstmt.setString(5,order.getMem_email());
				pstmt.setString(6,order.getMem_zipcode());
				pstmt.setString(7, order.getMem_id());
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
		
		//멤버 삭제
		public int deleteMemState (String mem_id) {
			int x=-1;
			try {
				con=pool.getConnection();
				sql="delete from mem_depth where mem_id=? ";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, mem_id);
				int delete =pstmt.executeUpdate();
				
				System.out.println("게시판 글 삭제 성공(delete):"+delete);
				rs=pstmt.executeQuery();
			}catch(Exception e) {
				System.out.println("deleteMemState()=>"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return x;
		}
	
	
		//비밀번호 초기화
			public int resetMemState (mem_updateDTO order) {
				int x=-1;
				try {
					con=pool.getConnection();
					sql="update mem_depth set mem_pwd=? where mem_id = ?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1,order.getMem_pwd());
					pstmt.setString(2, order.getMem_id());
					int update=pstmt.executeUpdate();
					System.out.println("resetStatus()성공="+update);
					rs=pstmt.executeQuery();
				}catch(Exception e) {
					System.out.println("resetStatus()오류="+e);
				}finally {
					pool.freeConnection(con,pstmt,rs);
				}
				return x;
			}
			
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
