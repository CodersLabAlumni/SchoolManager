<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../jspf/includes.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../jspf/headconfig.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../jspf/home_menu.jspf"%>

	<div class="jumbotron">
	
		<h2>${messageToView}</h2>
	
	<a class="btn btn-primary" href="${pageContext.request.contextPath}/">Home page</a>
	
	</div>
		
		


	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>