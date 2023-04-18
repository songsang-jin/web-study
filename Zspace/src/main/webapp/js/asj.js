
//회원가입 버튼 클릭 시 
function joinpop(){
	var url="join.html"
	var name=""
	var option="width=400 height=250 top=70 left=500"
	window.open(url, name, option)	
}

function close(){
   window.open.close();
    window.location.href = "login.jsp";
}
 
//회원정보 수정
function update(){    
	alert ('회원정보 수정이 완료되었습니다.');
	 window.location.href = "login.jsp";
}     
   
//회원 탈퇴하기 클릭 시
function delpop(){
	var url="delcheck.html"
	var name=""
	var option="width=500 height=400 top=70 left=500"
	window.open(url, name, option);
	windo.opener.close();
}  

//비밀번호 입력 후 
function memdel(){
	alert ('회원 탈퇴가 완료되었습니다.');
	 window.location.href = "login.jsp";
}
  
//관리자가 회원 탈퇴하기 클릭 시
function anmin_delpop(){
	var url="admin_delcheck.html"
	var name=""
	var option="width=500 height=200 top=70 left=500"
	window.open(url, name, option);
	windo.opener.close();
}

function comment(){
	//alert();

	document.add_form.submit();

 } 

$(function(){
  $("#name_error").hide();
  $("#addr_error").hide();
  $("#addr2_error").hide();
  $("#phone_error").hide();

  $('#pay_btn').click(function(){
	//1. 이름 체크
	var name=$("#mem_name").val();
	 if(name.length == null || name.length ==""){
		$('#name_error').show();
		return false;
	 }else{
		$('#name_error').hide();
	}
	//2. 주소1 체크
	var addr=$("#mem_addr1").val(); 
	 if(addr.length ==null || addr.length ==""){
		$('#addr_error').show();
		return false;
	 }else{
		$('#addr_error').hide();
	}
	//3. 주소2 체크
	var addr2=$("#mem_addr2").val();
	 if(addr2.length ==null || addr2.length ==""){
		$('#addr2_error').show();
		return false;
	 }else{
		$('#addr2_error').hide();
	}
	//4. 연락처 체크
	var phone=$("#mem_phone").val();
	 if(phone.length ==null || phone.length ==""){
		$('#phone_error').show();
		return false;
	 }else{
		$('#phone_error').hide();
	}
	//5. 결제 방법 선택
	var payment = $("input[type=radio][name=pay_meth]:checked").val();
	  if(payment==null){
		alert("결제방법을 체크해주세요");
		return false;
	  }
	  alert("결제가 완료되었습니다.");	
	});	
  });

