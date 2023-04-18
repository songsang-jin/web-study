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
<title>게시판 보기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<jsp:include page="sideBar.jsp" />
	<div id="contents">
		<div id="postView_header">
			<h2 class="text-center">EVENT</h2>
			<p>
			<p>
			<p>
			<hr>
		</div>
		<div>
			<table class="postView_posttable">
				<tr>
					<th width="10%">제목</th>
					<td width="90%">${article.post_title}</td>
				</tr>
				<tr>
					<th width="10%">작성자</th>
					<td width="90%">${article.admin_id}</td>
				</tr>
				<tr height="160px" valign="top">
					<th width="10%">게시글</th>
					<td width="90%" style="white-space:pre;"><p>${article.post_cnt}</td>
				</tr>
			</table>
		</div>
		<p>
		<div class="postView_buttonarea">
		 <%if(check >0) {
			if(mem_id.equals("admin")){ %>
			<button>
				<a
					href="/Zspace/deletePro2.do?post_num=${article.post_num}&pageNum=${pageNum}">삭제</a>
			</button>
			<button>
				<a
					href="/Zspace/updateForm2.do?post_num=${article.post_num}&pageNum=${pageNum}">수정</a>
			</button>
			<%}} %>
			<button>
				<a href="list2.do">글 목록</a>
			</button>
		</div>

		<div class="postView_movetablearea">
			<table class="postView_movetable">
				<tr>
					<th>이전</th>
					<td>이전 글 제목</td>
				</tr>
				<tr>
					<th>다음</th>
					<td>다음 글 제목</td>
				</tr>
			</table>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>