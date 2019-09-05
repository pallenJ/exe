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
//var url = "/records/record";
//var url = "/records/csvRecord";
var url = "/records/cusRecord";
$(location).attr("href", url);
</script>
<h1>Home</h1>
</body>
</html>