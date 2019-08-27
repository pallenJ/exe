<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Record</title>
</head>
<body>

	<div class="table table-bordered table-hover" align="center">
		<h1>Records</h1>
		<table id="records" style="text-align: center;" class="col-sm-6">
			<tr>
				<th>이름
				<th>국어
				<th>영어
				<th>수학
				<th>총합
				<th>평균 <%-- <tr>
			<td> ${rData['korean']['정형돈']}
			 --%> <c:forEach items="${stdList}" var="stdName">
						<tr>
							<th>${stdName}
							<td class="korean-rec"><fmt:formatNumber
									value="${rData['korean'][stdName]}" pattern="" />
							<td class="english-rec"><fmt:formatNumber
									value="${rData['english'][stdName]}" pattern="" />
							<td class="math-rec"><fmt:formatNumber
									value="${rData['math'][stdName]}" pattern="" />
							<td><fmt:formatNumber value="${rData['sta_total'][stdName]}"
									pattern="" />
							<td><fmt:formatNumber
									value="${rData['sta_average'][stdName]}" pattern=".00" />
					</c:forEach>
			<tr>
				<th class="std-name">총합
				<th id="k-total"><fmt:formatNumber
						value="${rData['sta_lec_total']['korean']}" pattern="" />
				<th id="e-total"><fmt:formatNumber
						value="${rData['sta_lec_total']['english']}" pattern="" />
				<th id="m-total"><fmt:formatNumber
						value="${rData['sta_lec_total']['math']}" pattern="" />
				<th rowspan="2" colspan="2" class="table-info"><select
					class="form-control" id="rec-select">
						<option class="lec-select" value="d" selected>default</option>
						<option class="lec-select" value="k">korean</option>
						<option class="lec-select" value="e">english</option>
						<option class="lec-select" value="m">mathematics</option>
						<option class="lec-select" value="t">total</option>
				</select>
			<tr>
				<th class="std-name">평균
				<th id="k-avg"><fmt:formatNumber
						value="${rData['sta_lec_average']['korean']}" pattern=".00" />
				<th id="e-avg"><fmt:formatNumber
						value="${rData['sta_lec_average']['english']}" pattern=".00" />
				<th id="m-avg"><fmt:formatNumber
						value="${rData['sta_lec_average']['math']}" pattern=".00" />
		</table>
	</div>

	<%-- ${rData} --%>
</body>

<script type="text/javascript">
	$(function() {
		
		var param = "${param.srt}";
		var k_avg = $("#k-avg").text();
		var e_avg = $("#e-avg").text();
		var m_avg = $("#m-avg").text();

		$("#rec-select").val(param)
		if(param =="" || param == null){
		$("#rec-select").val("d");
		}
		
		
		$("td").each(function() {

			var rec = $(this).text();
			var flag = false;

			switch ($(this).attr("class")) {
			case "korean-rec":
				flag = rec > k_avg;
				break;
			case "english-rec":
				flag = rec > e_avg;
				break;
			case "math-rec":
				flag = rec > m_avg;
				break;
			default:
				return;
			}
			$(this).attr("class", "table-" + (flag ? "success" : "warning"));

		});
		$("td").click(function() {
			alert($(this).attr("class"))
			//alert($(this).text()>53)
		})
		$("#rec-select").change(function() {
			var goURL = $(location).attr("pathname")+"?srt="+$(this).val();
			$(location).attr("href", goURL);
		})
	});
</script>

</html>