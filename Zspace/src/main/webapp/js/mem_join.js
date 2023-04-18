/**
 * 작성자: 송상진
 *	일	자 : 2022.03.22
 	기	능 :회원가입 유효성 검사
 */

function checkAll(){

	
	if(joinform.mem_name.value == ""){
		alert("이름을 반드시 입력하세요.");
		joinform.mem_name.focus();
		return false;
	}
	
	if(joinform.mem_id.value==""){
		alert("아이디를 반드시 입력하세요.");
		joinform.mem_id.focus();
		return false;
	}
	
	if(joinform.mem_pwd.value==""){
		alert("비밀번호를 반드시 입력하세요.");
		joinform.mem_pwd.focus();
		return false;
	}
	
	if(joinform.mem_pwd.value.length < 7){
		alert("비밀번호는 7글자 이상으로 입력하세요.");
		joinform.mem_pwd.focus();
		return false;
	}
	
	if(joinform.mem_pwd2.value==""){
		alert("비밀번호 확인란에  반드시 입력하세요.");
		joinform.mem_pwd2.focus();
		return false;
	}
	
	if(joinform.mem_pwd.value != joinform.mem_pwd2.value){
		alert("비밀번호가 일치하지 않습니다.");
		joinform.mem_pwd2.focus();
		return false;
	}
	
	if(joinform.mem_zipcode.value == ""){
		alert("우편번호를 입력하세요.");
		joinform.mem_zipcode.focus();
		return false;
	}
	
	if(joinform.mem_addr1.value == ""){
		alert("주소 입력하세요.");
		joinform.mem_addr1.focus();
		return false;
	}
	
	if(joinform.mem_addr2.value == ""){
		alert("주소 입력하세요.");
		joinform.mem_addr2.focus();
		return false;
	}
	
	if(joinform.mem_email.value == ""){
		alert("이메일을 입력하세요.");
		joinform.mem_email.focus();
		return false;
	}
	
	document.joinform.submit();
}

function idCheck(joinform, root){
	alert(joinform.mem_id.value);
	
	if(joinform.mem_id.value == ""){
		alert("아이디를 반드시 입력하세요");
		joinform.mem_id.focus();
		return false;
	}else{
		var url=root+"/idCheck.do?mem_id="+joinform.mem_id.value;
		window.open(url,"","width=400, height=200");
	}
}

function searchZipcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("mem_addr1").value = extraAddr;
                
                } else {
                    document.getElementById("mem_addr2").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('mem_zipcode').value = data.zonecode;
                document.getElementById("mem_addr1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("mem_addr2").focus();
                document.getElementById("mem_addr2").value = extraAddr;
            }
        }).open();
    }

function sendAddress (zipcode, sido, gugun, dong, ri, bunji){
	var address = sido + gugun + dong + ri + bunji;
	window.close();
	
	opener.joinform.zipcode.value=zipcode;
	opener.joinform.address.valu=address;
}