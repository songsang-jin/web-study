<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">


</head>
<body>
<!-- 사이드바영역 03.12  -->
<!-- 	<div id="container"> -->
		<jsp:include page="sideBar.jsp" flush="false" />
<div id="contents">  <!-- 전체 레이아웃을 감싸기 -->
  <div class="top">
    <h4>관리자 전용</h4>
    <hr>

<div id="faqPost_contents">
  <div id="faqPost_header">
    <h4 class="text-center">회원정보</h4><p>
	<hr>
  </div>
  
<div class="faqPost_main">
<b>회원목록(전체 회원수:${pgList.count})</b>
  <table class="table">
  
<div id="1tab">
	   <table id="tab1" border="1px solid black" >
	   	<thead>
		    <tr id="admin_page_tr" height="30px">
		     <th width="13%" class="text-center">이름</th>
		     <th width="15%" class="text-center">아이디</th>
		     <th width="30%" class="text-center">주소</th>
		     <th width="15%" class="text-center">전화번호</th>
		     <th width="15%" class="text-center">이메일</th>
		     <th width="7%" class="text-center">비밀번호 초기화</th>
		    </tr>
		   </thead>
		   <tbody>
	     	<c:forEach var="article" items="${articleList}">
			    <tr>
			      <td class="text-center">${article.mem_name}</td>
			      <td class="text-center">${article.mem_id}</td>
			      <td class="text-center">${article.mem_addr1 } , ${article.mem_addr2 }</td>
			      <td class="text-center">${article.mem_phone}</td>
			      <td class="text-center">${article.mem_email}</td>
			      <td class="text-center"><button type="button" onclick="location.href='/Zspace/pwd_resetPro.do?mem_id=${article.mem_id}'">초기화</button></td>
			    </tr>
			  </c:forEach>
		</tbody>
	   </table>
	   <form name="search" id="search" action="./admin_page.jsp">
        <select name="search">
         <option value="mem_name">이름</option>
      	 <option value="mem_id">아이디</option>
      	 <option value="mem_addr">주소</option>
      	 <option value="mem_phone">전화번호</option>
      	 <option value="mem_email">이메일</option>
   		</select>
  		<input type="text" size="15" name="searchtext">&nbsp;
   		<input type="submit" value="검색">
	   </form>
	  </div>
</table>
</div>
</div>
</div>
</div>


	  <jsp:include page="footer.jsp" flush="false" />

	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>