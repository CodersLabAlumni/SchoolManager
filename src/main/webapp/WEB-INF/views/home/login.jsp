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

 
			<ul class="navbar-nav mr-auto">
				<sec:authorize access="isAnonymous()">
					<li class="nav-item"><a class="btn btn-primary"
						href="${pageContext.request.contextPath}/login">Login</a></li>
					<li class="nav-item"><a class="btn btn-primary"
						href="${pageContext.request.contextPath}/register">Register</a></li>
				</sec:authorize>
			</ul>


	<div class="container">
		<h3>Login to School Manager:</h3>
		<form:form method="post">
			<p>
				<label for="username">Username</label> <input type="text"
					id="username" name="username" />
			</p>
			<p>
				<label for="password">Password</label> <input type="password"
					id="password" name="password" />
			</p>
			<input type="hidden" 6
		name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			<button type="submit" class="btn">Log in</button>
		</form:form>
		<br />
	</div>

	</div>
<%@ include file="../jspf/footer.jspf"%>
</body>
</html>