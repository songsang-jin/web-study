<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<!-- 사이드 바 -->
	<jsp:include page="sideBar.jsp" /> 
	
	<!-- 본문 -->
	<div id="contents">
	<div class="row">
			<h1>게시판 글쓰기</h1>
           <!-- mem_id는 test인경우 -->
			<form action="/Zspace/writePro6.do" method="post">
				<input type="hidden" name="post_num" value="${post_num}">
				<input type="hidden" name="mem_id" value="${mem_id}"> 
				<input type="text" name="post_title" class="form-control mt-4 mb-2" placeholder="제목을 입력해주세요." required>
				<div class="form-group">
					<textarea class="form-control" rows="10" name="post_cnt" cols="80%" placeholder="내용을 입력해주세요" required></textarea>
				</div>
				<button type="submit" class="btn btn-secondary mb-3 pull-right write_reg">등록</button>
				
			</form><!-- 글쓰기폼 -->
			<button type="submit" class="btn btn-secondary mb-3 pull-right write_cancel"
						  	 onClick="location.href='/Zspace/list6.do'">취소</button>
		</div><!-- row -->
	</div><!-- 본문 -->
	<!-- 푸터 -->
	<jsp:include page="footer.jsp" /> 
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>