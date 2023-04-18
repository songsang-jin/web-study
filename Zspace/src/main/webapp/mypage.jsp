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
    <h4 class="text-center">MY PAGE</h4><p>
	<hr>
  </div>



<div class="myPage_check">
	<div class="myPage_order">
	<a href="/Zspace/orderlist.do?mem_id=${mem_id }">
	  <h4>
	    <strong>ORDER</strong>
	  </h4>
	  주문하신 상품의 주문내역을 확인하실 수 있습니다.
	</a>
	</div>
	
	<div class="myPage_profile">
	<a href="/Zspace/mem_update.do?mem_id=${mem_id }">
	  <h4>
	    <strong>PROFILE</strong>
	  </h4>
	  회원이신 고객님의 개인정보를 관리하는 공간입니다.
	</a>
	</div>
	
    <div class="myPage_cart">
	<a href="/Zspace/cart.do?mem_id=${mem_id }">
	  <h4>
	    <strong>CART</strong>
	  </h4>
	  장바구니에 들어있는 상품의 목록을 보여드립니다. 
	</a>
	</div>
	
	<div class="myPage_wishlist">
	<a href="/Zspace/wishlist.do?mem_id=${mem_id }">
	  <h4>
	    <strong>WISHLIST</strong>
	  </h4>
	  관심상품으로 등록하신 상품의 목록을 보여드립니다.
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