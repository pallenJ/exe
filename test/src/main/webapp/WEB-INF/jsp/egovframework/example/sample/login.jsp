<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../themes.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>	
<title>login</title>
</head>
<body>
<br>
<br>
<br>
<br>
<div class="container center-block" style="max-width: 30rem; margin-top: auto;">
<div class="form-group">
    <label for="id">아이디</label>
    <input type="text" id="id" value='' placeholder="아이디를 입력하세요" class="form-control" />
</div>

 <div class="form-group">
    <label for="password">비밀번호</label>
    <input type="password" id="pw" value='' placeholder="비밀번호를 입력하세요" class="form-control" />
</div>
<button type="button" class="btn btn-primary btn-lg btn-block" id ="login-btn">로그인</button>

</div>
<form id = "submit" method = "post">
<input type="hidden" name = "id" id = "id_param">
<input type="hidden" name = "pw" id = "pw_param">
</form>

</body>

<script type="text/javascript">
	
	$(function() {
		$("#login-btn").click(function() {
			var id = $("#id").val();
			var pw = $("#pw").val();
			
			$("#id_param").val(id);
			$("#pw_param").val(pw);
			
			$("#submit").attr("action", "login/"+id+".do");
			
			$("#submit").submit();
		})
	})

</script>

</html>