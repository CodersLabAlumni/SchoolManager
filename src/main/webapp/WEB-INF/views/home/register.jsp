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
	<div class="jumbotron">
		
		<div id="login-box">

			<h2>Please provide login and password for new user</h2>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

			<form:form method="post" modelAttribute="user">
				<div class="form-group">
					Username:
					<form:input path="username" cssClass="form-control" />
					<form:errors path="username" />
				</div>
				<div class="form-group">
					Email:
					<form:input path="email" cssClass="form-control" />
					<form:errors path="email" />
				</div>

				<div class="form-group">
					Password:
					<form:password path="password" cssClass="form-control" />
					<form:errors path="password" />
				</div>
				<div class="form-group">
					Confirm password:
					<form:password path="confirmPassword" cssClass="form-control" />
					<form:errors path="confirmPassword" />
				</div>

				<input type="submit" class="btn btn-primary"
					value="Register new user">
			</form:form>
			<br />
			<h3>
				<c:out value="${msg}"></c:out>
			</h3>
		</div>
	</div>
	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>