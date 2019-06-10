<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>    
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>  
    
<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/views/include/header.jsp"%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<iframe src="https://calendar.google.com/calendar/embed?src=bizspring.co.kr_jqlfoiq74f2dp75qcobm2uonl0%40group.calendar.google.com&ctz=Asia%2FSeoul" style="border: 0" width="800" height="600" frameborder="0" scrolling="no">
</iframe>
</div>
 
</body>
<%@include file="/WEB-INF/views/include/footer.jsp"%>
</html>