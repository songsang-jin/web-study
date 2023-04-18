<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
int check = 0;
String mem_id=null;
 System.out.println("session값=>"+session.getAttribute("check") );
  if(session.getAttribute("check") != null){
	  check = (Integer)session.getAttribute("check");
	  mem_id = (String)session.getAttribute("mem_id");
 } 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트게시판</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link href="/Zspace/css/ssj.css" rel="stylesheet">
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="sideBar.jsp" />
	<div id="contents">
		<!-- 메인 -->
		<div>
			<h2>이벤트게시판</h2>
			<hr>
		</div>
		<div class="row">
			<!-- 게시판 글 영역 -->
			<div>
				<!-- 버튼과 테이블 -->
				<div>
					<!-- 글쓰기 버튼 -->
					<%if(check >0) {
						if(mem_id.equals("admin")){ %>
					 <button class="btn btn-primary btn-sm" onclick="location.href='/Zspace/writeForm2.do' ">글쓰기</button>
					 <%}} %>
				</div>
				<!-- 글쓰기 버튼 -->
				<p>
				<p>
				<table class="table table-hover">
					<thead>
						<tr class="text-center something">
							<td class="col-md-2">글번호</td>
							<td class="col-md-5">제목</td>
							<td class="col-md-4">작성자</td>
							<td class="col-md-1">작성일</td>
						</tr>
					</thead>
					<!-- 실제적으로 레코드를 출력 -->
					<c:set var="number" value="${pgList.number}" />
					<c:forEach var="article" items="${articleList}">
						<tbody>
							<tr>
								<!-- post_id / post_num -->
								<td class="text-center"><c:out value="${number}" /> <c:set
										var="number" value="${number-1}" /></td>
								<!-- <td class="text-center">${article.post_num}</td> -->
								<td class="text-center"><a
									href="/Zspace/content2.do?post_num=${article.post_num}&pageNum=${pgList.currentPage}">
										${article.post_title}</a></td>
								<td class="text-center">${article.admin_id}</td>
								<td class="text-center"><fmt:formatDate
										value="${article.post_date}" timeStyle="medium"
										pattern="yy.MM.dd" /></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
			<!-- 버튼과 테이블 -->
		</div>
		<!-- row -->

		<div class="page">
			<!-- 페이징 처리 -->
			<c:if test="${pgList.startPage>pgList.blockSize}">
				<a
					href="/Zspace/list2.do?pageNum=${pgList.startPage-pgList.blockSize}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${pgList.startPage}"
				end="${pgList.endPage}">
				<a href="/Zspace/list2.do?pageNum=${i}"> <c:if
						test="${pgList.currentPage==i}">
						<font color="blue"><b>[${i}]</b></font>
					</c:if> <c:if test="${pgList.currentPage!=i}"> [${i}] </c:if>
				</a>
			</c:forEach>
			<c:if test="${pgList.endPage<pgList.pageCount}">
				<a
					href="/Zspace/list2.do?pageNum=${pgList.startPage+pgList.blockSize}">[다음]</a>
			</c:if>
		</div>
		<form id="eventPost_boardSearchForm">
			<div class="eventPost_bottomsearch">
				<select id="search_key" name="search_key">
					<option value="writer_name">글쓴이</option>
					<option value="content">내용</option>
				</select> <input id="search" name="search" class="inputTypeText" type="text">
				<button>검색</button>
			</div>
		</form>
	</div>
	<jsp:include page="footer.jsp" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="/Zspace/js/bootstrap.min.js"></script>

</body>
</html>