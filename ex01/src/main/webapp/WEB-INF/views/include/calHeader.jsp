<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>  
<%
try{
	
pageContext.setAttribute("theme", new String[]{
		"cerulean","cosmo","cyborg","darkly","flatly","journal","litera",
		"lumen","lux","materia","minty","pulse","sandstone","simplex",
		"sketchy","slate","solar","spacelab","superhero","united","yeti"
				}[14]);//21개 테마
}catch(Exception e){
pageContext.setAttribute("theme", "journal");
	
}			
%>

<link rel="stylesheet"
	href="https://bootswatch.com/4/${theme}/bootstrap.css">
<link rel="stylesheet"
	href="https://bootswatch.com/4/${theme}/bootstrap.min.css">
<link rel="stylesheet"
	href="https://bootswatch.com/4/${theme}/_variables.scss">
<link rel="stylesheet"
	href="https://bootswatch.com/4/${theme}/_bootswatch.scss">
<sec:authorize access="!isAuthenticated()">	
<script type="text/javascript">
location.href = "/customLogin";
</script>
</sec:authorize>
<sec:authorize access="isAuthenticated()">	
<sec:authentication property="principal.username" var="userid"/>
<sec:authentication property="principal.member.userName" var="username"/>
<sec:authentication property="principal.member.authList[0].auth" var="userauth"/>
</sec:authorize>	
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>	
