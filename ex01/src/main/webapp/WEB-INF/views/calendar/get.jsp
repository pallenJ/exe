<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>    
<!DOCTYPE html>
<html>
<head>

<%@include file="../include/calHeader.jsp"%> 
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
  <span class="navbar-brand">Scehdule Read Page</span>

  <div class="collapse navbar-collapse" id="navbarColor01" align="right">
    
      		<button id = "regBtn" type="button" class="btn btn-outline-warning form-inline my-2 my-lg-0">Register New Schedule</button>
  </div>
</nav>
			<!-- 
			<div class="panel-heading">Scehdule Read Page
				<button id = "regBtn" type="button" class="btn btn-primary">Register New Board</button>
			</div>
			 -->
			
<div class="panel-body">
				<div class="form-group" id="show-cno">
				<label>Cno</label>
				<input class = "form-control" name="cno" value='<c:out value="${schedule.cno}"></c:out>' readonly="readonly">
				</div>
				
				<div class="form-group" id= "show-userid">
				<label>ID</label>
				<input class = "form-control" name="userid" value='<c:out value="${schedule.userid}"></c:out>' readonly="readonly">
				</div>

				<div class="form-group" id= "show-userauth">
				<label>ID</label>
				<input class = "form-control" name="userauth" value='<c:out value="${schedule.userauth}"></c:out>' readonly="readonly">
				</div>
				
				<div class="form-group">
				<label>date</label>
				<input class = "form-control" type="date" name="cal_date" value='<c:out value="${schedule.cal_date}"></c:out>' readonly="readonly">
				</div>
				
				<div class="form-group">
				<label>Title</label>
				<input class = "form-control" name="cal_title" value='<c:out value="${schedule.cal_title}"></c:out>' readonly="readonly">
				</div>
				<div class="form-group">
				
				<label>Content</label>
				<textarea class = "form-control" name="cal_content" rows="5" readonly="readonly">
				${schedule.cal_content}
				</textarea>
				</div>
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
				<button data-oper="modify" class="btn btn-outline-info" id="modify-btn">Modify</button>
								
				<button data-oper="list" class="btn btn-info">List</button>				
				
			</div>
</div></div></div></fieldset>


</body>
<script type="text/javascript">
	$(document).ready(
		function() {
		var userid = "${userid}";
		var userauth = "${userauth}"
		var writerId = "${schedule.userid}"
		
		$("#modify-btn").click(function() {
			location.href = 'modify?cno=${schedule.cno}';
		})	
		
		if(userauth!="ROLE_ADMIN"){
		$("#show-cno").hide();
		$("#show-userid").hide();
		$("#show-userauth").hide();
			if(writerId!=userid){
				$("#modify-btn").hide();
			}
		}
		
		
		
		});

</script>
  <%@include file="/WEB-INF/views/include/footer.jsp"%>
</html>


