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
	<div class="container">

		<div id="login-box">

			<h2>Please provide login and password for new user</h2>

			<c:if test="${not empty error}">
				<div class="error">${error}</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="msg">${msg}</div>
			</c:if>

		<form:form method="post" modelAttribute="user">
		<div class="form-group">Username: <form:input path="username" cssClass="form-control"/>
		<form:errors path="username" /></div>
		<div class="form-group">Email: <form:input path="email" cssClass="form-control"/>
		<form:errors path="email" /></div>
		
		<div class="form-group">Password: <form:password path="password" cssClass="form-control"/>
		<form:errors path="password" /></div>
		<input type="submit" class="btn btn-primary" value="Register new user">
		</form:form><br /> 
		<h3><c:out value="${msg}"></c:out></h3>
		</div>
		<div>
			<h4>New accounts need to be activated by administrator, please use contact details
			 below to notify administrator that you created new account. Include your
			  location in the email. Please be aware it may take up to 48
			  hours to activate new account (normally less than 24 hours).</h4>
		</div>
	</div>
<%@ include file="../jspf/footer.jspf"%>
</body>
</html>