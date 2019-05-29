<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> sample/admin Page</h1>
<p>principal: <sec:authentication property="principal"/> </p>
<p>memberVO: <sec:authentication property="principal.member"/> </p>
<p>username: <sec:authentication property="principal.member.userName"/> </p>
<p>userid: <sec:authentication property="principal.username"/> </p>
<p>author list: <sec:authentication property="principal.member.authList"/> </p>
<%-- 
 --%>
<a href="/customLogout">로그아웃</a>
</body>
</html>