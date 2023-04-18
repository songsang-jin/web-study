<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 보기</title>

<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">
<link href="/Zspace/css/kim.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
         Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
</head>
<body>
<jsp:include page="sideBar.jsp" />  

	<div id="contents">
	
	  <div id="postView_header">
	    <h4 class="text-center">Review</h4><p>    
		<h6 class="text-center">상품리뷰</h6>
		<hr>
	  </div>
	  
	
	<div class="postView_buttonarea">
	 <button><a href="/Zspace/deletePro4.do?post_num=${article.post_num}&pageNum=1">삭제</a></button>
	 <button><a href="/Zspace/updateForm4.do?post_num=${article.post_num}">수정</a></button>
	 <button><a href="/Zspace/list4.do">글 목록</a></button>
	</div>
	
	<div>
	  <table class="postView_posttable">
	   <tr>
	    <th>제목</th>
	    <td colspan="2">${article.post_title}</td>
	   </tr>
	   <tr>
	    <th>작성자</th>
	    <td>${article.mem_id}</td>
	   </tr>
	   <tr>
	    <th>작성일</th>
        <td><fmt:formatDate value="${article.post_date}" timeStyle="medium" pattern="yyyy-MM-dd"/></td>
	   </tr>	   
	   <tr>
	    <th>별점</th>
	    <td colspan="2">${article.rated}</td>
	   </tr>
	   <tr>
	     <td style="white-space:pre" colspan="2">${article.post_cnt}
	     </td>
	   </tr>
	  </table>
	</div>
	
	
	
	<p>
	
	<div class="postView_movetablearea">
	<table class="postView_movetable">
	    <tr>
	      <th>다음</th>
	      <td><a href="/Zspace/content4.do?post_num=${afterArticle.post_num}">${afterArticle.post_title}</a></td>
	    </tr>
	  <tr>
	      <th>이전</th>
	      <td><a href="/Zspace/content4.do?post_num=${beforeArticle.post_num}">${beforeArticle.post_title}</a></td>
	    </tr>
	</table>
	</div>
	
	</div>	<!-- contents -->

<jsp:include page="footer.jsp" />  


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>