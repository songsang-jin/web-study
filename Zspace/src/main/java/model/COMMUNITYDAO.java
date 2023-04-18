package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class COMMUNITYDAO {

	private DBConnectionMgr pool = null; // (DBConnectionMgr)에 접근하기 위해
	private Connection con = null;
	private PreparedStatement pstmt = null; // SQL실행목적 (변경할 것만 고르기)
	private ResultSet rs = null; // select
	private String sql = ""; // 실행시킬 SQL구문 저장 목적

	// 2) 생성자를 통해 연결
	public COMMUNITYDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류: " + e);
		}
	}// 생성자

	// ============= 게시판 =============
	
	//1) 페이징 처리 전체 레코드 수 구하기 (10개씩 끊어오기)
	public int getArticleCount() {
		int x = 0; // x=총 레코드의 갯수
		try {
			con = pool.getConnection();
			System.out.println("con: " + con);
			sql = "select count(*) from com_post";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 결과 있을 때
				x = rs.getInt(1); //
			}
		} catch (Exception e) {
			System.out.println("getArticleCount()에러유발: " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	//2) 검색어에 따른 레코드 수를 메서드 작성
	public int getArticleSearchCount(String search, String searchtext) {// getMemberCount() -> MemberDAO()
		int x = 0; // 총 레코드의 갯수를 저장
		try {
			con = pool.getConnection();
			System.out.println("con=" + con); // DBConnectionMgr에서 확인하기
			// ---------------------------------------------------------------
			if (search == null || search == "") {// 검색분야 선택x
				sql = "select count(*) from com_post";
			} else {// 검색분야(제목 작성자 제목+본문)
				if (search.equals("post_title_post_cnt")) {// 제목+본문
					sql = "select count(*) from com_post where post_title like '%" + searchtext
							+ "%' or post_cnt like '%" + searchtext + "%' ";
				} else { // 제목, 작성자 => 필드명 -> 매개변수를 이용해서 sql통합
					sql = "select count(*) from com_post where " + search + " like '%" + searchtext + "%' ";
				}
			}
			System.out.println("getArticleSearchCount 검색 sql=" + sql);
			// ----------------------------------------------------------------
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 보여주는 결과가 있다면
				x = rs.getInt(1); // 변수명=re.get자료형(필드명 또는 인덱스 번호)
									// 필드명이 없기 때문에 인덱스 번호로 불러오기
			}
		} catch (Exception e) {
			System.out.println("getArticleSearchCount() 에러유발=" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	//3).글목록보기에 대한 메서드구현->레코드 한개이상->한 페이지당 10개씩 끊어서 보여준다.
	// 1.레코드의 시작번호 2. 불러올 레코드의 갯수
	public List getArticles(int start, int end) {// getMemberList(int start,int end){

		ArrayList<COMMUNITYDTO> articleList = null;// ArrayList articleList=null;//(O)
		String sql = null;
		System.out.println("getArticles : " + start + "," + end);

		try {
			con = pool.getConnection();
			/*
			 * 그룹번호가 가장 최신의 글을 중심으로 정렬하되,만약에 level이 같은 경우에는 step값으로 오름차순을 통해서 몇번째 레코드번호를
			 * 기준해서 몇개까지 정렬할것인가를 지정해주는 SQL구문
			 */
			sql = "select * from com_post WHERE ROWNUM>=? AND ROWNUM <=? ORDER BY POST_NUM DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);// mysql은 레코드순번이 내부적으로 0부터 시작
			pstmt.setInt(2, end);// 불러와서 담을 갯수
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				articleList = new ArrayList(end);// 10=>end갯수만큼 데이터를 담을 공간을 만들어라
				do {
					COMMUNITYDTO article = new COMMUNITYDTO();// MemberDTO=>필드별로 담을것.
					article = makeArticleFromResult();
					articleList.add(article);// 생략하면 데이터가 저장X->for문 에러유발
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getArticleCount() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	//4) 게시판 목록 조회(일단 안건들여도됨)
	public List getBoardList(int startRow, int endRow) {
		List articleList = null;
		try {
			con = pool.getConnection();
			String sql = "SELECT * FROM com_post where ROWNUM>=? AND ROWNUM <=? ORDER BY POST_NUM DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				COMMUNITYDTO article = new COMMUNITYDTO();
				article = makeArticleFromResult();
				articleList.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

//5) 페이징 처리 계산을 정리해주는 메서드(ListAction에서 소스 가져오기)
	public Hashtable pageList(String pageNum, int count) {
		// 0. 페이징 처리 결과를 저장할 Hashtable 객체 선언
		Hashtable<String, Integer> pgList = new Hashtable<String, Integer>();

		int pageSize = 10; // numPerPage : 페이지당 보여주는 게시물 수(=레코드 수)
		int blockSize = 5;// pagePerBlock : 블럭당 보여주는 페이지 수 10

		// 게시판을 처음 실행시키면 무조건 1페이지부터 출력 : 가장 최근의 글이 나오게 설정

		if (pageNum == null) {
			pageNum = "1"; // default를 무조건 1페이지로 설정
		}
		// nowPage(현재 페이지(클릭해서 보는 페이지))
		int currentPage = Integer.parseInt(pageNum); // "1" -> 1
		// (1-1)*10 +1=1, (2-1)*10 +1=11, (3-1)*10+1=21, ...
		int startRow = (currentPage - 1) * pageSize + 1; // 시작레코드 번호
		int endRow = currentPage * pageSize; // 1*10=10, ...
		int number = 0; // beginPerPage : 페이지별로 시작하는 맨처음나오는 게시물 번호

		System.out.println("현재 레코드 수(count)=" + count);

		number = count - (currentPage - 1) * pageSize;
		System.out.println("페이지별 number=" + number);

		// 1. 총 페이지 수 구하기
		// 122/10=12.2+1.0=13.2 ==> int 13 //(122%10=2 ==>0이 아니니까 1)
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		// 2. 시작페이지
		int startPage = 0;
		if (currentPage % blockSize != 0) {// 1~9, 11~19, 21~29,
			startPage = currentPage / blockSize * blockSize + 1;
		} else { // 10%10=0,(10,20,30,40~)
					// ((10/10)-1)*10+1=1
			startPage = ((currentPage / blockSize) - 1) * blockSize + 1;
		} // 종료페이지
		int endPage = startPage + blockSize - 1; // 1+10-1=10, 11+10-1=20
		System.out.println("startPage=" + startPage + ", endPage=" + endPage);
		// 블럭별로 구분해서 링크 걸어 출력
		// 11 > 10 //마지막페이지=총페이지 수
		if (endPage > pageCount)
			endPage = pageCount;
		// 페이징 처리에 대한 계산 결과 : Hashtable에 저장해서 ListAction에 전달한 뒤
		// 메모리에 저장 후 공유해서 list.jsp에서 불러다 사용
		pgList.put("pageSize", pageSize); // pgList.get(key명) : key, value값 다르면 찾기 힘듦
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

	//6) 게시판의 글쓰기

	public void insertArticle(COMMUNITYDTO article) { // 신규글 or 답변글

		int post_num = article.getPost_num(); // 0이면 신규글, 0이 아니면 답변글
		int post_view = 0;//조회수
		int maxNum = 0;
		System.out.println("insertArticle 메서드의 내부 post_num: " + post_num);//0

		try {
			con = pool.getConnection();
			sql = "select max(post_num) from com_post";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 신규글 일 때
			if (rs.next()) { // 기존 레코드 데이터가 있으면
				maxNum = rs.getInt(1);
			} else { // 기존 데이터가없는 경우 1부터 시작한다는 뜻
				maxNum = 1;
			}
			post_num = maxNum + 1;//2
			
			System.out.println("post_num : " + post_num);//2
			sql = "insert into com_post(post_num,mem_id,post_title,post_cnt,post_date,";
			sql += " post_view)values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql); // article.setPost_num(rs.getInt("post_num"));
			
			pstmt.setInt(1, post_num);
			pstmt.setString(2, article.getMem_id());
			pstmt.setString(3, article.getPost_title());
			pstmt.setString(4, article.getPost_cnt());
			pstmt.setTimestamp(5, article.getPost_date());
			pstmt.setInt(6, article.getPost_view());

			int insert = pstmt.executeUpdate();
			System.out.println("게시판 글쓰기, 답댓글 성공 확인: " + insert);
		} catch (Exception e) {
			System.out.println("insertArticle() 에러 발생: " + e);
		} finally {
			pool.freeConnection(con, pstmt,rs);
		}
	}
	
	//7) 글 상세보기
	
		public COMMUNITYDTO getArticle(int post_num) {
			COMMUNITYDTO article = null;
			try {
				con = pool.getConnection();
				sql = "update com_post set post_view=post_view+1 where post_num=?"; // 조회수 증가
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
				int update = pstmt.executeUpdate();
				System.out.println("조회수 증가(update): " + update);

				sql = "select * from com_post where post_num=?"; // 글 찾기??
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					article = makeArticleFromResult();
				}
			} catch (Exception e) {
				System.out.println("getArticle() 에러: " + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return article;
		}
		
		//8) 검색어에 따른 레코드의 범위지정에 대한 메서드
		public List getBoardArticles(int start, int end, String search, String searchtext) {// getMemberList(int start,int
																							// end){
			List articleList = null;// ArrayList articleList=null;//(O)

			try {
				con = pool.getConnection();
				// -----------------------------------------------------------------
				if (search == null || search == "") {
					sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rnum, com_post.* FROM com_post) WHERE rnum between ? and ?";

				} else {// 제목+본문
					if (search.equals("post_title_post_cnt")) {// 제목+본문
						sql = "select * from com_post where post_title like '%" + searchtext + "%' or post_cnt like '%"
								+ searchtext + "%' order by post_num desc";
					} else {// 제목,작성자->매개변수를 이용해서 하나의 sql통합
						sql = "select * from com_post where " + search + " like  '%" + searchtext
								+ "%' order by post_num desc";
					}
				}
				// SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rnum,
				// faqpost.* FROM faqpost) WHERE rnum between ? and ?
				System.out.println("getBoardArticles()의 sql=>" + sql);
				// ------------------------------------------------------------------
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, start);// mysql은 레코드순번이 내부적으로 0부터 시작
				pstmt.setInt(2, end);// 불러와서 담을 갯수
				rs = pstmt.executeQuery();
				if (rs.next()) {// 보여주는 결과가 있다면
					articleList = new ArrayList(end);// 10=>end갯수만큼 데이터를 담을 공간을 만들어라
					do {
						COMMUNITYDTO article = new COMMUNITYDTO();// MemberDTO=>필드별로 담을것.
						article.setPost_num(rs.getInt("post_num"));
						article.setMem_id(rs.getString("mem_id"));
						article.setPost_title(rs.getString("post_title"));
						article.setPost_cnt(rs.getString("post_cnt"));

						article.setPost_date(rs.getTimestamp("post_date"));// 오늘날짜->코딩
						article.setPost_view(rs.getInt("post_view"));// 조회수 default->0
						// 추가
						articleList.add(article);// 생략하면 데이터가 저장X->for문 에러유발
					} while (rs.next());
				}
			} catch (Exception e) {
				System.out.println("getBoardArticles() 에러유발=>" + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return articleList;
		}

		//9) 글 수정하기
		public COMMUNITYDTO updateGetArticle(int post_num) {
			COMMUNITYDTO article = null;
			try {
				con = pool.getConnection();
				sql = "select * from com_post where post_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					article = makeArticleFromResult();
					System.out.println("updategetarticle 불러오기");
				}
			} catch (Exception e) {
				System.out.println("updateGetArticle() 에러: " + e);
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return article;
		}

		//10) 수정할 메서드
		public int updateArticle(COMMUNITYDTO article) {
			int x = -1; // 게시글의 성공유무 체크

			try {
				/*
				con = pool.getConnection();
				sql = "select * from com_post where post_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, article.getPost_num());
				rs = pstmt.executeQuery();
				*/

				con = pool.getConnection();
				sql = "update com_post set post_title=?, post_cnt=? where post_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, article.getPost_title());
				pstmt.setString(2, article.getPost_cnt());
				pstmt.setInt(3, article.getPost_num());
				System.out.println("article.getPost_num()" + article.getPost_num());

				int update = pstmt.executeUpdate();

				System.out.println("게시판의 글수정 성공 확인(update): " + update);
				x = 1;
			} catch (Exception e) {
				System.out.println("updateArticle() 메서드 에러: " + e);
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return x;
		}

		//11) 글 삭제하기
		public int deleteArticle(int post_num) {
			int x = -1;
			try {
				con = pool.getConnection();
				sql = "delete from com_post where post_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, post_num);
				int delete = pstmt.executeUpdate();

				System.out.println("게시판의 글 삭제 성공 확인: " + delete);
			} catch (Exception e) {
				System.out.println("deleteArticle() 에러 확인: " + e);
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return x;
		}
		
		//12) 중복되는 것들을 묶어주는 메서드
		private COMMUNITYDTO makeArticleFromResult() throws Exception {
			COMMUNITYDTO article = new COMMUNITYDTO();

			article.setPost_num(rs.getInt("post_num"));
			article.setMem_id(rs.getString("mem_id"));
			article.setPost_title(rs.getString("post_title"));
			article.setPost_cnt(rs.getString("post_cnt"));

			article.setPost_date(rs.getTimestamp("post_date"));// 오늘날짜->코딩
			article.setPost_view(rs.getInt("post_view"));// 조회수 default->0
			
			return article;
		}
	
	// ============ 댓글 =============
	
	//1) 댓글 상세 보기
		public List replyDetail (int post_num) {
			List replyList=null; //찾을 레코드를 담을 객체 선언
			
			try {
				con=pool.getConnection();
				String sql="select * from reply where post_num=?";
				pstmt=con.prepareStatement(sql);
				System.out.println("sql : " +sql);
				pstmt.setInt(1, post_num);
				rs=pstmt.executeQuery();
				if(rs.next()==true){
					replyList=new ArrayList(); //왜 제네릭이 필요?
					do {
						COMMUNITYreplyDTO reply=new COMMUNITYreplyDTO();
						reply.setReply_num(rs.getInt("reply_num")); //댓글 번호
						reply.setMem_id(rs.getString("mem_id")); //작성자
						reply.setReply_date(rs.getTimestamp("reply_date")); //작성 날짜
						reply.setReply_cnt(rs.getString("reply_cnt")); //댓글 내용
						reply.setPost_num(rs.getInt("post_num")); //게시물 번호
						
						replyList.add(reply); //생략하면 데이터 저장(X)
					}while(rs.next());
				}			
			}catch(Exception e){
				System.out.println("replyDetail() 에러유발 : "+e);
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return replyList;
		}
	
		//2) 댓글 삭제
		public int deleteReply (int reply_num) {
			//1) DB 연결
			int x=-1; //데이터 삭제 유무
			
			//2) DB 작업
			try {
			con=pool.getConnection();
			con.setAutoCommit(false);
			sql="delete from reply where reply_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reply_num);
			int delete=pstmt.executeUpdate();
			System.out.println("댓글 삭제 유무 : "+delete);
			con.commit();
		}catch(Exception e) {
			System.out.println("==deleteReply() 에러==");
			System.out.println("에러라인 565");
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return x;
	}
		
	//댓글 쓰기
	public void addReply(COMMUNITYreplyDTO reply) {
		//1. DB 작업
		try {
			con=pool.getConnection();
			
			System.out.println("reply.getMem_id() : "+reply.getMem_id());
			System.out.println("reply.getReply_date() : "+reply.getReply_date());
			System.out.println("reply.getReply_cnt() : "+reply.getReply_cnt());
			System.out.println("reply.getPost_num() : "+reply.getPost_num());
			
			//데이터 추가하는 코딩
			sql="insert into reply values(reply_num_seq.NEXTVAL,?,?,?,?)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,reply.getMem_id()); //작성자 ID
			pstmt.setTimestamp(2,reply.getReply_date()); //댓글 작성 날짜
			pstmt.setString(3,reply.getReply_cnt()); //댓글 내용
			pstmt.setInt(4,reply.getPost_num()); //게시판 번호
			
			int insert = pstmt.executeUpdate();
			System.out.println("댓글 쓰기 성공"+insert);
		}catch(Exception e) {
			System.out.println("addReply() 에러 발생 : "+ e);
		}finally {
			pool.freeConnection(con, pstmt);
		}
	}

	
	//머지?
	public COMMUNITYreplyDTO updateArticle(int post_num) {
		//찾은 데이터를 담을 객체선언
		COMMUNITYreplyDTO article=null; //찾은 데이터를 담을 그릇
		//글상세보기 -> 조회수를 증가시키면서 데이터출력
		try {
			con=pool.getConnection();
			
			String sql2="select * from reply where post_num=?";
			pstmt=con.prepareStatement(sql2);
			pstmt.setInt(1, post_num);
			rs=pstmt.executeQuery();
			//결과확인
			if(rs.next()) { //레코드 수를 구했다면
				article=new COMMUNITYreplyDTO();
				article.setPost_num(rs.getInt("post_num")); //게시판 번호
				article.setMem_id(rs.getString("mem_id")); //작성자
				article.setReply_date(rs.getTimestamp("reply_date")); //작성 날짜
				article.setReply_cnt(rs.getString("reply_cnt")); //댓글 내용
				
			} 
		}catch(Exception e) {
			System.out.println("updateArticle() 메서드 에러 유발 : "+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	/*
	 * //글 검색하기 : 검색어에 따른 레코드의 범위 지정에 대한 메서드(나중에 하기) public List
	 * getBoardArticles(int start,int end, String search, String searchtext) { List
	 * articleList=null; //ArrayList<BoardDTO> articleList=null (o) try {
	 * con=pool.getConnection();
	 * //---------------------------------------------------------------------------
	 * --- if(search==null||search=="") { //검색분야
	 * sql="select * from com_post  order by ref desc, re_step limit ?,?";//?,? => A
	 * and B //그룹번호=게시물번호역할도 하기 떄문에 가능 }else { //제목+본문
	 * if(search.equals("post_title_post_cnt")) { //제목+본문
	 * sql="select * from post where post_title like '%"
	 * +searchtext+"%' or post_cnt like '%"+
	 * searchtext+"%' and post_type=? order by ref desc,re_step limit ?,?"; }else {
	 * //제목,작성자-> 필드명 -> 매개변수를 이용해서 하나의 sql통합
	 * sql="select * from post where "+search+" like '%"+
	 * searchtext+"%' and post_type=? order by ref desc,re_step limit ?,?"; } }
	 * System.out.println("getBoardArticles()의 sql="+sql);
	 * 
	 * pstmt=con.prepareStatement(sql); pstmt.setInt(1, post_type); pstmt.setInt(2,
	 * start-1);//mysql은 레코드 번호 순번이 내부적으로 0부터 시작하기 때문에 pstmt.setInt(3, end); //몇 개까지
	 * 불러와서 담을 건가? default(10) rs=pstmt.executeQuery(); //기존에 데이터가 있으면 누적해서 쌓아
	 * 올려야한다. if(rs.next()) {//화면에 보여줄 데이터가 있으면 articleList=new ArrayList(end);
	 * //end갯수만큼 공간을 생성해라 do { boardDTO article=new boardDTO(); //회원리스트면 MemberDTO
	 * article=makeArticleFromResult(); //필드별로 setter메서드를 통해 각각 넣어준다. like 분리수거 //추가
	 * articleList.add(article);//생략하면 데이터 저장X => for문X(Null) }while(rs.next());//더
	 * 있으면 계속 } }catch(Exception e) { System.out.println("getArticles() 에러발생=>"+e);
	 * }finally { pool.freeConnection(con, pstmt, rs); } return articleList; }
	 */

}
