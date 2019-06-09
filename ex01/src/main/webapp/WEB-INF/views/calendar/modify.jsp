<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://bootswatch.com/4/journal/bootstrap.css">
<link rel="stylesheet"
	href="https://bootswatch.com/4/journal/bootstrap.min.css">
<link rel="stylesheet"
	href="https://bootswatch.com/4/journal/_variables.scss">
<link rel="stylesheet"
	href="https://bootswatch.com/4/journal/_bootswatch.scss">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>	
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1></h1>
<fieldset>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default form-group col-lg-4">
			<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <span class="navbar-brand">Scehdule edit Page</span>

  <div class="collapse navbar-collapse" id="navbarColor01" align="right">
    
      		<!-- <button id = "regBtn" type="button" class="btn btn-outline-warning form-inline my-2 my-lg-0">Register New Schedule</button> -->
  </div>
</nav>
			<!-- 
			<div class="panel-heading">Scehdule Read Page
				<button id = "regBtn" type="button" class="btn btn-primary">Register New Board</button>
			</div>
			 -->
			
<div class="panel-body">
				<form method="post" action="/calendar/modify" name="modify" accept-charset="utf-8">
				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<div class="form-group">
				<label>Cno</label>
				<input class = "form-control" name="cno" value='<c:out value="${cal.cno}"></c:out>' readonly="readonly">
				</div>
				
				<div class="form-group">
				<label>date</label>
				<input class = "form-control" name="cal_date" type="date" id="date" required="required" value="${cal.cal_date}" readonly="readonly">
				</div>
				
				<div class="form-group">
				<label>Title</label>
				<input class = "form-control" name="cal_title" id="title" value="${cal.cal_title}">
				</div>
				<div class="form-group">
				
				<label>Content</label>
				<textarea class = "form-control" name="cal_content" id="content" rows="5">
				${cal.cal_content}
				</textarea>
				
				<div class="form-group">
				<input type="checkbox" name="holi" id="holi">
				<label id="holi-label">	holiday</label>
				</div>
				<div class="form-group">
				
				</div>
				</div>
				<button type="button" data-oper="modify" id="modify" class="btn btn-outline-info">Modify</button>
				<button type="button" data-oper="remove" id="remove" class="btn btn-primary">Remove</button>
				<button type="button" data-oper="calcel" class="btn btn-info">Cancel</button>
				<div id="other_param"></div>
				</form>
				<form action="/calendar/remove" method="post" id="remove-action">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="hidden" value="${cal.cno}" name="cno" id="adf">
				</form>
				<%-- 
				<div class="form-group">
				<label>Writer</label>
				<input class = "form-control" name="writer" value='<c:out value=""></c:out>' readonly="readonly">
				</div> --%>
				
				<%-- <sec:authentication property="principal" var="pinfo"/> --%>
				<%-- <sec:authorize access="isAuthenticated()">
				<c:if test="${pinfo.username eq board.writer}">
				<button data-oper="modify" class="btn btn-default">Modify</button>
				</c:if>
				</sec:authorize>
				 --%>
				
			</div>
</div></div></div></fieldset>


<script type="text/javascript"  charset="utf-8">

$(document).ready(function() {
	var holi = "${cal.cal_holi}";
	$("#holi").prop('checked',holi>0);
	$("#modify").click(function() {
		var cal_date = $("#date").val();
		var dateArr = cal_date.split("-");
		
		var cal_year = dateArr[0];
		var cal_month = dateArr[1];
		var cal_day  = dateArr[2];
		var cal_holi  = $("#holi").is(":checked")?1:0;
		
		var str = "";
		
		str +="<input type='hidden' name = 'cal_year' value='"+cal_year+"'>";
		str +="<input type='hidden' name = 'cal_month' value='"+cal_month+"'>";
		str +="<input type='hidden' name = 'cal_day' value='"+cal_day+"'>";
		str +="<input type='hidden' name = 'cal_holi' value='"+cal_holi+"'>";
		$("#other_param").html(str);
		/* var cal_date = $("#date").val();
		var cal_title = $("#title").val();
		var cal_holi  = $("#holi").is(":checked")?1:0;
		var cal_content = $("#content") */
		$("form[name='modify']").submit();
	});
	
	$("#remove").click(function() {
		alert($("#adf").val());
		$("#remove-action").submit();
	})
	
	$("#holi-label").click(function() {
		$("#holi").click();
	})
})

</script>

</body>
</html>