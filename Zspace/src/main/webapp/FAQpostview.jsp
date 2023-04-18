<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
int check = 0;
String mem_id=null;
 System.out.println("session값=>"+session.getAttribute("check") );
  if(session.getAttribute("check") != null){
	  check = (Integer)session.getAttribute("check");
	  mem_id = (String)session.getAttribute("mem_id");
 } 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 보기</title>
 <link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/Zspace/css/pjh.css">
<link rel="stylesheet" type="text/css" href="/Zspace/css/jyh.css">
 <link rel="stylesheet" type="text/css" href="/Zspace/css/tool.css">
</head>
<body>
		<jsp:include page="sideBar.jsp" flush="false" />
<div id="contents">

  <div id="postView_header">
    <h4 class="text-center">공지사항</h4><p>
	<hr>
  </div>
 
<div class="postView_buttonarea">
 <%if(check >0) {
		if(mem_id.equals("admin")){ %>
 <button >
 <a href="/Zspace/deletePro1.do?post_num=${article.post_num}&pageNum=1">삭제</a></button>
 <button>
 	<a href="/Zspace/updateForm1.do?post_num=${article.post_num}&pageNum=1&post_date=${article.post_date}">수정</a></button>
  <%}} %>
 <button>
 	<a href="/Zspace/list1.do">글 목록</a>
 </button>
 <p>
</div>
<div>
  <table class="postView_posttable">
   <tr>
    <th>제목</th>
    	<td  colspan="2">  ${article.post_title}</td>
   </tr>
   <tr>
    <th>작성자</th>
    	<td>${article.admin_id}</td>
   </tr>
   <tr>
   	<th>글 내용</th>
     	<td colspan="2" height="80%" style="white-space:pre;">${article.post_cnt}</td>
   </tr>
   <tr>
  </table>
  <p style="text-align:right;">조회수:${article.post_view}</p>
</div>
<p>
<div class="postView_movetablearea">
<table class="postView_movetable">
  <tr>
      <th>이전</th>
     <td><a href="/Zspace/content1.do?post_num=${afterArticle.post_num}">${afterArticle.post_title}</a></td>
    </tr>
    <tr>
      <th>다음</th>
      <td><a href="/Zspace/content1.do?post_num=${beforeArticle.post_num}">${beforeArticle.post_title}</a></td>
    </tr>
</table>
</div>
</div>	
<jsp:include page="footer.jsp" flush="false" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>