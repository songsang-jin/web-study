<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">
<link href="/Zspace/css/kim.css" rel="stylesheet">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
         Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
</head>
<body>
<jsp:include page="sideBar.jsp" />  

	<div id="contents">     
			<h1>게시글 수정</h1>
		  
			<form action="/Zspace/updatePro4.do" method="post" name="writeform">	
		 <div>
		 [별점 주기]
			  <input type="radio" name="rated" value="★☆☆☆☆" />★
			  <input type="radio" name="rated" value="★★☆☆☆" />★★
			  <input type="radio" name="rated" value="★★★☆☆" />★★★
			  <input type="radio" name="rated" value="★★★★☆" />★★★★
			  <input type="radio" name="rated" value="★★★★★" />★★★★★
	  	</div>
				<input type="hidden" name="post_num" value="${article.post_num}">
				<input type="hidden" name="mem_id" value="${article.mem_id}">
				<input type="text" name="post_title" class="form-control mt-4 mb-2" value="${article.post_title}"
					placeholder="제목을 입력해주세요." required>
				<div class="form-group">
					<textarea class="form-control" rows="10" name="post_cnt" cols="80%" 
						placeholder="내용을 입력해주세요" required>${article.post_cnt}</textarea>
				</div>
				<button type="submit" class="btn btn-secondary mb-3 pull-right write_reg" >수정</button>
			</form>
				<button type="submit" class="btn btn-secondary mb-3 pull-right write_cancel"
				onclick="location.href='/Zspace/list4.do'">취소</button>


	   </div><!-- content -->

<jsp:include page="footer.jsp" />  
 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="/Zspace/js/bootstrap.min.js"></script>
    
</body>
</html>