<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@include file="../include/header.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Board Read</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page
				<button id = "regBtn" type="button" class="btn btn-xs pull-right">Register New Board</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group">
				<label>Bno</label>
				<input class = "form-control" name="bno" value='<c:out value="${board.bno}"></c:out>' readonly="readonly">
				</div>
				
				<div class="form-group">
				<label>Title</label>
				<input class = "form-control" name="title" value='<c:out value="${board.title}"></c:out>' readonly="readonly">
				</div>
				<div class="form-group">
				
				<label>TextArea</label>
				<textarea class = "form-control" name="bno" rows="3" readonly="readonly">
					<c:out value="${board.content}"></c:out>
				</textarea>
				</div>
				
				<div class="form-group">
				<label>Writer</label>
				<input class = "form-control" name="writer" value='<c:out value="${board.writer}"></c:out>' readonly="readonly">
				</div>
				
				<button data-oper="modify" class="btn btn-default">Modify</button>				
				<button data-oper="list" class="btn btn-info">List</button>				
				<form id = "operForm" action="/board/modify" method="get">
					<input type="hidden" id = "bno" name ="bno" value ='<c:out value="${board.bno}"></c:out>'>
					<input type="hidden" id = "pageNum" name ="pageNum" value ='<c:out value="${param.pageNum}"></c:out>'>
					<input type="hidden" id = "amount" name ="amount" value ='<c:out value="${param.amount}"></c:out>'>
					<input type="hidden" id = "type" name ="type" value ='<c:out value="${param.type}"></c:out>'>
					<input type="hidden" id = "keyword" name ="keyword" value ='<c:out value="${param.keyword}"></c:out>'>
				</form>
				
				<script type="text/javascript">
					$(document).ready(function () {
						var openForm = $("#operForm")
						$("button[data-oper='modify']").click(function(e) {
							openForm.attr("action", "/board/modify").submit();
						});					
						$("button[data-oper='list']").click(function(e) {
							openForm.find("#bno").remove();
							openForm.attr("action", "/board/list");
							openForm.submit();
						});					
					});
					
				
				</script>
				
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
</body>
</html>
<%@include file="../include/footer.jsp"%>    