<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Start Bootstrap - SB Admin Version 2.0 Demo</title>

       <!-- Core CSS - Include with every page -->
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <!-- Page-Level Plugin CSS - Tables -->
    <link href="/resources/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <!-- SB Admin CSS - Include with every page -->
    <link href="/resources/css/sb-admin.css" rel="stylesheet">

</head>

<body>
<div class="container">
    <form>
  <fieldset>
    <legend>Register</legend>
    <%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
    <div class="form-group row">
      <br>
    </div>
    <div class="form-group">
      <label for="">id</label>
      <input type="text" class="form-control" placeholder="Enter id" name="userid">
      <a class="btn btn-success">중복검사</a>
      <small id="emailHelp" class="form-text text-muted">중복검사해주세요</small>
    </div>
    
    <div class="form-group">
      <label for="">name</label>
      <input type="text" class="form-control" placeholder="Enter id" name="username">
      <small id="emailHelp" class="form-text text-muted">중복검사해주세요</small>
    </div>
    
    <div class="form-group">
      <label for="">Password</label>
      <input type="password" class="form-control" placeholder="Password" name="userpassword">
    </div>
    
    <div class="form-group">
      <label for="">Password Check</label>
      <input type="password" class="form-control" placeholder="Password" name="userpasswordCheck">
    </div>
    
    <fieldset class="form-group">
      <legend>check your role</legend>
      <div class="form-check">
        <label class="form-check-label">
          <input type="radio" class="form-check-input" name="auth" id="optionsRadios1" value="ROLE_USER" checked="checked">
          normal user
        </label>
      </div>
      <div class="form-check">
      <label class="form-check-label">
          <input type="radio" class="form-check-input" name="auth" id="optionsRadios2" value="ROLE_MANAGER">
          manager
        </label>
      </div>
      <div class="form-check disabled">
      <label class="form-check-label">
          <input type="radio" class="form-check-input" name="auth" id="optionsRadios3" value="ROLE_ADMIN" disabled>
          administrator
        </label>
      </div>
    </fieldset>
    <!-- <fieldset class="form-group">
      <legend>Checkboxes</legend>
      <div class="form-check">
        <label class="form-check-label">
          <input class="form-check-input" type="checkbox" value="" checked="">
          Option one is this and that—be sure to include why it's great
        </label>
      </div>
      <div class="form-check disabled">
        <label class="form-check-label">
          <input class="form-check-input" type="checkbox" value="" disabled="">
          Option two is disabled
        </label>
      </div>
    </fieldset> -->
    <button type="submit" class="btn btn-primary">Submit</button>
  </fieldset>
</form>
</div>

    <!-- Core Scripts - Include with every page -->
    <script src="/resources/js/jquery-1.10.2.js"></script>
    <script src="/resources/js/js/bootstrap.min.js"></script>
    <script src="/resources/js/js/plugins/metisMenu/jquery.metisMenu.js"></script>

    <!-- SB Admin Scripts - Include with every page -->
    <script src="/resources/js/js/sb-admin.js"></script>
	<script>
				
				$(".btn-success").click(function(e) {
					e.preventDefault();
					$("form").submit();
				});
				
	
	</script>
</body>

</html>
