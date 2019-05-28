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
			<div class="panel-heading">Board Modify Page
				<button id = "regBtn" type="button" class="btn btn-xs pull-right">Board Modify Page</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
			
			<form role="form" action="/board/modify" method="post">
			<%-- 	<%-- <input type="hidden" id = "pageNum" name ="pageNum" value ='<c:out value="${param.pageNum}"></c:out>'>
				<input type="hidden" id = "amount" name ="amount" value ='<c:out value="${param.amount}"></c:out>'>
				<input type="hidden" id = "type" name ="type" value ='<c:out value="${param.type}"></c:out>'>
				<input type="hidden" id = "keyword" name ="keyword" value ='<c:out value="${param.keyword}"></c:out>'> --%> --%>
			
				<div class="form-group">
				<label>Bno</label>
				<input class = "form-control" name="bno" value='<c:out value="${board.bno}"></c:out>'>
				</div>
				
				<div class="form-group">
				<label>Title</label>
				<input class = "form-control" name="title" value='<c:out value="${board.title}"></c:out>'>
				</div>
				<div class="form-group">
				
				<label>TextArea</label>
				<textarea class = "form-control" name="content" rows="3">
					<c:out value="${board.content}"></c:out>
				</textarea>
				</div>
				
				<div class="form-group">
				<label>Writer</label>
				<input class = "form-control" name="writer" value='<c:out value="${board.writer}"></c:out>' readonly="readonly">
				</div>
				
				<button type="submit" data-oper="modify" class="btn btn-default">Modify</button>				
				<button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>				
				<button type="submit" data-oper="list" class="btn btn-info">List</button>				
			</form>	
				
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>

	<script type="text/javascript">
		$(document).ready(function() {
			
			var formObj = $("form");
			
			$("button").click(function(e) {
				e.preventDefault();
				
				var operation = $(this).data("oper");
				
				console.log(operation);
				
				if(operation === 'remove'){
					formObj.attr("action", "/board/remove");
				}else if(operation === 'list'){
					formObj.attr("action", "/board/list").attr("method", "get");
					
					var pageNumTag = $("input[name='pageNum']").clone();
					var amountTag  = $("input[name='amount']").clone();
					var keywordTag = $("input[name='keyword']").clone();
					var typeTag    = $("input[name='type']").clone();
					
					formObj.empty();
				
					formObj.append(pageNumTag);
					formObj.append(amountTag);
					formObj.append(keywordTag);
					formObj.append(typeTag);
				}
				
				formObj.submit();
				
			})
			
			
		});
		
	</script>

</body>
</html>
<%@include file="../include/footer.jsp"%>    