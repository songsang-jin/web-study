<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 상세보기 jh -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 보기</title>

<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
</head>
<body>
<jsp:include page="sideBar.jsp" />    		
	
		<!-- 사이드바 끝 -->
<div id="contents">

  <div id="postView_header">
    <h4 class="text-center">CANCEL</h4><p>  
	<h6 class="text-center">주문취소게시판</h6>
	<hr>
  </div>
  <!-- 글목록 나오는 부분 -->
  <div>
         <table class="postView_posttable">
            <tr>
               <th width="10%">제목</th>
               <td colspan="90%">${article.post_title}</td>
            </tr>
            <tr>
               <p>
               <th width="10%">작성자</th>
               <td colspan="90%">${article.mem_id}</td>
            </tr>
            <tr>
               <th  width="10%" >게시글</th>
               <td colspan="90%" style="white-space:pre;">${article.post_cnt}</td>
            </tr>
         </table>
      </div>
<p>
  
<div class="postView_buttonarea">
 <button><a href="/Zspace/deletePro5.do?post_num=${article.post_num}&pageNum=${pageNum}">삭제</a></button>
 <button><a href="/Zspace/updateForm5.do?post_num=${article.post_num}&pageNum=${pageNum}">수정</a></button>
<!-- <button><a href="/Zspace/writeForm5.do?post_num=${article.post_num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}">답글</a></button> -->
 <button><a href="list5.do">글 목록</a></button>
</div>

	

<div class="postView_movetablearea">
<table class="postView_movetable">
  <tr>
      <th>이전</th>
      <td>이전 글 제목</td>
    </tr>
    <tr>
      <th>다음</th>
      <td>다음 글 제목</td>
    </tr>
</table>
</div>

</div>	
	<jsp:include page="footer.jsp" />    
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="Zspace/js/bootstrap.min.js"></script>
 </div>	
</body>
</html>