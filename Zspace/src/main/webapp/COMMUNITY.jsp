<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 게시판</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/jyh.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">
</head>
<body>
</head>
<body>

	<!-- 사이드 바 -->
	<jsp:include page="sideBar.jsp" /> 
	
	<!-- 본문 -->
	<div id="contents">
		<div id="reviewPost_header">
			<h2 class="text-left">커뮤니티</h2><p>
			<hr>
		</div>
		<button button class="btn btn-primary btn-xl" onclick="location.href='/Zspace/writeForm6.do'">글쓰기</button><p>
		<div>
			<table class="table">
				<tr class="text-center something">
					<td class="col-md-2" style="background:rgb(217, 217, 217);">글번호</td>
					<td class="col-md-5" style="background:rgb(217, 217, 217);">제목</td>
					<td class="col-md-2" style="background:rgb(217, 217, 217);">작성자</td>
					<td class="col-md-2" style="background:rgb(217, 217, 217);">작성일</td>
					<td class="col-md-1" style="background:rgb(217, 217, 217);">조회수</td>
				</tr>
			<c:forEach var="article" items="${articleList}">
				<tr class="text-center">
					<td>${article.post_num}</td>
					<td>
						<a href="/Zspace/content6.do?post_num=${article.post_num}&pageNum=${pgList.currentPage}">${article.post_title}</a>
					</td>
					<td>${article.mem_id}</td>
					<td><fmt:formatDate value="${article.post_date}" pattern="yyyy-MM-dd hh:mm"/></td>
					<td>${article.post_view}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	
	<!-- 페이징 처리 -->
		<center>
			<c:if test="${pgList.startPage > pgList.blockSize}">
				<a href="/Zspace/list6.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
				<a href="/Zspace/list6.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
					<c:if test="${pgList.currentPage==i}"><font color="red"><b> [${i}]</b></font></c:if>
					<c:if test="${pgList.currentPage!=i}">${i}</c:if>
				</a>
			</c:forEach>
			<c:if test="${pgList.endPage <pgList.pageCount}">
				<a href="/Zspace/list6.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">[다음]</a>
			</c:if>  
		</center>
	
	<!-- 검색구역 -->
		<form id="eventPost_boardSearchForm" action="#.do">
			<div class="eventPost_bottomsearch"> 검색어 &nbsp;
				<select id="search_key" name="search_key">
					<option value="num">번호</option>
					<option value="subject">제목</option>
					<option value="content">내용</option>      
				</select>
				<input id="search" name="search" class="inputTypeText" type="text">
				<button>검색</button>     
			</div>
		</form><!-- 검색폼 -->
	</div><!-- contents -->
	
	<!-- 푸터 -->
	<jsp:include page="footer.jsp" /> 
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>