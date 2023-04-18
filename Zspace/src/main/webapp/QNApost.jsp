<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<title>상품문의 게시판</title>

<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/ssj.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>  
<jsp:include page="sideBar.jsp" /> 

  <div id="contents"><!-- 메인 -->
	<div>
	  <div class="post_header"><h2>상품문의</h2>
	  	구매하시려는 상품에 대해 궁금한 점이 있으신 경우 문의해주세요.</div>
	  <hr>
	</div>
	 
	<div class="row"> <!-- 게시판 글 영역 -->
	  <div> <!-- 버튼과 테이블 -->
		<div> <!-- 글쓰기 버튼 -->
		  <button class="btn btn-primary btn-xl"
				  onclick="location.href='/Zspace/writeForm3.do'">글쓰기</button>
		</div> <!-- 글쓰기 버튼 -->  
		<p><p>
		<%-- <c:if test="${pgList.count>0}"> --%>
		  <div>
			<table class="table">
			<thead>
			  <tr class="text-center something">
				<td class="col-md-2">글 번호</td>
				<td class="col-md-5">제목</td>
				<td class="col-md-2">작성자</td>
				<td class="col-md-2">작성일</td>
				<td class="col-md-1">조회수</td>
			</tr>
		  </thead>
		  
		  <!-- 실제적으로 레코드를 출력 -->
		  <c:set var="number" value="${pgList.number}" />
		  <c:forEach var="article" items="${articleList}">
		  <tbody class="table-hover">		  
			<tr>
			 <td class="text-center"> 
       		   <c:out value="${number}" />
      		   <c:set var="number" value="${number-1}"/>
      		 </td>
			 <td class="text-left">
	  		   <a href="/Zspace/content3.do?post_num=${article.post_num}&pageNum=${pgList.currentPage}">
			  	     ${article.post_title}</a>
			 </td>
			 <td class="text-center">${article.mem_id}</td>
			 <td class="text-center">
			   <fmt:formatDate value="${article.post_date}" timeStyle="medium" pattern="yyyy-MM-dd" />
			 </td>
			 <td class="text-center">${article.post_view}</td>
			</tr>
		  </tbody>
		  </c:forEach>
		 </table>
	   <%-- </c:if> --%>
	  </div> <!-- 버튼과 테이블 -->
	</div> <!-- row -->
	
	<div class="page"> <!-- 페이징 처리 -->
	  <c:if test="${pgList.startPage>pgList.blockSize}">
        <a href="/Zspace/list3.do?pageNum=${pgList.startPage-pgList.blockSize}">[이전]</a>
	  </c:if>
      <c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
		<a href="/Zspace/list3.do?pageNum=${i}">
         <c:if test="${pgList.currentPage==i}"> <font color="blue"><b>[${i}]</b></font></c:if>
         <c:if test="${pgList.currentPage!=i}"> [${i}] </c:if>
        </a>
      </c:forEach>
	  <c:if test="${pgList.endPage<pgList.pageCount}">
        <a href="/Zspace/list3.do?pageNum=${pgList.startPage+pgList.blockSize}">[다음]</a>
  	  </c:if>
	</div> <!-- 페이징 처리 -->	
	
	<div class="btsearch">			
	 <form name="test" action="/Zspace/list3.do">
      <select name="search">
       <option value="post_title">제목</option>
       <option value="mem_id">작성자</option>
       <option value="post_cnt">내용</option>
      </select>
      <input type="text" size="15" name="searchtext">&nbsp;
      <input type="submit" value="검색">
     </form>
   </div>

  </div> 
</div>	<!-- 전체컨테이너 -->
<jsp:include page="footer.jsp" /> 

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>