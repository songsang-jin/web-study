package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class EVENTboardDAO {

	private DBConnectionMgr pool = null; // (DBConnectionMgr)에 접근하기 위해
	private Connection con = null;
	private PreparedStatement pstmt = null; // SQL실행목적 (변경할 것만 고르기)
	private ResultSet rs = null; // select
	private String sql = ""; // 실행시킬 SQL구문 저장 목적
	// 2) 생성자를 통해 연결

	public EVENTboardDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류: " + e);
		}
	}// 생성자
		// 1. 페이징 처리 전체 레코드 수 구하기 (10개씩 끊어오기)

	public int getArticleCount() {
		int x = 0; // x=총 레코드의 갯수
		try {
			con = pool.getConnection(); // 이미 만들어진 연결객체(Connection)를 얻어오는 것
			System.out.println("con: " + con); // DBConnection에서 확인
			sql = "select count(*) from eventpost ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();// select = 변경x
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

	// 2) 검색어에 따른 레코드 수를 메서드 작성(검색분야,검색어)
	public int getArticleSearchCount(String search, String searchtext) {// getMemberCount() -> MemberDAO()
		int x = 0; // 총 레코드의 갯수를 저장
		try {
			con = pool.getConnection();
			System.out.println("con=" + con); // DBConnectionMgr에서 확인하기
			// ---------------------------------------------------------------
			if (search == null || search == "") {// 검색분야 선택x
				sql = "select count(*) from eventpost ";
			} else {// 검색분야(제목 작성자 제목+본문)
				if (search.equals("post_title_post_cnt")) {// 제목+본문 post_title+post_cnt
					sql = "select count(*) from cencel_post where post_title like '%" + searchtext
							+ "%' or post_cnt like '%" + searchtext + "%' ";
				} else { // 제목, 작성자 => 필드명 -> 매개변수를 이용해서 sql통합
					sql = "select count(*) from eventpost where " + search + " like '%" + searchtext + "%' ";
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

	// --------------------------------------------------------------------------------

	// 게시판 목록 조회(일단 안건들여도됨)
	public List getArticles(int start, int end) throws Exception {
		ArrayList articleList = null;
		System.out.println("getArticles=>" + start + "," + end);
		try {
			con = pool.getConnection();
			sql = "select * from  eventpost where rownum >=? and rownum<=? ORDER BY post_num DESC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				articleList = new ArrayList(end);
				do {
					EVENTboardDTO article = new EVENTboardDTO();
					article = makeArticleFromResult();
					articleList.add(article); // 생략하면 데이터가 저장되지 않음
				} while (rs.next());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	// 3. 페이징 처리 계산을 정리해주는 메서드(ListAction에서 소스 가져오기)
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

	// 게시판의 글쓰기 및 답글쓰기
	public void insertArticle(EVENTboardDTO article) {
		// 신규글 or 답변글
		int post_num = article.getPost_num(); // 0이면 신규글, 0이 아니면 답변글
		int maxNum = 0;
		try {
			con = pool.getConnection();
			sql = "select max(post_num) from EVENTPOST";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				maxNum = rs.getInt(1) + 1;//
			} else {
				maxNum = 1;
			}
			// post_num=maxNum+1;
			// 테이블에 한개의 데이터가 없다면
			article.setPost_num(maxNum);
			sql = "insert into eventpost (post_num,admin_id,post_title, post_cnt,post_date,post_view)  values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getPost_num());
			// pstmt.setInt(2, article.getItem_num());
			pstmt.setString(2, article.getAdmin_id());
			pstmt.setString(3, article.getPost_title());
			pstmt.setString(4, article.getPost_cnt());
			// pstmt.setString(7, article.getItem_img());
			pstmt.setTimestamp(5, article.getPost_date());
			pstmt.setInt(6, article.getPost_view());

			int insert = pstmt.executeUpdate();
			System.out.println("게시판 글쓰기, 답댓글 성공 확인: " + insert);
		} catch (Exception e) {
			System.out.println("insertArticle() 에러 발생: " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// 글 상세보기
	public EVENTboardDTO getArticle(int post_num) {
		EVENTboardDTO article = null;
		try {
			con = pool.getConnection();
			sql = "update eventpost set post_view=post_view+1 where post_num=?"; // 조회수 증가
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			int update = pstmt.executeUpdate();
			System.out.println("조회수 증가(update): " + update);

			sql = "select * from eventpost where post_num=?"; // 글 찾기
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

	private EVENTboardDTO makeArticleFromResult() throws SQLException {
		// TODO Auto-generated method stub
		EVENTboardDTO article = new EVENTboardDTO();
		article.setPost_num(rs.getInt("post_num"));
		// article.setItem_num(rs.getInt("item_num"));
		article.setPost_view(rs.getInt("post_view"));
		// article.setCount(rs.getInt("count"));
		article.setAdmin_id(rs.getString("admin_id"));
		article.setPost_title(rs.getString("post_title"));
		article.setPost_cnt(rs.getString("post_cnt"));
		// article.setRated(rs.getString("rated"));
		article.setPost_date(rs.getTimestamp("post_date"));
		return article;
	}

	// 글 수정하기
	// 7-1. 글 수정하기 : 수정할 데이터를 찾자
	public EVENTboardDTO updateGetArticle(int post_num) {
		EVENTboardDTO article = null;
		try {
			con = pool.getConnection();
			sql = "select * from eventpost where post_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = makeArticleFromResult();
			}
		} catch (Exception e) {
			System.out.println("updateGetArticle() 에러: " + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}

	// 7-2. 수정할 메서드
	public int updateArticle(EVENTboardDTO article) {
		int x = -1; // 게시글의 성공유무 체크
		try {
			con = pool.getConnection();
			sql = "update eventpost  set post_title=?, post_cnt=?, admin_id=? where post_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getPost_title());
			pstmt.setString(2, article.getPost_cnt());
			pstmt.setString(3, article.getAdmin_id());
			pstmt.setInt(4, article.getPost_num());
			int update = pstmt.executeUpdate();
			System.out.println("post_cnt: "+article.getPost_cnt());
			System.out.println("게시판의 글수정 성공 확인(update): " + update);
			x = 1;
		} catch (Exception e) {
			System.out.println("updateArticle() 메서드 에러: " + e);
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return x;
	}

	// 8. 글 삭제하기
	public int deleteArticle(int post_num) {
		int x = -1;
		try {
			con = pool.getConnection();
			sql = "delete from eventpost where post_num=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			int delete = pstmt.executeUpdate();

			System.out.println("게시판 글 삭제 성공(delete):" + delete);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			System.out.println("deleteArticle()=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	// 글 검색하기 : 검색어에 따른 레코드의 범위 지정에 대한 메서드
	// 7) 글 검색하기 : 검색어에 따른 레코드의 범위 지정에 대한 메서드
	public List getBoardArticles(int start, int end, String search, String searchtext) {
		List articleList = null; // ArrayList<BoardDTO> articleList=null (o)
		try {
			con = pool.getConnection();
			// ------------------------------------------------------------------------------
			if (search == null || search == "") { // 검색분야
				// 0311 (SELECT ROW_NUMBER->내림차() OVER (ORDER BY ==>번호제한 post_num DESC)
				// ->정렬값 rnum으로 갈거고 cancel_post에 뜰거야 rnum between ? and ?"-->어디부터 어디까지
				sql = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY post_num DESC) AS rnum, eventpost.* FROM eventpost) WHERE rnum between ? and ?";
				// (=SELECT cp.*, ROWNUM AS rn FROM cancel_post cp ORDER BY cp.ref DESC,
				// cp.re_step DESC ) WHERE rn = ?
				// sql="select * from cancel_post where rnum >=? and rnum <= ? order by ref desc
				// re_step ";//?,? => A and B //그룹번호=게시물번호역할도 하기 떄문에 가능 order by ref desc
				// re_step limit ?,?
			} else { // 제목+본문
				if (search.equals("post_title_post_cnt")) { // 제목+본문
					sql = "select * from  eventpost where post_title like '%" + searchtext + "%' or post_cnt like '%"
							+ searchtext + "%' and re_step >= ? AND re_step <= ? order by ref desc";// limit ?,?->rnum
																									// 크거나 같을떄
				} else { // 제목,작성자-> 필드명 -> 매개변수를 이용해서 하나의 sql통합

					sql = "select * from  eventpost where " + search + " like '%" + searchtext
							+ "%' and re_step >= ? AND re_step <= ? order by ref desc"; // limit ?,?->
				}
			}
			// System.out.println("DAO start+end==>보여라"+start+";"+end);

			System.out.println("getBoardArticles()의 sql=" + sql);

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);// mysql은 레코드 번호 순번이 내부적으로 0부터 시작하기 때문에
			pstmt.setInt(2, end); // 몇 개까지 불러와서 담을 건가? default(10)
			rs = pstmt.executeQuery();
			// 기존에 데이터가 있으면 누적해서 쌓아 올려야한다.
			if (rs.next()) {// 화면에 보여줄 데이터가 있으면
				articleList = new ArrayList(end); // end갯수만큼 공간을 생성해라
				do {
					EVENTboardDTO article = new EVENTboardDTO(); // 회원리스트면 MemberDTO
					article = makeArticleFromResult();
					// 필드별로 setter메서드를 통해 각각 넣어준다. like 분리수거
					// 추가
					articleList.add(article);// 생략하면 데이터 저장X => for문X(Null)
				} while (rs.next());// 더 있으면 계속

			}
		} catch (Exception e) {
			System.out.println("getArticles() 에러발생=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}

		return articleList;
	}
}