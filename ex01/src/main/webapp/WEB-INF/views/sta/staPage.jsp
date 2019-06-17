
<%@page import="org.zerock.domain.TableDTO"%>
<%@page import="org.zerock.service.sta.StaServiceImpl"%>
<%@page import="org.zerock.service.sta.StaService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@include file="../include/header.jsp"%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row" align="left">
		<div class="col-lg-8">
			<div class="panel panel-default">
				<div class="panel-heading input-group custom-search-form">
					select table <select class="form-check" name="tbl-select">
						<option value="tbl_board_10">10</option>
						<option value="tbl_board_50">50</option>
						<option value="tbl_board_100">100</option>
					</select> select option <select class="form-check" name="option-select">
					
						<option value="<%=TableDTO.YMD%>">Full Date</option>
						<option value="<%=TableDTO.H%>">Hour</option>
						<option value="<%=TableDTO.A%>">Week</option>

						<option value="<%=TableDTO.U_l%>">Week from Sun</option>
						<option value="<%=TableDTO.U_s%>">Week from Mon</option>
						<option value="<%=TableDTO.D_H%>">DH</option>

						<option value="<%=TableDTO.W%>">W</option>
						<option value="10">Fulldate, Hour</option>
						<option value="100">With Writer</option>

					</select>

					<button class="btn btn-default" name="sta-btn">Go!</button>


					<%-- 
				<span style="align-content: center;">
				<button class="btn btn-default" name="sta-btn" value="<%=TableDTO.YMD %>">Full Date</button>
				<button class="btn btn-default" name="sta-btn" value="<%=TableDTO.H %>">Hour</button>
				<button class="btn btn-default" name="sta-btn" value="<%=TableDTO.D_H %>">Day - Hour</button>
				<button class="btn btn-default" name="sta-btn" value="<%=TableDTO.A %>">A</button>
				<button class="btn btn-default" name="sta-btn" value="<%=TableDTO.U_l %>">Week1</button>
				<button class="btn btn-default" name="sta-btn" value="<%=TableDTO.U_s %>">Week2</button>
				<button class="btn btn-default" name="sta-btn" value="<%=TableDTO.W %>">W</button>
				<button class="btn btn-default" name="sta-btn" value="10">Fulldate, Hour</button>
				<button class="btn btn-default" name="sta-btn" value="100">With Writer</button>
				</span> --%>

				</div>
				<div class="panel-body">
				<h2>${param.tableName}</h2>
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<tr>
								<c:forEach items="${datas[0]}" var="ths">
									<th>${ths.key}
								</c:forEach>

								<c:forEach items="${datas}" var="list">
									<tr>
										<c:forEach items="${list}" var="show">
											<td>${show.value}
										</c:forEach>
								</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<form id="actionForm">
	<input type="hidden" name="tableName" value="tbl_board_10"> <input
		type="hidden" name="ftNum" value="0">


</form>



</html>
<script type="text/javascript">
	$(document).ready(
			function() {

				$("button[name=sta-btn]").click(
						function() {
							//$("input[name=ftNum]").val(this.value);
							$("input[name=tableName]").val(
									$("select[name=tbl-select]").val());
							$("input[name=ftNum]").val(
									$("select[name=option-select]").val());
							$("#actionForm").submit();

						});
				var tblName = "${param.tableName}";
				var opNum = "${param.ftNum}"
				if (tblName != null && tblName.length != 0) {
					$("select[name=tbl-select]").val(tblName).attr("selected",
							"selected");
					$("select[name=option-select]").val(opNum).attr("selected",
							"selected");
				}

			});
</script>

<%@include file="../include/footer.jsp"%>