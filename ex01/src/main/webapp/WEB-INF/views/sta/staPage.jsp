
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
	<h2 id="table-title">${param.tableName}</h2>

	<div class="row" align="left">
		<div class="col-md-12" id="panel-width">
			<div class="panel panel-default">
				<div class="panel-heading input-group form-inline"
					style="width: 100%">
					<div class="form-group">
						<label>table</label> <select class="form-check form-control"
							name="tbl-select" style="width: 100%">

							<option value="tbl_board_10">10</option>
							<option value="tbl_board_50">50</option>
							<option value="tbl_board_100">100</option>
						</select>
					</div>
					&nbsp;
					<div class="form-group">
						<label>option</label> <select class="form-check form-control"
							name="option-select" style="width: 100%">

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
					</div>
					&nbsp;
					<div class="form-group">
						<label>&nbsp;</label>
						<button class="btn btn-default form-control" name="sta-btn">Go!</button>
					</div>


				</div>

				<div class="panel-body" style="width: auto;">

					<div class="col-lg-12">
						<div class="panel panel-default">

							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<div class="form-group" align="right">
										<button type="button" class="btn btn-primary form-check"
											name="poi-btn">excel download</button>
									</div>
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">


										<thead>
											<tr>
												<c:forEach items="${titles}" var="ths">
													<th>${ths}
												</c:forEach>
											</tr>
										</thead>
										<tbody>


											<c:forEach items="${datas}" var="list">
												<tr>
													<c:forEach items="${titles}" var="keyV">
														<td>${list[keyV]}
													</c:forEach>
											</c:forEach>
										</tbody>
									</table>


								</div>
								<!-- /.table-responsive -->

							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>

				</div>



			</div>
		</div>
	</div>
	<form id="actionForm">
		<input type="hidden" name="tableName" value="tbl_board_10"> <input
			type="hidden" name="ftNum" value="0">


	</form>
	<script src="/resources/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="/resources/js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					$("#dataTables-example").dataTable({
						responsive : true
					});
					$("#datas-table").dataTable({
						responsive : true

					});
					var tableTitle = "${param.tableName}";
					//alert($("h2[name='table-title']").val());
					if (tableTitle == null || tableTitle.length == 0) {
						$("#table-title").val("tbl_board_10")
					}

					$("button[name=sta-btn]")
							.click(
									function() {
										//$("input[name=ftNum]").val(this.value);
										$("input[name=tableName]").val(
												$("select[name=tbl-select]")
														.val());
										$("input[name=ftNum]").val(
												$("select[name=option-select]")
														.val());
										$("#actionForm").attr("action",
												"/sta/staPage");
										$("#actionForm").submit();

									});

					$("button[name=poi-btn]")
							.click(
									function() {
										//$("input[name=ftNum]").val(this.value);
										$("input[name=tableName]").val(
												$("select[name=tbl-select]")
														.val());
										$("input[name=ftNum]").val(
												$("select[name=option-select]")
														.val());
										$("#actionForm").attr("action",
												"/sta/poiTest");
										$("#actionForm").submit();

									});

					var tblName = "${param.tableName}";
					var opNum = "${param.ftNum}"
					/* if(opNum == 10){
						$("#panel-width").removeClass();
					}else{
						$("#panel-width").addClass("col-md-5");
					}
					 */
					if (tblName != null && tblName.length != 0) {
						$("select[name=tbl-select]").val(tblName).attr(
								"selected", "selected");
						$("select[name=option-select]").val(opNum).attr(
								"selected", "selected");
					}

				});
	</script>


	<!-- /.row -->
	<div class="row">

		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->

	<!-- /.col-lg-6 -->

	<!-- /.row -->



</body>



</html>

<%@include file="../include/footer.jsp"%>