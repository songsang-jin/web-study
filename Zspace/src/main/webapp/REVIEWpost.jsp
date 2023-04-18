<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">


<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
         Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
</head>
<script>
	$('.starRev span').click(function() {
		$(this).parent().children('span').removeClass('on');
		$(this).addClass('on').prevAll('span').addClass('on');
		return false;
	});
</script>
<body>
<jsp:include page="sideBar.jsp" />  

<div id="contents">
  <div id="reviewPost_header">
    <h4 class="text-left">상품리뷰</h4><p>
	<hr>
  </div>
	<button  class="btn btn-primary btn-xl" onclick="location.href='/Zspace/writeForm4.do'">글쓰기</button><p>
		<div>
			<table class="table">
				<tr class="text-center something" style="background-color:rgb(217,217,217);">
					<td class="col-md-1">글번호</td>
					<td class="col-md-5">제목</td>
					<td class="col-md-3">작성자</td>
					<td class="col-md-2">작성일</td>
					<td class="col-md-1">별점</td>
				</tr>
			<c:forEach var="article" items="${articleList}">
				<tr class="text-center">
					<td>${article.post_num}</td>
					
					<td>
						<a href="/Zspace/content4.do?post_num=${article.post_num}&pageNum=1">
	         					${article.post_title}</a>
					</td>
					<td>${article.mem_id}</td>
			 <td class="text-center">
			   <fmt:formatDate value="${article.post_date}" timeStyle="medium" pattern="yyyy-MM-dd" />
			 </td>
					<td>${article.rated}</td>
				</tr>
			 </c:forEach>
			</table>
		</div>
			<div class="page" >
<c:if test="${pgList.startPage > pgList.blockSize}">
    <a href="/Zspace/list4.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">[이전]</a>
</c:if>

<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
     <a href="/Zspace/list4.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
           <c:if test="${pgList.currentPage==i}">
           ${i}
           </c:if>
           <c:if test="${pgList.currentPage!=i}">
            ${i}
           </c:if>
     </a>
</c:forEach>

<c:if test="${pgList.endPage <pgList.pageCount}">
   <a href="/Zspace/list4.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">[다음]</a>
 </c:if> 

				</div>
				
  <div class="btsearch">			
	<form name="test"  action="/Zspace/list4.do">
     <select name="search">
       <option value="post_title">제목</option>
       <option value="mem_id">작성자</option>
       <option value="post_cnt">내용</option>
     </select>
     <input type="text" size="15" name="searchtext">&nbsp;
     <input type="submit" value="검색">
     </form>
   </div>
</div><!-- content끝 -->

<jsp:include page="footer.jsp" />  

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="/Zspace/js/bootstrap.min.js"></script>

</body>
</html>