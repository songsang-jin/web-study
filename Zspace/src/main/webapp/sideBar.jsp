<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
int check = 0;
String mem_id=null;
 System.out.println("session값=>"+session.getAttribute("check") );
  if(session.getAttribute("check") != null){
	  check = (Integer)session.getAttribute("check");
	  mem_id = (String)session.getAttribute("mem_id");
 } 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
</head>
<body>
<div id="leftbar">			
			<div class="logo">
				<a href="/Zspace/MainPage.jsp"><img src="/Zspace/project/rogo.png"></a>
			</div>

			<div class="menu_wrap" >
			
				<table class="menu_group" >
				<tr>				
					<th align="center">
					<%if (check == 0)  {%>
						<a href="/Zspace/login.jsp">로그인</a>  | 
						<a href="/Zspace/mem_join.jsp">회원가입  </a> 
					<%}else if( check > 0) {
							if(mem_id.equals("admin")){ %>
							<div><h3>${mem_id } 님</h3></div>
								<a href="/Zspace/admin_page.jsp">관리자페이지</a> |
								<a href="logout.do">로그아웃</a>
						<%}else{ %>
							<div><h3>${mem_id } 님</h3></div>
								<a href="/Zspace/cart.do?mem_id=${ mem_id}">장바구니</a> | 
								<a href="/Zspace/wishlist.do?mem_id=${mem_id }">관심상품</a> | 
								<a href="/Zspace/mypage.jsp">마이페이지</a> <br>
								<a href="logout.do">로그아웃</a>
							
							<%}; %>
						<%}; %>
						
					</th>	
				</tr>
				</table>		
				<!-- 검색창 -->
				
			      <div class="search_menu">		      
			        <input type="text" id="keyword"/>
			        <i class="material-icons">
			        	<a href="Search.jsp">search</a>			        
			        </i>		 
			     </div>
			  	<!--  -->	
			  	
				<div class="side_list">
				<!-- 좌측 -->
				<div class=" side_wrap">
					<div class="menu_cell">
						<label for="ch1"><a href="#.DECORATION">데코</a></label>
						<ul class="sub_menu">
							<li><a href="#Lamp">조명</a></li>
							<li><a href="#Frame">액자</a></li>
							<li><a href="#.Candle">캔들</a></li>
							<li><a href="#.Flower">식물/조화</a></li>
						</ul>
					</div>

					<div class="menu_cell">
						<label for="ch2"><a href="#.Furniture">가구</a></label>
						<ul class="sub_menu">						
							<li><a href="#.Chair">의자</a></li>
							<li><a href="#.Table">테이블</a></li>
							<li><a href="#.Dresser">서랍장</a></li>
						</ul>
					</div>

					<div class="menu_cell">
						<label for="ch3"><a href="#.FABRIC"> 패브릭</a></label>
						<ul class="sub_menu">
							<li><a href="#.rug">러그</a></li>
							<li><a href="#.blind">블라인드</a></li>
							<li><a href="#.blanket">이불</a></li>
						</ul>
					</div>

					<div class="menu_cell">
						<label for="ch4"><a href="#.KITCHEN">주방</a></label>
						<ul class="sub_menu">
							<li><a href="#.Pantry">팬트리</a></li>
							<li><a href="#.Cooking">조리도구</a></li>
							<li><a href="#.dishes">식기</a></li>
						</ul>
					</div>
				</div>

				<!-- 우측 -->
            <div class="side_wrap ">
           
               <div class="menu_cell">
                  <label for="ch6"><a href="/Zspace/list1.do?post_num=1">공지사항</a></label>                  
               </div>
               <div class="menu_cell">
                     <label for="ch8"><a href="/Zspace/list2.do?post_num=1">이벤트</a></label>      
               </div>
            
               <!-- 게시판 -->
               <div class="menu_cell">
                  <label for="ch3"><a href="#">게시판</a></label>
                  <ul class="sub_menu">
                     <li><a href="/Zspace/list3.do?post_num=1">질문게시판</a></li>
                     <li><a href="/Zspace/list4.do?post_num=1">리뷰게시판</a></li>
                     <li><a href="/Zspace/list5.do?post_num=1">취소게시판</a></li>
                     <li><a href="/Zspace/list6.do?post_num=1">커뮤니티</a></li>
                  </ul>
               </div> 
               
            </div>
				</div>
			<div class="slid_footer" style="white-space:pre">
<b>TEL</b> | 02-345-6789
<b>영업시간</b>  |  AM10:00 - PM 17:00
	        공휴일.토.일 휴무
<span>
<b>BANK INFO</b>
<b>예금주</b> |  (주) 공간의 집 <br> 
<b>농협</b>  |  111-0303-0333-33
<b>신한</b>  |  144-144-333333
<b>국민</b>  |  555555-44-445555
<b>하나</b>  |  281-919999-99999
</span>
				</div>			
			</div>
		</div>
</body>
</html>