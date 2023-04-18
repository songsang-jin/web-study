<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="sideBar.jsp" flush="false"  />

 <div id="contents">  <!-- 전체 레이아웃을 감싸기 -->
  <div class="top">
    <h4>관리자 전용</h4>
    <hr>
<div id="2tab" >
	   <table id="tab2" border="1px solid black">
	    <tr id="admin_page_tr" height="30px">
	     <th width="5%" class="text-center">주문 번호</th>
	     <th width="15%" class="text-center">주문 날짜</th>
	     <th width="10%" class="text-center">아이디</th>
	     <th width="30%" class="text-center">상품명</th>
	     <th width="10%" class="text-center">상태 변경</th>
	     <th width="15%" class="text-center">주문처리상태</th>
	     <th width="10%" class="text-center">변경</th>
	     <th width="5%" class="text-center">주문 결과</th>
	    </tr>
	   <c:forEach var="order" items="${order_checkList}">
	    <tr height="40px" class="text-center">
	     <td height="40px">${order.order_num}</td>
	     <td>${order.order_date}</td>
	     <td>${order.mem_id}</td>
	     <td class="text-left" style="padding-left:10px">${order.item_name}</td>
	     <td>
	     	<form action="/Zspace/order_updatePro.do" method="post" name="orderform">
			   <input type="hidden" name="order_num" value="${order.order_num}">
		       <select name="order_sta">
				<c:if test="${order.order_sta == 'return'}">
	         		<option value="return" selected>반품</option>
				</c:if>
				<c:if test="${order.order_sta != 'return'}">
	         		<option value="return">반품</option>
				</c:if>
				<c:if test="${order.order_sta == 'cancel'}">
	         		<option value="cancel" selected>취소</option>
				</c:if>
				<c:if test="${order.order_sta != 'cancel'}">
	         		<option value="cancel">취소</option>
				</c:if>
				<c:if test="${order.order_sta == 'exchange'}">
	         		<option value="exchange" selected>교환</option>
				</c:if>
				<c:if test="${order.order_sta != 'exchange'}">
	         		<option value="exchange">교환</option>
				</c:if>
				
	         	<!-- <option value="item_num">반품</option> -->
	      	 	<!-- <option value="iten_name">취소</option> -->
	      	 	<!-- <option value="mem_addr">교환</option> -->
	   		   </select> &nbsp;
	   		   <!-- <button id="tab2_btn" onclick="/Zspace/order_updatePro.do">처리</button> -->
	     </td>
	     <td>
			 <input type="hidden" name="order_num" value="${order.order_num}">
	         <select name="order_prcs">
				<c:if test="${order.order_prcs == 'payment_check'}">
	         		<option value="payment_check" selected>입금확인 중</option>
				</c:if>
				<c:if test="${order.order_prcs != 'payment_check'}">
	         		<option value="payment_check">입금확인 중</option>
				</c:if>
				<c:if test="${order.order_prcs == 'delivery_ready'}">
	         		<option value="delivery_ready" selected>배송준비 중</option>
				</c:if>
				<c:if test="${order.order_prcs != 'delivery_ready'}">
	         		<option value="delivery_ready">배송준비 중</option>
				</c:if>
				<c:if test="${order.order_prcs == 'delivery'}">
	         		<option value="delivery" selected>배송중</option>
				</c:if>
				<c:if test="${order.order_prcs != 'delivery'}">
	         		<option value="delivery">배송중</option>
				</c:if>
				<c:if test="${order.order_prcs == 'complete'}">
	         		<option value="complete" selected>배송완료</option>
				</c:if>
				<c:if test="${order.order_prcs != 'complete'}">
	         		<option value="complete">배송완료</option>
				</c:if>
												
<!-- 	/*        <option value="choose" selected>선택하세요</option>
         	<option value="payment_check">입금확인 중</option>
      	 	<option value="delivery_ready">배송준비 중</option>
      	 	<option value="delivery">배송중</option>
      	 	<option value="complete">배송완료</option> */ -->
   		   </select> 
   		   <td>
   		   <button type="submit">변경하기</button>
   		   </td>
   		    </form>
	     </td>
		<c:if test="${order.order_sta == 'return'}">
        		<td>반품</td>
		</c:if>
		<c:if test="${order.order_sta == 'cancel'}">
        		<td>취소</td>
		</c:if>
		<c:if test="${order.order_sta == 'exchange'}">
        		<td>교환</td>
		</c:if>
	    </tr>
	   </c:forEach>
	   </table>


	  </div>
	  <jsp:include page="footer.jsp" flush="false" />
</body>
</html>