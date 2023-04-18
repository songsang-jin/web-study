<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*, java.util.*"%>
<%String  mem_id = (String)session.getAttribute("mem_id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/asj.css" rel="stylesheet">
</head>
<body>
<jsp:include page="sideBar.jsp" />

  <div id="contents">
  	<center>
  	  <div i d="paypro_check">
	    <h1>주문이 완료되었습니다.</h1>
	    <button class="btn btn-default paypro_btn" onclick="location.href='/Zspace/orderList.do?mem_id=${mem_id}'">주문확인</button>
	  </div>
	 </center>
  </div>

<jsp:include page="footer.jsp" />
</body>
</html>