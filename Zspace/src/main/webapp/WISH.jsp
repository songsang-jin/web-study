
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%String  mem_id = (String)session.getAttribute("mem_id"); %>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/bgh.css" rel="stylesheet">
<link href="/Zspace/css/jyh.css" rel="stylesheet">


<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script> 
    $(document).ready(function() {
        $("#cbx_chkAll").click(function() {
            if ($("#cbx_chkAll").is(":checked"))
                $("input[name=chk]").prop("checked", true);
            else
                $("input[name=chk]").prop("checked", false);
        });
        $("input[name=chk]").click(function() {
            var total = $("input[name=chk]").length;
            var checked = $("input[name=chk]:checked").length;
            if (total != checked)
                $("#cbx_chkAll").prop("checked", false);
            else
                $("#cbx_chkAll").prop("checked", true);
        });
    });
</script>
</head>
<body>
	<!-- 사이드바 -->
	<jsp:include page="sideBar.jsp" />
	<!-- 본문 -->
	<div id="contents"> 
		<div class="wish_list_code_title">
			<h2>관심상품</h2>
			<hr/>
		</div>
		<br>
		<div class="wish_list_code_main" >
			<form method="post">
			<table border="1" >
				<colgroup  >
					<col width="4%" />
					<col width="7%"/>
					<col  width="17%"/>
					<col  width="8%" />
					<col  width="6%"/>
					<col  width="8%" />
					<col  width="8%" />
					<col width="10%" />
				</colgroup>
				<!-- 테이블표 상단에 색상을 #f4efe1;-->
				<thead class="wish_list_code_color">
					<tr>
						<th   class="text-center">
						   <input type="checkbox" id="cbx_chkAll" />
						 </th>
						<th scope="col"   class="text-center">이미지</th>
						<th scope="col"   class="text-center">상품정보</th>
						<th scope="col"  class="text-center">판매가</th>
						<th scope="col"  class="text-center" >수량</th>
						<th scope="col"  class="text-center">배송구분</th>
						<th scope="col" class="text-center">합계</th>
						<th scope="col" class="text-center">선택</th>
					</tr>
				</thead>

				<c:forEach var="wish" items="${wishlist}">
				<tbody class="text-center"
					class="xans-element- xans-myshop xans-myshop-wishlistitem">
					<tr class="xans-record-">
						<th   class="text-center">
						  <input type="checkbox" name="chk">
						<td style="line-height: 0">
						   <img src="${wish.item_img }(1).jpg" width="100" alt="#" /></td>
						<td>${wish.item_name }</td>
						<td>
							<fmt:formatNumber value="${wish.item_pay}" pattern="#,###" />	
						</td>
						<td>1</td>
						<td>기본배송</td>
						<td>
							<c:set var = "sum" value = "0" />
							<c:forEach var="iw" items="${wishlist}">
							<c:set var= "sum" value="${wish.item_pay}"/>
							</c:forEach>
							<fmt:formatNumber value="${sum}" pattern="#,###" />
						</td> 
						<td>
							<input type="button" style="width: 80px; height: 25px; margin-bottom:8px;'" value="삭제하기" 
										onclick="location.href='/Zspace/wishlistDelete.do?item_num=${wish.item_num}&mem_id=${mem_id }'">
	
							<input type="button" style="width: 100px; height: 25px" value="장바구니담기" 
									onclick="location.href='/Zspace/cart.do?item_num=${wish.item_num}&mem_id=${mem_id }'">
							
					</tr>
				</tbody>
				</c:forEach>
			
					
		<div class="wish_list_code_contents ">
		  <div class="gabtn">
			<input type="button" style="width: 90px; height: 25px" value="전체삭제하기"
						onclick="location.href='/Zspace/wishAllDelete.do?item_num=${wish.item_num}&mem_id=${mem_id}'">
			</div>	
		</div>
		
			</table>
			</form>
	
		<br>
		<!-- 관심상품 밑 주문하기 삭제 장바구니담기 -->
				</div>
</div>
	<!-- 푸터 -->
	<footer class="wish_footer">
		<div class="footerdiv">
			공간의 집 | KIC | 012-345-6789 | 주소 서울특별시 강남구
			<p>
				사업자번호 011-220345678 | ourhome@aaa.com
			<p>         		      
		</div>
    </footer>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</body>
</html>