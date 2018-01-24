<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../jspf/includes.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../jspf/headconfig.jspf"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
</head>
<body>

	<%@ include file="../jspf/home_menu.jspf"%>

	<div class="jumbotron">

		<h1 align="center">
			Welcome to School Manager <b><sec:authentication
					property="principal.username" /> </b>.
		</h1>

		<h2 align="center">Use top menu to navigate the website</h2>




	</div>


	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>