package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.DBConnectionMgr;

public class QNAboardDAO {

  private DBConnectionMgr pool=null; //(DBConnectionMgr)에 접근하기 위해
  private Connection con=null; //이미 만들어진 연결객체(Connection)를 얻어오는 것
  private PreparedStatement pstmt=null; //SQL실행목적 (변경할 것만 고르기)
  private ResultSet rs=null; //select
  private String sql=""; //실행시킬 SQL구문 저장 목적
  
  //생성자 만들기
  public QNAboardDAO() {
	try {
		pool=DBConnectionMgr.getInstance();
	}catch(Exception e) {
		System.out.println("DB접속 오류: "+e);
	}
  }
  
   //1. 검색어에 따른 레코드 수를 나타내는 메서드 작성
  public int getArticleSearchCount(String search, String searchtext) {
	  int x=0; //총 레코드의 갯수를 저장
	  try {
		con=pool.getConnection();
		System.out.println("con="+con); //DBConnectionMgr에서 확인하기
		if(search==null||search=="") {//검색분야 선택하지 않은 경우
		  sql="select count(*) from qna_post";
		}else {//검색분야 선택한 경우 (제목 제목+본문)
		  if(search.equals("post_title_post_cnt")) {//제목+본문
			sql="select count(*) from qna_post where post_title like '%"+searchtext+"%' or post_cnt like '%"+searchtext+"%'";
		  }else { //제목, 작성자 => 필드명 -> 매개변수를 이용해서 sql통합
			sql="select count(*) from qna_post where "+search+" like '%"+searchtext+"%'";
		  }		  
	    }
		System.out.println("getArticleSearchCount 검색 sql="+sql);
		//----------------------------------------------------------------
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		if(rs.next()) { //보여주는 결과가 있다면 
		  x=rs.getInt(1);
		}
	  }catch (Exception e){
		  System.out.println("getArticleSearchCount() 에러유발="+e);
	  }finally {
		  pool.freeConnection(con, pstmt, rs);
	  }
		  return x;
	  }
  
  
  //2. 글 목록 
  public List getArticles(int start, int end) {
	  ArrayList<QNAboardDTO> articleList=null;
	 String sql=null;
		System.out.println("getArticles =>"+start+","+end);
	try {
	  con=pool.getConnection();
	 sql = "select * from qna_post WHERE ROWNUM >= ? AND ROWNUM <= ? ORDER BY ref desc and post_num DESC";
	  //sql="select * from board order by ref desc,re_step limit ?,?";
	  // sql="SELECT * FROM qna_post ORDER BY post_num desc";
	 // sql="select * from qna_post where re_step >= ? and re_step <= ? order by ref desc";
	  
	  pstmt=con.prepareStatement(sql);
	  pstmt.setInt(1, start-1); //-1을 주면 전 페이지와 현재 페이지의 글이 하나 겹침 
	  pstmt.setInt(2, end);
	  rs=pstmt.executeQuery();
	  if(rs.next()) { //누적개념
		articleList=new ArrayList(end);
		do {
		  QNAboardDTO article=new QNAboardDTO();
		  article=makeArticleFromResult();
		  articleList.add(article); //생략하면 데이터가 저장되지 않음
		}while(rs.next());
	  }
	}catch(Exception e) {
	  System.out.println("getArticles() 글목록보기 에러 발생: "+e);
	}finally {
	  pool.freeConnection(con, pstmt, rs);
	}
	return articleList;
  }

 //3-1. 페이징 처리 계산을 정리해주는 메서드(ListAction에서 소스 가져오기)
  public Hashtable pageList (String pageNum, int count) {
	//0) 페이징 처리 결과를 저장할 Hashtable 객체 선언
	Hashtable<String,Integer> pgList=new Hashtable<String, Integer>();
	
	int pageSize=10; //numPerPage : 페이지당 보여주는 게시물 수(=레코드 수)
	int blockSize=5;//pagePerBlock : 블럭당 보여주는 페이지 수 10
		//게시판을 처음 실행시키면 무조건 1페이지부터 출력 : 가장 최근의 글이 나오게 설정
	if (pageNum==null){
	  pageNum="1"; //default를 무조건 1페이지로 설정
	}
	//nowPage(현재 페이지(클릭해서 보는 페이지))
	  int currentPage=Integer.parseInt(pageNum); //"1" -> 1
	//			         (1-1)*10 +1=1, (2-1)*10 +1=11, (3-1)*10+1=21, ...
	  int startRow=(currentPage-1)*pageSize+1; //시작레코드 번호
	  int endRow=currentPage*pageSize; //1*10=10, ...
	  int number=0; //beginPerPage : 페이지별로 시작하는 맨처음 나오는 게시물 번호

	  System.out.println("현재 레코드 수(count)="+count);
		
	  number=count-(currentPage-1)*pageSize;
	  System.out.println("페이지별 number="+number);
		
	  //1) 총 페이지 수 구하기
		//              122/10=12.2+1.0=13.2 ==> int 13 //(122%10=2 ==>0이 아니니까 1)
	  int pageCount=count/pageSize+(count%pageSize==0?0:1);
	  //2) 시작페이지
	  int startPage=0;
	  if(currentPage%blockSize!=0){//1~9, 11~19, 21~29,
		  startPage=currentPage/blockSize*blockSize+1;
	  }else{ //10%10=0,(10,20,30,40~)
				  //		((10/10)-1)*10+1=1
		startPage=((currentPage/blockSize)-1)*blockSize+1;
	  } //종료페이지
	  int endPage=startPage+blockSize-1; //1+10-1=10, 11+10-1=20
	  System.out.println("startPage="+startPage+", endPage="+endPage);
	  //블럭별로 구분해서 링크 걸어 출력
		   //    11 > 10      //마지막페이지=총페이지 수
	  if(endPage>pageCount) endPage=pageCount;
	 	//페이징 처리에 대한 계산 결과 : Hashtable에 저장해서 ListAction에 전달한 뒤
		//메모리에 저장 후 공유해서 list.jsp에서 불러다 사용
		pgList.put("pageSize", pageSize); //pgList.get(key명) : key, value값 다르면 찾기 힘듦
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

  //3-2. 페이징 처리 전체 레코드 수 구하기 (10개씩 끊어오기)
  public int getArticleCount() {
	int x=0; //x=총 레코드의 갯수
	  try {
		con=pool.getConnection(); 
		System.out.println("con: "+con);
		sql="select count(*) from qna_post";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
	    if(rs.next()) { 
			x=rs.getInt(1); 
		}
	  }catch (Exception e){
		 System.out.println("getArticleCount()에러유발: "+e);
	  }finally {
		pool.freeConnection(con, pstmt, rs);
	  }
	    return x;
	  }	
	
 // 4.게시판의 글쓰기 및 답글쓰기
	public void insertArticle (QNAboardDTO article) {
	  // 신규글 or 답변글
	  int post_view=0;
      int post_num=article.getPost_num();
      int ref=article.getRef();
	  int re_step=article.getRe_step();
	  int re_level=article.getRe_level();
	  
	  int number=0;// 데이터 저장을 위한 게시물 번호
	  System.out.println("insertArticle 메서드의 내부 호출");
	  System.out.println("ref="+ref+", re_step="+re_step+", re_level="+re_level);
	  
	  try {	
		con=pool.getConnection();
		sql="select max(post_num) from qna_post";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();
		//1) 신규글 일 때
		if (rs.next()) { // 기존 레코드 데이터가 있으면
			number=rs.getInt(1)+1; //number(0)+1
		}else { //기존 데이터가 없는 경우 1부터 시작한다는 뜻
			number=1;
		}
		//article.setPost_num(number); 논리적 오류

		//2) 답변글일 때
		if(post_num!=0) {//답변글이라면
		 /* sql="update qna_post set re_step=re_step+1 where ref=? and re_step > ?";
		  // = 그룹번호가 같은 사람에게 step을 1 증가시킴..는 뜻..(step은 최대 1)
		  pstmt=con.prepareStatement(sql);
		  pstmt.setInt(1,ref);
		  pstmt.setInt(2,re_step);
		  int update=pstmt.executeUpdate();
		  System.out.println("댓글 업로드 유무(update): "+update);*/
			
		  //답변글
		  re_step=re_step+1;
		  re_level=re_level+1;
		  post_num=number;
		}else {//신규글 post_num=0
		  ref=number;
		  re_step=0;
		  re_level=0;
		}
	    sql="insert into qna_post(post_num, mem_id, admin_id, post_title, post_cnt,";
	    sql+=" post_date, reply_cnt, reply_date, post_view, ref, re_step, re_level) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		pstmt=con.prepareStatement(sql);
	    pstmt.setInt(1, number);
	    pstmt.setString(2, article.getMem_id());
	    pstmt.setString(3, article.getAdmin_id());
	    pstmt.setString(4, article.getPost_title());
	    pstmt.setString(5, article.getPost_cnt());
	    pstmt.setTimestamp(6, article.getPost_date());
	    pstmt.setString(7, article.getReply_cnt());
	    pstmt.setTimestamp(8, article.getReply_date());
	    pstmt.setInt(9, post_view);
	    pstmt.setInt(10, ref);
	    pstmt.setInt(11, re_step);
	    pstmt.setInt(12, re_level);
	    int insert=pstmt.executeUpdate();
	    System.out.println("게시판 글쓰기, 답댓글 성공 확인: "+insert);
	  }catch(Exception e) {
		System.out.println("insertArticle() 에러 발생: "+e);
	  }finally {
		pool.freeConnection(con, pstmt, rs);
	  }
	}
		
	//5. 추가 : 답글 추가
	public void addReply(QNAreplyDTO redata) {
		try {
			con=pool.getConnection();
			sql="insert into reply values(reply_num_seq.NEXTVAL,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, redata.getAdmin_id());
			pstmt.setString(2, redata.getReply_cnt());
			pstmt.setTimestamp(3, redata.getReply_date());
			pstmt.setInt(4, redata.getPost_num());
			
			int reply=pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("addReply() 에러 발생: "+e);
		}finally {
			pool.freeConnection(con, pstmt);
		}
	}
	
	//5-2. 댓글 달기
	public List replyDetail(int post_num) {
		List replyList=null;
		try {
			con=pool.getConnection();
			String sql="select * from reply where post_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			rs=pstmt.executeQuery();
			if(rs.next()==true) {
				replyList=new ArrayList();
				do{ 
					QNAreplyDTO redata=new QNAreplyDTO();
					redata.setReply_num(rs.getInt("Reply_num"));
					redata.setAdmin_id(rs.getString("admin_id"));
					redata.setReply_date(rs.getTimestamp("reply_date"));
					redata.setReply_cnt(rs.getString("reply_cnt"));
					redata.setPost_num(rs.getInt("post_num"));

					replyList.add(redata);
				}while(rs.next());
			}
		}catch(Exception e){
			System.out.println("replyDetail() 에러 발생: "+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return replyList;
	}
	
	//5-3. 댓글 삭제하기
	public int delReply (int reply_num) {
		int x=-1; //데이터 삭제 유무
		String sql="";
		try {
			con=pool.getConnection();
			con.setAutoCommit(false);
			sql="delete from reply where reply_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reply_num);
			int delete=pstmt.executeUpdate();
			con.commit();
		}catch(Exception e) {
			System.out.println("delReply() 에러 발생: "+e);
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return x;
	}
	
	//댓글 DTO값
		private QNAreplyDTO makeReplyFromResult() throws Exception {
			QNAreplyDTO redata=new QNAreplyDTO();
		  
			redata.setReply_num(rs.getInt("reply_num"));
			redata.setPost_num(rs.getInt("post_num"));
			redata.setAdmin_id(rs.getString("admin_id"));
			redata.setReply_cnt(rs.getString("reply_cnt"));
			redata.setReply_date(rs.getTimestamp("reply_date"));
			redata.setPost_cnt(rs.getString("post_cnt"));
		  return redata;
		}
		
	//6. 글 상세보기
	public QNAboardDTO getArticle (int post_num) {
	  QNAboardDTO article=null;
	  try {
		con=pool.getConnection();
		sql="update qna_post set post_view=post_view+1 where post_num=?"; //해당 글 번호 조회수 증가
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, post_num);
		int update=pstmt.executeUpdate();
		System.out.println("조회수 증가(update) 확인: "+update);
		
		sql="select * from qna_post where post_num=?"; //글 번호 찾아서 확인
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,post_num);
		rs=pstmt.executeQuery();
		  if(rs.next()) {
			article=makeArticleFromResult();
		  }
	  }catch(Exception e) {
		  System.out.println("getArticle() 에러: "+e);
	  }finally {
		  pool.freeConnection(con,pstmt,rs);
	  }
	  return article;
	}

	//7. 전체 DTO 값
	private QNAboardDTO makeArticleFromResult() throws Exception {
	  QNAboardDTO article=new QNAboardDTO();
	  
	    article.setPost_num(rs.getInt("post_num"));
	    article.setPost_view(rs.getInt("post_view")); //default=0
	    article.setMem_id(rs.getString("mem_id"));
	    article.setAdmin_id(rs.getString("admin_id"));
	    article.setPost_title(rs.getString("post_title"));
	    article.setPost_cnt(rs.getString("post_cnt"));
	    article.setPost_date(rs.getTimestamp("post_date"));
	    article.setReply_cnt(rs.getString("reply_cnt"));
	    article.setReply_date(rs.getTimestamp("reply_date"));
	    article.setRef(rs.getInt("ref"));
	    article.setRe_step(rs.getInt("re_step"));
	    article.setRe_level(rs.getInt("re_level"));
	  return article;
	}
	
	//8. 글 수정하기 : 수정할 데이터를 찾자
	public QNAboardDTO updateGetArticle(int post_num) {
	  QNAboardDTO article=null;
	  try {
		con=pool.getConnection();
		sql="select * from qna_post where post_num=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, post_num);
		rs=pstmt.executeQuery();
		if(rs.next()) {
		  article=makeArticleFromResult();
		}	
	  }catch(Exception e) {
		  System.out.println("updateGetArticle() 에러: "+e);
	  }finally {
		  pool.freeConnection(con, pstmt, rs);
	  }
	  return article;
	}
	
	//8-2. 수정할 메서드
	public int updateArticle(QNAboardDTO article) {
	  int x=-1; //게시글의 성공유무 체크
	  try {
		con=pool.getConnection();
		sql="update qna_post set post_title=?, post_cnt=? where post_num=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, article.getPost_title());
		pstmt.setString(2, article.getPost_cnt());
		pstmt.setInt(3, article.getPost_num());
		int update=pstmt.executeUpdate();
		System.out.println("article.post_cnt: "+article.getPost_cnt());
		System.out.println("게시판의 글수정 성공 확인(update): "+update);
		x=1;
	  }catch(Exception e) {
		System.out.println("updateArticle() 메서드 에러: "+e);
	  }finally {
		pool.freeConnection(con, pstmt);
	  }
	  return x;
	}

	//9. 글 삭제하기
	public int deleteArticle(int post_num)  {
        int x=-1;
        try {
           con=pool.getConnection();
           sql="delete from qna_post where post_num=? ";
           pstmt=con.prepareStatement(sql);
           pstmt.setInt(1, post_num);
           int delete =pstmt.executeUpdate();
           
           System.out.println("게시판 글 삭제 성공(delete):"+delete);
           rs=pstmt.executeQuery();
        }catch(Exception e) {
           System.out.println("deleteArticle()=>"+e);
        }finally {
           pool.freeConnection(con,pstmt,rs);
        }
        return x;
	}
	
	
	//10. 글 검색하기 : 검색어에 따른 레코드의 범위 지정에 대한 메서드
	public List getBoardArticles(int start,int end, String search, String searchtext) {
		ArrayList<QNAboardDTO> articleList=null; //ArrayList<BoardDTO> articleList=null (o)

		try {
		con=pool.getConnection();
		//------------------------------------------------------------------------------
		if(search==null||search=="") { //검색어가 없으면
		//sql="select * from qna_post where re_step >= ? and re_step <= ? order by ref desc";
		//sql="SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rnum, qna_post.* FROM qna_post) WHERE rnum between ? and ?";
	 		/* (SELECT ROW_NUMBER() : 내림차 
				OVER (ORDER BY ==>번호제한  post_num DESC)->정렬값  rnum으로 갈거고  cancel_post에 뜰거야 rnum between ? and ?"-->어디부터 어디까지
	        // (=SELECT cp.*, ROWNUM AS rn FROM cancel_post cp ORDER BY cp.ref DESC, cp.re_step DESC ) WHERE rn = ?
			*/
		//sql="SELECT * FROM (SELECT qna_post.*, ROWNUM rnum FROM (SELECT * FROM qna_post ORDER BY ref DESC, re_step) qna_post WHERE ROWNUM <= ? ) WHERE rnum >= ?";	
		sql="SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rnum, qna_post.* FROM qna_post) q LEFT OUTER JOIN ";
		sql+=" qna_post r ON q.ref = r.post_num WHERE q.rnum BETWEEN ? AND ? ORDER BY q.post_num DESC";
		}else { //검색어가 있다면
		if(search.equals("post_title_post_cnt")) { //제목일 때, 본문일 때
		  sql="select * from qna_post where post_title like '%"+searchtext+"%' or post_cnt like '%"+
				  searchtext+"%' and re_step >= ? AND re_step <= ? order by ref desc";
		}else { //작성자
		  sql="select * from qna_post where "+search+" like '%"+
			   searchtext+"%' and re_step >= ? AND re_step <= ? order by ref desc";
		}
	  }
		 System.out.println("getBoardArticles()의 sql="+sql);
			
		 pstmt=con.prepareStatement(sql);
		 pstmt.setInt(1, start);//-1을 주면 전 페이지와 현재 페이지의 글이 하나 겹침
		 pstmt.setInt(2, end); //몇 개까지 불러와서 담을 건가? default(10)
		 rs=pstmt.executeQuery();

		 if(rs.next()) {//화면에 보여줄 데이터가 있으면
		   articleList=new ArrayList(end); //end갯수만큼 공간을 생성해라
		   do {
			 QNAboardDTO article=new QNAboardDTO(); //회원리스트면 MemberDTO
			 article=makeArticleFromResult();
			 //필드별로 setter메서드를 통해 각각 넣어준다. like 분리수거
			 articleList.add(article);//생략하면 데이터 저장X => for문X(Null)
		   }while(rs.next());//더 있으면 계속
		 }
	  }catch(Exception e) {
		System.out.println("getArticles() 검색 에러발생=>"+e);
	  }finally {
		pool.freeConnection(con, pstmt, rs);
	  }
		return articleList;
	}
}
	