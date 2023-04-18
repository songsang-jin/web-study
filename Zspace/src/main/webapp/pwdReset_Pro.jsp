<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입처리여부</title>
</head>
<body>
	<c:set var="root" value="${pageContext.request.contextPath }" />
	<c:if test="${check > 0 }">
		<script>
			alert("비밀번호 초기화가 완료되었습니다.");
			 location.href="/Zspace/admin_info.do?admin_id=admin";
		</script>
	</c:if>
	
	<c:if test="${check == 0 }">
		<script>
			alert("비밀번호 초기화가 완료되지 않았습니다.");
			 location.href="/Zspace/admin_info.do?admin_id=admin";
		</script>
	</c:if>
</body>
</html>