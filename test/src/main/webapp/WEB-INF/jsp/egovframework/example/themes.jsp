<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%
try{
	
pageContext.setAttribute("theme", new String[]{
		"cerulean","cosmo","cyborg","darkly","flatly","journal","litera",
		"lumen","lux","materia","minty","pulse","sandstone","simplex",
		"sketchy","slate","solar","spacelab","superhero","united","yeti"
				}[1]);//21개 테마
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
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>	