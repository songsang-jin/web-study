package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import model.DBConnectionMgr;

public class REVIEWboardDAO {
	
	
		private DBConnectionMgr pool=null;
		private Connection con=null;
		private PreparedStatement pstmt=null; 
		private ResultSet rs=null;
		private String sql="";
		
	//DB연결
	public REVIEWboardDAO() {
			try {
				pool=DBConnectionMgr.getInstance(); 
			}catch(Exception e) {
			}
		}

		
	//레코드 수 구하기
		public int getArticleCount() {
			int x =0;
			try {	
				con=pool.getConnection();	 
				sql ="select count(*) from review_post"; 
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

		
		
		//리스트 목록
		public List getArticles(int start, int end) {
			ArrayList<REVIEWboardDTO> articleList =null; 
			String sql=null;
			
			try {
				con=pool.getConnection();
				sql = "SELECT * FROM (SELECT rownum rn, b.* FROM (SELECT * FROM review_post ORDER BY post_num DESC) b WHERE rownum <= ?) WHERE rn > ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, end);
				pstmt.setInt(2, start - 1);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					articleList = new ArrayList(end);
					do {
						REVIEWboardDTO article=new REVIEWboardDTO();
						article=makeArticleFromResult();
						articleList.add(article);
					}while(rs.next());
				}
			}catch(Exception e) {
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return articleList;
		}
		
	
		//페이징 계산을 해주는 구문
		public Hashtable pageList(String pageNum, int count){
			
			Hashtable<String,Integer> pgList = new Hashtable<String,Integer>();
			
			
		     int pageSize=10;
		     int blockSize=5;
		     
		    if(pageNum==null){
		    	pageNum="1";
		    }
		    int currentPage=Integer.parseInt(pageNum);
		    int startRow=(currentPage-1)*pageSize+1; 
		    int endRow=currentPage*pageSize;
		    int number=0;
		    
		    number=count-(currentPage-1)*pageSize;

		   int pageCount=count/pageSize+(count%pageSize==0?0:1);
	 	   int startPage=0;
	 	   if(currentPage%blockSize!=0){
	 	      startPage=currentPage/blockSize*blockSize+1;
	 	   }else{
	 		  startPage=((currentPage/blockSize)-1)*blockSize+1; 
	 	   }
	 	   int endPage=startPage+blockSize-1;

	 	   
	 	   if(endPage > pageCount)  endPage=pageCount;
	 	   pgList.put("pageSize", pageSize);
	 	   pgList.put("blockSize", blockSize);
	 	   pgList.put("currentPage", currentPage);
	 	   pgList.put("startRow", startRow);
	 	   pgList.put("endRow", endRow);
	 	   pgList.put("count", count);
	 	   pgList.put("number", number);
	 	   pgList.put("startPage", startPage);
	 	   pgList.put("endPage", endPage);
		   pgList.put("pageCount", pageCount);
		   
	 	   
	 	   return pgList;
		}
		
		//글쓰기 및 답글
		public void insertArticle (REVIEWboardDTO article) {
			
			int post_num=article.getPost_num();
			int maxNum=0;

			try {
				con=pool.getConnection();
				sql="select max(post_num) from review_post";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					maxNum=rs.getInt(1); 
				}else {
					maxNum=1; //기존데이터가 없을경우 1로 시작
				}
				post_num= maxNum +1;

				String sql="insert into review_post(post_num,mem_id,post_title,post_cnt,rated,post_date) values(?,?,?,?,?,?)";
				pstmt= con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
				pstmt.setString(2, article.getMem_id());
				pstmt.setString(3, article.getPost_title());
				pstmt.setString(4, article.getPost_cnt());
				pstmt.setString(5, article.getRated());
				pstmt.setTimestamp(6, article.getPost_date());

				int insert = pstmt.executeUpdate();

			}catch(Exception e) {
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
		}
		
		//글 상세보기
		public REVIEWboardDTO getArticle(int post_num) {
			REVIEWboardDTO article = null;
			try {
				con=pool.getConnection();
				sql ="update review_post set post_view =post_view+1 where post_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
				int update = pstmt.executeUpdate();
				System.out.println("조회수 증가(update)"+update);
				
				sql = "select * from review_post where post_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					article = makeArticleFromResult();
				}
			}catch(Exception e) {
			}
			return article;
		}
		
		
		
		
// 리스트 DB내용 불러오기
		private REVIEWboardDTO makeArticleFromResult() throws SQLException {

			REVIEWboardDTO article=new REVIEWboardDTO();
			article.setPost_num(rs.getInt("post_num"));
			article.setMem_id(rs.getString("mem_id"));
			article.setPost_title(rs.getString("post_title"));
			article.setPost_cnt(rs.getString("post_cnt"));
			article.setRated(rs.getString("rated"));
		    article.setPost_date(rs.getTimestamp("post_date"));

			return article;
		}
		
	
		
		
		//글 수정
		public int updateArticle(REVIEWboardDTO article) {
			int update=0;
			try {
				con=pool.getConnection();	//연결객체를 얻어오는 구문
				sql ="update review_post set post_title=?, post_cnt=?, rated=? where post_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, article.getPost_title());
				pstmt.setString(2, article.getPost_cnt());
				pstmt.setString(3, article.getRated());
				pstmt.setInt(4, article.getPost_num());
				update=pstmt.executeUpdate();
				
			}catch(Exception e) {
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return update;
		}
		
		//글삭제
		public int deleteArticle(int post_num)  {
			int delete=0;
			try {
				con=pool.getConnection();
				sql="delete from review_post where post_num=? ";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
				delete=pstmt.executeUpdate();
				rs=pstmt.executeQuery();
			}catch(Exception e) {
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return delete;
		}
		
	
	
		   
			//이전글 보기
			public REVIEWboardDTO getBeforeArticle(int post_num) {
				REVIEWboardDTO article = null;
				try {
					con=pool.getConnection();
					sql ="select * from (select r.*, ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rn from review_post r where post_num < ?) where rn = 1";

					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, post_num);
					rs=pstmt.executeQuery();
					if(rs.next()) {
						article = makeArticleFromResult();
					}
				}catch(Exception e) {
				}
				return article;
			}

			//다음글 보기
			public REVIEWboardDTO getAfterArticle(int post_num) {
				REVIEWboardDTO article = null;
				try {
					con=pool.getConnection();
					sql ="select * from (select r.*, ROW_NUMBER() OVER (ORDER BY post_num ASC) AS rn from review_post r where post_num > ?) where rn = 1";

					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, post_num);
					rs=pstmt.executeQuery();
					if(rs.next()) {
						article = makeArticleFromResult();
					}
				}catch(Exception e) {
				}
				return article;
			}
			
			
			public int getArticleSearchCount(String search,String searchtext) { 
				int x=0;
				try {
					con=pool.getConnection();
					System.out.println("con=>"+con);
					
					if (search == null || search == "") {
						sql = "select count(*) from review_post order by post_num desc";
					} else { 
					    if (search.equals("post_title")) {
					        sql = "select count(*) from review_post where post_title like '%"+searchtext+"%'";
					    } else if (search.equals("mem_id")) {
					        sql = "select count(*) from review_post where mem_id like '%"+searchtext+"%'";
					    } else {
					        sql = "select count(*) from review_post where post_cnt like '%"+searchtext+"%'";
					    }
					}
					System.out.println("getArticleSearchCount 검색sql=>"+sql);
				
					pstmt=con.prepareStatement(sql);
					rs=pstmt.executeQuery();
					if(rs.next()) {
						x=rs.getInt(1);
					}
				}catch(Exception e) {
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return x;
			}




public List getBoardArticles(int start,int end,String search,String searchtext) {
	
	List articleList=null;
	
	try {
		con=pool.getConnection();
	 
	    if (search == null || search.equals("")) { 
	        sql = "SELECT * FROM (SELECT rownum rn, b.* FROM (SELECT * FROM review_post ORDER BY post_num DESC) b WHERE rownum <= ?) WHERE rn > ?";
	    } else { 
	        if (search.equals("post_title")) {
	        	sql = "SELECT * FROM (SELECT rownum rn, b.* FROM (SELECT * FROM review_post where post_title like '%" + searchtext + "%' ORDER BY post_num DESC) b WHERE rownum <= ?) WHERE rn > ?";
	        } else if (search.equals("mem_id")) { 
	        	sql = "SELECT * FROM (SELECT rownum rn, b.* FROM (SELECT * FROM review_post where mem_id like '%" + searchtext + "%' ORDER BY post_num DESC) b WHERE rownum <= ?) WHERE rn > ?";
	        } else { 
	        	sql = "SELECT * FROM (SELECT rownum rn, b.* FROM (SELECT * FROM review_post where post_cnt like '%" + searchtext + "%' ORDER BY post_num DESC) b WHERE rownum <= ?) WHERE rn > ?";
	        }
	    }
	
	    
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, end);
		pstmt.setInt(2, start-1);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			articleList=new ArrayList(end);
			do {
			  REVIEWboardDTO article=new REVIEWboardDTO();
			  article.setPost_num(rs.getInt("post_num"));
			  article.setMem_id(rs.getString("mem_id"));
			  article.setPost_title(rs.getString("post_title"));
			  article.setPost_cnt(rs.getString("post_cnt"));
			  article.setRated(rs.getString("rated"));
			  article.setPost_date(rs.getTimestamp("post_date"));


		
			  articleList.add(article);
			}while(rs.next());
		}
	}catch(Exception e) {
	}finally {
		pool.freeConnection(con, pstmt, rs);
	}
	return articleList;
}








}
