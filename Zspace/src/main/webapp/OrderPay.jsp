<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*, java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%String mem_id = (String)session.getAttribute("mem_id");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결제페이지</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/pjh.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/Zspace/js/asj.js?ver=1"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
  .error{
	  color:red;
  }
</style>
<!-- 검색이미지 -->
﻿<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|	Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">

</head>
<body>
<jsp:include page="sideBar.jsp" />

  <div id="contents">
	<div id="pay_header">
	  <h4 class="text-center">결제하기</h4><p>
	  <hr>
	</div>
	
	<input type="hidden" name="pay_num" value="${pay_num}">
    <form class="order_pay_fm" method="post" action="/Zspace/orderPro.do?mem_id=${mem_id}">
	<div class="pay_ordermenu" > <!-- 상품 -->
	
	 <table class="pay_ordertable">
	   <thead class="pay_ordertalbehead" style="background-color:bgr(217,217,217)">
		 <tr height="30px" width="100%">	
		  <th width="15%" class="text-center">이미지</th>
		  <th width="25%" class="text-center">상품정보</th>
		  <th width="10%" class="text-center">판매가</th>
		  <th width="10%" class="text-center">수량</th>
		  <th width="10%" class="text-center">배송구분</th>
		  <th width="10%" class="text-center">합계</th>
		 </tr> 
	   </thead>
	   <tbody class="pay_ordertalbebody text-center">
	   
	   <c:if test="${btn==2}">
	   <c:forEach var="order" items="${orderList}">
	     <c:set var="pay_amt" value="${order.item_pay * order.item_su}" /> 
	     
		 <tr height="60px">
		 <input type="hidden" name="item_img" value="${order.item_img}">
		 <input type="hidden" name="item_name" value="${order.item_name}">
		 <input type="hidden" name="item_pay" value="${order.item_pay}">
		 <input type="hidden" name="item_num" value="${order.item_num}">
		 <input type="hidden" name="item_su" value=1>
		 <input type="hidden" name="item_name" value="${order.item_name}">
		 <input type="hidden" name="pay_amt" value="${pay_amt}">
		  <td><img src="${order.item_img}(1).jpg" name="item_img" style="width:100px" class="text-center"></td>
		  <td>${order.item_name}<br></td>
		  <td><fmt:formatNumber value="${order.item_pay}" pattern="#,###" />원</td>
		  <td>1</td>
		  <td>기본배송</td>
		  <td><fmt:formatNumber value="${pay_amt }" pattern="#,###" />원</td>
		 </tr>
		 </c:forEach>
		 </c:if>
		 
		<c:if test="${btn==1}">
		<c:forEach var="order" items="${total}">
	     <c:set var="pay_amt" value="${order.item_pay * order.item_su}" /> 
	     
		 <tr height="60px">
		 <input type="hidden" name="item_img" value="${order.item_img}">
		 <input type="hidden" name="item_name" value="${order.item_name}">
		 <input type="hidden" name="item_pay" value="${order.item_pay}">
		 <input type="hidden" name="item_num" value="${order.item_num}">
		 <input type="hidden" name="item_su" value=1>
		 <input type="hidden" name="item_name" value="${order.item_name}">
		  <td><img src="${order.item_img}(1).jpg" name="item_img" style="width:100px" class="text-center"></td>
		  <td>${order.item_name}<br></td>
		  <td><fmt:formatNumber value="${order.item_pay}" pattern="#,###" />원</td>
		  <td>1</td>
		  <td>기본배송</td>
		  <td><fmt:formatNumber value="${pay_amt }" pattern="#,###" />원</td>
		 </tr>
		 </c:forEach>
		  </c:if>
	   </tbody>
	 </table>
	
	 
	 <div class="pay_back">
	  <button class="pay_prev btn btn-warning" onclick="history.back()">이전페이지</button>
	 </div>
	</div> <!-- <div class="pay_ordermenu" > 상품-->
	<p><p><hr>
	
	<div class="pay_tobody">  <!-- 회원정보 -->
	<table class="pay_bodytable0">
	 <thead class="pay_bodytable1">
	 <tr>
	  <th>배송 정보</th>
	  <td>
	   <div class="pay_bodytable1area">
	    <input class="pay_address pay_address_1" type="radio" onclick="loadData()" name="shipping" checked>
	      <label>회원 정보와 동일</label>&nbsp; &nbsp; 
	    <input class="pay_address pay_address_2" type="radio" onclick="clearTable()" name="shipping">
	      <label>새로운 배송지</label>
	   </div>
	  </td>
	 </tr>  
	 </thead>     
	 
<!-- 	<form class="order_pay_fm"> -->
   <c:if test="${btn==1}">
	<c:forEach var="cart" items="${orderList}">
	  <tbody class="pay_bodytable2">
	   <tr>
	     <th>받으시는 분</th>
	     <td>
	       <div class="pay_bodytable2area">
	         <input type="text" id="mem_name" size="15" name="mem_name" value="${cart.mem_name}" onkeydown="checkSpacebar();">
	         <span class="error" id="name_error">이름을 입력해주세요.</span>
	       </div>
	     </td>
	   </tr>
	  </tbody>
	  <tbody class="pay_bodytable3">
	   <tr>
	     <th>주소</th>   
		 <td>
		  <div class="pay_bodytable3area"><p>
		    <input size="6" maxlength="6" type="text" name="mem_zipcode" id="mem_zipcode" value="${cart.mem_zipcode}"> 
		     <button class="btn btn_default">우편번호</button>
		    <p>
		    <input size="40" name="mem_addr1" id="mem_addr1" type="text" value="${cart.mem_addr1}">
		   
		     <span class="grid">기본 주소</span>
		     <span class="error" id="addr_error">기본 주소를 입력해주세요.</span>
		    <p>
		    <input size="40" name="mem_addr2" id="mem_addr2" type="text" value="${cart.mem_addr2}"> 
		     <span class="grid">나머지 주소</span>
		     <span class="error" id="addr2_error">나머지 주소를 입력해주세요.</span>
		  </div>
		 </td>
	   </tr>
	  </tbody>
	  <tbody class="pay_bodytable5">
	   <tr>
	     <th>연락처</th>
	     <td>
	       <div class="pay_bodytable5area">
	         <input size="13" name="mem_phone" id="mem_phone" type="text" value="${cart.mem_phone}">
		     <span class="error" id="phone_error">연락처를 입력해주세요.</span>
		     
	       </div>
	     </td>
	   </tr>
	  </tbody>
	  </c:forEach>
	  <tbody class="pay_bodytable6">
	   <tr>
	     <th>배송메세지</th>
	     <td>
	       <div class="pay_bodytable6area">
	         <textarea maxlength="255" cols="70"></textarea>
	       </div>
	     </td>
	   </tr>
	  </tbody>
	  </c:if>
	  
	     <c:if test="${btn==2}">
	<c:forEach var="order1" items="${orderList}">
	  <tbody class="pay_bodytable2">
	   <tr>
	     <th>받으시는 분</th>
	     <td>
	       <div class="pay_bodytable2area">
	         <input type="text" id="mem_name" size="15" name="mem_name" value="${order1.mem_name}" onkeydown="checkSpacebar();">
	         <span class="error" id="name_error">이름을 입력해주세요.</span>
	       </div>
	     </td>
	   </tr>
	  </tbody>
	  <tbody class="pay_bodytable3">
	   <tr>
	     <th>주소</th>   
		 <td>
		  <div class="pay_bodytable3area"><p>
		    <input size="6" maxlength="6" type="text" name="mem_zipcode" id="mem_zipcode" value="${order1.mem_zipcode}"> 
		     <button class="btn btn_default">우편번호</button>
		    <p>
		    <input size="40" name="mem_addr1" id="mem_addr1" type="text" value="${order1.mem_addr1}">
		   
		     <span class="grid">기본 주소</span>
		     <span class="error" id="addr_error">기본 주소를 입력해주세요.</span>
		    <p>
		    <input size="40" name="mem_addr2" id="mem_addr2" type="text" value="${order1.mem_addr2}"> 
		     <span class="grid">나머지 주소</span>
		     <span class="error" id="addr2_error">나머지 주소를 입력해주세요.</span>
		  </div>
		 </td>
	   </tr>
	  </tbody>
	  <tbody class="pay_bodytable5">
	   <tr>
	     <th>연락처</th>
	     <td>
	       <div class="pay_bodytable5area">
	         <input size="13" name="mem_phone" id="mem_phone" type="text" value="${order1.mem_phone}">
		     <span class="error" id="phone_error">연락처를 입력해주세요.</span>
	       </div>
	     </td>
	   </tr>
	  </tbody>
	  </c:forEach>
	  <tbody class="pay_bodytable6">
	   <tr>
	     <th>배송메세지</th>
	     <td>
	       <div class="pay_bodytable6area">
	         <textarea maxlength="255" cols="70"></textarea>
	       </div>
	     </td>
	   </tr>
	  </tbody>
	  </c:if>
	 </table>
	</div><!-- <div class="pay_tobody" > -->
   
	<div width="50%" class="text-left pay_totalpayleft">
	  <b>결제 수단</b>
	</div>
	
	<div width="50%" class="text-right pay_totalpayright">
	  <b>총 결제 금액</b>
	</div>
	
	<div class="pay_bottomleft"><p> <!-- 결제 수단 -->
	  <label><input type="radio" id="payment" name="pay_meth" value="카드">카드 결제</label><p>
	  <label><input type="radio" id="payment" name="pay_meth" value="휴대폰">휴대폰 결제</label><p>
	  <label><input type="radio" id="payment" name="pay_meth" value="무통장">무통장 입금</label><p>
	  <label><input type="radio" id="payment" name="pay_meth" value="삼성페이">삼성 페이</label><p>
	</div>
	
	<div class="pay_bottomright"> <!-- 결제금액 -->
	
	 <div>
	   <c:if test="${btn==1}">
	   <div><b>총 주문 금액</b><p>
	       <c:set var="pay_amt" value="0"/>  
		   <c:forEach var="cart" items="${total}">  
			<c:set var="pay_amt" value="${pay_amt+(cart.item_pay*cart.item_su)}"/> 
			</c:forEach>  
		     <input type="hidden" name="pay_amt" value="${pay_amt}">
		     <fmt:formatNumber value="${pay_amt}" pattern="#,###" />원
		   </div><p>
		   <div><b>+ 배송비</b><br>
		     3,000원
		   </div>
		 <div class="pay_last">
		 <c:set var="pay_total" value="${pay_amt + 3000}" />
		   <font size="5pt"> 결제 금액= &nbsp; 
		     <input type="hidden" name="pay_total" value="${pay_total}">
		     <fmt:formatNumber value="${pay_total}" pattern="#,###" /></font><p>
		 </div>   
	 </c:if>
	 <c:if test="${btn==2}">
	   <div><b>총 주문 금액</b><p>
		     <fmt:formatNumber value="${pay_amt}" pattern="#,###" />원
		   </div><p>
		   <div><b>+ 배송비</b><br>
		     3,000원
		   </div>
		 <div class="pay_last">
		 <c:set var="pay_total" value="${pay_amt + 3000}" />
		   <font size="5pt"> 결제 금액= &nbsp; 
		     <input type="hidden" name="pay_total" value="${pay_total}">
		     <fmt:formatNumber value="${pay_total}" pattern="#,###" /></font><p>
		 </div>   
	 </c:if>
	 
		     <button type="submit" class="btn btn-success" id="pay_btn" >결제하기</button>
	</div><!-- <div class="pay_bottomright"> -->
	</div>
	</form>
   </div>
<jsp:include page="footer.jsp" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/Zspace/js/bootstrap.min.js"></script>

</body>
</html>