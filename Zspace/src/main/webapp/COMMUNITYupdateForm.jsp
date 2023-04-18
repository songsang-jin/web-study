<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>글쓰기</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/jyh.css" rel="stylesheet">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	<!-- 사이드바-->
	<jsp:include page="sideBar.jsp" /> 
	
	<!-- 본문 -->
	<div id="contents">
		<div class="row">
			<h1>글수정</h1> 
				<form action="/Zspace/updatePro6.do" method="post" name="writeform">
					<input type="hidden" name="post_num" value="${article.post_num}">
					<input type="hidden" name="pageNum" value="${pageNum}">
					<input type="hidden" name="mem_id" value="${mem_id}">
					<input type="text" name="post_title" class="form-control mt-4 mb-2"
				value="${article.post_title}">
					<div class="form-group">
					<textarea class="form-control" rows="10" cols="80%" name="post_cnt">${article.post_cnt}</textarea>
					</div>
					<button type="submit" class="btn btn-secondary mb-3 pull-right write_reg">글수정</button>
					<button type="submit" class="btn btn-secondary mb-3 pull-right write_cancel" 
								 onClick="document.location.href='/Zspace/list.do?pageNum=${pageNum}'">취소</button>
				</form>
		</div><!-- row -->
	</div><!-- contents -->

	<!-- 푸터 -->
	<jsp:include page="footer.jsp" /> 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</body>
</html>