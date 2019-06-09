<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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

						<div class="collapse navbar-collapse" id="navbarColor01"
							align="right">

							<!-- <button id = "regBtn" type="button" class="btn btn-outline-warning form-inline my-2 my-lg-0">Register New Schedule</button> -->
						</div>
					</nav>
					<!-- 
			<div class="panel-heading">Scehdule Read Page
				<button id = "regBtn" type="button" class="btn btn-primary">Register New Board</button>
			</div>
			 -->

					<div class="panel-body">
						<form method="post" action="/calendar/register" name="register"
							accept-charset="utf-8">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<div class="form-group">
								<label>date</label> <input class="form-control" name="cal_date"
									type="date" id="date" required="required" value="${date}">
							</div>

							<div class="form-group">
								<label>Title</label> <input class="form-control"
									name="cal_title" id="title">
							</div>
							<div class="form-group">

								<label>Content</label>
								<textarea class="form-control" name="cal_content" id="content"
									rows="5">
				
				</textarea>

								<div class="form-group">
									<input type="checkbox" name="holi" id="holi"> <label
										id="holi-label"> holiday</label>
								</div>
								<div class="form-group"></div>
							</div>
							<button type="button" data-oper="modify" id="register"
								class="btn btn-primary">register</button>
							<button type="button" data-oper="modify"
								class="btn btn-outline-primary">cancel</button>
							<div id="other_param"></div>
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
				</div>
			</div>
		</div>
	</fieldset>


	<script type="text/javascript" charset="utf-8">
		$(document)
				.ready(
						function() {
							$("#register")
									.click(
											function() {
												var cal_date = $("#date").val();
												var dateArr = cal_date
														.split("-");

												var cal_year = dateArr[0];
												var cal_month = dateArr[1];
												var cal_day = dateArr[2];
												var cal_holi = $("#holi").is(
														":checked") ? 1 : 0;

												var str = "";

												alert($("#title").val())

												str += "<input type='hidden' name = 'cal_year' value='"+cal_year+"'>";
												str += "<input type='hidden' name = 'cal_month' value='"+cal_month+"'>";
												str += "<input type='hidden' name = 'cal_day' value='"+cal_day+"'>";
												str += "<input type='hidden' name = 'cal_holi' value='"+cal_holi+"'>";
												$("#other_param").html(str);
												/* var cal_date = $("#date").val();
												var cal_title = $("#title").val();
												var cal_holi  = $("#holi").is(":checked")?1:0;
												var cal_content = $("#content") */
												$("form[name='register']")
														.submit();
											});
							$("#holi-label").click(function() {
								$("#holi").click();
							})
						})
	</script>

</body>
</html>