<%@page contentType="text/html;charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>글쓰기게시판</title>

    <!-- Bootstrap -->
   <link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/jyh.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">


    <script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    
    
  </head>
  <body>
		<jsp:include page="sideBar.jsp" />
	<div id="contents">
		<div class="row">
		<h1>게시판 글쓰기</h1>
		<!-- 입력 폼         -->
		<!-- /Zspace/writePro.do"는  commandPro.properties와 연결  method="post"는 getpost라 -->
		<form action="/Zspace/writePro5.do" method="post">

			<input type="hidden" name="post_num" value="${post_num}">
			<input type="hidden" name="mem_id" value="${mem_id}">
			<!-- 사용자id -->
			<input type="hidden" name="mem_id" value="abc">
			<!-- abc는 db안에 임의로 넣어둔값 -->
			<input type="hidden" name="admin_id" value="admin"> <input
				type="hidden" name="ref" value="${ref}"> <input
				type="hidden" name="re_step" value="${re_step}"> <input
				type="hidden" name="re_level" value="${re_level}">

			<c:if test="${post_num==0}">
				<!-- 일반글일때 -->
				<input type="text" name="post_title" class="form-control mt-4 mb-2"
					placeholder="제목을 입력해주세요." required>
			</c:if>
			<c:if test="${post_num!=0}">
				<!-- 답글일때 -->
				<input type="hidden" name="admin_id" value="admin">
				<input type="text" name="post_title" class="form-control mt-4 mb-2"
					placeholder="제목을 입력해주세요." value="[re]">
			</c:if>
			<div class="form-group">
				<textarea class="form-control" rows="10" name="post_cnt" cols="60"
					placeholder="내용을 입력해주세요" required></textarea>
			</div>
			<button type="submit"
				class="btn btn-secondary mb-3 pull-right write_reg">등록</button>
		</form>
		<button type="button"
			class="btn btn-secondary mb-3 pull-right write_cancel"
			onclick="location.href='/Zspace/list5.do' ">취소</button>

	</div>
	</div>
	<!-- main -->
	<jsp:include page="footer.jsp" />


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>