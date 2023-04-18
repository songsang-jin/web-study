package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import model.DBConnectionMgr;
import model.memberDTO;

public class memberDAO {
	
	
	//1.연결할 클래스 객체선언
	private DBConnectionMgr pool=null;
	private Connection con=null;
	private PreparedStatement pstmt=null; //
	private ResultSet rs=null;//select는 반환값이 있기 때문에 그 반환값을 담기 위함
	private String sql=""; //실행시킬 sql구문 저장용
	
	//2. 생성자를 통해서 서로 연결
	public memberDAO() {
		try {
			pool=DBConnectionMgr.getInstance(); //DB연결얻어오기
		}catch(Exception e) {
			System.out.println("DB접속 오류=>"+e); //오류확인을 위한 출력문
		}
	}//생성자
	
	public int getLogin(String mem_id, String mem_pwd) {
		int check=0;
		try {
			con=pool.getConnection();
			System.out.println("con="+con);
			sql="select * from mem_depth where mem_id=? and mem_pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_pwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("Login success!");
				check=1;
			}else {
				System.out.println("Login Failed!");
				check=0;
			}
			System.out.println("check =>"+check);
		}catch(Exception e) {
			System.out.println("getLogin() 에러유발="+e);
		}finally {
			 pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}
	//관리자로그인 체크
	public int getAdminLogin(String admin_id, String admin_pwd) {
		int check=0;
		try {
			con=pool.getConnection();
			System.out.println("con="+con);
			sql="select * from admin where admin_id=? and admin_pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, admin_id);
			pstmt.setString(2, admin_pwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("adminLogin success!");
				check=1;
			}else {
				System.out.println("adminLogin Failed!");
				check=0;
			}
			System.out.println("check =>"+check);
		}catch(Exception e) {
			System.out.println("getLogin() 에러유발="+e);
		}finally {
			 pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}
	
	private memberDTO makeArticleFromResult() throws Exception {
		// TODO Auto-generated method stub
		memberDTO article=new memberDTO();
		//System.out.println("야임마!!!");
		article.setMem_id(rs.getString("mem_id"));
		article.setMem_pwd(rs.getString("mem_pwd"));
		article.setMem_name(rs.getString("mem_name"));
		article.setMem_phone(rs.getString("mem_phone"));
		article.setMem_email(rs.getString("mem_email"));
		article.setMem_zipcode(rs.getString("mem_zipcode"));
		article.setMem_addr1(rs.getString("mem_addr1"));
		article.setMem_addr2(rs.getString("mem_addr2"));
		//article.setMem_keep(rs.getInt("mem_keep"));
		
		return article;
	}
	
	public int getCount(String mem_id) {
		int x=0;
		try {
			 con=pool.getConnection();
			  System.out.println("con="+con);
			  sql="select count(*) from mem_depth"; //select count(*) from member; pstmt=con.prepareStatement(sql);
			  pstmt=con.prepareStatement(sql);
			  rs=pstmt.executeQuery();
			  System.out.println("rs=>"+rs);
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
	
	//5)회원 수정->특정 회원 찾기(nup,kkk,kktest,,)
		//select * from member where id='nup'
	 public memberDTO getArticle(String mem_id) {
			memberDTO article=null; //ArrayList<BoardDTO> articleList
		    try {
			  con=pool.getConnection();
			  sql="select * from mem_depth where mem_id=? ";
			  pstmt=con.prepareStatement(sql);
			  pstmt.setString(1,mem_id);
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
	 //관리자
	 public memberDTO getAdminArticle(String admin_id) {
			memberDTO article=null; //ArrayList<BoardDTO> articleList
		    try {
			  con=pool.getConnection();
			  sql="select * from mem_depth where mem_id=? ";
			  pstmt=con.prepareStatement(sql);
			  pstmt.setString(1,admin_id);
			  rs=pstmt.executeQuery();
				if(rs.next()) {
					article.setMem_id(rs.getString("admin_id"));
					article.setMem_pwd(rs.getString("admin_pwd"));
			  }
		    }catch(Exception e) {
			  System.out.println("getArticle() 에러유발="+e);
			}finally {
			  pool.freeConnection(con,pstmt,rs);
			}
			  return article;
		  } 
		
		//6)찾은 회원을 수정=>회원가입해주는 메서드와 동일(sql구문이 다르다.)
		public int memberUpdate(memberDTO article) {
	        int check=0;//회원수정 성공유무
			
			try {
				con=pool.getConnection();
				con.setAutoCommit(false);//default->true
				sql="update mem_depth set mem_pwd=?,mem_name=?,mem_email=?, mem_phone=?, mem_zipcode=?,mem_addr1=?  where mem_id=?";
				//----------------------------------------------
			    pstmt=con.prepareStatement(sql);//NullPointerException
			    
			    pstmt.setString(1,article.getAdmin_pwd());
			    pstmt.setString(2,article.getMem_name());
			    pstmt.setString(3,article.getMem_email());
			    pstmt.setString(4,article.getMem_phone());
			    pstmt.setString(5,article.getMem_zipcode());
			    pstmt.setString(6,article.getMem_addr1());
			    pstmt.setString(7, article.getMem_addr2());
			    pstmt.setString(8,article.getAdmin_id());
			    
			    int update=pstmt.executeUpdate();//반환값 1 (성공) ,0 (실패)
			    con.commit();//메모리->실질 테이블에 반영
			    System.out.println("update(데이터 수정유무)=>"+update);
			    if(update==1) {
			    	check=1;//데이터 수정성공확인
			    }
			}catch(Exception e) {
				System.out.println("memberUpdate() 실행오류=>"+e);
			}finally {
				pool.freeConnection(con, pstmt);//rs X (select가 아님)
			}
			return check;
		}
		
		//7)회원 탈퇴
		public int memberDelete(String mem_id,String mem_pwd) {
			String dbpasswd="";//DB상에서 찾은 암호를 저장
			int x=-1;//회원탈퇴 유무
			
			try {
				con=pool.getConnection();//이미 만들어진 연결객체얻어옴
				con.setAutoCommit(false);//트랜잭션 처리
				sql="select mem_pwd from mem_depth where mem_id=?";//sql
				pstmt=con.prepareStatement(sql);//NullPointerException
				pstmt.setString(1, mem_id);//2.index~
				rs=pstmt.executeQuery();
				//암호를 찾았다면
				if(rs.next()) {
					dbpasswd=rs.getString("mem_pwd");
					System.out.println("dbpasswd=>"+dbpasswd);
					//dbpasswd(DB상 암호)==passwd(웹상에서 입력한값)
					if(dbpasswd.equals(mem_pwd)) {
						sql="delete from mem_depth where mem_id=?";
						pstmt=con.prepareStatement(sql);//NullPointerException
						pstmt.setString(1, mem_id);	
						int delete=pstmt.executeUpdate();
						System.out.println("delete(회원탈퇴 성공유무=>"+delete);
						con.commit();//트랜잭션 처리끝
						x=1;//회원탈퇴 성공
					}else {//암호가 틀린경우
						x=0;//회원탈퇴 실패
					}
				}else {//암호가 존재X
				   x=-1;	
				}	
			}catch(Exception e) {
				System.out.println("memberDelete() 실행오류=>"+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);//검색때문에
			}
			return x;
		}
		
		 public int getArticleSearchCount(String search, String searchtext) {//getMemberCount() -> MemberDAO()
				int x=0; //총 레코드의 갯수를 저장
				try {
				  con=pool.getConnection();
				  System.out.println("con="+con); //DBConnectionMgr에서 확인하기
				  //---------------------------------------------------------------
				  if(search==null||search=="") {//검색분야 선택x
				  sql="select count(*) from mem_depth"; //select count(*) from member;
				  }else {//검색분야(제목 작성자 제목+본문)
					  if(search.equals("subject_content")) {//제목+본문
						sql="select count(*) from mem_depth where mem_id like '%"+
					         searchtext+"%' or mem_name like '%"+searchtext+"%'";
					  }else { //제목, 작성자 => 필드명 -> 매개변수를 이용해서 sql통합
						sql="select count(*) from mem_depth where "+search+" like '%"+
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
			public List getMemberArticles(int start,int end, String search, String searchtext) {
				List articleList=null; //ArrayList<BoardDTO> articleList=null (o)
				try {
					con=pool.getConnection();
			        sql = "SELECT * FROM mem_depth";

					System.out.println("getBoardArticles()의 sql="+sql);
					System.out.println("getBoardArticles()====>"+start+","+end );
					pstmt=con.prepareStatement(sql);
					rs=pstmt.executeQuery();
					if(rs.next()) {//화면에 보여줄 데이터가 있으면
						articleList=new ArrayList<memberDTO>(end); //end갯수만큼 공간을 생성해라
						do {
							memberDTO article=new memberDTO(); //회원리스트면 MemberDTO
							article=makeArticleFromResult();
							System.out.println("getBoardArticles=>"+article.getMem_id());
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
				
				//1. 총 페이지 수 구하기
				//              122/10=12.2+1.0=13.2 ==> int 13 //(122%10=2 ==>0이 아니니까 1)
				int pageCount=count/pageSize+(count%pageSize==0?0:1);
				//2. 시작페이지
				int startPage=0;
				  if(currentPage%blockSize!=0){//1~9, 11~19, 21~29,
					startPage=currentPage/blockSize*blockSize+1;
				  }else{
					startPage=((currentPage/blockSize)-1)*blockSize+1;
				  } //종료페이지
				  int endPage=startPage+blockSize-1; //1+10-1=10, 11+10-1=20
				  System.out.println("startPage="+startPage+", endPage="+endPage);
				  //블럭별로 구분해서 링크 걸어 출력
				   //    11 > 10      //마지막페이지=총페이지 수
				  if(endPage>pageCount) endPage=pageCount;
				  
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

			public int checkId(String mem_id) {
				int check=0;
				try {
					con=pool.getConnection();
					sql = "select mem_id from mem_depth where mem_id =?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, mem_id);
					rs=pstmt.executeQuery();
					
					if (rs.next()) {
					    check = 1;
					} 
					
				}catch(Exception e) {
					System.out.println("checkId() =>"+e);
				}finally {
					 pool.freeConnection(con, pstmt, rs);
				}
				return check;
			}
			
			public int insert(memberDTO article) {
				int check =0;
				try {
					con=pool.getConnection();
					sql ="INSERT INTO mem_depth (mem_id, mem_pwd, mem_name, mem_phone, mem_email, mem_zipcode, mem_addr1, mem_addr2)VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, article.getMem_id());
					pstmt.setString(2, article.getMem_pwd());
					pstmt.setString(3, article.getMem_name());
					pstmt.setString(4, article.getMem_phone());
					pstmt.setString(5, article.getMem_email());
					pstmt.setString(6, article.getMem_zipcode());
					pstmt.setString(7, article.getMem_addr1());
					pstmt.setString(8, article.getMem_addr2());
					
					check=pstmt.executeUpdate();
				}catch(Exception e) {
					System.out.println("memberJoin() =>"+e);
				}finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return check;
			}

			public String loginCheck(String mem_id, String mem_pwd) {
				String check = null;
				try {
					con=pool.getConnection();
					sql="select * from mem_depth where mem_id=? and mem_pwd=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, mem_id);
					pstmt.setString(2, mem_pwd);
					rs=pstmt.executeQuery();
					System.out.println("rs=>"+rs.next());
					System.out.println("================");
				}catch(Exception e) {
					System.out.println("loginCheck=>"+e);
				}finally {
					pool.freeConnection(con,pstmt,rs);
				}
				return check;
			}
			
			public int pwdReset(String mem_id,String mem_pwd) {
				int check =0;
				try {
					con=pool.getConnection();
					sql="update mem_depth set mem_pwd =? where mem_id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, mem_pwd);
					pstmt.setString(2,mem_id); 
					int update=pstmt.executeUpdate();//반환값 1 (성공) ,0 (실패)
					System.out.println("update(초기화 수정유무)=>"+update);
					  if(update==1) {
					    	check=1;//데이터 수정성공확인
					   }
					
				}catch(Exception e) {
					System.out.println("pwd_reset=>"+e);
				}finally {
					pool.freeConnection(con,pstmt,rs);
				}
				return check;
			}
			

}
