<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<h1> Custom Login Page </h1>

<h2> <c:out value="${error}"></c:out> </h2>
<h2> <c:out value="${logout}"></c:out> </h2>
<form method="post" action="/login">

<div>
	<input type="text" name="username" placeholder="아이디를 입력하세요">
</div>

<div>
	<input type="password" name="password" placeholder="비밀번호를 입력하세요">
</div>

<div>
<input type="submit">
</div>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

</form>

</body>
</html>