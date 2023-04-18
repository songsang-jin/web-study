<%@page contentType="text/html;charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html> 
<head> 
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/asj.css" rel="stylesheet">
   
<title>::회원정보 수정::</title>

<style>
 table td{
  padding-left:10px
}  
</style>
</head>
<body>
<jsp:include page="sideBar.jsp" flush="false" />

 <div id="contents"> <!-- 사이드 제외 전체구역 -->
  <div class="main">
    <div class="top">
      <img src="./icon/mem_join.png" id="mem_info_img" width="50px">
    <font size="4"><b>내정보보기</b></font><br><p>
        <hr>
    </div>
    
    
    
    <div class="middle">
    <form action="/Zspace/mem_updatePro.do?mem_id=${article.mem_id}" method="post">
     <table id="mem_info_tb" align="center" border="2px solid gray">
      <tr>
        <td width="120px">이름</td>
        <td><input type="text" name="mem_name" disabled value="${article.mem_name}"></td>
      </tr>
      <tr>
        <td width="120px">아이디</td>
        <td><input type="text" name="mem_id" disabled value="${article.mem_id}"></td>
      </tr>
      <tr>
        <td width="120px">비밀번호</td>
        <!-- 초기화 버튼 클릭 시 자동으로 임의의 비밀번호 부여 -->
        <td><input type="password" name="mem_pwd" value="${article.mem_pwd }">
  	        <button id="pwd_reset"><a href="/Zspace/mem_resetPro.do?mem_id=${article.mem_id}">비밀번호 초기화</a></button>
        </td>
      </tr>
      <tr id="addr">
        <td width="120px">주소</td>
        <td><input type="text" id="mem_zipcode" name="mem_zipcode" value="${article.mem_zipcode }">
            <input type="button" onclick="searchZipcode()"  value="우편번호검색"><p></p>
            <input type="text" id="mem_addr1" name="mem_addr1" value="${article.mem_addr1 }"> &nbsp;
            <input type="text" id="mem_addr2" name="mem_addr2" width="100px" placeholder="추가 주소를 입력하세요" value="${article.mem_addr2 }">
        </td>
      </tr>
      <tr>
        <td width="120px">핸드폰 번호</td>
        <td><input type="text" name="mem_phone" value="${article.mem_phone }"></td>
      </tr>
      <tr>
        <td width="120px">이메일</td>
        <td><input type="text" name="mem_email" value="${article.mem_email}"></td>
      </tr>
     </table>
       <button type="submit" id="update_btn">수정하기</button>
    
       <button id="mem_info_close_btn" onclick="location.href='/Zspace/mypage.jsp'">취소</button>
       <script type="text/javascript" src="/Zspace/js/mem_join.js"></script>
       <button id="delete_mem_btn" onclick="location.href='/Zspace/mem_delPro.do?mem_id=${article.mem_id}'">회원 탈퇴하기</button>

      <!-- <button id="delete_mem_btn"><a href="/Zspace/mem_delPro.do?mem_id=${article.mem_id}">회원 탈퇴하기</a></button>-->
   </form>
    </div><!-- middle -->
  </div><!-- main -->
 </div>	 <!-- content -->
  <jsp:include page="footer.jsp"  />

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="/Zspace/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="/Zspace/js/mem_join.js"></script>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</body>
</html>