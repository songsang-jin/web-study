<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">
<link href="/Zspace/css/asj.css" rel="stylesheet">

   
<title>::로그인::</title>
<style>
#log_id input{
	background-image:url("./icon/id.png");
	background-position:1px center;
	background-size:16px;
	background-repeat:no-repeat;
}     
#log_pwd input{
	background-image:url("./icon/pwd.png");
	background-position: 1px center;
	background-size:16px;
	background-repeat:no-repeat
}
</style>
</head>
<body>
<!-- 사이드바영역 03.12  -->
<jsp:include page="sideBar.jsp" flush="false" />
<!--  -->
        
 <!-- 메인 -->      
 <div id="contents">  <!-- 전체 레이아웃을 감싸기 -->
  <div class="main"> 
    <form method="post" id="logfm" action="/Zspace/login.do">      
  	  <table id="login_tb" align="center"> 
       <tr>
	        <td colspan="2" align="center" height="180px">
	         <img src="/Zspace/icon/login.png" width="70px" vspace="10"><p>
	         <font size="4"><b>로그인</b></font>
        </td>
        <tr>
       		<td style="padding-left:21%"  width="40%" height="20px" >
       			<input type="radio" name="selecet" value="admin_login">관리자
       		</td>
       		<td width="60%">
       			<input type="radio" name="selecet"  value="mem_login">고객
       		</td>
       </tr>  
       <tr id="log_id"> <!-- 아이디 -->
	        <td style="height:35px; width:300px; padding-left:20%" align="right">
	         	<input type="text" id="mem_id" name="mem_id" placeholder="아이디">
        	</td>
	        <td rowspan="2" style="width:200px; padding-left:20%"  align="left">
	         	<button id="logbtn" type="submit">로그인</button>
	        </td>
       </tr>
       <tr id="log_pwd"> <!-- 비밀번호 -->
	        <td  style="height:35px; width:300px; padding-left:20%" align="right">
	         	<input type="password" id="mem_pwd" name="mem_pwd" placeholder="비밀번호">
	        </td>
       </tr>
       
       <tr id="find" >
	        <td colspan="2" align="center" height="50px">
	         <a id="login_a" href="id_find.jsp">아이디 찾기</a>&nbsp;&nbsp;|&nbsp;
	         <a id="login_a" href="pwd_find.jsp">비밀번호 찾기</a>&nbsp;&nbsp;|&nbsp;
	         <a id="login_a" href="mem_join.jsp">회원가입 </a>
	        <td>
       </tr>
   	  </table>
     </form>
     </div><!-- 메인 -->
     
     </div><!-- contents -->
   	<!-- footer -->
	<jsp:include page="footer.jsp" flush="false" />
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
   <script src="./js/bootstrap.min.js"></script>

</body>
</html>