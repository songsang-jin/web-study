<%@page contentType="text/html;charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>   
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="join.js?ver=1"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/asj.css" rel="stylesheet">
<link href="/Zspace/css/silde.css" rel="stylesheet">
     
<title>::회원정보 관리::</title>
<script>
$(function(){
  $('#email_sel').change(function(){
	$("#email_sel option:selected").each(function(){
	  if($(this).val()=='1'){
		 $('#email2').val('');
	     $('#email2').attr("disabled",false); //#email2 활성화
	  }else{
		 $('#email2').val($(this).text()); //선택값 입력
		 $('#email2').attr("disabled",true); //#email2 비활성화
	  }
	})
  })
})
</script>
<style>
table td{
  padding-left:10px
}
</style>
</head>
<body>

 <div id="contents"> <!-- 사이드 제외 전체구역 -->
  <div class="main">
    <div class="top">
      <img src="./icon/mem_join.png" id="admin_info_img" width="50px">
      <div id="admin_info"><font size="4"><b>회원정보 관리</b></font><br><p></div>
        <hr>
    </div>
    
    <div class="middle">
     <table id="admin_info_tb" align="center" style="padding-left:10px" border="2px solid gray">
      <tr>
        <td width="120px">이름</td>
        <td><input type="text" name="mem_name" disabled value="${article.mem_name}"></td>
      </tr>
      <tr>
        <td width="120px">아이디</td>
        <td><input type="text" name="mem_id" disabled value="${article.mem_id }"></td>
      </tr>
      <tr>
        <td width="120px">비밀번호</td>
        <!-- 초기화 버튼 클릭 시 자동으로 임의의 비밀번호 부여 -->
        <td><input type="password" name="mem_pwd" disabled value="${article.mem_pwd }">
  	        <a href="#"><button id="pwd_reset">초기화</button></a>
        </td>
      </tr>
      <tr id="addr">
        <td width="120px">주소</td>
        <td><input type="text" name="mem_zipcode">
            <button id="zipbtn">우편번호 검색</button><p><p>
            <input type="text" name="mem_addr1" value="${article.mem_addr1 }"> &nbsp;
            <input type="text" name="mem_addr2" width="100px" placeholder="추가 주소를 입력하세요" value="${article.mem_addr2 }">
        </td>
      </tr>
      <tr>
        <td width="120px">핸드폰 번호</td>
        <td><input type="text" name="mem_phone" value="${article.mem_phone }"></td>
      </tr>
      <tr> 
        <td width="120px">이메일</td>
        <td><input type="text" name="mem_email" value="${article.mem_email }">
        </td>
      </tr>
     </table>
     <div>  
       <button class="admin_info_close"><a href="admin_info.do?mem_id=${article.mem_id}">취소</a></button>
       <button class="admin_delete_mem"><a href="admin_infoDel.do?mem_id=${article.mem_id}&mem_pwd=${article.mem_pwd}">탈퇴 처리하기</a></button>
     </div>
    </div><!-- middle -->
  </div><!-- main -->

 </div>	 <!-- content -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="/Zspace/js/bootstrap.min.js"></script>

</body>
</html>