<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html>
<html>

<style>
	.h3-a-sameline h3,
	.h3-a-sameline a {
		display: inline;
	}
	.h3-a-sameline {text-align: center;}
	
	.h3-a-sameline a{float: right;}
	
</style>

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
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading h3-a-sameline">
                        <h3 class="panel-title">Please Sign In</h3>
                    	<a href="/">main</a>
                    </div>
                    <div class="panel-body">
                        <form method="post" action="/login" role="form">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="ID" name="username" type="text" autofocus required="required">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="" required="required">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember-me" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <a href="index.html" class="btn btn-lg btn-success btn-block">Login</a>
                                <a href="/customRegister" class="btn btn-lg btn-default btn-block">Register</a>
                            </fieldset>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
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
