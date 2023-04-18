<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
<head> 
 <meta charset="UTF-8">
<script type="text/javascript" src="/Zspace/js/asj.js?ver=1"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<link href="/Zspace/css/bootstrap.min.css" rel="stylesheet">
<link href="/Zspace/css/asj.css" rel="stylesheet">
<link href="/Zspace/css/tool.css" rel="stylesheet">  


</head>
<body>

 <jsp:include page="sideBar.jsp" />
  
  
 <div id="contents"> <!-- 사이드 제외 전체구역 -->
  <div class="main">  
   <div class="top">
     <img src="/Zspace/icon/find_id.png" id="id_find_img" width="50px"> 
     <div id="id_find"><font size="4"><b>아이디 찾기</b></font><br>
            아이디를 찾기 위한 방법을 선택해 주세요. <p>
     </div>
    <hr>
   </div>
      
   <div class="middle" align="center">
     <form method="post" id="idfm" action="#">
       <table id="findtb">
         <tr align="left" height="40">
           <td colspan="3" id="find_phone_td" valign="middle">
             <input type="radio" name="find"> <b>전화번호로 찾기</b>
           </td>
         </tr>
         <tr height="20">
           <td id="id1">이름</td>
           <td width="160"><input type="text" name="mem_name" size="21px"></td>
           <td width="100"><button id="phbtn">인증하기</button></td>
         </tr>
         <tr height="40">
           <td id="id1">휴대전화</td>
           <td colspan="2" width="160"><input type="text" name="mem_phone" size="21px"></td>
         </tr>
       </table>
       
       <!-- 이메일로 찾기-->
       <table id="findtb2">
         <tr align="left" height="40">
           <td colspan="3" id="find_email_td" valign="middle">
             <input type="radio" name="find"> <b>이메일로 찾기</b>
           </td>
         </tr>
         <tr height="20">
           <td id="id1">이름</td>
           <td width="160"><input type="text" name="mem_name" size="21px"></td>
           <td width="100"><button id="embtn">인증하기</button></td>
         </tr>
         <tr height="40">
           <td id="id1">이메일</td>
           <td colspan="2">
             <input type="text" name="mem_email" id="mem_email"size="13"> @
             <input type="text" name="mem_email2" id="email2" size="13">
             <select name="mem_email_sel" id="email_sel">
               <option value="" selected>선택하세요</option>
               <option value="1">직접 입력</option>
               <option value="daum.net">daum.net</option>
               <option value="google.com">google.com</option>
               <option value="naver.com">naver.com</option>
               <option value="nate.com">nate.com</option>
               <option value="empas.com">empas.com</option>
             </select>
           </td>
         </tr>
       </table>
     </form>
   </div><!-- middle -->
  </div> <!-- 메인 -->
 </div><!-- content -->   

 <jsp:include page="footer.jsp" />

   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
   <script src="/Zspace/js/bootstrap.min.js"></script>

</body>
</html>