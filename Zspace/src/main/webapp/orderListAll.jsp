<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import=" model.*,  java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <%String  mem_id = (String)session.getAttribute("mem_id"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/Zspace/css/bgh.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">

<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.3.1.min.js"></script>


</head>
<script>
	var startDate = "${startDate}";
	var endDate = "${endDate}";
	
	$(document).ready(function() {
		$('ul.tabs li').click(function() {
			var tab_id = $(this).attr('data-tab');

			$('ul.tabs li').removeClass('current');
			$('.tab-content').removeClass('current');

			$(this).addClass('current');
			$("#" + tab_id).addClass('current');
		})
	})

	 
</script>
<!-- 부트스트랩 -->
</head>
<body>

	<jsp:include page="sideBar.jsp" />
	<div id="contents">
		<!-- 탭 부분 -->
		<div class="container2"> 
			<ul class="tabs nav-tabs  nav-justified">
				<li class="tab-link current" data-tab="tab-1">주문내역조회</li>
				<li class="tab-link" data-tab="tab-2">교환내역</li>
				<li class="tab-link" data-tab="tab-3">반품내역</li>
				<li class="tab-link" data-tab="tab-4">주문취소내역</a></li>
			</ul>
		</div>
		
		<!-- 주문내역조회 order   -->     
		<div class="panel">	
		<div id="tab-1" class="tab-content current">				
			   	<!-- 날짜검색부분 -->      
				<table id="tab1" border="1px solid black" width="100%">
					검색기간설정	 		
				<div class="calendar">
			         <form name="test" action= "/Zspace/orderList.do?startDate=${startDate}&endDate=${endDate}" >
			         <input type="hidden" name="mem_id" value="${mem_id}">
			         	<td></td>
				        <input type="date" id="startDate" name="startDate" value="${startDate}2022-10-15"  
				        	max="2080-12-22" min="2008-11-02"   style="line-height: 18px; width: 115PX;" >
			          - 
				        <input type="date" id="endDate" name="endDate" value="${endDate}2023-04-15"  
				        	max="2080-12-22" min="2008-11-02" style="line-height: 18px; width: 115PX;" >
			         <input type="submit" id="order1" value="조회" style="width:40px;margin-left:10px;">
			         </form>
			     </div>    	
					<tr id="admin_page_tr" height="30px">
						<th width="25%" class="text-center">주문일자 <br>[주문번호]</th>				
						<th width="15%" class="text-center">이미지</th>
						<th width="30%" class="text-center">상품정보</th>
						<th width="15%" class="text-center">수량</th>
						<th width="15%" class="text-center">총 상품금액</th>
					</tr>
					<p>
			     <c:forEach items="${orderList}" var="ord">	
					<c:if test="${ord.order_date >= startDate && ord.order_date <= endDate}"> 	      
					<%-- <c:if test="${ord.order_date >= startDate}"> --%>
				     	<tr height="40px" class="text-center">
					        <td align="center"> 
								${ord.order_date}<p> [${ord.order_num}]
						    </td>
					        <td style="line-height: 0" align="center">
					        <img  src="${ord.item_img}(1).jpg" width="125"/> </td>
					        <td align="center">${ord.item_name}</td>
					        <td align="center">${ord.item_su}</td>
 					        <td align="center">${ord.pay_total}</td>
					    </tr> 
				    </c:if>
				</c:forEach >		    
				</table>
			</div>
			       
		<!-- 교환내역  exchange-list -->
			<div id="tab-2" class="tab-content">
				<table id="tab2" border="1px solid black" width="100%">
				검색기간설정
				<div class="calendar">
				<form name="test" action= "/Zspace/orderList.do?startDate=${startDate}&endDate=${endDate}" >				
				<td></td>
				<input type="date" id="startDate" name="startDate" value="${startDate}2022-10-15"  
				        	max="2080-12-22" min="2008-11-02"   style="line-height: 18px; width: 115PX;" >
			          - 
				        <input type="date" id="endDate" name="endDate" value="${endDate}2023-04-15"  
				        	max="2080-12-22" min="2008-11-02" style="line-height: 18px; width: 115PX;" >
			         <input type="submit" id="order2" value="조회" style="width:40px;margin-left:10px;"><p>
        </div>
					<tr id="admin_page_tr" height="30px">
						<th width="23%" class="text-center">주문일자 <br>[주문번호]</th>
						
						<th width="15%" class="text-center">이미지</th>
						<th width="26%" class="text-center">상품정보</th>
						<th width="11%" class="text-center">수량</th>
						<th width="13%" class="text-center">상품금액</th>
						<th width="26%" class="text-center">주문처리상태</th>
					</tr>
					<p>
			     <c:forEach items="${exchangeList}" var="ecl">
					<c:if test="${ecl.order_date >= startDate && ecl.order_date <= endDate}"> 	     
					<tr height="40px" class="text-center">
						<td align="center">	${ecl.order_date}<p> ${ecl.order_num}
						</td>
						<td style="line-height: 0" align="center">
						<img  src="${ecl.item_img}(1).jpg" width="125" /></td>
						<td align="center">${ecl.item_name}</td>
					    <td align="center">${ecl.item_su}</td>
						<td align="center"><fmt:formatNumber value="${ecl.pay_amt}" pattern="#,###" /> </td>
						<td align="center">${ecl.order_sta}</td>
					</tr>
					</c:if>
				</c:forEach >		
				</table>
			</div>
				
			       		<!-- 반품내역  return-list-->
			<div id="tab-3" class="tab-content">
				<table id="tab3" border="1px solid black" width="100%">
				검색기간설정
				<div class="calendar">
				<form name="test" action= "/Zspace/orderList.do?startDate=${startDate}&endDate=${endDate}" >				
				<td></td>
				<input type="date" id="startDate" name="startDate" value="${startDate}"  
				        	max="2080-12-22" min="2008-11-02"   style="line-height: 18px; width: 115PX;" >
			          - 
				        <input type="date" id="endDate" name="endDate" value="${endDate}"  
				        	max="2080-12-22" min="2008-11-02" style="line-height: 18px; width: 115PX;" >  
			            <input type="submit" id="order1" value="조회" style="width:40px;margin-left:10px;">
					<p>
		        </div>
					<tr id="admin_page_tr" height="30px">
						<th width="23%" class="text-center">주문일자 <br>[주문번호]
						</th>
						<th width="15%" class="text-center">이미지</th>
						<th width="26%" class="text-center">상품정보</th>
						<th width="11%" class="text-center">수량</th>
						<th width="13%" class="text-center">상품금액</th>
						<th width="26%" class="text-center">주문처리상태</th>
					</tr>
					<c:forEach items="${orderList}" var="ord">
					<c:if test="${ord.order_date >= startDate && ord.order_date <= endDate}"> 
					<tr height="40px" class="text-center">
						<td align="center">${ord.order_date}<p>${ord.order_num}
						</td>
						<td style="line-height: 0" align="center">
						<img  src="${ord.item_img}(1).jpg" width="125" /></td>
						<td align="center">${ord.item_name}</td>
					    <td align="center">${ord.item_su}</td>
						<td align="center"><fmt:formatNumber value="${ord.pay_amt}" pattern="#,###" /> </td>
						<td align="center">${order_sta}</td>
					</tr>
					</c:if>
					</c:forEach>
				</table>
			</div>
			       	
					
			<!-- 주문취소내역 cancel-list-->
			<div id="tab-4" class="tab-content">
				<table id="tab4" border="1px solid black" width="100%">
				검색기간설정
				<div class="calendar">
				<form name="test" action= "/Zspace/orderList.do?startDate=${startDate}&endDate=${endDate}" >				
				<td></td>
				<input type="date" id="startDate" name="startDate" value="${startDate}"  
				        	max="2080-12-22" min="2008-11-02"   style="line-height: 18px; width: 115PX;" >
			          - 
				        <input type="date" id="endDate" name="endDate" value="${endDate}"  
				        	max="2080-12-22" min="2008-11-02" style="line-height: 18px; width: 115PX;" >
			         <input type="submit" id="order1" value="조회" style="width:40px;margin-left:10px;">
			<p>
        </div>
					<tr id="admin_page_tr" height="30px">
						<th width="23%" class="text-center">주문일자 <br>[주문번호]
						</th>
						<th width="15%" class="text-center">이미지</th>
						<th width="26%" class="text-center">상품정보</th>
						<th width="11%" class="text-center">수량</th>
						<th width="13%" class="text-center">상품금액</th>
						<th width="26%" class="text-center">주문처리상태</th>
					</tr>
					<c:forEach items="${orderList}" var="ord">
					<c:if test="${ord.order_date >= startDate && ord.order_date <= endDate}"> 	     
					<tr height="40px" class="text-center">
						<td align="center">	${ord.order_date}<p> ${ord.order_num}
						</td>
						<td style="line-height: 0" align="center">
						<img  src="${ord.item_img}(1).jpg" width="125"/></td>
						<td align="center">${ord.item_name}</td>
					    <td align="center">${ord.item_su}</td>
						<td align="center"><fmt:formatNumber value="${ord.pay_total}" pattern="#,###" /> </td>
						<td align="center">${order_sta}</td>
					</tr>
				    </c:if>
				</c:forEach >		    
				</table>
			</div>
		</div>
	<!-- panel -->
		</div>
	</div>
	<jsp:include page="/footer.jsp" />
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>