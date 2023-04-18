package model;

//DBConnectionMgr(DB접속, 관리),
//BoardDTO(매개변수, 반환형으로 사용하거나 데이터를 담는 역할)

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
//ArrayList,List 사용
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class FAQboardDAO { //MemberDAO
	
  //has a 관계 
  private DBConnectionMgr pool=null; //1)연결할 클래스 객체 선언
  //공통
  private Connection con=null;
  private PreparedStatement pstmt=null;
  private ResultSet rs=null; //select
  private String sql=""; //실행시킬 SQL구문 저장용
	
  //2) 생성자를 통해 연결
  public FAQboardDAO() {
	try {
	  pool=DBConnectionMgr.getInstance();
	}catch(Exception e) {
	  System.out.println("DB 접속 오류="+e);
	}
  }//생성자
	
  //1. 페이징 처리를 위한 전체 레코드 수를 구해와야 함
	//  ㄴ 페이징 처리 : 10개씩 끊어서 보여주는 것(게시판)
  public int getArticleCount() {//getMemberCount() -> MemberDAO()
	int x=0; //총 레코드의 갯수를 저장
	try {
	  con=pool.getConnection();
	  System.out.println("con="+con); //DBConnectionMgr에서 확인하기
	  sql="select count(*) from faqpost"; //select count(*) from member;
	  pstmt=con.prepareStatement(sql);
	  rs=pstmt.executeQuery();
	  	if(rs.next()) { //보여주는 결과가 있다면 
	  	  x=rs.getInt(1); //변수명=re.get자료형(필드명 또는 인덱스 번호)
						//필드명이 없기 때문에 인덱스 번호로 불러오기
		}
	}catch (Exception e){
	  System.out.println("getArticleCount() 에러유발="+e);
	}finally {
	  pool.freeConnection(con, pstmt, rs);
	}
	  return x;
	}
  
  //(230116) 1) 게시판의 레코드수를 검색어에 따른 메서드작성(검색분야,검색어)
  public int getArticleSearchCount(String search, String searchtext) {//getMemberCount() -> MemberDAO()
		int x=0; //총 레코드의 갯수를 저장
		try {
		  con=pool.getConnection();
		  System.out.println("con="+con); //DBConnectionMgr에서 확인하기
		  //---------------------------------------------------------------
		  if(search==null||search=="") {//검색분야 선택x
		  sql="select count(*) from faqpost"; //select count(*) from member;
		  }else {//검색분야(제목 작성자 제목+본문)
			  if(search.equals("post_title_post_cnt")) {//제목+본문
				sql="select count(*) from faqpost where post_title like '%"+
			         searchtext+"%' or post_cnt like '%"+searchtext+"%'";
			  }else { //제목, 작성자 => 필드명 -> 매개변수를 이용해서 sql통합
				sql="select count(*) from faqpost where "+search+" like '%"+
					 searchtext+"%'";
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
  
	//(230116) 2) 검색어에 따른 레코드의 범위 지정에 대한 메서드
	public List getBoardArticles(int start,int end, String search, String searchtext) {
		List articleList=null; //ArrayList<BoardDTO> articleList=null (o)
		try {
			con=pool.getConnection();
			//------------------------------------------------------------------------------
			if (search == null || search.equals("")) {
	             sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rnum, faqpost.* FROM faqpost) WHERE rnum between ? and ?";
	         } else {
	             if (search.equals("subject_content")) {
	                 sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rnum, faqpost.* FROM faqpost WHERE post_title LIKE '%' || "+searchtext+" || '%' OR post_cnt LIKE '%' || "+searchtext+" || '%') WHERE rnum >= ? AND rnum <= ?";
	             } else {
	                 sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rnum, faqpost.* FROM faqpost WHERE " + search + " LIKE '%' || "+searchtext+" || '%') WHERE rnum >= ? AND rnum <= ?";
	             }
	         }
			System.out.println("getBoardArticles()의 sql="+sql);
			System.out.println("getBoardArticles()====>"+start+","+end );
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, start);//mysql은 레코드 번호 순번이 내부적으로 0부터 시작하기 때문에
			pstmt.setInt(2, end); //몇 개까지 불러와서 담을 건가? default(10)
			rs=pstmt.executeQuery();
			//기존에 데이터가 있으면 누적해서 쌓아 올려야한다.
			if(rs.next()) {//화면에 보여줄 데이터가 있으면
				articleList=new ArrayList<FAQboardDTO>(end); //end갯수만큼 공간을 생성해라
				do {
					FAQboardDTO article=new FAQboardDTO(); //회원리스트면 MemberDTO
					article=makeArticleFromResult();
					System.out.println("getBoardArticles=>"+article.getPost_num());
					articleList.add(article);//생략하면 데이터 저장X => for문X(Null)
				}while(	rs.next());//더 있으면 계속
			}
		}catch(Exception e) {
			System.out.println("getArticles() 에러발생=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList; //list.jsp에서 반환 -> for문 테이블에 출력
	}
  //(230116) 3) 페이징 처리 계산을 정리해주는 메서드(ListAction에서 소스 가져오기)
	//Hashtable 이유 : 페이징 처리에 관련된 처리 결과를 저장할 변수들을 하나의 객체에 담아서 반환시켜 줌(key,value) : ${key명}
	public Hashtable pageList (String pageNum, int count) {
		
		//0. 페이징 처리 결과를 저장할 Hashtable 객체 선언
		  Hashtable<String,Integer> pgList=new Hashtable<String, Integer>();
		//1. listAction에서 소스코드 가져오기 
		
		int pageSize=5; //numPerPage : 페이지당 보여주는 게시물 수(=레코드 수)
		int blockSize=3;//pagePerBlock : 블럭당 보여주는 페이지 수 10

		//게시판을 처음 실행시키면 무조건 1페이지부터 출력 : 가장 최근의 글이 나오게 설정

		if (pageNum==null){
		  pageNum="1"; //default를 무조건 1페이지로 설정
		}
		//nowPage(현재 페이지(클릭해서 보는 페이지))
		int currentPage=Integer.parseInt(pageNum); //"1" -> 1
		//			         (1-1)*10 +1=1, (2-1)*10 +1=11, (3-1)*10+1=21, ...
		int startRow=(currentPage-1)*pageSize+1; //시작레코드 번호
		int endRow=currentPage*pageSize; //1*10=10, ...
		int number=0; //beginPerPage : 페이지별로 시작하는 맨처음나오는 게시물 번호

		System.out.println("현재 레코드 수(count)="+count);
		
		number=count-(currentPage-1)*pageSize;
		System.out.println("페이지별 number="+number);
		
		//-------모델1의 list.jsp에서 소스코드 복사 후 편집------------
		//1. 총 페이지 수 구하기
		//              122/10=12.2+1.0=13.2 ==> int 13 //(122%10=2 ==>0이 아니니까 1)
		int pageCount=count/pageSize+(count%pageSize==0?0:1);
		//2. 시작페이지
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
	
  
  //3. 게시판의 글쓰기 및 답글 쓰기
  //insert into board values(?,,,,)
  public void insertArticle (FAQboardDTO article) { //~(MemberDTO mem
	//1)article : 신규글인지 답변글인지 확인하기
	int post_num=article.getPost_num(); //게시물 번호 : 0이면 신규글, 0이 아니면 답변글
	int post_view=0;
	Timestamp post_date = new Timestamp(new Date().getTime());
	int maxNum=0;//데이터를 저장하기 위한 게시물 번호 
	System.out.println("insertArticle 메서드의 내부 num="+post_num);
	System.out.println("insertArticle 메서드의 시간="+post_date);
	try {
	  con=pool.getConnection(); //sql문장 가져오기 위해	
	  sql="select max(post_num) from faqpost"; //MySQL로 실행 시 최대값 구할 수 있음
	  pstmt=con.prepareStatement(sql);
	  rs=pstmt.executeQuery();
	  if(rs.next()) { //데이터가 들어 있다면
		  maxNum=rs.getInt(1); //최대값+1	 (1) : 인덱스 번호  
	} else {//데이터가 없는 경우 :0
		maxNum=1;  
	}
	  post_num= maxNum +1;
	  //작성날짜 : sysdate, now()-MySQL
	  String sql="insert into faqpost(post_num,post_view,admin_id,post_title,post_cnt,post_date) values(?,?,?,?,?,?)";
		pstmt= con.prepareStatement(sql);
		pstmt.setInt(1, post_num);
		pstmt.setInt(2, post_view);
		pstmt.setString(3, article.getAdmin_id());
		pstmt.setString(4, article.getPost_title());
		pstmt.setString(5, article.getPost_cnt());
		pstmt.setTimestamp(6, post_date);
		
		int insert =pstmt.executeUpdate();
		System.out.println("게시판의 글쓰기 성공유무(insert)="+insert);
	}catch (Exception e) {
	  System.out.println("insertArticle() 에러유발="+e);
	}finally {
	  pool.freeConnection(con, pstmt, rs);
	}
  }
//글 상세 보기	
  public FAQboardDTO getArticle(int post_num) {
	FAQboardDTO article=null; //ArrayList<BoardDTO> articleList
	System.out.println("update(post_num) => "+post_num);
    try {
	  con=pool.getConnection();
	  sql="update faqpost set post_view=post_view+1 where post_num=?";
	  pstmt=con.prepareStatement(sql);
	  pstmt.setInt(1,post_num);
	  int update=pstmt.executeUpdate();
	  System.out.println("조회수 증가 유무 (update)="+update); //1 : 정상, 0: 이상	
		
	  sql="select * from faqpost where post_num=?";
	  pstmt=con.prepareStatement(sql);
	  pstmt.setInt(1,post_num);
	  rs=pstmt.executeQuery();
		if(rs.next()) {
		   article=makeArticleFromResult();
	  }
    }catch(Exception e) {
	  System.out.println("getArticle() 에러유발="+e);
	}finally {
	  pool.freeConnection(con,pstmt,rs);
	}
	  return article;
  } 
  
//-----중복된 코드를 한번에 담을 수 있는 메서드 만들어서 처리------------------
//접근지정자가 private인 경우 : 외부에서 호출목적이 아닌 내부에서 호출목적으로 사용 (일반메서드)
  private FAQboardDTO makeArticleFromResult() throws Exception{
	FAQboardDTO article=new FAQboardDTO(); //MemberDTO()

	article.setPost_num(rs.getInt("post_num"));
	article.setPost_view(rs.getInt("post_view"));
	article.setAdmin_id(rs.getString("admin_id"));
	article.setPost_title(rs.getString("post_title"));
	article.setPost_cnt(rs.getString("post_cnt"));
	article.setPost_date(rs.getTimestamp("post_date"));

	return article;
  }
	
//글 수정하기전 글 찾기
		public FAQboardDTO updateGetArticle(int post_num) {
			FAQboardDTO article=null;
			
			try {
				con=pool.getConnection();
				sql="select * from faqpost where post_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					article=makeArticleFromResult();
				}
			}catch(Exception e) {
				System.out.println("updateGetArticle()=>"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return article;
		}
		//글 수정
		public int updateArticle(FAQboardDTO article) {
			int x=-1;
			try {
				con=pool.getConnection();
				sql ="update faqpost set post_title=?, post_cnt=?, admin_id=?,post_date=? where post_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, article.getPost_title());
				pstmt.setString(2, article.getPost_cnt());
				pstmt.setString(3, article.getAdmin_id());
				pstmt.setTimestamp(4, article.getPost_date());
				pstmt.setInt(5, article.getPost_num());
				int update=pstmt.executeUpdate();
				
				System.out.println("게시판 글수정 성공 확인(update):"+update);
				
			}catch(Exception e) {
				System.out.println("updateArticle()=>"+e);
			}finally {
				pool.freeConnection(con,pstmt,rs);
			}
			return x;
		}
	


  //게시판 글 삭제 -> 암호를 비교해서 delete from board where num=?
  public int deleteArticle (int post_num) { //회원탈퇴와 같음
	int x=-1; //게시물의 삭제 성공 유무
	try {
	 con=pool.getConnection();
	 sql="delete from faqpost where post_num=?";
	 pstmt=con.prepareStatement(sql);
	 pstmt.setInt(1, post_num);
	 int delete=pstmt.executeUpdate();
			System.out.println("게시판의 글삭제 성공 유무(delete)="+delete);
	}catch(Exception e) {
		System.out.println("deleteArticle() 에러유발="+e);
	}finally {
		pool.freeConnection(con,pstmt,rs); //암호 찾기
	}
		  return x;	  
	  
  }
	
  public List getArticles(int start, int end) {
		List articleList=null; //ArrayList<BoardDTO> articleList=null;
		try {
		  con=pool.getConnection();
			/* 그룹번호가 가장 최신인 글을 중심으로 정렬하되, 만약 level 값이 같은 경우 step값은 오름차순을 통해
			 * 몇번 째 레코드 번호를 기준해서 몇 개까지 정렬할 것인가를 지정해주는 구문 */
		  sql="SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rnum, faqpost.* FROM faqpost) WHERE rnum between ? and ?"; //그룹번호를 내림차순(최근 글)으로 정렬해라
		  pstmt=con.prepareStatement(sql);
		  pstmt.setInt(1,start); //MySQL은 레코드 순번이 내부적으로 0부터 시작
		  pstmt.setInt(2, end); //불러와서 담을 갯수
		  rs=pstmt.executeQuery();
			//누적의 개념
			if(rs.next()) {//보여주는 데이터가 있다면
			  articleList=new ArrayList(end); //end갯수 만큼 데이터 공간 생성
			  do {
				  FAQboardDTO article=new FAQboardDTO(); //회원리스트면 MemberDTO
				  article=makeArticleFromResult();
				  articleList.add(article);//생략하면 데이터가 저장되지 않음 : for문(X) (NullPointer~)
			  }while(rs.next());
					  
			}
		}catch(Exception e) {
		  System.out.println("getAricle()에러유발="+e);
		}finally {
		  pool.freeConnection(con, pstmt, rs);
		}
		  return articleList; 
	  }
  
//이전글 보기
	public FAQboardDTO getBeforeArticle(int post_num) {
		FAQboardDTO article = null;
		try {
			con=pool.getConnection();
			sql ="select * from (select r.*, ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rn from faqpost r where post_num < ?) where rn = 1";

			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article = makeArticleFromResult();
			}
		}catch(Exception e) {
			System.out.println("이전글보기 에러=>"+e);
		}
		return article;
	}

	//다음글 보기
	public FAQboardDTO getAfterArticle(int post_num) {
		FAQboardDTO article = null;
		try {
			con=pool.getConnection();
			sql ="select * from (select r.*, ROW_NUMBER() OVER (ORDER BY post_num ASC) AS rn from faqpost r where post_num > ?) where rn = 1";

			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				article = makeArticleFromResult();
			}
		}catch(Exception e) {
			System.out.println("다음글보기 에러=>"+e);
		}
		return article;
	}

	
}//클래스


