<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="./include/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <jsp:forward page="/records/record"></jsp:forward> --%>
<script type="text/javascript">

$(location).attr("href", "/records/record");
</script>
<h1>Home</h1>
</body>
</html>