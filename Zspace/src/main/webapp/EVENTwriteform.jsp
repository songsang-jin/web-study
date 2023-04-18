<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>글쓰기게시판</title>
<!-- Bootstrap -->
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
			<h1>게시판 글쓰기</h1>
			<form action="/Zspace/writePro2.do" method="post">
				<input type="hidden" name="post_num" value="${post_num}"> <input
					type="hidden" name="admin_id" value="${admin_id}"> <input
					type="text" name="post_title" class="form-control mt-4 mb-2"
					placeholder="제목을 입력해주세요." required>
				<div class="form-group">
					<textarea class="form-control" rows="10" name="post_cnt" cols="80%"
						placeholder="내용을 입력해주세요" required></textarea>
				</div>
				<button type="submit"
					class="btn btn-secondary mb-3 pull-right write_reg">등록</button>
			</form>
			<button type="submit"
				class="btn btn-secondary mb-3 pull-right write_cancel"
				onclick="location.href='/Zspace/list2.do?'">취소</button>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="/Zspace/js/bootstrap.min.js"></script>

</body>
</html>