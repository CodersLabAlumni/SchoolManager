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
<%@ include file="../jspf/main_menu.jspf"%>

	<!--TODO Consider moving navbar into main_menu.jsp  -->
	</ul>
	<ul class="navbar-nav ml-auto">
		<sec:authorize access="isAuthenticated()">
			<li class="nav-item"><a class="btn btn-primary"
				href="${pageContext.request.contextPath}/logout">Logout</a></li>
		</sec:authorize>
	</ul>
	<ul class="navbar-nav mr-auto">
		<sec:authorize access="isAnonymous()">
			<li class="nav-item"><a class="btn btn-primary"
				href="${pageContext.request.contextPath}/login">Login</a></li>
			<li class="nav-item"><a class="btn btn-primary"
				href="${pageContext.request.contextPath}/register">Register</a></li>
		</sec:authorize>
	</ul>

	<div class="container">
		<h1>
			Welcome to School Manager <b><sec:authentication
					property="principal.username" /> </b>.
		</h1>
		<h2>
			Use top menu to navigate the website
			</h1>
			<h3>
				<a href="${pageContext.request.contextPath}/logout"
					class="btn btn-primary">Logout</a>
			</h3>
	</div>
	
	
<%@ include file="../jspf/footer.jspf"%>	
</body>
</html>