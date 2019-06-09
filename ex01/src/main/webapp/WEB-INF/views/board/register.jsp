<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form role="form" action="/board/register" method="post" accept-charset="utf-8" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Board Register</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		<div class="form-group">
			<label>title</label> <input type="text" class="form-control"
				name="title" id="title" placeholder="title" required="required">
		</div>
		<div class="form-group">
			<label>writer</label> <input type="text" class="form-control"
				name="writer" id="writer" placeholder="writer" value='<sec:authentication property="principal.username"/>' 
				readonly="readonly">
		</div>


		<div class="form-group"></div>
		<div class="form-group">
			<label for="exampleTextarea">textarea</label>
			<textarea class="form-control" id="content" name="content" rows="10"
				cols="10" required="required"></textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputFile">File input</label> <input type="file"
				class="form-control-file" id="exampleInputFile"
				aria-describedby="fileHelp">
		</div>

    <button type="submit" class="btn btn-default">Submit</button> 
    <button type="reset" class="btn btn-default">Reset Button</button> 


	</form>
	<!--   <fieldset>
    <div class="form-group">
      <label>name</label>
      <input type="text" class="form-control" name="writer" id="writer" placeholder="writer" required="required">
    </div>
    <div class="form-group">
      <label>title</label>
      <input type="text" class="form-control" name="title" id="title" placeholder="title" required="required">
    </div>
  
   
    <div class="form-group">
     
    </div>
    <div class="form-group">
      <label for="exampleTextarea">textarea</label>
      <textarea class="form-control" id="content" name="content" rows="10" cols="10" required="required"></textarea>
    </div>
    <div class="form-group">
      <label for="exampleInputFile">File input</label>
      <input type="file" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp">
    </div>
   
 
      
    
    </fieldset>
    -->
</body>
</html>