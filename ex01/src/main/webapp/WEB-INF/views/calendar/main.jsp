<%@page import="org.zerock.domain.CalendarVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="org.zerock.domain.DayInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>    
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>  
<%
HashMap<String,List<CalendarVO>> daysMap = (HashMap<String,List<CalendarVO>>)request.getAttribute("schedule");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<%@include file="../include/calHeader.jsp"%>   

<title>Insert title here</title>
<script type="text/javascript" src="/resources/js/calendar.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>
 
</style>


</head>
<body>


<h1>달력</h1>

<div align="center">
<h2>
<button class ="btn btn-primary" name="prevCal"> ◀ </button>

 ${year}년 ${month}월 
<button class ="btn btn-primary" name="nextCal"> ▶ </button>

 </h2>
<br>
</div>
<table class="table table-bordered table-hover">
<tr>
<!-- <th>일
<th>월
<th>화
<th>수
<th>목
<th>금
<th>토 -->

<c:forEach items="${calList[0]}" var = "wv">
<th background="blue" width=50 style="word-break:break-all; width: 14%;text-align: center;" class = "show-days show-days-${wv.dayOfWeek} show-weeks table-primary"> 
${wv.dayOfWeek}
</th>
</c:forEach>

<c:forEach items="${calList}" var="weeks">
<tr>
	<c:forEach items="${weeks}" var = "cals">
		
		<td style="background-color: white;" class="schedule">
		<input type="hidden" value="${cals}">
		<c:set var = "showDay" value="${cals}"></c:set>
		<%  
			String showDay = pageContext.getAttribute("showDay").toString();
			boolean flag =false;
			List<CalendarVO> clist=daysMap.get(showDay);
			try{
			for(CalendarVO vo:clist){
				if(vo.getCal_holi()>0){
					flag = true;
					break;
				}
				
			}
			}catch(Exception e){}
			pageContext.setAttribute("holi", flag);
			pageContext.setAttribute("diList", clist);
			%>
		<c:choose>
		<c:when test="${month != cals.monthOfYear}">
		<p style="color: gray" class="show-days show-days-${cals.dayOfWeek}">
		<input type="hidden" value="${cals}">
		${cals.dayOfMonth}
		</p>
		</c:when>
		<c:otherwise>
		
		<p class="show-days show-days-${cals.dayOfWeek}">
		<input type="hidden" value="${cals}">
			
			
			
			
			<%-- ${cals.dayOfMonth} --%>
			
		<c:choose>
			<c:when test="${holi}">
			<font color="red">
			${cals.dayOfMonth}
		   </font>
			</c:when>
			
			<c:otherwise>
			${cals.dayOfMonth}
			</c:otherwise>
		</c:choose>
		   
		   
		</p>
		</c:otherwise>		
		</c:choose>
		<c:forEach items="${diList}" var ="dis" varStatus="loop">
				<a href='/calendar/get?cno=${dis.cno}'>${dis.cal_title}</a>${!loop.last ? ', ' : ''}
			</c:forEach>
	
	</c:forEach>	
	<tr>
	
</c:forEach>
</table>


</body>
<script>

$(document).ready(
		
		function () {
			var nowpage = "/calendar/main";
			$("th[class^=show-days]").each(function(i, element) {
				$(this).text(["월","화","수","목","금","토","일"][element.textContent-1]);
			})
			
			$(".show-days-7").css("color", "red");
			
			
			$("button[name='nextCal']").click(function() {
				var mon = "${month}";
				mon = (mon>9?'':'0')+mon; 
				var ym = "${year}"+mon;
	
				if("${month}"==12){
					var nyear = "${year}";
					nyear *=1; nyear+=1;
					ym = nyear+"01";
				}else{
					ym*=1; ym+=1;ym+="";
				}
				location.href=nowpage+"?ym="+ym+"&f=${param.f}";
				
			})
			
			$("p[class^=show-days]").click(function() {
				var date = ($(this).find("input[type='hidden']")).val();
				location.href = "register?date="+date;
			})
			
			$("button[name='prevCal']").click(function() {
				var mon = "${month}";
				mon = (mon>9?'':'0')+mon; 
				var ym = "${year}"+mon;
				if("${month}"==1){
					var nyear = "${year}";
					nyear *=1; nyear-=1;
					ym = nyear+"12";
				}else{
					ym*=1; ym-=1;ym+="";
				}
				location.href=nowpage+"?ym="+ym+"&f=${param.f}";
				
			})
			/* $('td[class^=show-days]').click(function() {
				
				alert(dd)
			}) */
		});
</script>


  <%@include file="/WEB-INF/views/include/footer.jsp"%>
</html>