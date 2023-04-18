<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
 <link rel="stylesheet" type="text/css" href="/Zspace/css/jyh.css">
  <link rel="stylesheet" type="text/css" href="/Zspace/css/tool.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/Zspace/css/sidebar.css">
<link rel="stylesheet" type="text/css" href="/Zspace/css/silde.css">
<link rel="stylesheet" type="text/css" href="/Zspace/css/mine.css">
</head>
<body>
<!--  -->
    <jsp:include page="sideBar.jsp" flush="false" />
<!--  -->
	<div id="contents">     
			<h1>게시판 글쓰기</h1>
			<form action="/Zspace/writePro1.do" method="post">
				<input type="hidden" name="post_num" value="1">
				<input type="hidden" name="pageNum" value="1">
				<input type="hidden" name="admin_id" value="${admin_id }">
				<input type="text" name="post_title" class="form-control mt-4 mb-2"
					placeholder="제목을 입력해주세요." required>
				<div class="form-group">
					<textarea class="form-control" rows="10" name="post_cnt" cols="80%"
						placeholder="내용을 입력해주세요" required></textarea>
				</div>
				
				<button type="submit" class="btn btn-secondary mb-3 pull-right write_reg">등록</button>
			</form>
			<button type="submit" class="btn btn-secondary mb-3 pull-right write_cancel" onclick="location.href='/Zspace/list1.do' ">취소</button>
	</div><!-- main -->
<jsp:include page="footer.jsp" flush="false" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="/Zspace/js/bootstrap.min.js"></script>
    
</body>
</html>