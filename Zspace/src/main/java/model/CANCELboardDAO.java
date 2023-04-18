package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class CANCELboardDAO {

  private DBConnectionMgr pool=null; //(DBConnectionMgr)에 접근하기 위해
  
  private Connection con=null; 
  private PreparedStatement pstmt=null; //SQL실행목적 (변경할 것만 고르기)
  private ResultSet rs=null; //select
  private String sql=""; //실행시킬 SQL구문 저장 목적
	
  
  //0) 생성자를 통해 연결=>의존관계
  public CANCELboardDAO() {
	try {
		pool=DBConnectionMgr.getInstance();
	}catch(Exception e) {
		System.out.println("DB접속 오류: "+e);
	}
  }
  
  
  //1) 페이징 처리 전체 레코드 수 구하기 (10개씩 끊어오기)
  public int getArticleCount() {
	  int x=0; //x=총 레코드의 갯수
	  try {
		con=pool.getConnection(); //이미 만들어진 연결객체(Connection)를 얻어오는 것
		System.out.println("con: "+con); //DBConnection에서 확인
		sql="select count(*) from cancel_post";//내 담당테이블명 cencel_post
		pstmt=con.prepareStatement(sql);
		
		rs=pstmt.executeQuery();//select = 변경x
		  if(rs.next()) { //보여주는 결과가 있다면
			x=rs.getInt(1); // 
		  }
	  }catch (Exception e){
		  System.out.println("getArticleCount()에러유발: "+e);
	  }finally {
		  pool.freeConnection(con, pstmt, rs);
	  }
	  return x;
  }
  
//1-2) 페이징 처리 계산을 정리해주는 메서드(ListAction에서 소스 가져오기)
	public Hashtable pageList (String pageNum, int count) {
		
		//0.Hashtable 객체 선언 ->페이징처리 결과 저장
		Hashtable<String,Integer> pgList=new Hashtable<String, Integer>();
		
		int pageSize=10; 
		int blockSize=5;

		//게시판을 처음 실행시키면 무조건 1페이지부터 출력 : 가장 최근의 글이 나오게 설정
		if (pageNum==null){
		  pageNum="1";
		}
		//nowPage(현재 페이지(클릭해서 보는 페이지))
		int currentPage=Integer.parseInt(pageNum);
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=currentPage*pageSize;
		int number=0; //beginPerPage : 페이지별로 시작하는 맨처음나오는 게시물 번호

		System.out.println("현재 레코드 수(count)="+count);
		System.out.println("currentPage="+currentPage);
		
		number=count-(currentPage-1)*pageSize;
		System.out.println("페이지별 number="+number);
		
		//1. 총 페이지 수 구하기
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		//2. 시작페이지
		int startPage=0;
		  if(currentPage%blockSize!=0){//1~9, 11~19, 21~29,
			startPage=currentPage/blockSize*blockSize+1;
		  }else{  
			startPage=((currentPage/blockSize)-1)*blockSize+1;//시작페이지1로
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
	
  //2) 검색어에 따른 레코드 수를 메서드 작성(검색분야,검색어)
  public int getArticleSearchCount(String search, String searchtext) {//getMemberCount() -> MemberDAO()
	  int x=0; //총 레코드의 갯수를 저장
	  try {
		con=pool.getConnection();
		System.out.println("con="+con); //DBConnectionMgr에서 확인하기
		//---------------------------------------------------------------
		if(search==null||search=="") {//검색분야 선택x
		  sql="select count(*) from cancel_post ";
		}else {//검색분야(제목 작성자 제목+본문)
		  if(search.equals("post_title_post_cnt")) {//제목+본문 post_title+post_cnt
			sql="select count(*) from cencel_post where post_title like '%"+
		         searchtext+"%' or post_cnt like '%"+searchtext+"%' ";
		  }else { //제목, 작성자 => 필드명 -> 매개변수를 이용해서 sql통합
			sql="select count(*) from cencel_post where "+search+" like '%"+
				 searchtext+"%' ";
		  }	  
	    }
		System.out.println("getArticleSearchCount 검색 sql="+sql);
		//----------------------------------------------------------------
		pstmt=con.prepareStatement(sql);
		 
		rs=pstmt.executeQuery();
		if(rs.next()) { //보여주는 결과가 있다면 
		  x=rs.getInt(1); //변수명=re.get자료형(필드명 또는 인덱스 번호)
		  				//필드명이 없기 때문에 인덱스 번호로 불러오기
		}
	  }catch (Exception e){
		  System.out.println("getArticleSearchCount() 에러유발="+e);
	  }finally {
		  pool.freeConnection(con, pstmt, rs);
	  }
		  return x;
	  }

	
	
 
 // 3) 게시판의 글쓰기 및 답글쓰기  0316수정================================================================
	public void insertArticle (CANCELboardDTO article) {
	  // 신규글 or 답변글 확인용
	  int post_num=article.getPost_num(); //0이면 신규글, 0이 아니면 답변글
	  System.out.println("insertArticle메서드 내부의 post_num=>"+post_num);
	  int ref=article.getRef();//답변글부분
	  int re_step=article.getRe_step();
	  int re_level=article.getRe_level();

	  int n=0; //초기화  데이터 저장을 위한 게시물 번호
	  System.out.println("insertArticle 메서드의 내부 post_num: "+post_num);//신규글일떄
	  System.out.println("ref: "+ref+", re_step: "+re_step+",re_level=>"+re_level);
	  
		try {
			con=pool.getConnection();
			sql="select max(post_num) from cancel_post";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {//보여주는 결과가 있다면
				n=rs.getInt(1)+1;//최대값+1
			}else {
				n=1;//테이블에 한개의 데이터가 없다면
			}
			
			//답변,신규 구분하는곳
			if(post_num!=0) {//양수이면서 1이상
				//같은 그룹번호를 가지고 있으면서 나보다 step값이 큰 게시물을 찾아서 그 step값을 하나증가시켜라
				/*  System.out.println("답변글을 쓰기위한 로직테스트"); */
				  sql="update cancel_post set re_step=re_step+1 where ref=? and re_step > ?";
				  pstmt=con.prepareStatement(sql);
				  pstmt.setInt(1, ref);
				  pstmt.setInt(2, re_step);
				  
				  System.out.println("답변글을 쓰기위한 로직테스트222");
				  int update=pstmt.executeUpdate();
				  System.out.println("댓글수정유무(updte)=>"+update);//1.성공, 0(실패)
		
				  re_step=re_step+1;
				  re_level=re_level+1;
				  //post_num=n;
			}else {//신규글이라면 post_num=0
				ref=n;//1,2,3,4,,,n 
				re_step=0;
				re_level=0;
				System.out.println(" 신규한 경우의 ref ="+ref+"re_level=>"+re_level+" n= "+n);
				//post_num=n;
			}
			
		/**/
		//글번호,고객아이디,글제목,글내용
		sql="insert into cancel_post(post_num,mem_id,post_title,post_cnt,post_date,ref,re_step,re_level) values(?,?,?,?,?,?,?,?)";		//
		pstmt=con.prepareStatement(sql);
	   // pstmt.setInt(1, post_num);	
	    pstmt.setInt(1, n);	
	    pstmt.setString(2, article.getMem_id());//신규글일때->test
	    pstmt.setString(3, article.getPost_title());
	    pstmt.setString(4, article.getPost_cnt());
	    pstmt.setTimestamp(5, article.getPost_date());//0316 04:10수정 추가해줌
	    //답글저장부분 계산후 ref,re_step   
	    pstmt.setInt(6, ref);//n과 동일한 값
	    pstmt.setInt(7, re_step);	    
	    pstmt.setInt(8, re_level);
	    int insert=pstmt.executeUpdate();
	    
	    System.out.println("게시판 글쓰기, 답댓글 성공 확인(insert): "+insert);
	  }catch(Exception e) {
		System.out.println("boardDAO/insertArticle() 메소드에러 발생: "+e);
	  }finally {
		pool.freeConnection(con, pstmt, rs);
	  }
	}
	
	/* 수정중이던 관리자의 답글 &일반 글쓰기
	public void insertArticle (boardDTO article) {
		  // 신규글 or 답변글 확인용
		  int post_num=article.getPost_num(); //0이면 신규글, 0이 아니면 답변글
		  System.out.println("insertArticle메서드 내부의 post_num=>"+post_num);
		  int ref=article.getRef();//답변글부분
		  int re_step=article.getRe_step();
		  int re_level=article.getRe_level();

		  int n=0; //초기화  데이터 저장을 위한 게시물 번호
		  
		  System.out.println("insertArticle 메서드의 내부 post_num: "+post_num);//신규글일떄
		  System.out.println("ref: "+ref+", re_step: "+re_step+",re_level=>"+re_level);
		  
			try {
				con=pool.getConnection();
				sql="select max(post_num) from cancel_post";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {//보여주는 결과가 있다면
					n=rs.getInt(1)+1;//최대값+1
				}else {
					n=1;//테이블에 한개의 데이터가 없다면
				}
				
				//post_num=n+1;//글증가  최대값+1을 여기서 직접 넣어주는거
				//article.setPost_num(post_num);

				if(post_num!=0) {//양수이면서 1이상
					//같은 그룹번호를 가지고 있으면서 나보다 step값이 큰 게시물을 찾아서 그 step값을 하나증가시켜라
					try {
					  System.out.println("답변글을 쓰기위한 로직테스트");
					  sql="update cancel_post set re_step=re_step+1 where ref=? and re_step > ?";
					  pstmt=con.prepareStatement(sql);
					  pstmt.setInt(1, ref);
					  pstmt.setInt(2, re_step);
					  System.out.println("답변글을 쓰기위한 로직테스트222");
					  int update=pstmt.executeUpdate();
					  System.out.println("댓글수정유무(updte)=>"+update);//1.성공, 0(실패)
					  
					  //답변글
					  re_step=re_step+1;
					  re_level=re_level+1;
					  
					//글번호,고객아이디,글제목,글내용 여기 post_num인데 위에 n에서 값을 가져오는 이유 - 집어넣을 값이 데이터가 없으면 0이 나올것인데 위에서 그계산을n이했음
					  //sql 문장이 잘못됨 우리가 db에 만든 테이블 순서에 맞춰서 date부분은 sysdate,
						sql="insert into cancel_post(post_num,mem_id,admin_id, post_title,post_date,post_cnt,ref,re_step,re_level) values(?,?,?,?,'YY/MM/DD',?,?,?,?)";		//
						pstmt=con.prepareStatement(sql);
					   // pstmt.setInt(1, post_num);	
					    pstmt.setInt(1, n);	
					    pstmt.setString(2, article.getMem_id());//신규글일때->test
					    pstmt.setString(3,article.getAdmin_id());//관리자++
					    pstmt.setString(4, article.getPost_title());
					    
					    pstmt.setTimestamp(5, article.getPost_date());//0316 04:10수정 추가해줌
					    pstmt.setString(6, article.getPost_cnt());
					    //답글저장부분 계산후 ref,re_step   
					    pstmt.setInt(7, ref);//n과 동일한 값
					    pstmt.setInt(8, re_step);	    
					    pstmt.setInt(9, re_level);
					    int insert=pstmt.executeUpdate();
					    
					    System.out.println("게시판 글쓰기, 답댓글 성공 확인(insert): "+insert);
					  }catch(Exception e) {
						System.out.println("boardDAO/insertArticle() 메소드에러 발생: "+e);
					  }finally {
						pool.freeConnection(con, pstmt, rs);
					  }
				}else {//신규글이라면 post_num=0
				  try {
					ref=n;//1,2,3,4,,,n 
					re_step=0;
					re_level=0;
					//글번호,고객아이디,글제목,글내용
					sql="insert into cancel_post(post_num,admin_id,post_title,post_cnt,post_date,ref,re_step,re_level) values(?,?,?,?,?,?,?,?)";		//
					pstmt=con.prepareStatement(sql);
				   // pstmt.setInt(1, post_num);	
				    pstmt.setInt(1, n);	
				    pstmt.setString(2, article.getAdmin_id());//신규글일때->test 신규가아니라면?
				    pstmt.setString(3, article.getPost_title());
				    pstmt.setString(4, article.getPost_cnt());
				    pstmt.setTimestamp(5, article.getPost_date());//0316 04:10수정 추가해줌
				    //답글저장부분 계산후 ref,re_step   
				    pstmt.setInt(6, ref);//n과 동일한 값
				    pstmt.setInt(7, re_step);	    
				    pstmt.setInt(8, re_level);
				    
				    int insert=pstmt.executeUpdate();
				    
				    System.out.println("게시판 글쓰기, 답댓글 성공 확인(insert): "+insert);
				  }catch(Exception e) {
					System.out.println("boardDAO/insertArticle() 메소드에러 발생: "+e);
				  }finally {
					pool.freeConnection(con, pstmt, rs);
				  }
				}}catch(Exception e) {}
		}*/
	
	//4)글 상세보기
	public CANCELboardDTO getArticle (int post_num) {
	  CANCELboardDTO article=null;
	  try {
		con=pool.getConnection();
		sql="update cancel_post set post_view=post_view+1 where post_num=?"; //조회수 증가
		//sql="update cancel_post set post_num=post_num+1";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, post_num);
		int update=pstmt.executeUpdate();
		System.out.println("조회수 증가(update): "+update);
		
		sql="select * from cancel_post where post_num=? "; //글 찾기??
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,post_num);
		rs=pstmt.executeQuery();
		  if(rs.next()) {
			article=makeArticleFromResult();// 생성된객체를 얻어오는부분 
		  }
	  }catch(Exception e) {
		  System.out.println("getArticle() 에러: "+e);
	  }finally {
		  pool.freeConnection(con,pstmt,rs);
	  }
	  return article;
	}
	//중복된 레코드를 한군데모아서 처리하는거 접근지정자가 private인 경우=->외부에서 호출목적X ,내부에서 호출목적으로 사용
	private CANCELboardDTO makeArticleFromResult() throws Exception {
	  CANCELboardDTO article=new CANCELboardDTO();//MemberDTO=>필드별로 담을것.
	  
	    article.setPost_num(rs.getInt("post_num"));//글번호	  
	    article.setMem_id(rs.getString("mem_id"));
	    article.setAdmin_id(rs.getString("admin_id"));
	    article.setPost_title(rs.getString("post_title"));//글제목
	    article.setPost_cnt(rs.getString("post_cnt"));//글내용
	    article.setPost_date(rs.getTimestamp("post_date"));//작성일
	    //답변글쪽
	    article.setRef(rs.getInt("ref")); //그룹번호 : 신규글+답변글 묶어주기
	    article.setRe_step(rs.getInt("re_step")); //답변글이 나오는 순서
	    article.setRe_level(rs.getInt("re_level"));//들여쓰기
	 
	    
	  return article;
	}
	
	//"cancel_post" 테이블에서 "post_num" 값이 주어진 값과 일치하는 행을 검색
	//5)글 수정하기 (1)	수정할데이터찾기
	public CANCELboardDTO updateGetArticle(int post_num ) {
	  CANCELboardDTO article=null;
	  try {
		con=pool.getConnection();
		sql="select * from cancel_post where post_num=?  ";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, post_num);
		rs=pstmt.executeQuery();
		if(rs.next()) {//보여줄결과가 있다면
		  article=makeArticleFromResult();
		}	
	  }catch(Exception e) {
		  System.out.println("updateGetArticle() 에러: "+e);
	  }finally {
		  pool.freeConnection(con, pstmt, rs);
	  }
	  return article;
	}

	//sql=update cancel_post테이블에 글제목,글내용 업데이트 글이름이 일치하는
	//5-1)글수정하기 (2) ->(1)에 일치하는 데이터 변경(update)하기 
	public int updateArticle(CANCELboardDTO article) {
	  int x=-1; //게시글의 성공유무 체크
	  try {
		con=pool.getConnection();
		sql="update cancel_post set post_title=?, post_cnt=? where post_num=?";// item_img=?, rated=?,
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, article.getPost_title());
		pstmt.setString(2, article.getPost_cnt());
		pstmt.setInt(3, article.getPost_num());
		int update=pstmt.executeUpdate();
		
		System.out.println("게시판의 글수정 성공 확인(update): "+update);
		x=1;
	  }catch(Exception e) {
		System.out.println("updateArticle() 메서드 에러: "+e);
	  }finally {
		pool.freeConnection(con, pstmt);
	  }
	  return x;
	}
	
	
	
	/*6)글삭제하기*/
	public int deleteArticle(int post_num)  {
        int x=-1;
        System.out.println("deleteArticle에서의 num=>"+post_num);
        try {
           con=pool.getConnection();
           sql="delete from cancel_post where post_num=? ";
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
	
	//여기 이해못하겠음========================================================================================
	//글목록이 나올수있게 불러오는부분
	
	//7) 글 검색하기 : 검색어에 따른 레코드의 범위 지정에 대한 메서드
	public List getBoardArticles(int start,int end, String search, String searchtext) {
	  List articleList=null; //ArrayList<BoardDTO> articleList=null (o)
	  try {
		con=pool.getConnection();
		//------------------------------------------------------------------------------
		if(search==null||search=="") { //검색분야
			//0311	(SELECT ROW_NUMBER->내림차() OVER (ORDER BY ==>번호제한  post_num DESC)
			//->정렬값  rnum으로 갈거고  cancel_post에 뜰거야 rnum between ? and ?"-->어디부터 어디까지
			sql="SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rnum, cancel_post.* FROM cancel_post) WHERE rnum between ? and ?";
			// (=SELECT cp.*, ROWNUM AS rn FROM cancel_post cp ORDER BY cp.ref DESC, cp.re_step DESC ) WHERE rn = ?
			  //sql="select * from cancel_post where rnum >=? and rnum <= ? order by ref desc re_step ";//?,? => A and B //그룹번호=게시물번호역할도 하기 떄문에 가능 order by ref desc re_step limit ?,?
			}else { //제목+본문
			if(search.equals("post_title_post_cnt")) { //제목+본문
			  sql="select * from  cancel_post where post_title like '%"+searchtext+"%' or post_cnt like '%"+
					  searchtext+"%' and re_step >= ? AND re_step <= ? order by ref desc";// limit ?,?->rnum 크거나 같을떄
			}else { //제목,작성자-> 필드명 -> 매개변수를 이용해서 하나의 sql통합
				
			  sql="select * from  cancel_post where "+search+" like '%"+
					  searchtext+"%' and re_step >= ? AND re_step <= ? order by ref desc"; //limit ?,?->
			}
			}
		//System.out.println("DAO start+end==>보여라"+start+";"+end);
		
		 System.out.println("getBoardArticles()의 sql="+sql);
			
		 pstmt=con.prepareStatement(sql);
		 pstmt.setInt(1, start);//mysql은 레코드 번호 순번이 내부적으로 0부터 시작하기 때문에
		 pstmt.setInt(2, end); //몇 개까지 불러와서 담을 건가? default(10)
		 rs=pstmt.executeQuery();
			//기존에 데이터가 있으면 누적해서 쌓아 올려야한다.
		 if(rs.next()) {//화면에 보여줄 데이터가 있으면
		   articleList=new ArrayList(end); //end갯수만큼 공간을 생성해라
		   do {
			 CANCELboardDTO article=new CANCELboardDTO(); //회원리스트면 MemberDTO
			 article=makeArticleFromResult();
			 //필드별로 setter메서드를 통해 각각 넣어준다. like 분리수거
			 //추가
			 articleList.add(article);//생략하면 데이터 저장X => for문X(Null)
		   }while(rs.next());//더 있으면 계속
		   
		 }
	  }catch(Exception e) {
		System.out.println("getArticles() 에러발생=>"+e);
	  }finally {
		pool.freeConnection(con, pstmt, rs);
	  }
	  
		return articleList;
	}
}
