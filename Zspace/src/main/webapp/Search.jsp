<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="model.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>상품검색페이지</title>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/ssj.css" rel="stylesheet">
<link href="/Zspace/css/asj.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons|Material+Icons+Outlined|
			Material+Icons+Two+Tone|Material+Icons+Round|Material+Icons+Sharp"rel="stylesheet">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function() {
  // select 요소와 option 요소
  const categorySelect = $('#search');
  const options = categorySelect.find('option');
  // select 요소의 값이 변경될 때마다 실행되는 이벤트 핸들러 함수 작성
  categorySelect.change(function() {
    // 선택된 옵션의 값
    const selectedValue = options.filter(':selected').val();
    // 선택된 옵션 값에 따라서 category_num 변수 설정
    let category_num;
    switch (selectedValue) {
      case '1': category_num = 1;
        break;
      case '2': category_num = 2;
        break;
      case '3': category_num = 3;
        break;
      case '4': category_num = 4;
        break;
      default:
        category_num = '';
        break;
    }
  });
});
</script>
</head>
<body>
<jsp:include page="sideBar.jsp" />

  <div id="contents"><!-- 메인 -->
   <div><h1>상품 검색</h1></div>
  	 <div> <!-- 검색 form -->
  	   <form name="searchfm" class="searchfm"
  	   		action="/Zspace/search.do?search=${search}&searchtext=${searchtext}">
	    <table class="item_search_tb" style="margin-left:300px;margin-bottom:50px" >
	      <tr height="70px" valign="bottom">
	        <td width="200px"align="center" colspan="2" >상품분류</td>
	        <td class="category_num" width="300px" align="left"> 
	          <select name="search" id="category_num_search" style="width:160px;height:23px">
	        	<option value="choose" selected><b>카테고리</b></option>
         		<option value='1' >데코</option>
      	 		<option value='2' >가구</option>
      	 		<option value='3' >패브릭</option>
      	 		<option value='4' >주방</option>
			  </select>
			</td>
	      </tr>
	      <tr height="100px">
	        <td width="200px" align="center" colspan="2" >상품명</td>
	        <td class="item_name" width="300px">
	          <input type="text" name="searchtext" id="item_name_searchtext" width="100px" placeholder="검색어를 입력하세요.">
	           &nbsp; <button type="submit" class="btn btn-secondary">검색</button>
	          <!-- onclick="location.href='/Zspace/search.do'" -->
	        </td>
	      </tr>
	    </table>
       </form><!-- 검색 form -->
	 </div> <p>
	  
	 <div>
	  <div class="post_header">
		<font size="3pt">검색 결과 (상품 수:${pgList.count})</font>
		<!-- <div class="color">
			<a href="#" class="btn btn-default btn-xs" role="button">무료배송</a>
			<a href="#" class="btn btn-default btn-xs" role="button">배송유형</a>
			<a href="#" class="btn btn-default btn-xs" role="button">가격대</a>
		</div> -->
	  </div>
	</div>
	<!-- 게시글 영역 -->
	<!-- <div class="row"> -->
	
	<div margin-top="100px"> <!-- 검색 내용 출력 테이블 -->
		 <!-- <div>
			<a href="#">정확도순</a> | <a href="#">낮은가격순</a> | <a href="#">평점높은순</a>
		  </div>  --> 
	 <p>

     <c:if search="${pgList.count>0}"> 
	  <table class="table">
	    <colgroup>
		  <col width=30%>
		  <col width=50%>
		  <col width=20%>
	    </colgroup>
	    <tr  style="background-color:rgb(217, 217,217)">
		  <th class="text-center">이미지</th>
		  <th class="text-center">상품명</th>
		  <th class="text-center">가 격</th>
		</tr>
		<!-- 여기부터 -->
		<c:set var="number" value="${pgList.number}" />
		 <%
		 List<searchDTO> itemList=(ArrayList<searchDTO>)request.getAttribute("itemList");
		 if(itemList!=null){	
		   List<String> itemNameList = new ArrayList<String>(); //검색 결과에 출력된 상품명 저장 ArrayList
		   for(int i=0;i<itemList.size();i++){ //상품정보 가져오기
			 searchDTO item=itemList.get(i); // 여기까지 상품정보 가져오기
			  if (!itemNameList.contains(item.getItem_name())) { //현재 상품명이 "존재하지 않을 때"
	              itemNameList.add(item.getItem_name()); //itemNameList에 상품명 추가
		 %>
		<tbody height="140px" class="table-hover">
		 <tr class="text-center">
		  <td align="center">
		  <a href="/Zspace/item.do?item_num=<%=item.getItem_num()%>">
		    <img class="itemSerch_imgline" src="project/<%=item.getCategory_depth()%>/<%=item.getItem_img()%>(1).jpg"> 
		  </a></td>
		  <td><a href="/Zspace/item.do?item_num=<%=item.getItem_num()%>"><%=item.getItem_name() %></a></td>
		  <td><%=item.getItem_pay() %>원 </td>
		</tr>
	   </tbody>
		<!-- 여기까지 -->
		
		<%}
			 }
	  } %> 
	  </table>	
     </c:if>     
	</div>
	<%-- 
		 <c:forEach var="item" items="${itemList}">
		<tr class="text-center table-hover" valign="middle">
		  <td>${item.item_img}</td>
				<!-- 위랑 바꾸기
				  <td><a href="#" class="itemSerch_imgline">
				    <img class="itemSerch_img" src=""></a>
			      </td> -->
		  <td align="left">${item.item_name}</td>
			  <!-- 위에 추가하기 <a href="/Zspace/search.do? style="text-style:none"> -->
		  <td>${item.item_pay}</td>
		</tr>
		</c:forEach>--%>
		
    <div class="page"> <!-- 페이징 처리 -->
	  <c:if test="${pgList.startPage>pgList.blockSize}">
        <a href="/Zspace/search.do?pageNum=${pgList.startPage-pgList.blockSize}">[이전]</a>
	  </c:if>
      <c:forEach var="i" begin="${pgList.startPage}" end="${pgList.endPage}">
		<a href="/Zspace/search.do?pageNum=${i}">
         <c:if test="${pgList.currentPage==i}"> <font color="blue"><b>[${i}]</b></font></c:if>
         <%-- <c:if test="${pgList.currentPage!=i}"> [${i}] </c:if>  --%>
        </a>
      </c:forEach>
	  <c:if test="${pgList.endPage<pgList.pageCount}">
        <a href="/Zspace/search.do?pageNum=${pgList.startPage+pgList.blockSize}">[다음]</a>
  	  </c:if>
	</div> <!-- 페이징 처리 -->	 	
  </div><!-- 전체컨테이너 -->

<jsp:include page="footer.jsp" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="Zspace/js/bootstrap.min.js"></script>
</body>
</html>