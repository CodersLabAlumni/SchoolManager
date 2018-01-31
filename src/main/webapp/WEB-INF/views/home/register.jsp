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
	<div class="container">

		<div id="login-box">

			<h2>Registration form</h2>

			<form:form method="post" modelAttribute="user">
				<div class="form-group">
					Username:
					<form:input path="username" cssClass="form-control" />
					<form:errors class="text-danger" path="username" />
				</div>
				<div class="form-group">
					Email:
					<form:input path="email" cssClass="form-control" />
					<form:errors class="text-danger" path="email" />
				</div>

				<div class="form-group">
					First name:
					<form:input path="firstName" cssClass="form-control" />
					<form:errors class="text-danger" path="firstName" />
				</div>
				<div class="form-group">
					Last name:
					<form:input path="lastName" cssClass="form-control" />
					<form:errors class="text-danger" path="lastName" />
				</div>

				<div class="form-group">
					Password:
					<form:password path="password" cssClass="form-control" />
					<form:errors class="text-danger" path="password" />
				</div>

				<div class="form-group">
					Confirm password:
					<form:password path="confirmPassword" cssClass="form-control" />
				</div>

				<input type="submit" class="btn btn-primary"
					value="Register new user">

			</form:form>
		</div>
	</div>
	<%@ include file="../jspf/footer.jspf"%>
</body>
</html>