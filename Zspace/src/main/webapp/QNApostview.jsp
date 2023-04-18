<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>  
<head>
<meta charset="UTF-8">
<title>게시판 보기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<script type="text/javascript" src="/Zspace/js/asj.js?ver=1"></script>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/asj.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
         Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
</head>
<body>
<jsp:include page="sideBar.jsp" />  

  <div id="contents"> <!-- 전체 content -->
	<div id="postView_header">
     <h2 class="text-center">상품문의</h2><p><p><p>     <!-- 게시판마다 이름 바껴야됨  -->
	 <hr>
    </div>

    <div>
     <table class="postView_posttable">
       <tr>
        <th width="10%">제목</th>
        <td width="90%">${article.post_title}</td>
       </tr>
       <tr>
        <th width="10%">작성자</th>
        <td width="90%">${article.mem_id}</td>
       </tr>
       <tr>
        <th width="10%">작성일</th>
        <td width="90%"colspan="3" ><fmt:formatDate value="${article.post_date}" timeStyle="medium" pattern="yyyy-MM-dd"/></td>
       </tr>
       <tr height="160px" valign="top">
        <th width="10%">글 내용</th>
        <td width="90%" style="white-space:pre"><p>${article.post_cnt}</td>
       </tr>
	</table>
  </div>
  <p>

  <div class="postView_buttonarea">
	<button type="submit"><a href="/Zspace/deletePro3.do?post_num=${article.post_num}&pageNum=${pageNum}">삭제</a></button>
	<button type="submit"><a href="/Zspace/updateForm3.do?post_num=${article.post_num}&pageNum=${pageNum}">수정</a></button>
	<button type="submit"><a href="list3.do">글 목록</a></button>
  </div>

<!-- 댓글 테이블 -->
  <div class="reply_tb_div">
	<table id="reply_tb" >  
	  <c:forEach var="replyList" items="${replyList}">
	    <tr>
		  <td width="15%">${replyList.admin_id}</td>
		  <td width="600">${replyList.reply_cnt} </td>
		  <td><fmt:formatDate value="${replyList.reply_date}" timeStyle="medium"  pattern="yy.MM.dd"/></td>
	    </tr>
	    <tr>
		  <td colspan=4 height="1" background="/icon/line_h01.gif"></td>
 	    </tr>
	    <tr>
	  	  <td colspan=4 height="1" background="/icon/line_h01.gif"></td>
	    </tr>
	  </c:forEach>
	</table> <!-- reply_tb -->
	<hr>
	<form method="post" name="add_form" action="/POST/replyPro3.do">
 	 <table width="100%" cellspacing="0" cellpadding="0" align="center" bgcolor=F8F8F8 >  
	   <tr>
		<td class="col-md-1" align="left" style=padding:10;>
		  <input type="hidden" name="admin_id" size="10" value="${admin_id}">&nbsp;${admin_id}
		</td>
		<td class="col-md-9" align="left" style=padding:10;>
		  <textarea name="content" rows="4" cols="120"></textarea></td>
		  <input TYPE="hidden" name="post_num" value="${article.post_num}">
		  <input TYPE="hidden" name="pageNum" value="${pageNum}">
		  <input type="hidden" name="admin_id" value="${admin_id}">
		  <input type="hidden" name="post_cnt" value="post_cnt">
		<td class="col-md-2">
		  <button onclick="comment()">등록<br>하기 </button>  
		</td>
	   </tr>
	 </table>
	</form>   
  </div><!-- <div class="reply_tb_div"> -->

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
  </div>	<!-- 전체 contents -->

<jsp:include page="footer.jsp" /> 
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>