<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %> 
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
		<thead>
			<tr>
				<th>이름
				<th>국어
				<th>영어
				<th>수학
				<th>총합
				<th>평균 
		</thead>
		
		<tbody>
		<c:forEach items="${csvRecord}" var="rec" varStatus="std_status">
		<tr class = "rows row-${std_status.count}">
			<th class = "std-name data-row" id = "${std_status.count}-name">${rec.get('name')} 
			<c:forEach items = "${subject}" var = "sbj">
				<td class="std-${std_status.count} sbj-${sbj} data-row" id = "${std_status.count}-${sbj}"> ${rec.get(sbj)}<label id = "${sbj}-${std_status.count}-ck" class="table"></label>
			</c:forEach>
				<td class="std-${std_status.count}-total data-row" id = "${std_status.count}-total"><label></label>
				<td class="std-${std_status.count}-avg data-row" id = "${std_status.count}-avg">
		</c:forEach>
		</tbody>				
		<tfoot>
			<tr>
				<th>총합
				<c:forEach items = "${subject}" var = "sbj">
				<td class="${sbj}-total" id = "${sbj}-total">
				</c:forEach>
				<td>
				<td>
			<tr>
				<th>평균
				<c:forEach items = "${subject}" var = "sbj">
				<td class="${sbj}-avg" id = "${sbj}-avg">
				</c:forEach>
				<td>
				<td>
		</tfoot>
		
		</table>
	</div>

</body>

<script type="text/javascript">
	$(function() {
			
		
		
		
		var subj = ["korean" , "english" , "math"];
		var printHeader = ["name","korean" , "english" , "math","total","avg"]
		var subjMAX = [0,0,0];
		var subjMIN = [1000,1000,1000];
		var recData = new Array(10);
		var basicData = new Array(10);
		var stdCnt = "${fn:length(csvRecord)}";
		
		setSta();
		setColor();
		
		function setSta(){//초기 Setting
			//alert(recData.length)
			//alert(recData[0].length)
		var totalTmp = 0;
		var stmp = [0,0,0];
		var sum = 0;
		for (var i = 1; i <= stdCnt; i++) {
			var tmpArr = new Array(6);
			tmpArr[0] = $("#"+i+"-name").text().replace(/[^가-힇]/g,'');
			for (var j = 0; j < subj.length; j++) {
				var temp = parseInt($("#"+i+"-"+subj[j]).text());
				totalTmp+= temp;
				stmp[j] += temp; 
				if(subjMAX[j]<temp)  subjMAX[j] = temp; 
				if(subjMIN[j]>temp)  subjMIN[j] = temp; 
				tmpArr[j+1] = temp;
			}
			var prAvg = (parseFloat(totalTmp)/subj.length).toFixed(2);
			tmpArr[4] = totalTmp;
			tmpArr[5] = prAvg;
			recData[i] = tmpArr;
			$("#"+i+"-total").text(totalTmp);
			$("#"+i+"-avg").text(prAvg);
			
		var totalTmp = 0;
		 }
		
		for (var i = 0; i < subj.length; i++) {
			$("#"+subj[i]+"-total").text(stmp[i]);
			$("#"+subj[i]+"-avg").text(parseFloat(stmp[i])/stdCnt);
		}
		//basicData = recData;
	
		}
		function sortRec(code) {
			if(code ==-1){
				recData = basicData;
			}else{
				
			recData.sort(function(a, b) {
				return b[code] - a[code];
			})
			}
			alert(recData)
		}
		sortTable(3);
		function sortTable(code){
			sortRec(code);
			alert()
			for (var i = 0; i < stdCnt; i++) {
				var pRecs = recData[i];
				for (var j = 0; j < printHeader.length; j++) {
				var td = $("#"+(i+1)+"-"+printHeader[j]);	
				td.html(pRecs[j])
					if(pRecs[j] == subjMAX[i]){
						td.append("<label class = 'btn-primary'> + </label>")
					}
				}
				//alert(pRecs)
				
			 }
					setColor();
			
		}
		
		
		function setColor(){
			for (var i = 0; i < subj.length; i++) {
				var stemp = subj[i];
				for (var j = 1; j <= stdCnt; j++) {
					var thisRec = parseInt($("#"+j+"-"+stemp).text());
					//alert($("#"+stemp+"-total").text())
					var thisAvg = $("#"+stemp+"-avg").text().replace(/[^0-9.]/g,'');
					var tblClass = "table-"+(thisRec>thisAvg?"success":"warning");
					$("#"+j+"-"+stemp).prop("class", tblClass);
					if(thisRec == subjMAX[i] ){
						$("#"+stemp+"-"+j+"-ck").text(" + ")
						$("#"+stemp+"-"+j+"-ck").prop("class", "table-info")
					}else if(thisRec == subjMIN[i] ){
						$("#"+stemp+"-"+j+"-ck").text(" - ")
						$("#"+stemp+"-"+j+"-ck").prop("class", "table-danger")
					} 
					
				}
			}
			
		
			
		}
		
		$("tr").click(function() {
			var arr = $(this).text().replace(/[^.0-9가-힇]/g,' ').split(' ');
			
			alert(arr);
			arr.forEach(function(msg) {
				//alert(msg)
			})
		})
		
	});
</script>

</html>