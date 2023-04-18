<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%String  mem_id = (String)session.getAttribute("mem_id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
			src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<title>상품상세페이지</title>
<!-- 부트스트랩 -->
    <link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
    <link href="/Zspace/css/orderform.css" rel="stylesheet"/>
    <link href="/Zspace/css/jyh.css" rel="stylesheet">
    <link href="/Zspace/css/tool.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
	<script type="text/javascript" src="/Zspace/js/orderform.js"></script>  
	<style>
@font-face {
  font-family: 'S-CoreDream-3Light';
  src: url('S-CoreDream-3Light.woff') format('woff');
  font-weight: normal;
  font-style: normal;
  font-size: 11px;
}

body{
    font-family:'S-CoreDream-3Light';
}
</style> 
<script>
	$(function(){
		//$('.small a').mouseover(function(){}) //OK
		$('.small_img a').hover(function(){
			var imgname=$(this).attr('href');
			//alert(imgname); fadeIn, fadeOut -> fadeTo(유지시간,투명도(0 or 1))
			$('.large_img').fadeTo("slow",0,function(){ //서서히 안보이게 설정하고나서 0은 안보이게
				$('.large_img').attr('src',imgname); // $('.large').attr('src'$(this).('href'));
			}).fadeTo("slow",1); //서서히 눈에 보인다.(투명도1) -> 새로운 이미지로 전환효과
		},function(){}) //mouseout을 쓰지 않아도 되는 이유? mouseover로도 충분히 각 이미지가 변경이 되기 떄문에
	})
</script>
</head>
<body>  
    <body>
    
	<!-- 사이드 바 -->
	<jsp:include page="sideBar.jsp" /> 
		
		<!-- 본문 -->
	<div id="contents">	
    <div class="row goods_detail_wrap">
	<div class="goods_detail_main">
			<!-- 상품 섹션-->
			<section>
            	<div class="container">
            	<div class="row">
                    <div class="col-md-6">
	                    <img src="${goods.item_img }(1).jpg" class="large_img"
	                    		style="border:none;margin:10px;width:500px;height:450px";><!-- 원래 이미지 크기 --> 
						<div class="small_img">
							<a href="${goods.item_img }(1).jpg"><img src="${goods.item_img }(1).jpg"></a>
							<a href="${goods.item_img }(2).jpg"><img src="${goods.item_img }(2).jpg"></a>
							<a href="${goods.item_img }(3).jpg"><img src="${goods.item_img }(3).jpg"></a>
						</div>
                    </div>
                    
                    <!-- 상품정보 -->
                    <div class="col-md-6 goods_detail_info">
                        <h1>${goods.item_name }</h1><p>
                        <hr>
                        <div>
                        	<table width="500" height="100">
                        		<tr>
                        			<td>판매가</td>
                        			<td><fmt:formatNumber value="${goods.item_pay}" pattern="#,### 원" /></td>
                        		</tr>
                        		<tr>
                        			<td>배송</td><td>국내배송</td>
                        		</tr>
                        		<tr>
                        			<td>배송방법</td><td>택배</td>
                        		</tr>
                        		<tr>
                        			<td>배송비</td><td>3,000원</td>
                        		</tr>                        		                        		
                        	</table>
                        </div>
                        	<p>
                        	<hr>           
                    <!-- 가격,주문수량 -->
                    <div class="goods_price">
						<div class="sale_price">가격 : <fmt:formatNumber value="${goods.item_pay}" pattern="#,### 원" /></div>
					</div>			
					<div class="line">
					</div>	
					<div class="button_quantity">
						주문수량
						<input type="text" class="quantity_input" value="1">
						<span>
							<button class="plus_btn">+</button>
							<button class="minus_btn">-</button>
						</span>
					</div>
					<!-- 버튼 -->
					<div class="goods_detail_button">
						<div class="button_set">
						   <%--  item_num :<c:out value="${item_num}" /> --%>
							<!-- <form> -->
								<%-- 
								<input type="hidden" value="${goods.item_num}" id="item_num"/>
								<input type="hidden" value="${goods.mem_id}" id="item_num"/> --%>
								<button class="btn_buy btn-lg goods_cart" style="background-color:#f4efe1" 
											 onclick="location.href='/Zspace/cart.do?item_num=${item_num}&mem_id=${mem_id }'">장바구니</button>                                    
								<button  class="btn_buy btn-lg goods_wish" style="background-color:#f4efe1" 
								             onclick="location.href='/Zspace/wishlist.do?item_num=${item_num}&mem_id=${mem_id}'">관심상품</button>
								<button class="btn_buy btn-lg goods_pay" style="background-color:#f4efe1"  
											 onclick="location.href='/Zspace/order.do?item_num=${item_num}&mem_id=${mem_id}&btn=2'">구매</button>
							<!-- </form> -->
						</div>
					</div>
            </div><!-- 상품정보 -->
            </div><!-- container -->
            </div>
        </section>
        
        <!-- 상품상세정보 섹션 -->
        <section>
        	<!-- 상품상세정보 이미지 -->
        	<div class="container text-center">            
                <div class="row">
		        	<div class="col-md-12">
		        		<div >
		        			<h3 class="goods_detail_little_title">PRODUCT DETAIL</h3>
		        		</div>
		        		<div class="product_detail">
		        			<img src="${goods.item_img }(4).jpg">
		        		</div>
		        	</div>  
		        </div><!-- row div -->	           	
		        	<!-- 상품상세정보 상세 이미지 -->
		        <div class="row">	
		        	<div class="col-md-12">	
		        		<h3 class="goods_detail_little_title">DETAIL VIEW</h3>
		        		<div class="detail_view">
		        			<%-- <img src="${goods.item_img }(5).jpg"> --%>
		        			<img src="${goods.item_img }(5).jpg">
		        			<img src="${goods.item_img }(6).jpg">
		        		</div>
		        	</div>	 
		        </div><!-- row div -->  	
		        
		        <!-- 가이드 -->
		        <div class="goods_guide_wrap">
		        <div class="row">	
		        	<div class="col-md-12">		      
		        		<h3 class="goods_detail_little_title">PAYMENT INFO</h3>
		        		<div class="payment_info text-left">
		        			<!-- 결제 안내 -->
		        			<div class="guide">
		        				고액결제의 경우 안전을 위해 카드사에서 확인전화를 드릴 수도 있습니다. 
		        				<br>
		        				확인과정에서 도난 카드의 사용이나 타인 명의의 주문등 정상적인 주문이 아니라고 판단될 경우
		        				임의로 주문을 보류 또는 취소할 수 있습니다. &nbsp; 
		        				<br>
      							무통장 입금은 상품 구매 대금은 PC뱅킹, 인터넷뱅킹, 텔레뱅킹 혹은 가까운 은행에서 직접 입금하시면 됩니다. &nbsp;
      							<br>      							
							    주문시 입력한&nbsp;입금자명과 실제입금자의 성명이 반드시 일치하여야 하며, 7일 이내로 입금을 하셔야 하며&nbsp;입금되지
							    않은 주문은 자동취소 됩니다. 							    
		        			</div>
		        			<!-- 배송 안내 -->
		        			<div class="guide goods_guide">
							<h4>DELIVERY</h4>
									<b>배송 방법</b> : 택배<br>
									<b>배송 지역</b> : 전국지역<br>
									<b>배송 비용</b> : 3,000원<br>
									<b>배송 기간</b> : 3일 ~ 7일<br>
									<b>배송 안내</b> : <br>
									- 산간벽지나 도서지방, 제주도의 경우 별도의 추가금액을 지불하셔야 배송이 가능합니다.
									- 배송은 입금 확인 후 약 2~5일 소요됩니다.<br>
									- 재고가 있는 상품의 경우 당일 출고 가능하지만 그렇지 않은 경우 상품 준비기간이 8일~14일이 필요합니다.<br>
									- 배송지연 및 조기 품절 시 고객님께 미리 안내 메세지를 발송해 드립니다.<br></li>
								
							</div><!-- 배송 안내 div -->
							
							<!-- 교환 및 반품 -->
							<div class="good_detail_page_guide goods_guide">
							<h4>EXCHANGE INFO</h4>
								<div>
						            <b>&nbsp;교환 및 반품 주소</b><br>
						            - 서울 강남구 테헤란로1길 10 세경빌딩 3층<br><br>						            
						            <b>교환 및 반품이 가능한 경우</b>
						            <div>							        
										- 상품 수령일로부터 7일 이내에 고객센터에 상품이 도착해야 처리가 진행됩니다.<br>																		
										- 교환 및 반품 신청 접수가 완료된 상품에 한해서 가능하며, 접수 없이 임의로 반송된 상품은 자동 철회됩니다.<br>
										- 가전제품의 경우 포장을 개봉하였거나 포장이 훼손되어 상품가치가 상실된 경우에는 교환/반품이 불가능합니다.<br>
										- 공급 받으신 상품 및 용역의 내용이 표시.<br>									
										 &nbsp; &nbsp;광고 내용과 다르거나 다르게 이행된 경우에는 공급받은 날로부터 3월이내, 그사실을 알게 된 날로부터 30일이내
									</div><br>																				
									<b>교환 및 반품이 불가능한 경우</b>
									<div id="cont">
									- 상품의 공정 과정에서 발생한 빈티지 디테일, 실밥, 초크자국, 혹은 주문제작상품의 경우	<br>
									- 고객님의 책임 있는 사유로 상품등이 멸실 또는 훼손된 경우. 단, 상품의 내용을 확인하기 위하여<br>
									 &nbsp; &nbsp;포장 등을 훼손한 경우는 제외<br>
									- 포장을 개봉하였거나 포장이 훼손되어 상품가치가 상실된 경우<br>
									 &nbsp; &nbsp;(예 : 가전제품, 식품, 음반 등, 단 액정화면이 부착된 노트북, LCD모니터, 디지털 카메라 등의 불량화소에<br>
									 &nbsp; &nbsp;따른 반품/교환은 제조사 기준에 따릅니다.)<br>
									- 고객님의 사용 또는 일부 소비에 의하여 상품의 가치가 현저히 감소한 경우 단, 화장품등의 경우 시용제품을<br>
									 &nbsp; &nbsp;제공한 경우에 한 합니다.<br>
									- 시간의 경과에 의하여 재판매가 곤란할 정도로 상품등의 가치가 현저히 감소한 경우<br>
									- 복제가 가능한 상품등의 포장을 훼손한 경우<br>
									 &nbsp; &nbsp;(자세한 내용은 고객만족센터 카카오톡 플러스친구 1:1 상담을 이용해 주시기 바랍니다.)<p>
									※ 고객님의 마음이 바뀌어 단순변심에 의해 교환, 반품을 하실 경우 상품반송 비용은 반드시 고객님께서 부담하셔야 합니다.<br>									
									 &nbsp; &nbsp;(색상 교환, 사이즈 교환 등 포함)
									 </div>
								</div><!-- con -->
							
							</div><!-- 교환 및 반품 div -->
							
		        		
		        		<div class="good_detail_page_guide guide goods_guide"">
						<h4>SERVICE INFO</h4>
							<div>
							<b>고객센터</b><br>
							문의량이 많아 전화통화가 어려울 수 있습니다.<br>
							상단 Q&A 게시판에 글을 남겨주시거나 카카오톡 플러스친구 '1조' 로 연락 주시면 빠른 상담이 가능합니다.<br>
							상담전화 : 010-1234-5678<br>
							평일 : 09:00 ~ 18:00 , 점심시간 : 12~13시<br>
							(토요일,일요일 휴무)<br>								
							</div>	
						</div><!-- SERVICE INFO -->
		        		</div><!-- payment_info -->
		        		</div><!-- 가이드 col-md-12 -->
        		</div><!-- 가이드 row -->
        	</div><!-- container div -->
        </section>
	</div><!-- main -->
	</div><!-- row -->
	</div><!-- contents -->
	
	<!-- 푸터 -->
	<footer class="goods_detail_footer">
		<div class="footerdiv" >
			공간의 집 | KIC | 012-345-6789 | 주소 서울특별시 강남구
			<p>
				사업자번호 011-220345678 | ourhome@aaa.com
			<p>         		      
		</div>
    </footer>
	<%-- <jsp:include page="footer.jsp" />  --%>
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
  </body>
</html>