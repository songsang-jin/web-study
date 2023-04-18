<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="#">
<meta charset="UTF-8">
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/asj.css" rel="stylesheet">
     
<title>::관리자 페이지::</title>
</head>
<script>
$(function(){
	  //첫번째 탭을 제외한 나머지 탭의 내용은 안보이게 설정하기 = :not #tab1
	  $("div.panel div:not("+ $("ul.nav li a.selected").attr("rel") +")").hide();
	  
	  $("ul.nav li a").click(function(){
		  $("ul.nav li a").removeClass("selected");
		  $(this).addClass("selected"); //$(this) : 클릭한 a 태그에 대한 이벤트를 발생시킴
		  
		  var activeTab = $(this).attr("rel");
		  $(activeTab).fadeIn();
		  $("div.panel div:not(" + activeTab + ")").hide(); //선택한 탭의 내용빼고 나머지 모든 탭의 내용은 숨김
		  //안쪽의 $(this):클릭한 tab의 a태그  ex)tab2
		console.log("여기냐?");
		  if(activeTab == "active1"){
			  $('#1tab').load("/Zspace/admin_info.do"); 
			  console.log("여기냐?");
		  }else if(activeTab == "active2"){
			  $('#2tab').load("/Zspace/order_list.do");
			  console.log("여기냐?");
		  }
	  });
	});

 
<!-- 탭 클릭 시 스크롤 자동 이동을 방지-->
$(function(){
 $("a").click(function(e){
   e.preventDefault();
   e.stopPropagation();
 })
}) 
<!-- 체크박스 -->
$(document).ready(function() {
	$("#soldout_chk").click(function() {
		if($("#soldout_chk").is(":checked")) $("input[name=soldout]").prop("checked", true);
		else $("input[name=soldout]").prop("checked", false);
	});
	
	$("input[name=soldout]").click(function() {
		var total = $("input[name=soldout]").length;
		var checked = $("input[name=soldout]:checked").length;
		
		if(total!=checked) $("#soldout_chk").prop("checked", false);
		else $("#soldout_chk").prop("checked", true); 
	});
});
  </script>
  <!-- 부트스트랩 -->
</head>
<body>

<jsp:include page="sideBar.jsp" flush="false"  />

   <!-- 메인 -->      
 <div id="contents">  <!-- 전체 레이아웃을 감싸기 -->
  <div class="top">
    <h4>관리자 전용</h4>
    <hr>
    
	<ul class="nav nav-tabs nav-justified">
	 <li><a rel="active1" class="selected">회원정보</a></li>
	 <li><a rel="active2">회원주문관리</a></li>
	 <li><a rel="active3">적립금 현황</a></li>
	 <li><a rel="active4" >상품관리</a></li>
	</ul>
  </div>
	 
	 
	<div class="panel">
	  <!-- 회원정보 -->
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
	
<!-- 회원주문관리 -->
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
	  
	  <!-- 적립금 -->
	  <div id="3tab">
	   <table id="tab3" border="1px solid black">
	    <tr id="admin_page_tr" height="30px">
	     <th width="13%" class="text-center">아이디</th>
	     <th width="13%" class="text-center">적립 날짜</th>
	     <th width="30%" class="text-center">적립 내역</th>
	     <th width="13%" class="text-center">적립</th>
	     <th width="13%" class="text-center">사용</th>
	     <th width="18%" class="text-center">총 적립금</th>
	    </tr>
	    <tr height="40px" class="text-center">
	     <td>abc</td>
	     <td>2023-02-25</td>
	     <td class="text-left" style="padding-left:10px" >로그인</td>
	     <td><font color="red">+100</font></td>
	     <td><font color="blue">0</font></td>
	     <td><b>100</b></td>
	    </tr>
	    <tr height="40px" class="text-center">
	     <td>qwe</td>
	     <td>2023-02-25</td>
	     <td class="text-left" style="padding-left:10px">리뷰작성</td>
	     <td><font color="red">+300</font></td>
	     <td><font color="blue">0</font></td>
	     <td><b>3300</b></td>
	    </tr>
	    <tr height="40px" class="text-center">
	     <td>ytr</td>
	     <td>2023-02-23</td>
	     <td class="text-left" style="padding-left:10px">상품구매 (20230225-000012)</td>
	     <td><font color="red">0</font></td>
	     <td><font color="blue">-1600</font></td>
	     <td><b>4000</b></td>
	    </tr>
	   </table>
	   <form name="search" id="search" action="/TeamFront/admin_page.do">
        <select name="search">
         <option value="mem_name">아이디</option>
   		</select>
  		<input type="text" size="15" name="searchtext">&nbsp;
   		<input type="submit" value="검색">
	   </form>
	  </div>
	  
	  <!-- 상품관리 -->
	  <div id="4tab">
	   <table id="tab4" border="1px solid black">
	    <tr id="admin_page_tr" height="30px" >
	     <th width="3%" class="text-center"><input type="checkbox" id="soldout_chk"></th>
	     <th colspan=2 width="59%" class="text-center">상품명</th>
	     <th width="9%" class="text-center">상품 수정</th>
	     <th width="9%" class="text-center">상품 삭제</th>
	     <th width="9%" class="text-center">상품 재고</th>
	     <th width="11%" class="text-center">품절 처리</th>
	    </tr>
	    <tr id="admin_page_tr2" class="text-center">
	     <td width=><input type="checkbox" name="soldout"></td>
	     <td width="15%" height="90px">상품사진</td>
	     <td width="45%"  class="text-left" style="padding-left:10px">화이트 머쉬룸 조명</td>
	     
	     <td><center><a href="#"><button id="insert" width="10%">수정</button></a></center></td>
	     <td><center><button id="delete" width="10%" onclick="alert('정말 삭제하시겠습니까?')">삭제</button></center></td>
	     <td>30개</td>
	     <td><center><button id="item_soldout_btn">품절</button> <p><p>
	                 <button id="item_sale_btn">재 판매</button></center>
	     </td>
	    </tr>
	    <tr id="admin_page_tr2" class="text-center">
	     <td width=><input type="checkbox" name="soldout"></td>
	     <td width="15%" height="90px">상품사진</td>
	     <td width="45%"  class="text-left" style="padding-left:10px">자연의 바람</td>
	     <td><center><a href="#"><button id="insert" width="10%">수정</button></a></center></td>
	     <td><center><button id="delete" width="10%" onclick="alert('정말 삭제하시겠습니까?')">삭제</button></center></td>
	     <td>10개</td>
	     <td><center><button id="item_soldout_btn">품절</button> <p><p>
	                 <button id="item_sale_btn">재 판매</button></center>
	     </td>
	    </tr>
	   </table>
	   <a href="#"><button class="item_upload">상품 추가</button></a>
	   <a href="#"><button class="item_soldout">품절 처리</button></a>
	   <a href="#"><button class="item_sale">재 판매</button></a>
	  </div>
	</div> <!-- panel -->
   </div><!-- contents -->
   
   <!-- 페이징처리 -->
   <p>
<div class="faqPost_paging"> 
	<c:if test="${pgList.startPage > pgList.blockSize}">
     <a href="/Zspace/list.do?pageNum=${pgList.startPage-pgList.blockSize}&search=${search}&searchtext=${searchtext}">[이전]</a>
     <a>이전</a>
</c:if>

<c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
     <a href="/Zspace/list.do?pageNum=${i}&search=${search}&searchtext=${searchtext}">
     	<c:if test="${pgList.currentPage==i }">
     		<font color="red"><b>[${i}]</b></font>
     	</c:if>
     	<c:if test="${pgList.currentPage!=i }">
     		[${i}]
     	</c:if>
     	</a>
</c:forEach>

<c:if test="${pgList.endPage <pgList.pageCount}">
      <a href="/Zspace/list.do?pageNum=${pgList.startPage+pgList.blockSize}&search=${search}&searchtext=${searchtext}">[다음]</a>
 </c:if>  
<p>
</div>
<p>
	  

<jsp:include page="footer.jsp" flush="false" />
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/Zspace/js/bootstrap.min.js"></script>
</body>
</html>