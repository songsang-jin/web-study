<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>글 쓰기</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/jyh.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
         Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
</head>
<body>
<jsp:include page="sideBar.jsp" />  
	
  <div id="contents">
	<div class="row">     
      <h2>상품 문의 하기</h2>
	  <hr>
	  <!-- 입력 폼 -->
	  <form action="/Zspace/writePro3.do" method="post">
		<input type="hidden" name="post_num" value="${post_num}">
		<input type="hidden" name="mem_id" value="${mem_id}"> 
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="re_step" value="${re_step}">
		<input type="hidden" name="re_level" value="${re_level}">
		<input type="text" name="post_title" class="form-control mt-4 mb-2"
			   placeholder="제목을 입력해주세요." required>
 
		<div class="form-group">
		  <textarea class="form-control" rows="10" cols="80%" name="post_cnt"
					placeholder="내용을 입력해주세요" required></textarea>
		</div>	
		<button type="submit" class="btn btn-secondary mb-3 pull-right write_reg">등록</button>
	  </form>
	  <button type="submit" class="btn btn-secondary mb-3 pull-right write_cancel"
				onclick="location.href='/Zspace/list3.do'">취소</button>
	</div><!-- row-->
  </div>
   
<jsp:include page="footer.jsp" /> 

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>