<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<title>공지사항</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/Zspace/css/pjh.css">
<link rel="stylesheet" type="text/css" href="/Zspace/css/jyh.css">
 <link rel="stylesheet" type="text/css" href="/Zspace/css/tool.css">
 <script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
         Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
</head>
<body>
		<jsp:include page="sideBar.jsp" flush="false" />
<div id="contents">
<div id="faqPost_contents">
  <div id="faqPost_header">
    <h4 class="text-center">공지사항</h4><p>
	<hr>
  </div>
  
<div class="faqPost_main">
<b>글목록(전체 글:${pgList.count})</b>
<p></p>
<%if(check >0) {
		if(mem_id.equals("admin")){ %>
 <button class="btn btn-primary btn-sm" onclick="location.href='/Zspace/writeForm1.do' ">글쓰기</button>
 <%}} %>
 <p>
   <table class="table">
			<c:if test="${count==0}"> 
			  <table border="1" width="700" cellpadding="0" cellspacing="0" align="center">
			   <tr>
			    <td align="center">게시판에 저장된 글이 없습니다. </td>
			   </tr>
			  </table> 
			</c:if>
			<!--게시글이 존재하지 않을때 출력문 -->
			<thead  style="background-color:rgb(217,217,217);">
				<tr >
					<td class="text-center">글번호</td>
					<td class="text-center">제목</td>
					<td class="text-center">작성자</td>
					<td class="text-center">작성일</td>
				</tr>
			</thead>
			<tbody>
		  <c:forEach var="article" items="${articleList}">
			    <tr>
			      <td class="text-center">${article.post_num}</td>
			      <td class="text-center"><a href="/Zspace/content1.do?post_num=${article.post_num}&pageNum=${pgList.currentPage}">${article.post_title}</a></td>
			       <td class="text-center">${article.admin_id}</td>
			      <td class="text-center">
			        <fmt:formatDate value="${article.post_date}" timeStyle="medium" pattern="yyyy.MM.dd" />
			      </td>
			    </tr>
			  </c:forEach>
			</tbody>
		</table>
</div>
<p>
<div class="faqPost_paging"> 
	<c:if test="${pgList.startPage > pgList.blockSize}">
     <a href="/Zspace/list1.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">[이전]</a>
     <a>이전</a>
</c:if>
<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
     <a href="/Zspace/list1.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
     	<c:if test="${pgList.currentPage==i }">
     		<font color="red"><b>[${i}]</b></font>
     	</c:if>
     	<c:if test="${pgList.currentPage!=i }">
     		[${i}]
     	</c:if>
     	</a>
</c:forEach>
<c:if test="${pgList.endPage <pgList.pageCount}">
      <a href="/Zspace/list.do1?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">[다음]</a>
 </c:if>  
<p>
</div>
<p>
<!-- 검색구역 -->
   <div class="btsearch">			
	<form name="test"  action="/Zspace/list1.do">
     <select name="search">
       <option value="post_title">제목</option>
       <option value="mem_id">작성자</option>
       <option value="post_cnt">내용</option>
     </select>
     <input type="text" size="15" name="searchtext">&nbsp;
     <input type="submit" value="검색">
     </form>
   </div>
<!-- content끝 -->
   </div>
</div>
</div>
<jsp:include page="footer.jsp" flush="false" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>