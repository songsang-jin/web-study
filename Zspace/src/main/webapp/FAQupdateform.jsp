<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>faqPost</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
 <link rel="stylesheet" type="text/css" href="/Zspace/css/jyh.css">
  <link rel="stylesheet" type="text/css" href="/Zspace/css/tool.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<!--  -->
  <jsp:include page="sideBar.jsp" flush="false" />
	
	<div id="contents">     
			<h1>게시판 글 수정하기</h1>
		  
			<form action="/Zspace/updatePro1.do" method="post">
				<input type="hidden" name="pageNum" value="1">
				<input type="hidden" name="post_num" value="${article.post_num}">
				<input type="hidden" name="post_view" value="${article.post_view }">
				<intput type="hidden" name="post_date" value="${article.post_date}">
				<div class="form-group">
					<input type="text" class="form-control" name="post_title" maxlength="50" value="${article.post_title}">
				</div>
				<div class="form-group">
					<textarea class="form-control" rows="10" name="post_cnt" cols="80%" required>${article.post_cnt }</textarea>
				</div>
				<button type="submit" class="btn btn-secondary mb-3 pull-right write_reg">등록</button>
			</form>
			<button  class="btn btn-secondary mb-3 pull-right write_cancel" ><a href="/Zspace/list1.do">취소</a></button>
	    </div><!-- main -->

<jsp:include page="footer.jsp" flush="false" />
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="/Zspace/js/bootstrap.min.js"></script>
    
</body>
</html>