<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!-- 주문취소게시판cancel -->
<html>
<head>
<meta charset="UTF-8">
<title>취소게시판</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
	<!-- 사이드바 내용시작부분 -->
	<jsp:include page="sideBar.jsp"/>  
		  
<div id="contents">
  <div id="cancelPost_header">
    <h2 class="text-center">CANCEL</h2><p>
	<h5 class="text-center">주문취소게시판</h5>
	<hr>
  </div>

<div class="row">
<div>
	<div>
		<div class="cancel Post_main"><!-- UpdateFormAction.java에 리턴값 -->
	<button class="btn btn-primary btn-sm" onclick="location.href='/Zspace/writeForm5.do' ">글쓰기</a></button>
	    <p>
	</div>
<!-- 게시판 사이트-->

<table class="table  table-hover" >
	<thead>
		<tr class="text-center something" style="background-color:rgb(217,217,217);">
			<td  class="col-md-2">번호</td>
			<td  class="col-md-6">제목</td>
			<td  class="col-md-2">작성자</td>
			<td  class="col-md-4">작성일</td>
		</tr>
	</thead>
<!-- WriteFormAction.java 보고함 -->
	<tbody>
		<c:forEach var="article" items="${articleList}">
			<tr class="text-center">
				<td>${article.post_num}</td>
				<td class="text-center"><a href="/Zspace/content5.do?post_num=${article.post_num}&pageNum=${pgList.currentPage }">
						${article.post_title}</a></td>
				<td>${article.mem_id }</td>
				<td><fmt:formatDate value="${article.post_date}"
						timeStyle="medium" pattern="yy.MM.dd" /></td>
			</tr>

		</c:forEach>
	</tbody>
</table>

</div><!-- 테이블 -->
</div><!-- row -->



<!-- 페이징 처리 0327-->
<div class="cancelPost_paging page">
<c:if test="${pgList.startPage > pgList.blockSize}">
<a href="/Zspace/list5.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">[이전]</a>
</c:if>

<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
<a href="/Zspace/list5.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
<c:if test="${pgList.currentPage==i}">
<font color="red"><b> ${i}</b></font>
</c:if>
<c:if test="${pgList.currentPage!=i}">
${i}
</c:if>
</a>
</c:forEach>
</div>

<p>

<!-- 검색구역 -->
  <form id="cancelPost_boardSearchForm">
  <div class="cancelPost_bottomsearch"> 
        <select id="search_key" name="search_key">
          <option value="content">내용</option>
          <option value="writer_name">글쓴이</option>      
        </select>
        <input id="search" name="search" class="inputTypeText" type="text">
        <button>검색</button>     
  </div>
 </form>
 
</div>
</div><!-- contents -->

<jsp:include page="footer.jsp"/>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>

			</body>
</html>