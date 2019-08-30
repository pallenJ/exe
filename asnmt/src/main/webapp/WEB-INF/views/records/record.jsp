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
				<th>평균
				
				
				 <c:forEach items="${stdList}" var="stdName">
						<tr>
							<th>${stdName}
							
							<c:forEach items="${lecList}" var = "l_key">
							
							<td class="${l_key}-rec">
							<fmt:formatNumber value="${rData[l_key][stdName]}" pattern="" />
							
							<c:if test="${rData['rc_MAX'][l_key] eq rData[l_key][stdName]}"><label class="btn-primary btn-sm"> + </label></c:if>
							<c:if test="${rData['rc_MIN'][l_key] eq rData[l_key][stdName]}"><label class="btn-danger btn-sm"> - </label></c:if>
				<%-- 			
							<fmt:formatNumber value="${rData['rc_MAX'][l_key]}" pattern="" />,
							<fmt:formatNumber value="${rData['rc_MIN'][l_key]}" pattern="" /> --%>
							
							</c:forEach>
							
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
				<th rowspan="2" colspan="2" class="table-info">
				<div class = "form-inline">
				<select
					class="form-control" id="rec-select">
						<option class="lec-select" value="d" selected>default</option>
						<option class="lec-select" value="k">korean</option>
						<option class="lec-select" value="e">english</option>
						<option class="lec-select" value="m">mathematics</option>
						<option class="lec-select" value="t">total</option>
				</select>
				
				<button type="button" class="btn btn-outline-primary" id = "sort-btn">go</button>
				</div>
				<div class="custom-control custom-checkbox">
				<input type="checkbox" id ="asc-check" name="order" value="ASC" class="custom-control-input"> 
				<label id = "asc-div" class="custom-control-label">오름차순으로</label>
				</div>
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
		
		if("${param.order}"=="ASC"){
		$("#asc-check").prop("checked", true)	
		}
/* 		$("#asc-check").change(function() {
		alert($(this).prop("checked"));	
		$(this).prop("checked", false)	
		alert($(this).prop("checked"));	
		})
		 */
		
		
		$("td").each(function() {

			var rec = $(this).text();
			rec = rec.replace(/[^0-9]/g,'');
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
		
		//$("#asc-check").prop("checked", false)
		
		$("td").click(function() {
			/* alert($(this).attr("class"));
			alert($(this).text()); */
		})
		$("#asc-div").click(function() {
			$("#asc-check").prop("checked", !$("#asc-check").prop("checked"));
		})
		$("#sort-btn").click(function() {
			//alert("${param.srt}"=="d")
			var order = "&order=" + ($("#asc-check").prop("checked")?"ASC":"DESC")
			var goURL = $(location).attr("pathname")+"?srt="+$("#rec-select").val()+order;
			$(location).attr("href", goURL);
		})
		
		
		
	});
</script>

</html>