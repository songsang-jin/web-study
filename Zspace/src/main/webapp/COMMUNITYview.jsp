<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
<link href="/Zspace/css/jyh.css" rel="stylesheet">
</head>
<body> 

<!-- 사이드 바 -->
	<jsp:include page="sideBar.jsp" /> 
	
<!-- 본문 -->
	<div id="contents">
		<center>
			<form>
				<!-- 제목,작성자,작성일,조회수 -->
				<table class="community_notice_header">
					<tr>
						<td class="community_notice_header_title">${article.post_title}</td>	 
						<td class="community_notice_header_writer">${article.mem_id}</td>
						<td>|</td>
						<td class="community_notice_header_date"><fmt:formatDate value="${article.post_date}" pattern="yyyy-MM-dd"/></td> 
						<td>|</td>
						<td class="community_notice_header_readcnt">${article.post_view}</td>
					</tr>
				</table>
			<!-- 내용 -->	
			<table class="community_notice_content_table">
				<tr>			
					<td class="community_notice_content_view" style="white-space:pre">${article.post_cnt}</td>
				</tr>
			</table> 
			<div class="community_notice_update_button_div">
				<input type="button" value="수정" class="community_notice_update_button"
							onclick="document.location.href='/Zspace/updateForm6.do?post_num=${article.post_num}&pageNum=${pageNum}'">
				 			&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="삭제" class="community_notice_delete_button"
							onclick="document.location.href='/Zspace/deletePro6.do?post_num=${article.post_num}&pageNum=${pageNum}'">
							&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="목록" style="margin-right:10px;" class="community_notice_list_button"
							onclick="document.location.href='/Zspace/list6.do?pageNum=${pageNum}'">
			</div>
			</form>
		
		<!-- 댓글 -->
		<div class="community_notice_reply">
			<table class="community_notice_reply_table">
				<c:forEach var="replyList" items="${replyList}">
				<tr class="community_notice_reply_tr">
					<td class="community_notice_nickname">${reply.mem_id}</td>
					<td class="community_notice_reply_text">${reply.reply_cnt}</td>
					<td class="community_notice_date"><fmt:formatDate value="${reply.reply_date}" timeStyle="medium"  pattern="yy.MM.dd  (hh:mm)"/></td>
					<td align=right>
						<A HREF="/Zspace/ReplyDel6.do?post_num=${article.post_num}&reply_num=${replyList.reply_num}&pageNum=${pageNum}">글삭제</A>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		
		<!-- 댓글쓰기 -->
		<div>
			<form method="post" name="add_form" action="/Zspace/ReplyPro6.do">
				<table class="community_notice_reply_write">
					<tr class="community_notice_reply_tr">
					<td class="community_notice_nickname">
						<input type="hidden" name="mem_id" size="10" value="test">&nbsp;
						<input type="hidden" name="mem_id" size="10" value="${mem_id}">&nbsp;${mem_id}
					</td>
					<td>
						<textarea name="content" class="community_notice_textarea_write"></textarea>
					</td>
						<input type="hidden" name="post_num" value="${article.post_num}">
						<input type="hidden" name="pageNum" value="${pageNum}">
						<!-- <input type="hidden" name="mem_id" value="test"> -->
						<input type="hidden" name="mem_id" value="${mem_id}">
						<input type="hidden" name="content" value="content">
					<td class="community_notice_apply">
					<button class="community_notice_apply_button" onclick="reply()">등록</button>
					</td>
					</tr>
				</table>
			</form>
		</div><!-- 댓글쓰기 --> 
		</center>
	</div><!-- contents -->
	
	<!-- 푸터 -->
	<jsp:include page="footer.jsp" /> 
</body>
</html>      