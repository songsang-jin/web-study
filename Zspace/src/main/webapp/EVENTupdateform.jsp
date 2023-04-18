<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>글 수정하기</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/jyh.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
	<jsp:include page="sideBar.jsp" />
	<div id="contents">
		<div class="row">
				<h2>글 수정하기</h2>
				<hr>
				<form method="post" name="updateform" action="/Zspace/updatePro2.do">
					<input type="hidden" name="post_num" value="${article.post_num}">
					<input type="hidden" name="pageNum" value="${pageNum}"> 
					 <input type="text" name="post_title" class="form-control mt-4 mb-2"
						value="${article.post_title}">
					<div class="form-group">
						<textarea class="form-control" rows="10" cols="80%"
							name="post_cnt">${article.post_cnt}</textarea>
					</div>
					<button type="submit"
						class="btn btn-secondary mb-3 pull-right write_reg">글 수정</button>
				</form>
				<button type="submit"
					class="btn btn-secondary mb-3 pull-right write_cancel"
					onclick="location.href='/Zspace/list2.do?pageNum=${pageNum}'">
					취소</button>
		</div>
</div>
	<!-- 풋터 -->
	<jsp:include page="footer.jsp" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>