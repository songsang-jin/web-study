<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!--  
2023.02.25 작성자 : 김인아
2023.03.13 메인페이지 사이드 footer조정완료
2023.03.26 side ->jstl로 변환  이거 안적혀있으면 안되어 있는거
-->
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>공간의 집</title>
<!-- 부트스트랩 -->
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="/Zspace/js/slidekim.js"></script>

<!-- 메인페이지,사이드바 css -->
<link rel="stylesheet" href="/Zspace/css/kim.css">
<link rel="stylesheet" href="/Zspace/css/tool.css">

<style>

@font-face {
  font-family: 'S-CoreDream-3Light';
  src: url('S-CoreDream-3Light.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}

body{
    font-family:'S-CoreDream-3Light';  
}
</style>
</head>

<body>
<jsp:include page="sideBar.jsp" />  

		<!-- ========본문시작===========-->
	<div id="container">
		<div class="slideshow-container">
				<article class="m_slide ">
				<ul><!-- 1500*644 -->					
					<li><a href="#.1rug(10).jpg" title=""><img src="/Zspace/project/slide/vis6.jpg" alt=""></a></li>
					<li><a href="#.3table(8)" title=""><img src="/Zspace/project/slide/5table(3).jpg" alt=""></a></li>
					<li><a href="#" title=""><img src="/Zspace/project/slide/1rug(10).jpg" alt=""></a></li>
					<li><a href="#" title=""><img src="/Zspace/project/slide/3table(8).jpg" alt=""></a></li>
				</ul>
				
				<div class="slide-btns"> 				
					<div class="btn_wrap">
						<button id="btnPrev">
							<i class="fas fa-angle-left"></i>
						</button>
						<button id="btnNext">
							<i class="fas fa-angle-right"></i>
						</button>
					</div>
				</div>				
			  </article>
		  </div> <!-- slideshow-container-->
		
		<div class="conent">
		 	 
<!-- 제품목록 -->
            <div class="conent_list " align="center">
            <p class="text2">오늘의 베스트 상품</p>
               <div class="list_imgs"> <!-- 이미지 목록 -->      
               
                     <div class=" col-md-4 warp">
                        <a href="/Zspace/item.do?item_num=2" title="">   
                           <img src="/Zspace/project/blind/1blind(1).jpg" class=" img " width="100%">
                           <div class="text">아라크네 블라인드<p>가격 : 225,000원</p>추천</div>   
                        </a>
                     </div>            
                           
                     <div class="col-md-4 warp">
                        <a href="/Zspace/item.do?item_num=1" title="">   
                        <img src="/Zspace/project/blanket/5blanket(1).jpg" class="img" width="100%">
                        <div class="text">세미 화이버 블랭킷</p>가격 : 300,000원</p>추천 </div>
                        </a>
                     </div>
                     
                     <div class="col-md-4 warp">
                        <a href="/Zspace/item.do?item_num=9" title="">   
                           <img src="/Zspace/project/rug/1rug(1).jpg" class="img" width="100%">
                           <div class="text">메종 드룸 러그</p>가격 : 200,000원</p></div>
                        </a>
                     </div>               
               
                     <div class=" col-md-4 warp">
                        <a href="/Zspace/item.do?item_num=5" title="">   
                        <img src="/Zspace/project/dresser/1dresser(1).jpg" class="img" width="100%">
                        <div class="text">비에스디 드레서</p>가격 : 350,000원</p>추천</div>
                        </a>
                     </div>
                     
                     <div class="col-md-4 warp">
                        <a href="/Zspace/item.do?item_num=8" title="">   
                           <img src="/Zspace/project/lamp/3lamp(1).jpg" class="img" width="100%">
                           <div class="text">머쉬룸 스탠드</p>가격 : 150,000원</p>추천 </div>
                        </a>
                     </div>
                     
                     <div class="col-md-4 warp">
                        <a href="/Zspace/item.do?item_num=11" title="">   
                           <img src="/Zspace/project/cutlery/2cutlery(1).jpg" class="img" width="100%">
                           <div class="text">트라몬티나 커트러리</p>가격 : 75,000원</p> </div>
                        </a>
                     </div>               
   
                     <div class=" col-md-4 warp">
                        <a href="/Zspace/item.do?item_num=3" title="">   
                           <img src="/Zspace/project/candle/1candle(1).jpg" class="img" width="100%">
                           <div class="text">메르시앤코 네이처 캔들</p>가격 : 30,000원</p>추천 </div>
                        </a>
                     </div>
                     
                     <div class="col-md-4 warp">
                        <a href="/Zspace/item.do?item_num=4">
                           <img src="/Zspace/project/chair/1chair(2).jpg" class="img" width="100%">
                           <div class="text">오트밀 심플 체어</p>가격 : 125,000원</p>추천</div>
                        </a>
                     </div>
                     
                     <div class="col-md-4 warp">
                        <a href="/Zspace/item.do?item_num=6" title="">   
                           <img src="/Zspace/project/flower/1flower(1).jpg" class="img" width="100%">
                           <div class="text">양귀비 조화</p>가격 : 15,000원</p> </div>
                        </a>
                     </div>
               </div>
            </div>
            <!-- 제품리스트 -->
		<!-- footer 하단부분 밀려서 지움-->
		<footer>
			<div class="ftsizeout">
			      <span class="ftsizein" >
			         공간의 집 | KIC | 012-345-6789 | 주소 서울특별시 강남구<p>
			            사업자번호 011-220345678 | ourhome@aaa.com<p>         
			      </span>
			</div>
		</footer>
		
		</div><!-- conent 본문시작부분 -->
		

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>		
		<script src="/Zspace/js/bootstrap.min.js"></script>
	</div><!-- contents -->
	
		</body>
</html>