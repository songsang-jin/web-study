<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String  mem_id = (String)session.getAttribute("mem_id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">

</head>
<body>
<jsp:include page="sideBar.jsp" flush="false" />
<div id="contents">
  <div id="myPage_header">
    <h4 class="text-center">ADMIN PAGE</h4><p>
	<hr>
  </div>



<div class="myPage_check">
	<div class="myPage_order">
	<a href="/Zspace/admin_info.do">
	  <h4>
	    <strong>회원정보</strong>
	  </h4>
	  전체 회원정보 조회
	</a>
	</div>

	<div class="myPage_profile">
	<a href="/Zspace/order_list.do">
	  <h4>
	    <strong>회원주문관리</strong>
	  </h4>
	  전체 회원 주문관리 조회
	</a>
	</div>
	
    <div class="myPage_cart">
	<a href="#">
	  <h4>
	    <strong>적립금 현황</strong>
	  </h4>
	  회원 전체 적립금 현황 조회
	</a>
	</div>
	
	<div class="myPage_wishlist">
	<a href="#">
	  <h4>
	    <strong>상품관리</strong>
	  </h4>
	  등록된 상품조회 보여드립니다.
	</a>
	</div>
	
</div>
</div>
<jsp:include page="footer.jsp" flush="false" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>