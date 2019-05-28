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
<h1> Denied Page </h1>

<h2> <c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage()}"></c:out> </h2>
<h2> <c:out value="${msg}"></c:out> </h2>

</body>
</html>